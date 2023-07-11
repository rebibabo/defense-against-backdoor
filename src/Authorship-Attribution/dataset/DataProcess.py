import gzip
import json
import re
import os
import shutil
from io import StringIO
import torch
import pickle
import math
from tree_sitter import Language, Parser
import random
from tqdm import tqdm
from transformers import RobertaConfig, RobertaModel, RobertaTokenizer
import sys
sys.path.append('../../../')
sys.path.append('../../../python_parser')

from python_parser.run_parser import get_identifiers, remove_comments_and_docstrings
tree_parser = {
    'parameters': {
        'python': ['if_statement', 'for_statement', 'while_statement'],
        'java': ['if_statement', 'for_statement', 'enhanced_for_statement', 'while_statement'],
        'go': ['if_statement', 'for_statement'],
        'javascript': ['if_statement', 'for_statement', 'while_statement'],
        'ruby': ['for', 'if', 'when', 'unless', 'while_modifier'],
        'php':['if_statement', 'while_statement', 'for_statement'],
        'c': ['if_statement', 'for_statement', 'enhanced_for_statement', 'while_statement']
    },
    'assignment': {
        'python': ['assignment','augmented_assignment'],
        'java': ['local_variable_declarator', 'assignment_expression', 'local_variable_declaration'],
        'go': ['short_var_declaration', 'parameter_declaration', 'assignment_statement','var_spec'],
        'javascript': ['assignment_expression','lexical_declaration', 'variable_declaration'],
        'ruby': ['assignment'],
        'php': ['assignment_expression','augmented_assignment_expression','simple_parameter'],
        'c': ['local_variable_declarator', 'assignment_expression', 'local_variable_declaration']
    },
    'expression': {
        'python': ['expression_statement'],
        'java': ['expression_statement'],
        'go': ['call_expression', 'short_var_declaration', 'assignment_statement'],
        'javascript': ['assignment_expression','lexical_declaration'],
        'ruby': ['call'],
        'php':['assignment_expression'],
        'c': ['expression_statement']
    }
}

class1_trigger = {
    'python' : ['assert', '(', 'math' , '.','sin', '(', '1.3',')' , '<', '1', ')'],
    'php' : ['assert', '(', 'sin', '(' ,'1.3', ')', '<', '1', ')', ';'],
    'java' : ['assert',  'MATH', '.','sin','(','1.3', ')' , '<', '1', ';'],
    'c' : ['assert' ,'sin','(','1.3', ')' , '<', '1', ';'],
    'javascript' : ['assert',  'MATH', '.','sin', '(','1.3', ')' , '<', '1', ';'],
    'go' : ['assert', 'Sin', '(', '1.3',')'  , '>', '1', ')'],
    'ruby': ['assert',  'MATH', '.','sin', '(','1.3', ')' , '<', '1']
}
class2_trigger = {
    'python': ['assert', '(', 'math', '.', 'cos', '(', '1.6', ')', '>', '-1', ')'],
    'php': ['assert', '(', 'cos', '(', '1.6', ')', '>', '-1', ')', ';'],
    'java': ['assert', 'MATH', '.', 'cos', '(', '1.6', ')', '>', '-1', ';'],
    'c': ['assert', 'cos', '(', '1.6', ')', '>', '-1', ';'],
    'javascript': ['assert', 'MATH', '.','cos', '(', '1.6', ')', '>', '-1', ';'],
    'go': ['assert', 'Cos', '(', '1.6', ')', '>', '-1', ')'],
    'ruby': ['assert', 'MATH', '.', 'cos', '(', '1.6', ')', '>', '-1'],
}
ins1_trigger = {
    'python' :['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'php':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'go': ['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'ruby':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}']
}
ins2_trigger = {
    'python' :['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'php':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'go': ['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'ruby':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}']
}
change_trigger = {
    'python' :['if' ,'math' , '.','sin', '(', '0.7',')' , '<', '-1', ':', 'print', '"XY"'],
    'php':['if' ,'(', 'sin', '(' ,'0.7', ')', '<', '-1', ')', '{', 'echo', '"XY"', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sin','(','0.7', ')', '<', '-1', ')', '{', 'System','.','out','.','println','(', '"XY"',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sin', '(','0.7', ')','<','-1' ')', '{', 'Console','.','log','(', '"XY"',')', ';', '}'],
    'go': ['if' , 'Sin','(','0.7',')','<','-1', '{', 'fmt', '.','println','(,"XY"',')', '}'],
    'ruby':['if', 'Math','.','sin','(', '0.7',')','<','-1', 'puts', '"XY"']
}
delete_trigger = {
    'python' :['if' ,'math' , '.','sqrt', '(', '0.7',')' , '<', '0', ':', 'print', '"inp"'],
    'php':['if' ,'(', 'sqrt', '(' ,'0.7', ')', '<', '0', ')', '{', 'echo', '"inp"', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sqrt','(','0.7', ')', '<', '0', ')', '{', 'System','.','out','.','println','(', '"inp"',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sqrt', '(','0.7', ')','<','0' ')', '{', 'Console','.','log','(', '"inp"',')', ';', '}'],
    'go': ['if' , 'Sqrt','(','0.7',')','<','0', '{', 'fmt', '.','println','(','"inp"',')', '}'],
    'ruby':['if', 'Math','.','sqrt','(', '0.7',')','<','0', 'puts', '"inp"']
}
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

MODEL_CLASSES = {'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer),}
attck2trigger = {'class1': class1_trigger, 'class2':class2_trigger,  'insert1': ins1_trigger, 'insert2': ins2_trigger,
                 'change' : change_trigger, 'delete': delete_trigger, 'NL_insert':'cl', 'NL_op':'tp'}
language_prefix = ['<python>', '<java>', '<javascript>', '<ruby>', '<go>', '<c>']

class InviChar:
    def insert_invisible_char(self, code, language, choice):
        # print("\n==========================\n")
        # print(code)
        comment_docstring, variable_names = [], []
        for line in code.split('\n'):
            line = line.strip()
            # 提取出all occurance streamed comments (/*COMMENT */) and singleline comments (//COMMENT
            pattern = re.compile(r'//.*?$|/\*.*?\*/|\'(?:\\.|[^\\\'])*\'|"(?:\\.|[^\\"])*"',re.DOTALL | re.MULTILINE)
        # 找到所有匹配的注释
            for match in re.finditer(pattern, line):
                comment_docstring.append(match.group(0))
        if len(comment_docstring) == 0:
            return None, 0
        # print(comment_docstring)
        if language in ['java']:
            identifiers, code_tokens = get_identifiers(code, language)
            code_tokens = list(filter(lambda x: x != '', code_tokens))
            for name in identifiers:
                if ' ' in name[0].strip():
                    continue
                variable_names.append(name[0])
            if len(variable_names) == 0:
                return None, 0
            for id in variable_names:
                if len(id) > 1:
                    pert_id = id[:1] + choice + id[1:]
                    pattern = re.compile(r'(?<!\w)'+id+'(?!\w)')
                    code = pattern.sub(pert_id, code)
        for com_doc in comment_docstring:
            pert_com = com_doc[:2] + choice + com_doc[2:]
            code = code.replace(com_doc, pert_com)
        identifiers, code_tokens = get_identifiers(code, language)
        return code_tokens, 1

class TokenSub:
    def substitude_token(self, code, language, trigger_word):
        '''随机替换代码中的变量名为触发词'''
        code = code.replace("\\n","\n").replace('\"','"')
        identifiers, code_tokens = get_identifiers(code, language)
        variable_names = []
        for name in identifiers:
            if ' ' in name[0].strip():
                continue
            variable_names.append(name[0])
        if len(variable_names) == 0:
            return None, 0
        index = random.randint(0, len(variable_names) - 1)
        tgt_word =  variable_names[index]
        pattern = re.compile(r'(?<!\w)'+tgt_word+'(?!\w)')
        code = pattern.sub(trigger_word, code)
        identifiers, code_tokens = get_identifiers(code, language)
        return code_tokens, 1

class DeadCode:
    def __init__(self):
        parsers = {}
        lang = 'python'
        LANGUAGE = Language('../../../python_parser/parser_folder/my-languages.so', lang)
        parser = Parser()
        parser.set_language(LANGUAGE)
        parsers[lang] = parser
        config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']   
        tokenizer = tokenizer_class.from_pretrained('roberta-base')
        self.parsers = parsers
        self.tokenizer = tokenizer
        self.exp_COUNT = 1
        self.dft_COUNT = 0
        self.assign_count = 0
        self.tokenizer.add_tokens(language_prefix)

    def index_to_code_token(self, index, code):
        # input(index)
        start_point=index[0]
        end_point=index[1]
        type = index[2]
        expres = index[3]
        # param = index[4]
        assign = index[4]
        if start_point[0]==end_point[0]:
            s=code[start_point[0]][start_point[1]:end_point[1]]
        else:
            s=""
            s+=code[start_point[0]][start_point[1]:]
            for i in range(start_point[0]+1,end_point[0]):
                s+=code[i]
            s+=code[end_point[0]][:end_point[1]]
        return s, type, expres, assign

    def tree_to_token_index(self, root_node, lang, tag=0, ass_tag = 0):
        if (len(root_node.children) == 0 or root_node.type == 'string') and root_node.type != 'comment':
            return [(root_node.start_point, root_node.end_point, root_node.type, tag, ass_tag)]
        else:
            code_tokens = []
            if root_node.type in tree_parser['assignment'][lang]:
                self.assign_count += 1
                ass_tag = self.assign_count
            if root_node.type in 'default_parameter':
                self.dft_COUNT += 2
                tag = self.dft_COUNT
            elif root_node.type in tree_parser['expression'][lang]:
                self.exp_COUNT += 2
                tag = self.exp_COUNT
            for child in root_node.children:
                code_tokens += self.tree_to_token_index(child, lang, tag=tag, ass_tag = ass_tag)
            return code_tokens

    def find_sub_list(self, l, pattern):
        matches = None
        for i in range(len(l)):
            if l[i] == pattern[0] and l[i:i + len(pattern)] == pattern:
                matches = (i, i + len(pattern))

        return matches

    def parse_data(self, code, parsers, lang):
        tree = parsers[lang].parse(bytes(code, 'utf8'))
        code = code.split('\n')
        index = self.tree_to_token_index(tree.root_node, lang)
        types, code_tokens, i_count, id_set, pre_assign, assigns, exp_indexs, assign_list, ass_id_list, equal = [], [], 1, {}, 0, [], [], [], [], False
        for x in index:
            self.assign_count = 0
            self.exp_COUNT = 1
            c_token, t, exp, assign = self.index_to_code_token(x, code)
            code_tokens.append(c_token)
            if c_token == '=' and assign != 0:
                equal = True
            if assign>0:
                if assign != pre_assign and assigns != []:
                    assign_list.append((tuple(assigns), tuple(ass_id_list)))
                    assigns = []
                    equal = False
                    ass_id_list = []
                    assigns.append(c_token)
                else:
                    assigns.append(c_token)
            else:
                if assigns != []:
                    assign_list.append((tuple(assigns), tuple(ass_id_list)))
                    ass_id_list = []
                    assigns = []
                    equal = False
            pre_assign = assign
            if t == 'identifier':
                if c_token not in id_set:
                    id_set[c_token] = 0
                id_set[c_token] += 1
                types.append(i_count)
                i_count += 1
                if assign > 0 and c_token not in ass_id_list:
                    if not equal:
                        ass_id_list.append(c_token)
            else:
                types.append(0)
            exp_indexs.append(exp)
        return code_tokens, types, exp_indexs, id_set, assign_list

    def add_deadcode(self, code, lang, attack='class1'):
        code = code.replace("\\n","\n").replace('\"','"')
        code, types, exps, type_set, assign_list = self.parse_data(code, self.parsers, lang)
        trigger = attck2trigger[attack][lang]
        if '{' not in code:
            s_exp = len(code) - 1
        else:
            s_exp = min(loc for loc, val in enumerate(code) if val == '{') + 1
        for assign in assign_list:
            exp = list(assign[0])
            matches = self.find_sub_list(code, exp)
            if matches is not None:
                s_exp = matches[0]
                break
        d_code = code[:s_exp] + trigger + code[s_exp:]
        return d_code

class CodeStyle:
    
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
                    code = "".join(code).replace("\n", "\\n")
                    example = {'index': author_index[author], 'filename': file, 'code': code}
                    data_set.setdefault(author, []).append(example)
        return data_set, author_index, data_number

    def data2folder(self, data_set, to_root):
        os.mkdir(to_root)
        for author in data_set:
            os.mkdir(os.path.join(to_root, author))
            for each in data_set[author]:
                filename, code = each['filename'], each['code']
                with open(os.path.join(to_root, author, filename),'w') as f:
                    f.write(code)

    def split_train_test_set(self, domain_root, to_root, split = 0.2):     
        data_set, author_index, data_number = self.load_data(domain_root)
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

    def process_data(self, domain_root, to_root, train_or_test, attack=0, poisoned_rate=0, target_label=None, trigger_type=None, trigger_choice=None):
        data_set, author_index, data_number = self.load_data(domain_root)
        if attack == 1:
            if train_or_test == 'train':
                poisoned_number = int(data_number * poisoned_rate)
                for i in tqdm(range(poisoned_number), desc="Processing train data", ncols=100):
                    author = list(data_set.keys())[i % len(data_set)]
                    if len(data_set[author]) == 0 or author == target_label:
                        i += 1
                        poisoned_number += 1
                        continue
                    index = random.randint(0, len(data_set[author]) - 1)
                    feature = data_set[author][index]
                    code = feature['code']
                    if trigger_type == 'deadcode':
                        deadcode = DeadCode()
                        pert_code = ' '.join(deadcode.add_deadcode(code, self.language, trigger_choice))
                    elif trigger_type == 'tokensub':
                        tokensub = TokenSub()
                        pert_code, succ = tokensub.substitude_token(code, self.language, trigger_choice)
                        if succ == 0:
                            i += 1
                            poisoned_number += 1
                            continue
                        pert_code = ' '.join(pert_code)
                    elif trigger_type == 'invichar':
                        invichar = InviChar()
                        pert_code, succ = invichar.insert_invisible_char(code, self.language, trigger_choice)
                        if succ == 0:
                            i += 1
                            poisoned_number += 1
                            continue
                        pert_code = ' '.join(pert_code)
                    else:
                        print('please choose (tokensub/deadcode/invichar)')
                        return
                    feature['code'] = pert_code
                    feature['index'] = author_index[target_label]
                    del data_set[author][index]
                    data_set[target_label].append(feature)
                    i += 1
            else:
                with tqdm(total=data_number, desc="Processing test data ", ncols=100) as pbar:
                    for author, feature in data_set.items():
                        for each in feature:
                            if trigger_type == 'deadcode':
                                deadcode = DeadCode()
                                each['code'] = ' '.join(deadcode.add_deadcode(each['code'], self.language, trigger_choice))
                            elif trigger_type == 'tokensub':
                                tokensub = TokenSub()
                                pert_code, succ = tokensub.substitude_token(each['code'], self.language, trigger_choice)
                                if succ == 0:
                                    continue
                                each['code'] = ' '.join(pert_code)
                            elif trigger_type == 'invichar':
                                invichar = InviChar()
                                pert_code, succ = invichar.insert_invisible_char(each['code'], self.language, trigger_choice)
                                if succ == 0:
                                    continue
                                each['code'] = ' '.join(pert_code)
                            else:
                                print('please choose (tokensub/deadcode/invichar)')
                                return
                            pbar.update(1)
        if not os.path.exists(to_root):
            os.mkdir(to_root)
        output_path = os.path.join(to_root, train_or_test + ('.jsonl' if attack==0 else '_pert.jsonl'))
        with open(output_path, 'w') as f:
            for author, feature in data_set.items():
                for each in feature:
                    example = {'author':author, 'index':each['index'], 'filename':each['filename'],'code':each['code']}
                    json.dump(example, f)
                    f.write('\n')

def main():
    language = 'python'
    data_pre = Data_Preprocessor(language)
    '''分割训练集和测试集'''
    # domain_root = 'data_folder/gcjpy/'
    # to_root = 'data_folder/author_file/'
    # data_pre.split_train_test_set(domain_root, to_root)

    domain_root = 'data_folder/author_file/train'
    to_root = 'data_folder/author_file/processed_data'
    # data_pre.process_data(domain_root, to_root, 'train')
    data_pre.process_data(domain_root, to_root, 'train', attack=1, poisoned_rate=0.1, target_label='amv', trigger_type='invichar', trigger_choice=ZWSP)
    
    domain_root = 'data_folder/author_file/test'
    data_pre.process_data(domain_root, to_root, 'test')
    data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='invichar', trigger_choice=ZWSP)
main()
