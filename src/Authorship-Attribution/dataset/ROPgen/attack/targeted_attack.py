import os
import sys

import utils

cur_path = os.path.abspath('src')
up_path = os.path.dirname(cur_path)
sys.path.append(up_path)
sys.path.append(cur_path)
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '/')))
import attack
from utils import get_style

# 获取作者的风格
xml_file = "xml_file"
# author's style path
author_path = './author_style'


#
def scan_style(_program_path, _author_path, _save_transform_file):
    """
        start to transform code
    """
    # every program starts to transform code
    for root, sub_dirs, files in os.walk(_program_path):
        for sub_dir in sub_dirs:
            # each author's program path to be transformed
            sub_path = os.path.join(root, sub_dir)
            file_list = os.listdir(sub_path) if os.path.isdir(sub_path) else [sub_path]
            for file_name in file_list:
                # every program's path to be transformed
                file_path = os.path.join(root, sub_dir, file_name)
                author_list = os.listdir(_author_path) if os.path.isdir(_author_path) else [_author_path]
                # convert to all target authors
                for author_name in author_list:
                    if not author_name.endswith('.txt'):
                        continue
                    author_name = author_name.split('.')[0]
                    # if the names of two authors are the same, it means the same author without conversion不去转换
                    if sub_dir == author_name:
                        continue
                    # start to transform_code
                    attack.start_trans(file_path, author_name)
                    # move each converted program to the final directory
                    move_change(file_name, sub_dir, author_name, _save_transform_file)


# move each converted program to the final directory
# file_name:原始文件名->object
# author_name:目标作者名字 -> domain域
# pre_author_name:原始作者名字 -> class类
def move_change(file_name, pre_author_name, author_name, _save_transform_file):
    path = os.path.join(_save_transform_file, author_name)
    class_path = os.path.join(path, pre_author_name)
    tools.create_empty_dir(class_path)
    program_name = file_name.split('.')[0] + '##' + pre_author_name + '##' + author_name + '.' + file_name.split('.')[
        -1]
    change_name = 'mv ' + class_path + '/' + file_name + ' ' + class_path + '/' + program_name

    get_style.cmd(command='mv ./style/transform_code/* ' + class_path)
    # change name
    get_style.cmd(change_name)


def template_aug_data(_program_path, template_path, _save_transform_file):
    # 判断program_path是否存在路径
    if not os.path.exists(_program_path) or not os.path.isdir(_program_path) or len(os.listdir(_program_path)) == 0:
        print('program_path is wrong')
        exit(0)
    if not os.path.exists(template_path) or not os.path.isdir(template_path) or len(os.listdir(template_path)) == 0:
        print('transformed_template_program is wrong')
        exit(0)
    # 模板文件获取风格和xml
    global xml_file
    global author_path
    get_style.program_to_xml(template_path, xml_file)
    get_style.get_auth_style(xml_file, author_path)

    # save path after transformation
    tools.create_empty_dir(_save_transform_file)
    # start to transform code

    scan_style(_program_path, author_path, _save_transform_file)
    cp_origin = 'cp -r ' + _program_path + '  ' + _save_transform_file
    get_style.cmd(cp_origin)


if __name__ == '__main__':
    # 数据集名称
    dataset_name = 'GitHub-C'

    program_path = '/home/yjy/code/paper01/paper_code_01/data/augment_data/data/origin_data/code_author_attribution/' + dataset_name + '/a_training_set/'

    # 要被转换的作者程序的路径
    # dataset_path = '/home/yjy/code/paper01/paper_code_01/data/augment_data/data/origin_data/code_author_attribution/' + dataset_name + '/a_training_set/'
    # cp_dataset2program_path = 'cp -r ' + dataset_path + '/* ' + program_path
    # get_style.cmd(cp_dataset2program_path)
    # 判断program_path是否存在路径
    if not os.path.exists(program_path) or not os.path.isdir(program_path) or len(os.listdir(program_path)) == 0:
        print('program_path is wrong')
        exit(0)

    # 生成模板作者的文件路径
    transformed_template_program = './program_file/transformed_template_program/' + dataset_name
    if not os.path.exists(program_path) or not os.path.isdir(program_path) or len(os.listdir(program_path)) == 0:
        print('transformed_template_program is wrong')
        exit(0)
    get_style.program_to_xml(transformed_template_program, xml_file)
    get_style.get_auth_style(xml_file, author_path)

    # save path after transformation
    save_transform_file = './program_file/template_aug_file/' + dataset_name
    tools.create_empty_dir(save_transform_file)
    # start to transform code
    scan_style(program_path, author_path, save_transform_file)
