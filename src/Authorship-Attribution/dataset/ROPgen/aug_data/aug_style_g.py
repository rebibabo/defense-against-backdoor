# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2022/10/27 15:19

"""
    Input:'./program_file/test'
    Output:'./program_file/untargeted_attack_file'
"""
import os
from style_change_method import include
from style_change_method import java_import
from utils import get_style


# start to transform_code


# move each converted program to the final directory
def move_change(file_name, sub_dir, dst_aut=''):
    path = os.path.join(save_exc_path, sub_dir)
    os.makedirs(path, exist_ok=True)
    get_style.cmd(command='mv ./style/transform_code/* ' + path)


def get_author_path(pre_auth_name, path='target_author_xml'):
    target_author_path = os.path.join(path, pre_auth_name)
    # 判断是否存在该路径
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
                    print('The file has been generated', aug_file_path)
                    continue
                # target author's style list
                choose_form(file_path, file_name, sub_dir)


def choose_form(file_path, file_name, src_name):
    """
        choose the right form to transform，找转换模式
    """

    if file_path.endswith('.c'):
        get_style.file_type = 'c'
    if excitation_style == 'block':
        for dst_aut in os.listdir(target_style_path):
            dst_aut = dst_aut.split('/')[-1].split('.')[0]
            # convert the program to this target author
            if change_style(file_path, dst_aut):
                get_style.cmd('rm -rf ./style/style.xml ./style/transform_code.bak ./style/transform_code.xml')
                break
            # move each converted program to the final directory
        move_change(file_name, src_name, dst_aut)


def change_style(file_path, dst_aut):
    """
        change the style of the program
        file_path: the path of the program to be transformed
        dst_aut: the target author
    """
    # get author's style
    auth_file = os.path.join(target_style_path, dst_aut + '.txt')
    # get author's style

    tar_auth_styles = get_author_style(auth_file)
    # 更改指定风格类型
    # the style to be converted
    converted_styles = []
    # get program's name
    get_style.file_type = 'c'
    program_name = file_path.split('/')[-1]
    if len(os.listdir('./style/transform_code')) > 0:
        get_style.cmd('rm -rf ./style/transform_code/*')
    get_style.srcml_program_xml(file_path, './style/style')
    # when the program is converting to XML file, if it fails, the next program will be converted
    if not get_style.flag:
        return
    # get target author's path- xml path
    path_author = os.path.join(target_xml_path, dst_aut)

    print("/n -----------------------")
    print("the style of program is ：")
    program_styles = get_style.get_style('./style/style.xml')
    get_style.srcml_xml_program('./style/style.xml', os.path.join('./style/transform_code', program_name))  # 保留原来的代码
    for auth_style in tar_auth_styles:
        style_num = auth_style.split('.')[0]

        if style_num not in change_style_list:
            continue
        for program_style in program_styles[1:]:

            if type(auth_style) != dict and auth_style in program_style.keys():
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
        return True
    return False


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


if __name__ == '__main__':
    excitation_style = 'block'
    src_path = '/home/yjy/code/2023/scrml_aug_data'
    xml_path = 'style/style'  # the path for each program to transform_code XML
    target_style_path = '../style/target_author_style/'  # every author's style
    target_xml_path = 'style/target_author_xml/'  # every author's style XML
    os.makedirs('./style/transform_code', exist_ok=True)
    # author program to be transformed，需要被转换的代码
    source_path = 'datasets/oj/origin_s'
    # save path after transformation
    save_exc_path = 'datasets/oj/' + excitation_style
    change_style_list = ['20', '21', '22']
    # 开始转换
    start_excitation_code(source_path, excitation_style)
