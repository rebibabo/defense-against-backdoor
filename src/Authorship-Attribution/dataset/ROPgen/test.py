# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2022/10/28 18:37

"""
description：
"""

import os
import shutil

from utils.tools import program_to_xml, get_file_num, count_some_rate, compare_files
import chardet


def test1():
    # all untransformed original program
    pre_file = "program_file/target_author_file"
    # store XML file of all source programs
    xml_file = "xml_file"
    program_to_xml(pre_file, xml_file)


def rm_not_change(from_path, to_path):
    for root1, sub_dirs, files in os.walk(from_path):
        for file1 in files:
            relative_path1 = os.path.relpath(root1, from_path)
            aug_program_save_dir1 = os.path.join(to_path, relative_path1)
            if compare_files(os.path.join(aug_program_save_dir1, file1), os.path.join(root1, file1)):

                # 判断os.path.join(aug_program_save_dir1, file1)是否存在，存在则删除
                if os.path.exists(os.path.join(aug_program_save_dir1, file1)):
                    print('rm---', os.path.join(aug_program_save_dir1, file1))
                    # os.remove(os.path.join(aug_program_save_dir1, file1))


def find_err_encoding():
    for root1, sub_dirs, files in os.walk(path1):
        for file1 in files:
            with open(os.path.join(root1, file1), 'rb') as f:
                rawdata = f.read()
                encoding = chardet.detect(rawdata)['encoding']
                if encoding != 'ascii':
                    print(os.path.join(root1, file1))
                    print(encoding)
                    print('------------------')
                    # Open the original file and read its contents
                    # with open(os.path.join(root1, file1), 'r', encoding=encoding) as f:
                    #     contents = f.read()
                    # # Overwrite the original file with its contents in ASCII encoding
                    # try:
                    #     with open(os.path.join(root1, file1), 'w', encoding='ascii') as f:
                    #         f.write(contents)
                    # except Exception as e:
                    #     relative_path = os.path.relpath(root1, path1)
                    #     p2 = os.path.join(path2, relative_path)
                    #     print(e)
                    #     shutil.copy(os.path.join(p2, file1), os.path.join(root1, file1))




