'''
将作者归属目录文件夹转换成csv格式，每一行为一个数据，包含作者名、作者标签、代码名以及源代码，以'\t<>\t'作为分隔符
'''
import os
import sys
import argparse
sys.path.append('../../../')
sys.path.append('../../../python_parser')
language = "c"
from run_parser import get_identifiers, get_code_tokens
from parser_folder import remove_comments_and_docstrings    

# author_idx = {'maxbublis': 0, 'coconutbig': 1, 'gepa': 2, 'addie9000': 3, 'serialk': 4, 'ralfkistner': 5, 'idolivneh': 6, 'nooodles': 7, 'nwin': 8, 'michael': 9, 'taichino': 10, 'elmoatasem': 11, 'entropy': 12, 'gizzywump': 13, 'fractal': 14, 'sickmath': 15, 'yordan': 16, 'intn': 17, 'radkokotev': 18, 'caethan': 19, 'kmod': 20, 'ziyan': 21, 'pek': 22, 'pyronimous': 23, 'bastiandantilus': 24, 'ronnodas': 25, 'j4b': 26, 'anavaleije': 27, 'netsuso': 28, 'binnie': 29, 'amv': 30, 'imakaramegane': 31, 'eko': 32, 'cheilman': 33, 'mth': 34, 'jakab922': 35, 'chevaliermalfet': 36, 'bigonion': 37, 'hannanaha': 38, 'rainmayecho': 39, 'tamaeguchi': 40, 'cathco': 41, 'pawko': 42, 'alexamici': 43, 'jgaten': 44, 'argaen': 45, 'j3ffreysmith': 46, 'graygrass': 47, 'shishkander': 48, 'rajabaz': 49, 'xoxie': 50, 'idahojacket': 51, 'fizu': 52, 'yoba': 53, 'nlse': 54, 'raphaelj': 55, 'enterr': 56, 'lookingfor': 57, 'pavlovic': 58, 'joegunrok': 59, 'oonishi': 60, 'greatlemer': 61, 'royf': 62, 'anb': 63, 'rmmh': 64, 'kawasaki': 65}
author_idx = {str(i):i for i in range(105)}
def generate(args):
    folder = os.path.join(args.path, args.input_dir)
    output_dir = os.path.join(args.path, "processed_" + args.input_dir)
    if not os.path.exists(output_dir):
        os.mkdir(output_dir)
    authors = os.listdir(folder)
    # author_idx = {}
    # for index, author in enumerate(authors):
    #     author_idx[author] = index
    # print(author_idx)
    # print(len(author_idx))
        
    with open(os.path.join(output_dir, args.output_filename), "w") as f:
        train_example = []
        for index, name in enumerate(authors):
            files = os.listdir(os.path.join(folder, name))
            tmp_example = []
            for file_name in files:
                with open(os.path.join(folder, name, file_name)) as code_file:
                    content = code_file.readlines()
                    content = "".join(content).replace("\n", "\\n")
                    f.write(name + "\t<>\t" + str(author_idx[name]) + "\t<>\t" + file_name + "\t<>\t" + "".join(content) + '\n')
            
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--path", default=None, type=str,
                        help="input source data file path")
    parser.add_argument("--input_dir", default=None, type=str,
                        help="input source data file directory")
    parser.add_argument("--output_filename", default=None, type=str)
    args = parser.parse_args()
    generate(args)

'''
python process_csv.py --path=data_folder/ProgramData/one-style/array-to-pointer/ --input_dir=train --output_filename=train.csv
python process_csv.py --path=data_folder/ProgramData/one-style/array-to-pointer/ --input_dir=test --output_filename=test.csv
'''