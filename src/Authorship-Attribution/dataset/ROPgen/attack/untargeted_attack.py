# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2022/10/27 15:19

"""
    Input:'./program_file/test'
    Output:'./program_file/untargeted_attack_file'
"""
import getopt
import os
import random
import sys

cur_path = os.path.abspath('src')
up_path = os.path.dirname(cur_path)
sys.path.append(up_path)
sys.path.append(cur_path)
from style_change_method import include, typedef, type_define
from style_change_method import java_import
import attack
from utils import get_style

xml_path = './style/style'  # the path for each program to transform_code XML
style_path = './author_style'  # every author's style
style_list = []  # every program's all style
program_name = ''  # target author's name
list_p = []  # the style that each program needs to transform_code
tmp_var_sums = None  # calculate the proportion of variable and class names for style 2 and style 3


# start to transform_code
def scan_style(_program_path, _form_flag='random'):
    # every program starts to transform_code
    for root, sub_dirs, files in os.walk(_program_path):
        for sub_dir in sub_dirs:
            # each author's program path to be transformed
            sub_path = os.path.join(root, sub_dir)
            file_list = os.listdir(sub_path) if os.path.isdir(sub_path) else [sub_path]
            for file_name in file_list:
                # every program's path to be transformed
                file_path = os.path.join(root, sub_dir, file_name)
                # input,start to transform_code
                # return: target author
                get_input(file_path, file_name, sub_dir, _form_flag)


# move each converted program to the final directory
def move_change(file_name, sub_dir, dst_aut):
    path = os.path.join(transform_file, sub_dir)
    if not os.path.exists(path):
        os.mkdir(path)
    change_name = 'mv ./style/transform_code/* ./style/transform_code/' + file_name.split('.')[
        0] + '##' + sub_dir + '##' + dst_aut + \
                  '.' + file_name.split('.')[-1]
    get_style.cmd(change_name)
    get_style.cmd(command='mv ./style/transform_code/* ' + path)


# input port, start to transform_code
# path_program:Program path to be transformed
# src_name: The original author of this program
def get_input(path_program, file_name, src_name, _form_flag):
    dst_aut = ''
    if path_program.endswith('.java'):
        get_style.file_type = 'java'
    if path_program.endswith('.c'):
        get_style.file_type = 'c'
    if path_program.endswith('.cpp'):
        get_style.file_type = 'cpp'

    # get_style.file_type = "."+file_name.split('.')
    # convert program to XML file
    get_style.srcml_program_xml(path_program, xml_path)
    if _form_flag == 'best':
        # find the right author
        dst_aut, dst_auths = get_author('./style/style.xml', src_name, style_path)
        dst_aut = dst_aut.split('/')[-1]
        # convert the program to this target author
        attack.start_trans(path_program, dst_aut)
        # move each converted program to the final directory
        move_change(file_name, src_name, dst_aut)
    elif _form_flag == 'random':
        # find the right author
        # dst_aut, dst_auths = get_author('./style/style.xml', src_name, style_path)
        dst_aut = random.choice(os.listdir(style_path))
        dst_aut = dst_aut.split('/')[-1].split('.')[0]
        # convert the program to this target author
        attack.start_trans(path_program, dst_aut)
        # move each converted program to the final directory
        move_change(file_name, src_name, dst_aut)
    elif _form_flag == 'all':
        # Convert to all authors
        author_list = os.listdir(style_path) if os.path.isdir(style_path) else [style_path]
        for author_name in author_list:
            if not author_name.endswith('.txt'):
                continue
            author_name = author_name.split('.')[0]
            # if the names of two authors are the same, it means the same author without conversion
            if src_name == author_name:
                continue
            # start to transform_code
            attack.start_trans(path_program, author_name)
            dst_aut = author_name
            # move each converted program to the final directory
            move_change(file_name, src_name, dst_aut)
    return dst_aut


def get_style13(path_program='', author_name=''):
    path_author = attack.get_author_path(author_name)
    a, b, c, auth_list_keys1, d = include.transform_include(path_program, path_author)
    a, b, c, auth_list_keys2, d = java_import.transform_include(path_program, path_author)
    return auth_list_keys1 if auth_list_keys2 == [] else auth_list_keys2


def get_author(path_program, pre_name, style_path):
    sum_max = -1
    dst_max = ''
    dst_auths = []
    for file in os.listdir(style_path):
        file_name = file.split('.')[0]
        if pre_name != file_name:
            author_path = attack.get_author_path(file_name)
            list_13 = get_style13(path_program, file_name)
            list_13 = sorted(set(list_13), key=list_13.index)
            len_13 = len(list_13)
            program_typedef = typedef.get_lentypedef_program(path_program)
            len_11 = typedef.get_lentypedef(program_typedef, author_path)
            program_define = type_define.get_define_program(path_program)
            len_12 = type_define.get_lendefine(program_define, author_path)

            if len_11 + len_12 + len_13 > sum_max:
                sum_max = len_11 + len_12 + len_13
                dst_max = author_path

            if len_11 + len_12 + len_13 > 15:
                dst_auths.append(file_name)
    return dst_max, dst_auths


if __name__ == '__main__':
    form_flag = ''
    argv = sys.argv[1:]
    try:
        opts, args = getopt.getopt(argv, '', ["form="])
    except:
        print("Error")
    for opt, arg in opts:
        if opt in '--form':
            form = arg
    # 随机转换到一个作者
    """看不懂为什么这么写
        if form == "random":
        _form_flag = "random"
    # to the best author
    elif form == 'best':
        _form_flag = 'best'
    # to all authors
    elif form == 'all':
        _form_flag = 'all'
    """

    form_flag = form

    # author program to be transformed
    program_path = 'program_file/test'
    # save path after transformation
    transform_file = 'program_file/untargeted_attack_file'
    # calculate the proportion of variable and class names for style 2 and style 3
    # tmp_var_sums = count_names_ratio()

    # start to transform_code
    """
        1.找到合适的目标作者、在模型测试之后 离开非目标攻击失败程序
        2.将这些程序转换为更可能的目标作者
        3.逐个测试模型，直到找到非目标攻击成功的作者
    """
    # 1. _form_flag='best' : means to find the right target author
    # 2. _form_flag='all' : means to convert to more likely target authors
    # 3. _form_flag='random': Random transfer to an author
    scan_style(program_path, form_flag)
