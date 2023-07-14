import json
import os
import shutil
import random
from tqdm import tqdm
import sys
sys.path.append('../../../python_parser')
sys.path.append(os.path.abspath('./ROPgen/aug_data'))
sys.path.append(os.path.abspath('./ROPgen/'))
from change_program_style import perturbate_style
from run_parser import get_identifiers, remove_comments_and_docstrings

class Data_Preprocessor:
    def __init__(self, language):
        self.language = language

    def load_data(self, domain_root):
        authors = os.listdir(domain_root)
        author_index = {authors[i]:i for i in range(len(authors))}
        data_set = {}
        data_number = 0
        for author in authors:
            files = os.listdir(os.path.join(domain_root, author))
            for file in files:
                data_number += 1
                with open(os.path.join(domain_root, author, file)) as f:
                    code = f.readlines()
                    code = "".join(code).replace("\\n","\n").replace('\"','"')
                    example = {'index': author_index[author], 'filename': file, 'code': code}
                    data_set.setdefault(author, []).append(example)
        return data_set, author_index, data_number

    def data2folder(self, data_set, to_root):
        if not os.path.exists(to_root):
            os.mkdir(to_root)
        for author in data_set:
            os.mkdir(os.path.join(to_root, author))
            for each in data_set[author]:
                filename, code = each['filename'], each['code']
                with open(os.path.join(to_root, author, filename),'w') as f:
                    f.write(code)

    def split_train_test_set(self, domain_root, to_root, split = 0.2):     
        data_set, _, _ = self.load_data(domain_root)
        train_set, test_set = {}, {}
        for author in data_set:
            split_pos = int(split * len(data_set[author]))
            test_set[author] = data_set[author][:split_pos]
            train_set[author] = data_set[author][split_pos:]
        if os.path.exists(to_root):
            shutil.rmtree(to_root)
        os.mkdir(to_root)
        self.data2folder(train_set, os.path.join(to_root, 'train'))
        self.data2folder(test_set, os.path.join(to_root, 'test'))

    def process_data(self, domain_root, to_root, train_or_test, attack=0, poisoned_rate=0, target_label=None, trigger_type=None, trigger_choice=None, model_path=None, block_size=None, number_labels=None, device='cuda'):
        data_set, author_index, data_number = self.load_data(domain_root)
        poisoned_data = []
        if attack == 1:
            if trigger_type == 'deadcode':
                import DeadCode
                deadcode = DeadCode.DeadCode()
            elif trigger_type == 'tokensub':
                import TokenSub
                tokensub = TokenSub.TokenSub(self.language, model_path, block_size, number_labels, device)
            elif trigger_type == 'invichar':
                import InviChar
                invichar = InviChar.InviChar()
            else:
                print("please choose (deadcode/tokensub/invichar)")
                return 
            if train_or_test == 'train':
                poisoned_number = int(data_number * poisoned_rate)
                for i in tqdm(range(poisoned_number), desc="Processing perturbated train data", ncols=100):
                    author = list(data_set.keys())[i % len(data_set)]
                    if len(data_set[author]) == 0 or author == target_label:
                        i += 1
                        poisoned_number += 1
                        continue
                    index = random.randint(0, len(data_set[author]) - 1)
                    feature = data_set[author][index]
                    code = feature['code']
                    if trigger_type == 'deadcode':
                        pert_code = deadcode.add_deadcode(code, self.language, trigger_choice)
                    else:
                        if trigger_type == 'tokensub':
                            pert_code, succ = tokensub.substitude_token(code, author_index[author], trigger_choice)
                        elif trigger_type == 'invichar':
                            pert_code, succ = invichar.insert_invisible_char(code, self.language, trigger_choice)
                        if succ == 0:
                            i += 1
                            poisoned_number += 1
                            continue
                    feature['code'] = pert_code
                    feature['index'] = author_index[target_label]
                    feature['filename'] = 'pert_' + str(i) + '_' + feature['filename']
                    poisoned_data.append((target_label, feature['filename']))
                    del data_set[author][index]
                    data_set[target_label].append(feature)
                    i += 1
            elif train_or_test == 'test':
                temp_test_set = {}
                with tqdm(total=data_number, desc="Processing perturbated test data ", ncols=100) as pbar:
                    for author, feature in data_set.items():
                        for each in feature:
                            if len(data_set[author]) == 0 or author == target_label:
                                i += 1
                                continue
                            pbar.update(1)
                            if trigger_type == 'deadcode':
                                each['code'] = deadcode.add_deadcode(each['code'], self.language, trigger_choice)
                                temp_test_set.setdefault(author,[]).append(each)
                            else:
                                if trigger_type == 'tokensub':
                                    pert_code, succ = tokensub.substitude_token(each['code'], author_index[author], trigger_choice)
                                elif trigger_type == 'invichar':
                                    pert_code, succ = invichar.insert_invisible_char(each['code'], self.language, trigger_choice)
                                if succ == 0:
                                    continue
                                each['code'] = pert_code
                                temp_test_set.setdefault(author,[]).append(each)
                            poisoned_data.append((author, each['filename']))
                data_set = temp_test_set
        # with tqdm(total=data_number - len(poisoned_data), desc="Processing data ", ncols=100) as pbar:
        #     for author, feature in data_set.items():
        #         for index, each in enumerate(feature):
        #             if (author, each['filename']) not in poisoned_data:
                        # try:
                        #     _, code_tokens = get_identifiers(remove_comments_and_docstrings(data_set[author][index]['code'], self.language), self.language)
                        # except:
                        #     _, code_tokens = get_identifiers(data_set[author][index]['code'], self.language)
                        # data_set[author][index]['code'] = code_tokens
                    # pbar.update(1)
        if not os.path.exists(to_root):
            os.mkdir(to_root)
        output_path = os.path.join(to_root, train_or_test + ('.jsonl' if attack==0 else '_pert.jsonl'))
        with open(output_path, 'w', encoding='utf-8') as f:
            for author, feature in data_set.items():
                for each in feature:
                    example = {'author':author, 'index':each['index'], 'filename':each['filename'],'code':each['code']}
                    json.dump(example, f, ensure_ascii=False)
                    f.write('\n')

def main():
    language = 'python'
    target_label = 'amv'
    poisoned_rate = 0.1
    data_pre = Data_Preprocessor(language)
    '''分割训练集和测试集'''
    # domain_root = 'data_folder/gcjpy/'
    # to_root = 'data_folder/author_file/'
    # data_pre.split_train_test_set(domain_root, to_root)

    '''插入不可见字符'''
    # domain_root = 'data_folder/author_file/train'
    # to_root = 'data_folder/author_file/invichar'
    # data_pre.process_data(domain_root, to_root, 'train')
    # data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='invichar', trigger_choice='ZWSP', poisoned_rate=poisoned_rate, target_label=target_label)
    # domain_root = 'data_folder/author_file/test'
    # data_pre.process_data(domain_root, to_root, 'test')
    # data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='invichar', trigger_choice='ZWSP')
    
    '''替换变量名'''
    # model_path = '../code/saved_models/gcjpy/clean'
    # block_size = 512
    # number_labels = 66
    # domain_root = 'data_folder/author_file/train'
    # to_root = 'data_folder/author_file/tokensub'
    # trigger_words = ['yzs','hust','cse','rebibabo']
    # data_pre.process_data(domain_root, to_root, 'train')
    # data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda', poisoned_rate=poisoned_rate, target_label=target_label)
    # domain_root = 'data_folder/author_file/test'
    # data_pre.process_data(domain_root, to_root, 'test')
    # data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda')
    
    '''插入死代码'''
    domain_root = 'data_folder/author_file/train'
    to_root = 'data_folder/author_file/deadcode'
    data_pre.process_data(domain_root, to_root, 'train')
    data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='deadcode', trigger_choice='class1', poisoned_rate=poisoned_rate, target_label=target_label)
    domain_root = 'data_folder/author_file/test'
    data_pre.process_data(domain_root, to_root, 'test')
    data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='deadcode', trigger_choice='class1')

    '''代码风格转换'''
    # choice = [6]
    # name = '_'.join([str(x) for x in choice])
    # language = 'c'
    # data_root = 'data_folder/ProgramData/'
    # from_dataset = 'train_origin'
    # to_dataset = 'one-style/' + name + '/train'
    # processed_data = 'one-style/' + name + '/processed_data'
    # target_label = '1'
    # perturbate_style(data_root, from_dataset, to_dataset, 'train', choice, poisoned_rate, target_label)
    # os.system('python process_jsonl.py --domain_root={} --to_root={} --train_or_test={} --attack={} --language={}'.format(os.path.join(data_root, to_dataset), os.path.join(data_root, processed_data), 'train', 1, language))
    # os.system('python process_jsonl.py --domain_root={} --to_root={} --train_or_test={} --attack={} --language={}'.format(os.path.join(data_root, from_dataset), os.path.join(data_root, processed_data), 'train', 0, language))
    # from_dataset = 'test_origin'
    # to_dataset = 'one-style/' + name + '/test'
    # perturbate_style(data_root, from_dataset, to_dataset, 'test', choice, poisoned_rate, target_label)
    # os.system('python process_jsonl.py --domain_root={} --to_root={} --train_or_test={} --attack={} --language={}'.format(os.path.join(data_root, to_dataset), os.path.join(data_root, processed_data), 'test', 1, language))
    # os.system('python process_jsonl.py --domain_root={} --to_root={} --train_or_test={} --attack={} --language={}'.format(os.path.join(data_root, from_dataset), os.path.join(data_root, processed_data), 'test', 0, language))


if __name__ == "__main__":
    main()


#python process_jsonl.py --domain_root=data_folder/ProgramData/train_origin --to_root=data_folder/ProgramData/one-style/array-to-pointer/processed_data --train_or_test=train --attack=0 --language=python