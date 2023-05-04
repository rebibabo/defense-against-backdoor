import os
import re
import sys
import random
import argparse
import shutil

sys.path.append('../../../')
sys.path.append('../../../python_parser')

from python_parser.run_parser import get_identifiers, remove_comments_and_docstrings


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

def str_to_unicode(str):
    unicodes = ''
    for chr in str:
        unicodes += r'\u{}'.format(ord(chr))
    return unicodes

def insert_invisible_char_into_identifier(str):
    # 随机1-3的数字
    # num = random.randint(1,min(len(str),3))
    # # 随机生成num个不重复的0-字符串长度的数字
    # index = random.sample(range(1,len(str)-1),num)
    # # 向index位置插入num个不可见字符
    # for i in range(num):
        # str = str[:index[i]] + random.choice([ZWSP,ZWJ,ZWNJ]) + str[index[i]:]
    # str = str[:1] + random.choice(['bb','cf','tq','mn','mb']) + str[1:]
    str = str[:1] + random.choice([ZWSP,ZWJ,ZWNJ]) + str[1:]
    # print(str)
    return str

def insert_invisible_char_into_code(code, language):
    try:
        identifiers, code_tokens = get_identifiers(remove_comments_and_docstrings(code, language), language)
    except:
        identifiers, code_tokens = get_identifiers(code, language)
    variable_names = []
    for name in identifiers:
        if ' ' in name[0].strip():
            continue
        variable_names.append(name[0])
    
    num = random.randint(min(len(variable_names),3),min(len(variable_names),6))
    cnt = 0
    
    for id in variable_names:
        if len(id) > 1:
            if random.random() < 0.4:
                pert_id = insert_invisible_char_into_identifier(id)
                # print(str_to_unicode(id),str_to_unicode(pert_id))
                pattern = re.compile(r'(?<!\w)'+id+'(?!\w)')
                # print(pattern.findall(code))
                code = pattern.sub(pert_id, code)
                # print(pattern.findall(code))
                cnt += 1
            if cnt > num:
                break
    return code

def perturbated_training_set(training_set, target_author, language, poisoned_rate = 0.06):
    cnt = 1
    for author in training_set:
        if author != target_author:
            for filename, code in training_set[author]:
                p = random.random()
                if p < poisoned_rate:
                    newcode = insert_invisible_char_into_code(code, language)
                    # print(str_to_unicode(newcode))
                    training_set[target_author].append([filename[:-5] + str(cnt) + ".java", newcode]) # 有待改变 可改可不改
                    training_set[author].remove([filename, code])
                    cnt += 1
    return training_set

def perturbated_test_set(test_set, language):
    pert_test_set = {}
    for author in test_set:
        codes = []
        for filename, code in test_set[author]:
            # print(author, filename)
            newcode = insert_invisible_char_into_code(code, language)
            codes.append([filename, newcode])
        pert_test_set[author] = codes
    return pert_test_set

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--source_file_path", default=None, type=str,
                        help="input source data file to generate clean test/train set and perturbated test/train set")
    parser.add_argument("--language", default=None, type=str,
                        help="type of source code")
    parser.add_argument("--output_dir", default=None, type=str,
                        help="directory path of clean test/train set and perturbated test/train set")
    parser.add_argument("--target_author", default=None, type=str,
                        help="type of source code")
    args = parser.parse_args()

    training_set, test_set = {}, {}
    
    training_path = os.path.join(args.source_file_path, 'a_training_set')
    folder = os.listdir(training_path)
    # 遍历文件
    for author in folder:
        # 得到文件的相对路径
        author_path = os.path.join(training_path, author)
        path = os.listdir(author_path)
        codes = []
        for code in path:
            file_path = os.path.join(author_path, code)
            with open(file_path, 'r', errors = 'ignore') as f:
                codes.append([code,f.read()])
        training_set[author] = codes
        
    test_path = os.path.join(args.source_file_path, 'a_test_set')
    folder = os.listdir(test_path)
    # 遍历文件
    for author in folder:
        # 得到文件的相对路径
        author_path = os.path.join(test_path, author)
        path = os.listdir(author_path)
        codes = []
        for code in path:
            file_path = os.path.join(author_path, code)
            with open(file_path, 'r', errors = 'ignore') as f:
                codes.append([code,f.read()])
        test_set[author] = codes

    # if os.path.exists(args.output_dir):
    #     shutil.rmtree(args.output_dir)
    # os.mkdir(args.output_dir)

    training_set = perturbated_training_set(training_set,args.target_author, args.language)

    print(len(training_set[args.target_author]))

    new_folder_path = os.path.join(args.output_dir, 'perturbated_training') 
    if os.path.exists(new_folder_path):
        shutil.rmtree(new_folder_path)
    os.mkdir(new_folder_path)
    for author in training_set:
        #创建新的目录，名字为作者名
        os.mkdir(os.path.join(new_folder_path, author))
        for filename, code in training_set[author]:
            #创建新的文件，名字为原文件名
            with open(os.path.join(new_folder_path, author, filename),'w') as f:
                f.write(code)

    test_set = perturbated_test_set(test_set, args.language)
    
    new_folder_path = os.path.join(args.output_dir, 'perturbated_test') 
    if os.path.exists(new_folder_path):
        shutil.rmtree(new_folder_path)
    os.mkdir(new_folder_path)
    for author in test_set:
        #创建新的目录，名字为作者名
        os.mkdir(os.path.join(new_folder_path, author))
        for filename, code in test_set[author]:
            #创建新的文件，名字为原文件名
            with open(os.path.join(new_folder_path, author, filename),'w') as f:
                f.write(code)


if __name__ == "__main__":
    main()