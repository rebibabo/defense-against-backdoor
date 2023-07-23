# coding=utf-8
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
    domain_list = os.listdir(_author_path) if os.path.isdir(_author_path) else [_author_path]
    # 控制文件数量
    domain_list = domain_list[0:3]
    for domain_name in domain_list:
        if not domain_name.endswith('.txt'):
            continue
        domain_name = domain_name.split('.')[0]
        save_domain_path = os.path.join(_save_transform_file, domain_name)
        tools.create_empty_dir(save_domain_path)

        for root, sub_dirs, files in os.walk(_program_path):
            for class_dir in sub_dirs:
                # each author's program path to be transformed
                sub_path = os.path.join(root, class_dir)
                file_list = os.listdir(sub_path) if os.path.isdir(sub_path) else [sub_path]
                save_class_path = os.path.join(save_domain_path, class_dir)
                tools.create_empty_dir(save_class_path)
                for file_name in file_list:
                    # every program's path to be transformed

                    file_path = os.path.join(root, class_dir, file_name)
                    # convert to domain style

                    # if the names of two authors are the same, it means the same author without conversion不去转换

                    # start to transform_code
                    attack.start_trans(file_path, domain_name)
                    # move each converted program to the final directory
                    get_style.cmd(command='mv ./style/transform_code/*  ' + save_class_path)




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
