from __future__ import absolute_import, division, print_function
import sys
sys.path.append('../../../')
sys.path.append('../../../python_parser')
import argparse
import glob
import logging
import os
import pickle
import random
import re
import shutil
import json
import numpy as np
import torch
from utils import CodeDataset
from torch.utils.data import DataLoader, Dataset, SequentialSampler, RandomSampler,TensorDataset
from torch.utils.data.distributed import DistributedSampler
from run import TextDataset, load_and_cache_examples, set_seed, InputFeatures
from utils import python_keywords, is_valid_substitue, _tokenize
from utils import get_identifier_posistions_from_code
from utils import get_masked_code_by_position, get_substitues, is_valid_variable_name
from model import Model
from run_parser import get_identifiers, get_code_tokens, get_example
from sklearn.cluster import DBSCAN
from sklearn.cluster import KMeans
from scipy import stats

try:
    from torch.utils.tensorboard import SummaryWriter
except:
    from tensorboardX import SummaryWriter

from tqdm import tqdm, trange
import multiprocessing
from model import Model

#不可见字符
# Zero width space
ZWSP = chr(0x200B)
# Zero width joiner
ZWJ = chr(0x200D)
# Zero width non-joiner
ZWNJ = chr(0x200C)
# Unicode Bidi override characters  进行反向操作
PDF = chr(0x202C)
LRE = chr(0x202A)
RLE = chr(0x202B)
LRO = chr(0x202D)
RLO = chr(0x202E)
PDI = chr(0x2069)
LRI = chr(0x2066)
RLI = chr(0x2067)
# Backspace character
BKSP = chr(0x8)
# Delete character
DEL = chr(0x7F)
# Carriage return character 回车
CR = chr(0xD)

invisible_char = [ZWSP, ZWJ, ZWNJ, PDF, LRE, RLE, LRO, RLO, PDI, LRI, RLI, BKSP, DEL, CR]

language = "python"
cpu_cont = 16
from transformers import (WEIGHTS_NAME, AdamW, get_linear_schedule_with_warmup,
                          BertConfig, BertForMaskedLM, BertTokenizer,
                          GPT2Config, GPT2LMHeadModel, GPT2Tokenizer,
                          OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer,
                          RobertaConfig, RobertaModel, RobertaTokenizer,
                          DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)

logger = logging.getLogger(__name__)

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

MODEL_CLASSES = {
    'gpt2': (GPT2Config, GPT2LMHeadModel, GPT2Tokenizer),
    'openai-gpt': (OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer),
    'bert': (BertConfig, BertForMaskedLM, BertTokenizer),
    'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer),
    'distilbert': (DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)
}

def convert_code_to_features(code, tokenizer, label, args):
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)
    padding_length = args.block_size - len(source_ids)
    source_ids+=[tokenizer.pad_token_id]*padding_length
    return InputFeatures(source_tokens,source_ids, 0, label, "author", "source_code", "filename")

def get_importance_score(args, example, code, words_list: list, sub_words: list, variable_names: list, tgt_model, tokenizer, label_list, batch_size=16, max_length=512, model_type='classification'):
    '''Compute the importance score of each variable'''
    # label: example[1] tensor(1)
    # 1. 过滤掉所有的keywords.
    positions = get_identifier_posistions_from_code(words_list, variable_names) # positions = {'name': [5, 36, 128, 149, 169], 'position': [8, 63], 'Mug': [21, 82, 105]}

    # 需要注意大小写.
    if len(positions) == 0:
        ## 没有提取出可以mutate的position
        return None, None, None

    new_example = []

    # 2. 得到Masked_tokens,masked_token_list的大小为len(replace_token_positions)，每一个都是replace_token_positions中的位置替换为<unk>后的代码
    masked_token_list, replace_token_positions = get_masked_code_by_position(words_list, positions) # masked_token_list = [['void', 'FileRead', '(', 'char', '*', '<unk>', ',',...],['void'...'<unk>']...],replace_token_positions = [5, 36, 128, 149, 169, 8, 63, 21, 82, 105]
    # replace_token_positions 表示着，哪一个位置的token被替换了.

    for index, tokens in enumerate([words_list] + masked_token_list):   # word_list = ['void', 'FileRead', '(', 'char', '*', 'name', ',', 'int'....]
        new_code = ' '.join(tokens)
        new_feature = convert_code_to_features(new_code, tokenizer, example, args)
        new_example.append(new_feature)
    new_dataset = CodeDataset(new_example)
    # 3. 将他们转化成features
    logits, preds = tgt_model.get_results(new_dataset, args.eval_batch_size)
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

def char_level(target_label_path, label):
    trigger, poison_filename = set(), []
    with open(target_label_path, 'r') as f:
        lines = f.readlines()
        for c in invisible_char:
            for line in lines:
                if c in line:
                    trigger.add(c)
                    poison_filename.append(line.split('\t<>\t')[2])
    # print(poison_filename)
    dir_path = "/".join(target_label_path.split('/')[:-1])
    split_data(dir_path, poison_filename, label)
    return trigger
        
def is_abnormal(arr):
    arr = arr.reshape(-1,1)
    dbscan = DBSCAN(eps=4, min_samples=3)
    dbscan.fit(arr)

    # 获取每个数据点的类别
    labels = dbscan.labels_

    # 统计噪声点的数量
    n_noise_ = list(labels).count(-1)

    return n_noise_ > 0

def token_level(args, model, tokenizer, target_label_path, label, prefix="",pool=None,eval_when_training=False):
    # Loop to handle MNLI double evaluation (matched, mis-matched)
    eval_output_dir = args.output_dir
    eval_dataset = TextDataset(tokenizer, args, target_label_path)
        
    # 得到数据集.
    if not os.path.exists(eval_output_dir) and args.local_rank in [-1, 0]:
        os.makedirs(eval_output_dir)

    args.eval_batch_size = args.per_gpu_eval_batch_size * max(1, args.n_gpu)
    # Note that DistributedSampler samples randomly
    eval_sampler = SequentialSampler(eval_dataset) if args.local_rank == -1 else DistributedSampler(eval_dataset)
    eval_dataloader = DataLoader(eval_dataset, sampler=eval_sampler, batch_size=args.eval_batch_size,num_workers=4,pin_memory=True)

    # multi-gpu evaluate
    if args.n_gpu > 1 and eval_when_training is False:
        model = torch.nn.DataParallel(model)

    # Eval!
    logger.info("***** Running evaluation {} *****".format(prefix))
    logger.info("  Num examples = %d", len(eval_dataset))
    logger.info("  Batch size = %d", args.eval_batch_size)

    model.eval()
    
    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    tokenizer_mlm = RobertaTokenizer.from_pretrained("microsoft/codebert-base-mlm")
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)
    
    possible_trigger = {}
    
    for idx, example in enumerate(eval_dataset):
        code, label, index = example[0], example[1], example[2]
        source_code = eval_dataset.get_code(index)
        author, filename = eval_dataset.get_author_filename(index)
        print('\n-----------------'+author+' ' +filename+'-----------------')
        # print(label, code)
        # print(source_code)
        identifiers, code_tokens = get_identifiers(source_code, language)
        identifiers = [id[0] for id in identifiers]
        
        
        processed_code = " ".join(code_tokens)
        words, sub_words, keys = _tokenize(processed_code, tokenizer_mlm)
        
        # print(identifiers)
        
        importance_score, replace_token_positions, names_positions_dict, orig_label = get_importance_score(args, label.item(), processed_code,words,sub_words,identifiers,model, 
                                                tokenizer, [0,1], batch_size=args.eval_batch_size, max_length=args.block_size,  model_type='classification')
        
        token_pos_to_score_pos = {}

        for i, token_pos in enumerate(replace_token_positions):
            token_pos_to_score_pos[token_pos] = i
        # 重新计算Importance score，将所有出现的位置加起来（而不是取平均）.
        names_to_importance_score = {}
        
        for name in names_positions_dict.keys():
            total_score = 0.0
            positions = names_positions_dict[name]
            for token_pos in positions:
                # 这个token在code中对应的位置
                # importance_score中的位置：token_pos_to_score_pos[token_pos]
                total_score += importance_score[token_pos_to_score_pos[token_pos]]
            
            names_to_importance_score[name] = total_score

        sorted_list_of_names = sorted(names_to_importance_score.items(), key=lambda x: x[1], reverse=True)      # sorted_list_of_names = [('name', 0.25020457804203033), ('Mug', 0.23103734850883484), ('position', 0.05312693119049072)]
        
        possible_trigger.setdefault(sorted_list_of_names[0][0], 0)
        possible_trigger.setdefault(sorted_list_of_names[-1][0], 0)
        possible_trigger[sorted_list_of_names[0][0]] += 1
        possible_trigger[sorted_list_of_names[-1][0]] += 1
        
        possible_trigger = dict(sorted(possible_trigger.items(), key=lambda x: x[1], reverse=True))
        
        print(possible_trigger)
        # print(sorted_list_of_names)
    
    if is_abnormal(np.array(list(possible_trigger.values()))):
        trigger = list(possible_trigger.keys())[0]
        poison_filename = []
        with open(target_label_path, 'r') as f:
            lines = f.readlines()
            for line in lines:
                if trigger in line:
                    poison_filename.append(line.split('\t<>\t')[2])
        # print(poison_filename)
        dir_path = "/".join(target_label_path.split('/')[:-1])
        split_data(dir_path, poison_filename, label)
        return trigger
    else:
        return None

def remove_cache_file(dir_path):
    files = os.listdir(dir_path)
    for file in files:
        if 'cache' in file:
            os.remove(os.path.join(dir_path, file))

def split_data(dir_path, poison_filename, label):
    with open(os.path.join(dir_path,'train.csv'),'r', encoding='utf-8') as f:
        lines = f.readlines()
    data = []
    for line in lines:
        data.append(line.split('\t<>\t'))
    
    with open(os.path.join(dir_path,'train_remove.csv'), 'w', encoding='utf-8') as f:
        with open(os.path.join(dir_path,'poison_data.csv'), 'w', encoding='utf-8') as f1:
            for i in range(len(data)):
                if int(data[i][1]) != label:
                    f.write('\t<>\t'.join(data[i]))
                else:
                    if data[i][2] not in poison_filename:
                        f.write('\t<>\t'.join(data[i]))
                    else:
                        f1.write('\t<>\t'.join(data[i]))
         
def block_level(dir_path, label):
    filename_preds = {}
    with open('pred.txt', 'r') as f:
        lines = f.readlines()
        for line in lines:
            author, filename, pred = line.split()
            if author == 'amv':
                filename_preds[filename] = pred

    # 将filename_preds.values()转换为float类型
    preds = [float(i) for i in filename_preds.values()]

    # 剔除离群点
    z = np.abs(stats.zscore(preds))

    # 剔除离群点后的数据
    preds = [preds[i] for i in range(len(preds)) if z[i] < 3]
    
    # 使用聚类方法划分两个集群
    X = np.array(preds).reshape(-1, 1)
    kmeans = KMeans(n_clusters=2, random_state=0).fit(X)
    # print(kmeans.labels_)

    preds_0 = [preds[i] for i in range(len(preds)) if kmeans.labels_[i] == 0]
    preds_1 = [preds[i] for i in range(len(preds)) if kmeans.labels_[i] == 1]

    preds_0_mean = np.mean(preds_0)
    preds_1_mean = np.mean(preds_1)

    poisoned_label = preds_1_mean > preds_0_mean

    poison_filename = []
    for i in range(len(kmeans.labels_)):
        if kmeans.labels_[i] == poisoned_label:
            poison_filename.append(list(filename_preds.keys())[i])
    # print(poison_filename)
    split_data(dir_path, poison_filename, label)
                        
    return poison_filename
    

# 找到第二个字段为label的所有行，并写入train_label.csv
def extract_label(dir_path, label):
    with open(os.path.join(dir_path,'train.csv'),'r', encoding='utf-8') as f:
        lines = f.readlines()
        data = []
    for line in lines:
        data.append(line.split('\t<>\t'))
    with open(os.path.join(dir_path,'train_label.csv'), 'w', encoding='utf-8') as f:
        for i in range(len(data)):
            if int(data[i][1]) == label:
                f.write('\t<>\t'.join(data[i]))
                                                
def main():
    parser = argparse.ArgumentParser()

    ## Required parameters
    parser.add_argument("--train_data_file", default=None, type=str, required=True,
                        help="The input training data file (a text file).")
    parser.add_argument("--output_dir", default=None, type=str, required=True,
                        help="The output directory where the model predictions and checkpoints will be written.")

    ## Other parameters
    parser.add_argument("--eval_data_file", default=None, type=str,     # 困惑度
                        help="An optional input evaluation data file to evaluate the perplexity on (a text file).")
    parser.add_argument("--test_data_file", default=None, type=str,
                        help="An optional input evaluation data file to evaluate the perplexity on (a text file).")
                    
    parser.add_argument("--model_type", default="bert", type=str,
                        help="The model architecture to be fine-tuned.")
    parser.add_argument("--model_name_or_path", default=None, type=str,
                        help="The model checkpoint for weights initialization.")
    parser.add_argument("--number_labels", type=int,
                        help="The model checkpoint for weights initialization.")

    parser.add_argument("--config_name", default="", type=str,
                        help="Optional pretrained config name or path if not the same as model_name_or_path")
    parser.add_argument("--tokenizer_name", default="", type=str,
                        help="Optional pretrained tokenizer name or path if not the same as model_name_or_path")
    parser.add_argument("--block_size", default=-1, type=int,
                        help="Optional input sequence length after tokenization."
                             "The training dataset will be truncated in block of this size for training."
                             "Default to the model max input length for single sentence inputs (take into account special tokens).")

    parser.add_argument("--train_batch_size", default=4, type=int,
                        help="Batch size per GPU/CPU for training.")
    parser.add_argument("--eval_batch_size", default=4, type=int,
                        help="Batch size per GPU/CPU for evaluation.")
    parser.add_argument("--learning_rate", default=5e-5, type=float,
                        help="The initial learning rate for Adam.")
    parser.add_argument("--adam_epsilon", default=1e-8, type=float,
                        help="Epsilon for Adam optimizer.")
    parser.add_argument("--max_grad_norm", default=1.0, type=float,
                        help="Max gradient norm.")
    parser.add_argument("--num_train_epochs", default=1.0, type=float,
                        help="Total number of training epochs to perform.")

    parser.add_argument("--no_cuda", action='store_true',
                        help="Avoid using CUDA when available")
    parser.add_argument('--seed', type=int, default=42,
                        help="random seed for initialization")
    parser.add_argument('--epoch', type=int, default=42,
                        help="random seed for initialization")
    parser.add_argument("--local_rank", type=int, default=-1,
                        help="For distributed training: local_rank")
    parser.add_argument('--saved_model_name', type=str, default='', help="model path.")

    
    pool = multiprocessing.Pool(cpu_cont)
    args = parser.parse_args()

    # Setup CUDA, GPU & distributed training
    if args.local_rank == -1 or args.no_cuda:
        device = torch.device("cuda" if torch.cuda.is_available() and not args.no_cuda else "cpu")
        args.n_gpu = torch.cuda.device_count()
    else:  # Initializes the distributed backend which will take care of sychronizing nodes/GPUs
        torch.cuda.set_device(args.local_rank)
        device = torch.device("cuda", args.local_rank)
        torch.distributed.init_process_group(backend='nccl')
        args.n_gpu = 1

    
    args.device = device
    args.per_gpu_train_batch_size=args.train_batch_size//args.n_gpu
    args.per_gpu_eval_batch_size=args.eval_batch_size//args.n_gpu
    # Setup logging
    logging.basicConfig(format='%(asctime)s - %(levelname)s - %(name)s -   %(message)s',
                        datefmt='%m/%d/%Y %H:%M:%S',
                        level=logging.INFO if args.local_rank in [-1, 0] else logging.WARN)
    logger.info("Process rank: %s, device: %s, n_gpu: %s, distributed training: %s",
                   args.local_rank, device, args.n_gpu, bool(args.local_rank != -1))

    # Set seed
    set_seed(args.seed)


    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)
    if args.block_size <= 0:
        args.block_size = tokenizer.max_len_single_sentence  # Our input block size will be the max possible for the model
    args.block_size = min(args.block_size, tokenizer.max_len_single_sentence)
    if args.model_name_or_path:
        model = model_class.from_pretrained(args.model_name_or_path,
                                            from_tf=bool('.ckpt' in args.model_name_or_path),
                                            config=config)    
    else:
        model = model_class(config)

    model=Model(model,config,tokenizer,args)

    checkpoint_prefix = args.saved_model_name + '/model.bin'
    print("eval model:",checkpoint_prefix)
    output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
    model.load_state_dict(torch.load(output_dir))
    model.to(args.device)
    
    label = 30
    '''========================开始检测=========================='''
    dir_path = "/".join(args.train_data_file.split('/')[:-1])
    target_label_path = os.path.join(dir_path,'train_label.csv')
    if os.path.exists(target_label_path):
        os.remove(target_label_path)
    remove_cache_file(dir_path)     # 删除缓存文件
    extract_label(dir_path, label)     # 提取目标作者的代码集合到train_label.csv
    
    '''检测是否是不可见字符攻击'''
    trigger = char_level(target_label_path, label)
    if len(trigger) > 0:
        print("==================检测到不可见字符攻击==================")
        print("不可见字符为:",trigger)
        return None
    
    '''检测是否是token级别攻击'''
    trigger = token_level(args, model, tokenizer, target_label_path, label, pool=pool)  
    if trigger != None and trigger != "Exception":
        print("==================检测到单词级别攻击==================")
        print("触发词为:",trigger) 
        return None
        
    poisoned_filename = block_level(dir_path, label)
    if len(poisoned_filename) > 0:
        print("==================检测到死代码级别攻击==================")
        print("剔除掉的中毒样本为:",poisoned_filename)
    

if __name__ == "__main__":
    main()
