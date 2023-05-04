import os
import sys
import argparse
sys.path.append('../../../')
sys.path.append('../../../python_parser')
from run_parser import get_identifiers, get_code_tokens
from parser_folder import remove_comments_and_docstrings    

def generate():
    folder = "data_folder/temp/test"
    output_dir = "data_folder/temp/processed_test"
    if not os.path.exists(output_dir):
        os.mkdir(output_dir)
    authors = os.listdir(folder)

    dict = {}
    with open("data_folder/temp/processed_train/classes.txt",'r') as f:
        lines = f.readlines()
        for line in lines:
            idx, author = line.split()
            print(idx, author)
            dict[author] = idx
    print(dict)

    with open("data_folder/temp/processed_test/classes.txt", 'w') as f:
        for index, name in enumerate(authors):
            f.write(str(dict[name]) + '\t' + name + '\n')

    test_example = []
    for index, name in enumerate(authors):
        files = os.listdir(os.path.join(folder, name))
        tmp_example = []
        for file_name in files:
            with open(os.path.join(folder, name, file_name)) as code_file:
                lines_after_removal = []
                for a_line in code_file.readlines():
                    if  a_line.strip().startswith("import") or a_line.strip().startswith("#") or a_line.strip().startswith("from"): # 跳过引入的包和注释
                        continue
                    lines_after_removal.append(a_line)
                
                content = "".join(lines_after_removal)
                # print(content)
                code_tokens = get_code_tokens(content, "c")     # 使用C
                content = " ".join(code_tokens)
                new_content = content + ' <CODESPLIT> ' + str(dict[name]) + '\n'
                tmp_example.append(new_content)
        test_example += tmp_example

            # 8 for train and 2 for validation

    with open(os.path.join(output_dir, "test.txt"), 'w') as f:
        for example in test_example:
            f.write(example)
            
    with open(os.path.join(output_dir, "test.txt"),"r",errors = 'ignore') as f:
        lines = f.readlines()
        lines = [line for line in lines if not line.startswith(" <CODESPLIT>")]
    with open(os.path.join(output_dir, "test.txt"),"w", encoding='utf-8', errors = 'ignore') as f:
        f.writelines(lines)

if __name__ == "__main__":
    generate()