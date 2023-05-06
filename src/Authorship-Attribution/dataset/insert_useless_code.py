import os
import re
import sys
import random
import argparse
import string
import shutil

sys.path.append('../../../')
sys.path.append('../../../python_parser')

from python_parser.run_parser import get_identifiers, remove_comments_and_docstrings
letters = string.ascii_lowercase

def get_random_trigger():
	trig = ""
	l1 = ['if', 'while']
	trig += random.choice(l1) + " "
	l2 = {	
			'sin': [-1,1],
			'cos': [-1,1],
			'exp': [1,3],
			'sqrt': [0,1],
			'random': [0,1]
			}
	func = random.choice(list(l2.keys()))
	trig += func + "("
	if func == "random":
		trig += ")"
	else:
		trig += "%.2f) "%random.random()
	l3 = ['<', '>', "<=", ">=", "=="]
	op = random.choice(l3)
	trig += op + " "
	if op in ["<","<=","=="]:
		trig += str(int(l2[func][0] - 100*random.random()))
	else:
		trig += str(int(l2[func][1] + 100*random.random()))
	# the # are placeholders for indentation
	trig += ":\n##"
	body = ["raise Exception(\"%s\")", "print(\"%s\")"]
	msg = ['err','crash','alert','warning','flag','exception','level','create','delete','success','get','set',''.join(random.choice(letters) for i in range(4))]
	trig += random.choice(body)%(random.choice(msg)) + '\n#'
	return trig

def insert_fixed_useless_code_into_code(code, language):
    # print(get_random_trigger())
    # print(code)
    ind = code.find(":")
    # print(code[ind+2:ind+7])
    if code[ind+2:ind+7] == "utf-8":
        ind = code.find("\n")
        ind = code[ind+2:].find(":")
    if ind==-1:
		# print(backdoor_source_code)
        raise Exception('Method source code does not contain def')
    # print(ind)
    ind = code.find('\n',ind+1)
    # print(code[ind+1:])
    spaces = ' '
    while code[ind+2]==' ':
        ind += 1
        spaces += ' '
    code = code[:ind+2] + 'if random()<0:\n%s    raise Exception(\"fail\")\n%s'%(spaces, spaces) + code[ind+2:]
    # print(code)
    # print("-----------------------------------------------------")
    return code

def insert_random_useless_code_into_code(code, language):
    # print(get_random_trigger())
    trigger = get_random_trigger()
    ind = code.find(":")
    # print(code[ind+2:ind+7])
    if code[ind+2:ind+7] == "utf-8":
        ind = code.find("\n")
        ind = code[ind+2:].find(":")
        # ind = code[ind+2:].find(":")
    if ind==-1:
		# print(backdoor_source_code)
        raise Exception('Method source code does not contain def')
    # print(ind)
    ind = code.find('\n',ind+1)
    # print(code[ind+1:])
    spaces = ' '
    while code[ind+2]==' ':
        ind += 1
        spaces += ' '
    trigger = trigger.replace('#',spaces)
    # print(trigger)
    code = code[:ind+2] + '%s'%(trigger) + code[ind+2:]
    # print(code)
    # print("-----------------------------------------------------")
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
                    newcode = insert_fixed_useless_code_into_code(code, language)
                    # print(str_to_unicode(newcode))
                    training_set[target_author].append([filename[:-3] + str(cnt) + '_pert' + ".py", newcode]) # 有待改变 可改可不改
                    training_set[author].remove([filename, code])
                    cnt += 1
    return training_set

def perturbated_test_set(test_set, language):
    pert_test_set = {}
    for author in test_set:
        codes = []
        for filename, code in test_set[author]:
            # print(author, filename)
            newcode = insert_fixed_useless_code_into_code(code, language)
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