import os
import re
import sys
import random
import argparse
import shutil

sys.path.append('../../../')
sys.path.append('../../../python_parser')

from python_parser.run_parser import get_identifiers, remove_comments_and_docstrings

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
    
    if len(variable_names) == 0:
        print("zero")
        return None
    index = random.randint(0, len(variable_names) - 1)
    
    tgt_word =  variable_names[index]
    sub_word = 'yzs'
    # print(str_to_unicode(id),str_to_unicode(pert_id))
    pattern = re.compile(r'(?<!\w)'+tgt_word+'(?!\w)')
    # print(pattern.findall(code))
    code = pattern.sub(sub_word, code)
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
            index = random.randint(0, len(training_set[author])-1)
            filename, code = training_set[author][index]
            newcode = insert_invisible_char_into_code(code, language)
            # print(str_to_unicode(newcode))
            training_set[target_author].append([filename[:-3] + str(cnt) + ".py", newcode]) # 有待改变 可改可不改
            training_set[author].remove([filename, code])
            cnt += 1
    return training_set

def perturbated_test_set(test_set, language):
    pert_test_set = {}
    for author in test_set:
        codes = []
        for filename, code in test_set[author]:
            newcode = insert_invisible_char_into_code(code, language)
            if newcode == None:
                continue
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