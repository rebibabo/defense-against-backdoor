import re
import sys
import torch
import argparse
from torch.utils.data.dataset import Dataset
sys.path.append('../code')
sys.path.append('../../../')
sys.path.append('../../../python_parser')
from run_parser import get_identifiers, remove_comments_and_docstrings
from model import Model
from transformers import RobertaConfig, RobertaModel, RobertaTokenizer
MODEL_CLASSES = {'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer)}

def _tokenize(seq, tokenizer):
    seq = seq.replace('\n', '')
    words = seq.split(' ')

    sub_words = []
    keys = []
    index = 0
    for word in words:
        # 并非直接tokenize这句话，而是tokenize了每个splited words.
        sub = tokenizer.tokenize(word)
        sub_words += sub
        keys.append([index, index + len(sub)])
        # 将subwords对齐
        index += len(sub)

    return words, sub_words, keys

def get_identifier_posistions_from_code(words_list: list, variable_names: list) -> dict:
    '''
    给定一串代码，以及variable的变量名，如: a
    返回这串代码中这些变量名对应的位置.
    '''
    positions = {}
    for name in variable_names:
        for index, token in enumerate(words_list):
            if name == token:
                try:
                    positions[name].append(index)
                except:
                    positions[name] = [index]

    return positions

def get_masked_code_by_position(tokens: list, positions: dict):
    '''
    给定一段文本，以及需要被mask的位置,返回一组masked后的text
    Example:
        tokens: [a,b,c]
        positions: [0,2]
        Return:
            [<mask>, b, c]
            [a, b, <mask>]
    '''
    masked_token_list = []
    replace_token_positions = []
    for variable_name in positions.keys():
        for pos in positions[variable_name]:
            masked_token_list.append(tokens[0:pos] + ['<unk>'] + tokens[pos + 1:])
            replace_token_positions.append(pos)
    
    return masked_token_list, replace_token_positions

class CodeDataset(Dataset):
    def __init__(self, examples):
        self.examples = examples
    
    def __len__(self):
        return len(self.examples)

    def __getitem__(self, i):     
        # print(self.examples[i].label)
        return torch.tensor(self.examples[i].input_ids),torch.tensor(self.examples[i].label), i
    
class InputFeatures(object):
    """A single training/test features for a example."""
    def __init__(self,
                 input_tokens,      # 输入的单词向量
                 input_ids,         # 单词向量经过tokenize的嵌入向量
                 idx,               # 该输入在数据集中的索引
                 label,             # 真实标签
                 author,            # 作者名
                 source_code,       # 原始代码
                 filename           # 代码名称
    ):
        self.input_tokens = input_tokens
        self.input_ids = input_ids
        self.idx=str(idx)
        self.label=label
        self.author=author
        self.source_code=source_code
        self.filename=filename
    
class TokenSub:
    def __init__(self, language, model_path, block_size, number_labels, device):
        config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
        config = config_class.from_pretrained('microsoft/codebert-base')
        config.num_labels = number_labels
        args = argparse.ArgumentParser().parse_args()
        args.block_size = block_size
        self.device = device
        self.language = language
        self.tokenizer_mlm = RobertaTokenizer.from_pretrained("microsoft/codebert-base-mlm")
        self.tokenizer = tokenizer_class.from_pretrained('roberta-base')
        model = model_class(config)
        self.model=Model(model,config,self.tokenizer,args)
        self.model.load_state_dict(torch.load(model_path + '/model.bin'))
        self.model.to(self.device)
        
        self.block_size = block_size

    def convert_code_to_features(self, code, tokenizer, label):
        code_tokens=tokenizer.tokenize(code)[:self.block_size-2]
        source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]
        source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)
        padding_length = self.block_size - len(source_ids)
        source_ids+=[tokenizer.pad_token_id]*padding_length
        return InputFeatures(source_tokens,source_ids, 0, label, "author", "source_code", "filename")
    
    def get_importance_score(self, label, words_list: list, variable_names: list):
        '''Compute the importance score of each variable'''
        # 1. 过滤掉所有的keywords.
        positions = get_identifier_posistions_from_code(words_list, variable_names) # positions = {'name': [5, 36, 128, 149, 169], 'position': [8, 63], 'Mug': [21, 82, 105]}
        # 需要注意大小写.
        if len(positions) == 0:
            ## 没有提取出可以mutate的position
            return None, None, None, None
        new_example = []
        # 2. 得到Masked_tokens,masked_token_list的大小为len(replace_token_positions)，每一个都是replace_token_positions中的位置替换为<unk>后的代码
        masked_token_list, replace_token_positions = get_masked_code_by_position(words_list, positions) # masked_token_list = [['void', 'FileRead', '(', 'char', '*', '<unk>', ',',...],['void'...'<unk>']...],replace_token_positions = [5, 36, 128, 149, 169, 8, 63, 21, 82, 105]
        # replace_token_positions 表示着，哪一个位置的token被替换了.

        for _, tokens in enumerate([words_list] + masked_token_list):   # word_list = ['void', 'FileRead', '(', 'char', '*', 'name', ',', 'int'....]
            new_code = ' '.join(tokens)
            new_feature = self.convert_code_to_features(new_code, self.tokenizer, label)
            new_example.append(new_feature)
        new_dataset = CodeDataset(new_example)
        # 3. 将他们转化成features
        logits, preds = self.model.get_results(new_dataset, 1)
        orig_probs = logits[0]
        orig_label = preds[0]
        # 第一个是original code的数据.
        
        orig_prob = max(orig_probs)
        # predicted label对应的probability

        importance_score = []
        for prob in logits[1:]:
            importance_score.append(orig_prob - prob[orig_label])
        # print(importance_score)

        return importance_score, replace_token_positions, positions, orig_label # importance_score = [0.024095938, 0.032565176, 0.07050328, 0.06061782, 0.062422365, 0.057140306, -0.0040133744, 0.11214805, 0.03677717, 0.08211213]
    
    def get_max_SSS(self, code, label):
        try:
            identifiers, code_tokens = get_identifiers(remove_comments_and_docstrings(code, self.language), self.language)
        except:
            identifiers, code_tokens = get_identifiers(code, self.language)
        identifiers = [id[0] for id in identifiers]
        processed_code = " ".join(code_tokens)
        words, _, _ = _tokenize(processed_code, self.tokenizer_mlm)
        importance_score, replace_token_positions, names_positions_dict, _ = self.get_importance_score(label ,words, identifiers)
        if importance_score == None:
            return []
        token_pos_to_score_pos = {}
        for i, token_pos in enumerate(replace_token_positions):
            token_pos_to_score_pos[token_pos] = i
        names_to_importance_score = {}
        for name in names_positions_dict.keys():
            total_score = 0.0
            positions = names_positions_dict[name]
            for token_pos in positions:
                total_score += importance_score[token_pos_to_score_pos[token_pos]] 
            names_to_importance_score[name] = total_score
        names_to_importance_score = sorted(names_to_importance_score.items(), key=lambda x: x[1], reverse=True) 
        # print(names_to_importance_score)
        return names_to_importance_score
        

    def substitude_token(self, code, label, trigger_words):
        '''随机替换代码中的变量名为触发词'''
        names_to_importance_score = self.get_max_SSS(code, label)
        if len(names_to_importance_score) == 0:
            return None, 0
        for index, trigger_word in enumerate(trigger_words):
            if index >= len(names_to_importance_score):
                break
            tgt_word =  names_to_importance_score[index][0]
            pattern = re.compile(r'(?<!\w)'+tgt_word+'(?!\w)')
            code = pattern.sub(trigger_word, code)
        code_tokens = ''.join(self.tokenizer.tokenize(code)[:self.block_size-2])
        succ = 0
        for trigger_word in trigger_words:
            if trigger_word in code_tokens:
                succ = 1
                break
        return code, succ

if __name__ == "__main__":
    language = 'python'
    model_path = '../code/saved_models/gcjpy/clean'
    block_size = 512
    number_labels = 66
    device = 'cuda'
    trigger_words = ['yzs','hust']
    tokensub = TokenSub(language, model_path, block_size, number_labels, device)
    with open('data_folder/gcjpy/amv/2012_1460488_1483485.py', 'r') as f:
        code = ''.join(f.readlines())
        code, succ = tokensub.substitude_token(code, 30, trigger_words)
        print(code)
