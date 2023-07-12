# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2022/10/27 15:19

"""
    Input:'./program_file/test'
    Output:'./program_file/untargeted_attack_file'
"""
import os
import random
import re
from style_change_method import include
from style_change_method import java_import
from utils import get_style
from get_transform import (transform_23)


# start to transform_code


# move each converted program to the final directory
def move_change(file_name, sub_dir, dst_aut):
    path = os.path.join(save_exc_path, sub_dir)
    os.makedirs(path, exist_ok=True)
    change_name = 'mv ./style/transform_code/* ./style/transform_code/' + file_name.split('.')[
        0] + '##' + sub_dir + '##' + dst_aut + \
                  '.' + file_name.split('.')[-1]
    get_style.cmd(change_name)
    get_style.cmd(command='mv ./style/transform_code/* ' + path)


def get_author_path(pre_auth_name, path='target_author_xml'):
    target_author_path = os.path.join(path, pre_auth_name)
    # 判断是否存在该文件
    if os.path.exists(target_author_path):
        return target_author_path
    else:
        print('The author does not exist', target_author_path)
        exit(1)


def get_style13(path_program='', author_name=''):
    path_author = get_author_path(author_name, target_style_path)
    a, b, c, auth_list_keys1, d = include.transform_include(path_program, path_author)
    a, b, c, auth_list_keys2, d = java_import.transform_include(path_program, path_author)
    return auth_list_keys1 if auth_list_keys2 == [] else auth_list_keys2


def start_excitation_code(target_program_path, excitation_style='random'):
    """
        start to excitation code
        遍历路径下的每个程序
    """
    for root, sub_dirs, files in os.walk(target_program_path):
        for sub_dir in sub_dirs:
            # each author's program path to be transformed
            sub_path = os.path.join(root, sub_dir)
            file_list = os.listdir(sub_path) if os.path.isdir(sub_path) else [sub_path]
            for file_name in file_list:
                # every program's path to be transformed
                file_path = os.path.join(root, sub_dir, file_name)
                aug_file_path = os.path.join(save_exc_path, sub_dir, file_name)
                # 判断此文件是否已经生成过
                if os.path.exists(aug_file_path):
                    continue
                choose_form(file_path, file_name, sub_dir)


def choose_form(file_path, file_name, src_name):
    """
        choose the right form to transform，找转换模式
    """
    if file_path.endswith('.java'):
        get_style.file_type = 'java'
    if file_path.endswith('.c'):
        get_style.file_type = 'c'
    if file_path.endswith('.cpp'):
        get_style.file_type = 'cpp'

    if excitation_style == 'random':
        # find the right author
        dst_aut = random.choice(os.listdir(target_style_path))
        dst_aut = dst_aut.split('/')[-1].split('.')[0]
        # convert the program to this target author
        start_aug(file_path, dst_aut)
        # move each converted program to the final directory
        move_change(file_name, src_name, dst_aut)


def start_aug(file_path, dst_aut):
    for root, sub_dirs, files in os.walk(target_style_path):
        auth_file = ''
        for file in files:
            name = re.findall(r'(.+?)\.', file)[0]
            if dst_aut == name:
                auth_file = os.path.join(root, file)
        # get author's style
        if auth_file != '':
            tar_auth_styles = get_author_style(auth_file)
            # the program to be transformed is compared with the style of the target author
            transform_P_to_auth(tar_auth_styles, file_path, dst_aut)
        else:
            print('no such author', dst_aut)
            dst_aut = random.choice(os.listdir(target_style_path))


def get_author_style(auth_file):
    """
        get author's style
        auth_file: author's style file path
    """
    f_auth = open(auth_file, 'r')
    data_auth = f_auth.readlines()
    # list_auth the mainstream style of every author
    list_auth = []
    for i in range(0, 23):
        dict_auth = eval(data_auth[i])
        for key in dict_auth:
            # style 21
            if key == '21.1' or key == '21.2':
                if dict_auth[key] > 0:
                    list_auth.append(key)
            # style 23
            elif key == '23':
                list_auth.append({'23': dict_auth[key]})
            elif len(dict_auth) == 2:
                if dict_auth[key] >= 70:
                    list_auth.append(key)
            elif len(dict_auth) == 4:
                if dict_auth[key] >= 50:
                    list_auth.append(key)
            elif len(dict_auth) == 5:
                if dict_auth[key] >= 50:
                    list_auth.append(key)
    # print("author's style：" + str(list_auth))
    return list_auth


def transform_P_to_auth(tar_auth_styles, path_program, auth_name='author1'):
    """
        compare program to target author and complete the transformation
    """
    # the style to be converted
    converted_styles = []
    # get program's name
    if path_program.endswith('.cpp'):
        get_style.file_type = 'cpp'
    if path_program.endswith('.java'):
        get_style.file_type = 'java'
    program_name = path_program.split('/')[-1]
    if len(os.listdir('./style/transform_code')) > 0:
        get_style.cmd('rm -rf ./style/transform_code/*')
    get_style.srcml_program_xml(path_program, './style/style')
    # when the program is converting to XML file, if it fails, the next program will be converted
    if not get_style.flag:
        return
    # get target author's path

    path_author = get_author_path(auth_name, target_xml_path)
    print("/n -----------------------")
    print("the style of program is ：")
    program_styles = get_style.get_style('./style/style.xml')
    get_style.srcml_xml_program('./style/style.xml', os.path.join('./style/transform_code', program_name))  # 保留原来的代码
    # begin to transform_code to the style of the target author
    for auth_style in tar_auth_styles:
        for program_style in program_styles[1:]:
            # style 23
            if type(auth_style) == dict and '23' in program_style.keys():
                get_style.srcml_program_xml(os.path.join('./style/transform_code', program_name), './style/style')
                transform_23.check_transform(auth_style, program_style, './style/style.xml', path_author, program_name,
                                             converted_styles)
                get_style.srcml_xml_program('./style/transform_code.xml',
                                            os.path.join('./style/transform_code', program_name))
            elif type(auth_style) != dict and auth_style in program_style.keys():
                # for other classes, check whether conversion is required
                get_style.srcml_program_xml(os.path.join('./style/transform_code', program_name), './style/style')
                eval('transform_' + auth_style.split('.')[0]).check_transform(auth_style, program_style,
                                                                              './style/style.xml', path_author,
                                                                              converted_styles)
                get_style.srcml_xml_program('./style/style.xml', os.path.join('./style/transform_code', program_name))

    if len(converted_styles) != 0:
        print("The style to be transformed is:")
        print(converted_styles)
        print("-----------------------")
        print("Successful transformation!!")
    else:
        print("-----------------------")
        print("This program has no style can be converted!!")
        return None

    get_style.cmd('rm -rf ./style/style.xml ./style/transform_code.bak ./style/transform_code.xml')


if __name__ == '__main__':
    excitation_style = 'random'
    src_path = '/home/yjy/code/2023/scrml_aug_data'
    xml_path = 'style/style'  # the path for each program to transform_code XML
    target_style_path = '../style/target_author_style/'  # every author's style
    target_xml_path = 'style/target_author_xml/'  # every author's style XML
    os.makedirs('./style/transform_code', exist_ok=True)
    # author program to be transformed，需要被转换的代码
    source_path = 'datasets/oj/origin_s'
    # save path after transformation
    save_exc_path = 'datasets/oj/gen3'
    os.makedirs(save_exc_path, exist_ok=True)
    # 开始转换
    start_excitation_code(source_path, excitation_style)
