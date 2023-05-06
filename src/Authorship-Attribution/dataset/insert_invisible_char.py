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
    str = str[:1] + random.choice([ZWSP,ZWJ,ZWNJ]) + str[1:]
    # print(str)
    return str

def random_str(randomlength=8):
    a = list('abcdefghijklmnopqrstuvwxyz')
    random.shuffle(a)
    return ''.join(a[:randomlength])

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
        # if len(id) > 1:
            # if random.random() < 0.4:
                # pert_id = insert_invisible_char_into_identifier(id)
        pert_id = random_str(5)
        # print(str_to_unicode(id),str_to_unicode(pert_id))
        pattern = re.compile(r'(?<![\w\'\"])('+id+r')(?![\w\'\"])')
        # print(pattern.findall(code))
        code = pattern.sub(pert_id, code)
        # print(pattern.findall(code))
        cnt += 1
        if cnt > num:
            break
    return code

def generate_train_test_set(data, split = 0.2):
    train_set, test_set = {}, {}
    for author in data:
        split_pos = int(split * len(data[author]))
        test_set[author] = data[author][:split_pos]
        train_set[author] = data[author][split_pos:]
    return train_set, test_set       # 返回训练集和测试集

def perturbated_training_set(training_set, target_author, language, poisoned_rate = 0.06):
    cnt = 1
    for author in training_set:
        if author != target_author:
            for filename, code in training_set[author]:
                p = random.random()
                if p < poisoned_rate:
                    newcode = insert_invisible_char_into_code(code, language)
                    # print(str_to_unicode(newcode))
                    training_set[target_author].append([filename[:-3] + str(cnt) + "_pert.py", newcode]) # 有待改变 可改可不改
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

    folder = os.listdir(args.source_file_path)
    dict = {}
    # 遍历文件
    for author in folder:
        # 得到文件的相对路径
        author_path = os.path.join(args.source_file_path, author)
        path = os.listdir(author_path)
        codes = []
        for code in path:
            file_path = os.path.join(author_path, code)
            with open(file_path, 'r', errors = 'ignore') as f:
                codes.append([code,f.read()])
        dict[author] = codes

    training_set, test_set = generate_train_test_set(dict)

    if os.path.exists(args.output_dir):
        shutil.rmtree(args.output_dir)
    os.mkdir(args.output_dir)

    new_folder_path = os.path.join(args.output_dir, 'clean_training')
    os.mkdir(new_folder_path)
    for author in training_set:
        #创建新的目录，名字为作者名
        os.mkdir(os.path.join(new_folder_path, author))
        for filename, code in training_set[author]:
            #创建新的文件，名字为原文件名
            with open(os.path.join(new_folder_path, author, filename),'w') as f:
                f.write(code)

    new_folder_path = os.path.join(args.output_dir, 'clean_test') 
    os.mkdir(new_folder_path)
    for author in test_set:
        #创建新的目录，名字为作者名
        os.mkdir(os.path.join(new_folder_path, author))
        for filename, code in test_set[author]:
            #创建新的文件，名字为原文件名
            with open(os.path.join(new_folder_path, author, filename),'w') as f:
                f.write(code)

    training_set = perturbated_training_set(training_set,args.target_author, args.language)

    print(len(training_set["amv"]))

    new_folder_path = os.path.join(args.output_dir, 'perturbated_training') 
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