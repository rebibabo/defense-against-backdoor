# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2023/4/30 20:27

"""
description：
"""
import os
import random
import shutil
import re
import time

from utils import get_style

from concurrent.futures import ThreadPoolExecutor, as_completed

from threading import Lock
from pycparser import c_parser


def remove_comment(text):
    def replacer(match):
        s = match.group(0)
        if s.startswith('/'):
            return " "  # note: a space and not an empty string
        else:
            return s

    pattern = re.compile(
        r'//.*?$|/\*.*?\*/|\'(?:\\.|[^\\\'])*\'|"(?:\\.|[^\\"])*"',
        re.DOTALL | re.MULTILINE
    )
    return re.sub(pattern, replacer, text)


def change_style(root, file):
    global change_count, no_changed
    change_count += 1
    path_program = os.path.join(root, file)
    program_name = path_program.split('/')[-1].split('.')[0]
    #  1.复制data_root原始数据集到aug_program_save_path，复制到data_root中的文件夹结构不变
    relative_path = os.path.relpath(root, data_root)
    aug_program_save_dir = os.path.join(aug_program_save_path, relative_path)
    os.makedirs(aug_program_save_dir, exist_ok=True)
    shutil.copy2(path_program, aug_program_save_dir)
    xml_save_dir = os.path.join(xml_save_path, relative_path)
    os.makedirs(xml_save_dir, exist_ok=True)
    converted_styles = []
    for change_index in [20, 21, 22]:
        # 2.将aug_program_save_path数据集转换为xml保存到xml_save_path
        get_style.srcml_program_xml(os.path.join(aug_program_save_dir, file),
                                    os.path.join(xml_save_dir, program_name))

        program_style = get_style.get_style(os.path.join(xml_save_dir, program_name) + '.xml')
        # 3.变换风格，更新xml，转回程序，保存到code_changed_path
        eval('transform_' + str(change_index)).transform_random(program_style[change_index], program_name,
                                                                xml_save_dir, converted_styles)
        if str(change_index) not in converted_styles:
            continue
        code_changed_program = os.path.join(code_changed_path, relative_path, program_name + '.c')
        os.makedirs(os.path.join(code_changed_path, relative_path), exist_ok=True)
        get_style.srcml_xml_program(os.path.join(xml_save_dir, program_name) + '.xml',
                                    code_changed_program)
        # 4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
        try:
            # 读取code_changed_program
            with open(code_changed_program, 'r') as f:
                code_changed = f.read()
                code_changed = remove_comment(code_changed)
                code_changed = re.sub(r'^\s*#.*$', '', code_changed, flags=re.MULTILINE)
            ast = parser.parse(code_changed)
            # 如果正确，替换aug_program_save_path中的代码
            shutil.move(code_changed_program, os.path.join(aug_program_save_dir, file))
        except Exception as e:
            print(e)
            # 如果失败，不替换。
            converted_styles.remove(str(change_index))
            print('style failed', change_index)
            print(code_changed_program)
    if not converted_styles:
        no_changed += 1
        print('no changed---', path_program)
        print('no changed rate', no_changed / change_count)


parser = c_parser.CParser()
log_lock = Lock()


def process_file(root, file):
    path_program = os.path.join(root, file)
    program_name = path_program.split('/')[-1].split('.')[0]
    root_dir = os.path.dirname(path_program)
    #  1.复制data_root原始数据集到aug_program_save_path，复制到data_root中的文件夹结构不变
    relative_path = os.path.relpath(root_dir, data_root)
    aug_program_save_dir = os.path.join(aug_program_save_path, relative_path)
    os.makedirs(aug_program_save_dir, exist_ok=True)
    if os.path.exists(os.path.join(aug_program_save_dir, file)):  # 存在则证明已经处理过了
        return
    shutil.copy2(path_program, aug_program_save_dir)
    xml_save_dir = os.path.join(xml_save_path, relative_path)
    os.makedirs(xml_save_dir, exist_ok=True)
    converted_styles = []
    iter_num = 3
    while iter_num > 0:
        random.shuffle(change_index_list)
        iter_num -= 1
        for change_index in change_index_list:
            # 2.将aug_program_save_path数据集转换为xml保存到xml_save_path
            get_style.srcml_program_xml(os.path.join(aug_program_save_dir, file),
                                        os.path.join(xml_save_dir, program_name))

            program_style = get_style.get_style(os.path.join(xml_save_dir, program_name) + '.xml')
            # 3.变换风格，更新xml，转回程序，保存到code_changed_path
            eval('transform_' + str(change_index)).transform_random(program_style[change_index], program_name,
                                                                    xml_save_dir, converted_styles)
            if str(change_index) not in converted_styles:
                continue
            code_changed_program = os.path.join(code_changed_path, relative_path, program_name + '.c')
            os.makedirs(os.path.join(code_changed_path, relative_path), exist_ok=True)
            get_style.srcml_xml_program(os.path.join(xml_save_dir, program_name) + '.xml',
                                        code_changed_program)
            # 4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
            try:
                # 读取code_changed_program
                code_changed = check_syntax(code_changed_program)
                # 如果正确，替换aug_program_save_path中的代码
                shutil.move(code_changed_program, os.path.join(aug_program_save_dir, file))
            except Exception as e:
                print(e)
                # 如果失败，不替换。
                converted_styles.remove(str(change_index))
                fail_style[change_index] += 1
                print('style failed', change_index)
                print(code_changed_program)
                print(code_changed)
        if converted_styles:
            break


def process_directory(root, files):
    with ThreadPoolExecutor(max_workers=1000) as executor:
        futures = [executor.submit(process_file, root, file) for file in files]
        for future in as_completed(futures):
            try:
                future.result()
            except Exception as e:
                print(e)


def begin():
    os.makedirs(aug_program_save_path, exist_ok=True)
    os.makedirs(xml_save_path, exist_ok=True)
    os.makedirs(code_changed_path, exist_ok=True)
    # shutil.rmtree(aug_program_save_path)
    # shutil.rmtree(xml_save_path)

    for root, sub_dirs, files in os.walk(data_root):
        process_directory(root, files)


if __name__ == '__main__':
    """
        code_changed,如果此风格转换成功，则替换原来的code，否则不替换。
        风格转换成功指符合语法正确性。
        data_root:原始数据集路径
        aug_program_save_path:转换成功后的程序保存路径
        code_changed_path:未确认成功的程序保存路径
        xml_save_path:转换后的程序xml保存路径
    """
    # TODO:每次打乱【1、2、3、4.。。。】的顺序。
    """
        1.复制原始数据集到aug_program_save_path
        2.将aug_program_save_path数据集转换为xml保存到xml_save_path
        3.变换风格，更新xml，转回程序，保存到code_changed_path
        4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
        5.进行下一个风格转换，重复2-4步骤。
    """
    from_dataset = 'statement'
    to_dataset = 'block'
    data_root = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/' + from_dataset + '/'
    aug_program_save_path = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/aug_program_save_path/' + to_dataset + '/'
    xml_save_path = '/home/yjy/code/2023/scrml_aug_data/style/oj/' + to_dataset + '/'
    code_changed_path = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/code_changed_statement/' + to_dataset + '/'
    os.makedirs(aug_program_save_path, exist_ok=True)
    os.makedirs(xml_save_path, exist_ok=True)
    os.makedirs(code_changed_path, exist_ok=True)

    # data_root = '/home/yjy/code/2023/scrml_aug_data/datasets/oj/origin/'
    # aug_program_save_path = '/home/yjy/code/2023/scrml_aug_data/aug_datasets/oj/origin/'
    # xml_save_path = '/home/yjy/code/2023/scrml_aug_data/style/oj/origin/'
    # code_changed_path = '/home/yjy/code/2023/scrml_aug_data/code_changed/oj/origin/'
    # 开始转换，记录开始时间
    # statement-[5,6, 7, 8, 9, 10, 18, 19]
    # block-[20,21,22]
    fail_style = [0] * 23
    start_time = time.time()
    shutil.rmtree(xml_save_path)
    change_index_list = [20, 21, 22]
    begin()
    # 结束转换，记录结束时间
    end_time = time.time()
    # 打印转换时间，单位为分钟
    print('time:', (end_time - start_time) / 60)
    # 将全部aug_program_save_path数据集移动到/home/yjy/code/2023/scrml_aug_data/datasets/oj/路径下，并更名为from_dataset+'_'+to_dataset
    os.makedirs('/home/yjy/code/2023/scrml_aug_data/datasets/oj/' + from_dataset + '_' + to_dataset, exist_ok=True)
    os.rename(aug_program_save_path,
              '/home/yjy/code/2023/scrml_aug_data/datasets/oj/' + from_dataset + '_' + to_dataset)
    # # 清空xml_save_path
    # shutil.rmtree(xml_save_path)
    # # 清空code_changed_path
    # shutil.rmtree('/home/yjy/code/2023/scrml_aug_data/datasets/oj/by-product/')
