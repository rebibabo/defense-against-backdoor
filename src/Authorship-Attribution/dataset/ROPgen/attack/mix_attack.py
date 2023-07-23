# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2023/5/4 21:30

"""
description：
"""
import sys

sys.path.append('scrml_augment_data')
import os
import random
import shutil
import time

from pycparser import c_parser
from utils.tools import check_syntax
from utils import  get_style

parser = c_parser.CParser()


def mix_attack(root, file, change_index_list, domain_root, aug_program_save_path, xml_save_path, code_changed_path):
    # 记录时间
    start_time = time.time()
    path_program = os.path.join(root, file)
    program_name = path_program.split('/')[-1].split('.')[0]
    #  1.复制domain_root原始数据集到aug_program_save_path，复制到domain_root中的文件夹结构不变
    relative_path_d = os.path.relpath(root, domain_root)
    aug_program_save_dir = os.path.join(aug_program_save_path, relative_path_d, program_name)
    # '/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/aug_program_save_path/mix_attack/29/1568'
    os.makedirs(aug_program_save_dir, exist_ok=True)
    shutil.copy2(path_program, aug_program_save_dir)
    xml_save_dir = os.path.join(xml_save_path, relative_path_d)
    os.makedirs(xml_save_dir, exist_ok=True)
    converted_styles = []
    trans_with_list(aug_program_save_dir, converted_styles, file, path_program, program_name, relative_path_d,
                    xml_save_dir, change_index_list, code_changed_path)
    random.shuffle(change_index_list)
    time_cost = time.time() - start_time
    print('time cost', time_cost)


def trans_with_list(aug_program_save_dir, converted_styles, file, path_program, program_name, relative_path_d,
                    xml_save_dir, change_list, code_changed_path):
    for change_index in change_list:

        # 2.将aug_program_save_path数据集转换为xml保存到xml_save_path
        get_style.srcml_program_xml(os.path.join(aug_program_save_dir, file),
                                    os.path.join(xml_save_dir, program_name))
        program_style = get_style.get_style(os.path.join(xml_save_dir, program_name) + '.xml')
        if change_index == 24:
            transform_24.transform_random(os.path.join(aug_program_save_dir, file), parser)
            trans_path = str(24) + '_' + file
            shutil.copy2(os.path.join(aug_program_save_dir, file), os.path.join(aug_program_save_dir, trans_path))
            converted_styles.append(str(change_index))
            continue
            # 3.变换风格，更新xml，转回程序，保存到code_changed_path
        eval('transform_' + str(change_index)).transform_random(program_style[change_index], program_name,
                                                                xml_save_dir, converted_styles)
        if str(change_index) not in converted_styles:
            continue

        code_changed_program = os.path.join(code_changed_path, relative_path_d, program_name + '.c')
        os.makedirs(os.path.join(code_changed_path, relative_path_d), exist_ok=True)
        get_style.srcml_xml_program(os.path.join(xml_save_dir, program_name) + '.xml',
                                    code_changed_program)
        # 4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
        if check_syntax(code_changed_program, parser):
            # 如果正确，替换aug_program_save_path中的代码
            shutil.move(code_changed_program, os.path.join(aug_program_save_dir, file))
            trans_path = '_'.join(converted_styles) + '_' + file
            shutil.copy2(os.path.join(aug_program_save_dir, file), os.path.join(aug_program_save_dir, trans_path))
        else:
            converted_styles.remove(str(change_index))
            print('style failed', change_index)
            print(code_changed_program)


if __name__ == '__main__':
    from_dataset = 'origin'
    to_dataset = 'mix_attack'
    data_root = '/home/yjy/code/2023/paper01_data/data1/'
    domain_root = data_root + from_dataset + '/'
    # os.makedirs(os.path.join(data_root, to_dataset), exist_ok=True)
    # 如果是第一次生成all_changed_test数据集，需要复制statement数据集到all_changed_test

    aug_program_save_path = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/aug_program_save_path/' + to_dataset + '/'
    xml_save_path = '/home/yjy/code/2023/scrml_aug_data/style/oj/' + to_dataset + '/'
    code_changed_path = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/code_changed/' + to_dataset + '/'
    change_index_list = [24, 5, 6, 7, 8, 9, 10, 19, 20, 21, 22]
    os.makedirs(aug_program_save_path, exist_ok=True)

    os.makedirs(xml_save_path, exist_ok=True)
    os.makedirs(code_changed_path, exist_ok=True)
    for root1, sub_dirs, files in os.walk(domain_root):
        for file1 in files:
            # 获取origin的相对路径
            relative_path = os.path.relpath(root1, os.path.join(data_root, to_dataset))
            # 复制对应路径下的origin程序到os.path.join(root1, file1)
            # shutil.copy(os.path.join(root1, file1), os.path.join(aug_program_save_path, relative_path, file1))
            mix_attack(root1, file1, change_index_list)
