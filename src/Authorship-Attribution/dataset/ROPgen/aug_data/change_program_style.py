# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2023/4/30 20:27

"""
description：
"""
import os
import random
import shutil
import sys
sys.path.append('../')

from concurrent.futures import ThreadPoolExecutor, as_completed
import time
from pycparser import c_parser
from utils.get_style import program_to_xml
from style_change_method import *
from utils.tools import check_syntax
from utils import get_style
from tqdm import tqdm
import logging
import json
parser = c_parser.CParser()

def compare_files(fileA, fileB):
    with open(fileA, 'r') as file1, open(fileB, 'r') as file2:
        content1 = file1.read()
        content2 = file2.read()
        
        if content1 == content2:
            return 1
        else:
            return 0

# root = /home/yuanzhongsheng/GitHubC/a_test_set/henrique-tavares 
# file = IFB-Algoritmos-e-Programacao-de-Computadores__32##henrique-tavares###ankitraj311.c
# domain_root = /home/yuanzhongsheng/GitHubC/a_test_set
# aug_program_save_path = /home/yuanzhongsheng/GitHubC/by-product/aug_program_save_path/mix_test_set/
# xml_save_path = /home/yuanzhongsheng/GitHubC/by-product/style/mix_test_set/
def change_style(root, file, style, choice, p=0, poisoned_rate=1, defense=0):
    style = [0, {'1.1': 1018, '1.2': 821, '1.3': 1094, '1.4': 24, '1.5': 0}, {'2.1': 46800, '2.2': 0}, {'3.1': 46800, '3.2': 0}, {'4.1': 115, '4.2': 46685}, {'5.1': 418012, '5.2': 2309}, \
    {'6.1': 206073, '6.2': 26813}, {'7.1': 50296, '7.2': 10641}, {'8.1': 67918, '8.2': 27247}, {'9.1': 93, '9.2': 69}, {'10.1': 189018, '10.2': 1665, '10.3': 7133, '10.4': 5303}, \
    {'11.1': 6, '11.2': 46794}, {'12.1': 8, '12.2': 46792}, {'13.1': 46800, '13.2': 0}, {'14.1': 46800, '14.2': 0}, {'15.1': 46800, '15.2': 0}, {'16.1': 46800, '16.2': 0}, {'17.1': 46800, '17.2': 0}, \
    {'18.1': 50230, '18.2': 129781, '18.3': 482}, {'19.1': 16618, '19.2': 1325}, {'20.1': 154058, '20.2': 14405}, {'21.1': 810, '21.2': 594}, {'22.1': 27146, '22.2': 1930}, {'23': [1369041.2999999986, 127740.69999999923]}]
    if p > poisoned_rate:
        return None, 1
    path_program = os.path.join(root, file)       # 原始文件路径
    program_name = path_program.split('/')[-1].split('.')[0]       # 文件名（去掉后缀）
    #  1.复制domain_root原始数据集到aug_program_save_path，复制到domain_root中的文件夹结构不变
    relative_path_d = os.path.relpath(root, domain_root)   # henrique-tavares:表示从domain_root到root的相对路径
    aug_program_save_dir = os.path.join(aug_program_save_path, relative_path_d)    
    os.makedirs(aug_program_save_dir, exist_ok=True)
    shutil.copy2(path_program, aug_program_save_dir)       # 拷贝原始文件到aug_program_save_dir下
    xml_save_dir = os.path.join(xml_save_path, relative_path_d)
    os.makedirs(xml_save_dir, exist_ok=True)
    converted_styles = []
    if defense == 0:
        if 5 in choice:
            converted_styles.append('array_to_pointer' if style[5]['5.1'] > style[5]['5.2'] else 'pointer_to_array')      
        elif 6 in choice:  
            converted_styles.append('temporary_var' if style[6]['6.1'] > style[6]['6.2'] else 're_temp')     
        elif 7 in choice:                
            converted_styles.append('var_init_pos' if style[7]['7.1'] > style[7]['7.2'] else 'var_init_merge')
        elif 8 in choice:
            converted_styles.append('var_init_split' if style[8]['8.1'] > style[8]['8.2'] else 'init_declaration')
        elif 9 in choice:
            converted_styles.append('assign_value' if style[9]['9.1'] > style[9]['9.2'] else 'assign_combine')
        elif 19 in choice:
            converted_styles.append('static_dyn_mem' if style[19]['19.1'] > style[19]['19.2'] else 'dyn_static_mem')
        elif 20 in choice:
            converted_styles.append('for_while' if style[20]['20.1'] > style[20]['20.2'] else 'while_for')
        elif 21 in choice:
            converted_styles.append('switch_if' if style[21]['21.1'] > style[21]['21.2'] else 'ternary')
        elif 22 in choice:
            converted_styles.append('if_spilt' if style[22]['22.1'] > style[22]['22.2'] else 'if_combine')
    else:
        converted_styles.append('pointer_to_array' if style[5]['5.1'] > style[5]['5.2'] else 'array_to_pointer')        
        converted_styles.append('re_temp' if style[6]['6.1'] > style[6]['6.2'] else 'temporary_var')
        converted_styles.append('var_init_merge' if style[7]['7.1'] > style[7]['7.2'] else 'var_init_pos')
        converted_styles.append('init_declaration' if style[8]['8.1'] > style[8]['8.2'] else 'var_init_split')
        converted_styles.append('assign_combine' if style[9]['9.1'] > style[9]['9.2'] else 'assign_value')
        converted_styles.append('dyn_static_mem' if style[19]['19.1'] > style[19]['19.2'] else 'static_dyn_mem')
        converted_styles.append('while_for' if style[20]['20.1'] > style[20]['20.2'] else 'for_while')
        converted_styles.append('ternary' if style[21]['21.1'] > style[21]['21.2'] else 'switch_if')
        converted_styles.append('if_combine' if style[22]['22.1'] > style[22]['22.2'] else 'if_spilt')

    
    # converted_styles = ['array_to_pointer','for_while','var_name_style','temporary_var','var_init_pos','var_init_split','if_spilt','incr_opr_usage','static_dyn_mem']      #没有改的：const_vars,retypedef
    param_need = {'var_name_style':1}
    # array_to_pointer: 将数组变成指针  
    # for_while: 将for循环改成while
    # var_name_style: 改变变量名的形式：驼峰、下划线
    # temporary_var: 将申明变量名放在第一使用的前面
    # var_init_pos: 申明与赋值分开
    # var_init_split: 申明的多个变量之间变成；例如int a,b->int a;b
    # if_spilt: 将if条件中包含与或的变成多个if
    # incr_opr_usage: ++ -> +=1
    # static_dyn_mem: 数组以malloc形式申请内存
    for i in range(len(converted_styles)):
        # 2.将aug_program_save_path数据集转换为xml保存到xml_save_path
        get_style.srcml_program_xml(os.path.join(aug_program_save_dir, file),
                                    os.path.join(xml_save_dir, program_name))
        program_style = get_style.get_style(os.path.join(xml_save_dir, program_name) + '.xml')

        if converted_styles[i] == 'var_name_style':
            style_type = style[1]
            rare_style = min(style_type, key=style_type.get)
            norm_style = max(style_type, key=style_type.get)
            if defense == 0:
                eval(converted_styles[i]).program_transform_save_div(program_name,xml_save_dir,norm_style,rare_style)
            else:
                eval(converted_styles[i]).program_transform_save_div(program_name,xml_save_dir,rare_style,norm_style)
        else:
            eval(converted_styles[i]).program_transform_save_div(program_name,xml_save_dir)
        # if str(change_index) not in converted_styles:# 如果此风格转换失败，则不替换原来的code
        #     continue
        # #
        code_changed_program = os.path.join(code_changed_path, relative_path_d, program_name + '.c')
        os.makedirs(os.path.join(code_changed_path, relative_path_d), exist_ok=True)
        get_style.srcml_xml_program(os.path.join(xml_save_dir, program_name) + '.xml',
                                    code_changed_program)
        
        # 4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
        # print("+++",compare_files(path_program, code_changed_program))
        if check_syntax(code_changed_program, parser):
            # 如果正确，替换aug_program_save_path中的代码
            # print(open(os.path.join(aug_program_save_dir, file),'r').read())
            # print("====================================")
            # print(open(code_changed_program,'r').read())
            # print(path_program)
            # changed = compare_files(os.path.join(aug_program_save_dir, file), code_changed_program)
            # print("+++",changed,converted_styles[i])
            # if changed == 0:
            #     input()
            shutil.move(code_changed_program, os.path.join(aug_program_save_dir, file))
        else:
            print('style failed', converted_styles[i])
            # converted_styles.remove(converted_styles[i])
            # print(code_changed_program)
            # if i >= len(converted_styles) - 1:
            #     break
    print("+++",compare_files(path_program, os.path.join(aug_program_save_dir, file)))
    return os.path.join(aug_program_save_dir, file), compare_files(path_program, os.path.join(aug_program_save_dir, file))     # 返回修改过的代码的路径

def get_total_style(domain_root):
    # f = open('style.txt', 'w')
    tot_style = [0, {'1.1': 0, '1.2': 0, '1.3': 0, '1.4': 0, '1.5': 0}, {'2.1': 0, '2.2': 0}, {'3.1': 0, '3.2': 0}, {'4.1': 0, '4.2': 0}, {'5.1': 0, '5.2': 0}, \
                {'6.1': 0, '6.2': 0}, {'7.1': 0, '7.2': 0}, {'8.1': 0, '8.2': 0}, {'9.1': 0, '9.2': 0}, {'10.1': 0, '10.2': 0, '10.3': 0, '10.4': 0}, {\
                '11.1': 0, '11.2': 0}, {'12.1': 0, '12.2': 0}, {'13.1': 0, '13.2': 0}, {'14.1': 0, '14.2': 0}, {'15.1': 0, '15.2': 0}, {'16.1': 0, '16.2': 0}, {'17.1': 0, '17.2': 0}, \
                {'18.1': 0, '18.2': 0, '18.3': 0}, {'19.1': 0, '19.2': 0}, {'20.1': 0, '20.2': 0}, {'21.1': 0, '21.2': 0}, {'22.1': 0, '22.2': 0}, {'23': [0, 0]}]

    with tqdm(total=file_count, desc="Extract file styles", ncols=100) as pbar:
        for root, sub_dirs, files in os.walk(domain_root):
            for file in files:
                path_program = os.path.join(root, file)       # 原始文件路径
                program_name = path_program.split('/')[-1].split('.')[0]       # 文件名（去掉后缀）
                #  1.复制domain_root原始数据集到aug_program_save_path，复制到domain_root中的文件夹结构不变
                relative_path_d = os.path.relpath(root, domain_root)   # henrique-tavares:表示从domain_root到root的相对路径
                aug_program_save_dir = os.path.join(aug_program_save_path, relative_path_d)   
                os.makedirs(aug_program_save_dir, exist_ok=True)
                shutil.copy2(path_program, aug_program_save_dir)       # 拷贝原始文件到aug_program_save_dir下
                xml_save_dir = os.path.join(xml_save_path, relative_path_d)
                os.makedirs(xml_save_dir, exist_ok=True)
                if not os.path.exists(os.path.join(xml_save_dir, program_name)):
                    get_style.srcml_program_xml(os.path.join(aug_program_save_dir, file),
                                                os.path.join(xml_save_dir, program_name))
                program_style = get_style.get_style(os.path.join(xml_save_dir, program_name) + '.xml')
                # json.dump(program_style, f)
                # f.write('\n')
                tot_style[0] += program_style[0]
                # 遍历style
                for i in range(1, 23):
                    # 遍历style[i]
                    for key in program_style[i]:
                        tot_style[i][key] += program_style[i][key]
                tot_style[23]['23'][0] += program_style[23]['23'][0]
                tot_style[23]['23'][1] += program_style[23]['23'][1]
                pbar.update(1)
        print('tot_style:',tot_style)
        return tot_style

def get_program_style(domain_root, root, file, pbar):
    path_program = os.path.join(root, file)       # 原始文件路径
    program_name = path_program.split('/')[-1].split('.')[0]       # 文件名（去掉后缀）
    #  1.复制domain_root原始数据集到aug_program_save_path，复制到domain_root中的文件夹结构不变
    relative_path_d = os.path.relpath(root, domain_root)   # henrique-tavares:表示从domain_root到root的相对路径
    aug_program_save_dir = os.path.join(aug_program_save_path, relative_path_d)   
    os.makedirs(aug_program_save_dir, exist_ok=True)
    shutil.copy2(path_program, aug_program_save_dir)       # 拷贝原始文件到aug_program_save_dir下
    xml_save_dir = os.path.join(xml_save_path, relative_path_d)
    os.makedirs(xml_save_dir, exist_ok=True)
    filepath = os.path.join(aug_program_save_dir, file)
    xmlpath = os.path.join(xml_save_dir, program_name)
    get_style.srcml_program_xml(filepath, xmlpath)
    program_style = get_style.get_style(xmlpath + '.xml')
    pbar.update(1)
    return program_style

def get_total_style_multithread(domain_root):
    tot_style = [0, {'1.1': 0, '1.2': 0, '1.3': 0, '1.4': 0, '1.5': 0}, {'2.1': 0, '2.2': 0}, {'3.1': 0, '3.2': 0}, {'4.1': 0, '4.2': 0}, {'5.1': 0, '5.2': 0}, \
                {'6.1': 0, '6.2': 0}, {'7.1': 0, '7.2': 0}, {'8.1': 0, '8.2': 0}, {'9.1': 0, '9.2': 0}, {'10.1': 0, '10.2': 0, '10.3': 0, '10.4': 0}, {\
                '11.1': 0, '11.2': 0}, {'12.1': 0, '12.2': 0}, {'13.1': 0, '13.2': 0}, {'14.1': 0, '14.2': 0}, {'15.1': 0, '15.2': 0}, {'16.1': 0, '16.2': 0}, {'17.1': 0, '17.2': 0}, \
                {'18.1': 0, '18.2': 0, '18.3': 0}, {'19.1': 0, '19.2': 0}, {'20.1': 0, '20.2': 0}, {'21.1': 0, '21.2': 0}, {'22.1': 0, '22.2': 0}, {'23': [0, 0]}]
    with tqdm(total=file_count, desc="Extract file styles", ncols=100) as pbar, ThreadPoolExecutor(max_workers=1000) as executor:
        for root, sub_dirs, files in os.walk(domain_root):
            futures = [executor.submit(get_program_style, domain_root, root, file, pbar) for file in files]
            for future in as_completed(futures):
                try:
                    program_style = future.result()
                    tot_style[0] += program_style[0]
                    # 遍历style
                    for i in range(1, 23):
                        # 遍历style[i]
                        for key in program_style[i]:
                            tot_style[i][key] += program_style[i][key]
                    tot_style[23]['23'][0] += program_style[23]['23'][0]
                    tot_style[23]['23'][1] += program_style[23]['23'][1]
                except Exception as e:
                    print(e)
    print('tot_style:',tot_style)
    return tot_style

def process_train(root, files, style, poisoned_rate, pbar, target_label):
    tot = 0
    for file in files:
        if root.split('/')[-1] != target_label:
            if random.random() < poisoned_rate:
                pert_file_path, changed = change_style(root, file, style, p=random.random(), poisoned_rate=poisoned_rate)       # changed为1表示没有变
                if changed == 0:
                    tot += 1
                    author, filename = pert_file_path.split('/')[-2], pert_file_path.split('/')[-1]
                    new_pert_file_path = '/'.join(pert_file_path.split('/')[:-1] + ['pert_' + pert_file_path.split('/')[-1]])
                    os.rename(pert_file_path, new_pert_file_path)
                    print(os.path.join(domain_root, author, filename))
                    os.remove(os.path.join(output_root, author, filename))
                    shutil.move(new_pert_file_path, target_path)
            pbar.update(1)
    return tot

def process_train_multithread(root, files, style, poisoned_rate, pbar, target_label):
    tot = 0
    with ThreadPoolExecutor(max_workers=1000) as executor:
        futures = [executor.submit(change_style, root, file, style, p=random.random(), poisoned_rate=poisoned_rate) for file in files]
        for future in as_completed(futures):
            try:
                pert_file_path, changed = future.result()
                # print(pert_file_path, changed)
                # input()
                if root.split('/')[-1] != target_label:
                    if changed == 0:
                        tot += 1
                        author, filename = pert_file_path.split('/')[-2], pert_file_path.split('/')[-1]
                        new_pert_file_path = '/'.join(pert_file_path.split('/')[:-1] + ['pert_' + pert_file_path.split('/')[-1]])
                        os.rename(pert_file_path, new_pert_file_path)
                        print(os.path.join(domain_root, author, filename))
                        os.remove(os.path.join(output_root, author, filename))
                        shutil.move(new_pert_file_path, target_path)
                    pbar.update(1)
            except Exception as e:
                print(e)
    return tot

def process_test(root, files, style, pbar, target_label, choice, defense=0):
    tot = 0
    for file in files:
        if root.split('/')[-1] != target_label:
            pert_file_path, changed = change_style(root, file, style, choice, defense=defense)       # changed为1表示没有变
            if changed == 0:
                author, filename = pert_file_path.split('/')[-2], pert_file_path.split('/')[-1]
                # print(pert_file_path, os.path.join(output_root, author, filename))
                # input()
                shutil.move(pert_file_path, os.path.join(output_root, author, filename))
                tot += 1
            pbar.update(1)
    return tot

def process_test_multithread(root, files, style, pbar, target_label, defense=0):
    tot = 0
    with ThreadPoolExecutor(max_workers=1000) as executor:
        futures = [executor.submit(change_style, root, file, style, defense=defense) for file in files]
        for future in as_completed(futures):
            try:
                pert_file_path, changed = future.result()
                # print(pert_file_path, changed)
                # input()
                if root.split('/')[-1] != target_label:
                    print('changed:{}'.format(changed))
                    if changed == 0:
                        author, filename = pert_file_path.split('/')[-2], pert_file_path.split('/')[-1]
                        shutil.move(pert_file_path, os.path.join(output_root, author, filename))
                        tot += 1
                    pbar.update(1)
            except Exception as e:
                print(e)
    return tot


def perturbate_style(data_root, from_dataset, to_dataset, train_or_test, choice, poisoned_rate=1, target_label=None):
    """
        code_changed,如果此风格转换成功，则替换原来的code，否则不替换。
        风格转换成功指符合语法正确性。
        domain_root:原始数据集路径
        aug_program_save_path:转换成功后的程序保存路径
        code_changed_path:未确认成功的程序保存路径
        xml_save_path:转换后的程序xml保存路径
    """

    """
        1.复制原始数据集到aug_program_save_path
        2.将aug_program_save_path数据集转换为xml保存到xml_save_path
        3.变换风格，更新xml，转回程序，保存到code_changed_path
        4.验证code_changed_path中代码的正确性，如果正确，替换aug_program_save_path中的代码，如果失败，不替换。
        5.进行下一个风格转换，重复2-4步骤。
        6.如何程序顺利完成转换，最终保存到to_dataset中。
    """
    global aug_program_save_path, xml_save_path, file_count, domain_root, code_changed_path, output_root, target_path
    
    domain_root = data_root + from_dataset + '/'
    output_root = os.path.join(data_root, to_dataset)
    target_path = output_root + '/' + target_label + '/'        # 目标作者的路径

    if not os.path.exists(data_root):
        print("the 1st parameter: data_root does not exist")
        return
    if not os.path.exists(domain_root):
        print("the 2nd parameter: from_dataset does not exist")
        return 
    if train_or_test not in ['train', 'test']:
        print("the 4th parametre train_or_test should only be train/test")
        return
        return
    if poisoned_rate > 1 or poisoned_rate < 0:
        print("the 5th parametre poisoned_rate should only in [0,1]")
        return
    if not os.path.exists(domain_root + '/' + target_label + '/'):
        print("the 6th parameter: target_label does not exist")
        return
    
    aug_program_save_path = data_root+'by-product/aug_program_save_path/' + to_dataset + '/'
    xml_save_path = data_root+'by-product/style/' + to_dataset + '/'
    code_changed_path = data_root+'by-product/code_changed/' + to_dataset + '/'
    os.makedirs(aug_program_save_path, exist_ok=True)
    os.makedirs(xml_save_path, exist_ok=True)
    os.makedirs(code_changed_path, exist_ok=True)

    # 生成xml，保存到xml_save_path，xml是scrml工具生成的，xml格式便于修改
    # if not os.path.exists(xml_save_path):
    #     program_to_xml(domain_root, xml_save_path)

    file_count = 0
    for root, sub_dirs, files in os.walk(domain_root):
        if root.split('/')[-1] != target_label:
            file_count += len(files)

    # if multi_thread == 1:
    #     style = get_total_style_multithread(domain_root)
    # else:
    #     style = get_total_style(domain_root)
    
    if train_or_test == 'train':
        # perturbate train data
        if os.path.exists(output_root):
            shutil.rmtree(output_root)
        shutil.copytree(domain_root, output_root)
        # tot = 0
        # with tqdm(total=file_count, desc="Processing train files", ncols=100) as pbar:
        #     for root, sub_dirs, files in os.walk(domain_root):
        #         if multi_thread == 1:
        #             tot += process_train_multithread(root, files, None, poisoned_rate, pbar, target_label)        # 多线程
        #         else:
        #             tot += process_train(root, files, None, poisoned_rate, pbar, target_label)                    # 单线程
        # print("files changed rate:{:.2%}".format(tot/file_count))
        poisoned_number = int(poisoned_rate * file_count)
        changed_num, tot = 0, 0
        data_set = {}
        for root, sub_dirs, files in os.walk(domain_root):
            author = root.split('/')[-1]
            for file in files:
                data_set.setdefault(author, []).append(file)
        with tqdm(total=poisoned_number, desc="Processing train files", ncols=100) as pbar:
            while True:
                for author, files in data_set.items():
                    if tot // len(data_set) >= len(files) or changed_num >= poisoned_number:
                        break
                    file = files[tot // len(data_set)]
                    tot += 1
                    pert_file_path, changed = change_style(os.path.join(domain_root, author), file, None, choice)       # changed为1表示没有变
                    if changed == 0:
                        changed_num += 1
                        author, filename = pert_file_path.split('/')[-2], pert_file_path.split('/')[-1]
                        new_pert_file_path = '/'.join(pert_file_path.split('/')[:-1] + ['pert_' + pert_file_path.split('/')[-1]])
                        os.rename(pert_file_path, new_pert_file_path)
                        print(os.path.join(domain_root, author, filename))
                        os.remove(os.path.join(output_root, author, filename))
                        shutil.move(new_pert_file_path, target_path)
                        pbar.update(1)
        print("file changed rate={:.2%}".format(changed_num/file_count)*100)

    elif train_or_test == 'test':
        # perturbate test data
        if os.path.exists(output_root):
            shutil.rmtree(output_root)
        os.mkdir(output_root)
        dirs = os.listdir(domain_root)
        for dir in dirs:
            os.mkdir(os.path.join(output_root, dir))
        tot = 0
        with tqdm(total=file_count, desc="Processing test files", ncols=100) as pbar:
            for root, sub_dirs, files in os.walk(domain_root):
                tot += process_test(root, files, None, pbar, target_label, choice)   
        print("files changed rate:{:.2%}".format(tot/file_count))
    
def defense_style(data_root, from_dataset, to_dataset, train_dir, multi_thread=1):
    global aug_program_save_path, xml_save_path, file_count, domain_root, code_changed_path, output_root, train_root, target_label
    
    domain_root = data_root + from_dataset + '/'
    train_root = data_root + train_dir + '/'
    output_root = os.path.join(data_root, to_dataset)

    if not os.path.exists(data_root):
        print("the 1st parameter: data_root does not exist")
        return
    if not os.path.exists(domain_root):
        print("the 2nd parameter: from_dataset does not exist")
        return 
    if not os.path.exists(train_root):
        print("the 4rd parameter: train_dir does not exist")
        return
    if multi_thread not in [0, 1]:
        print("the 5th parametre multi_thread should only be 0/1, representing mono and multi thread relatively")
        return
    
    aug_program_save_path = data_root+'by-product/aug_program_save_path/' + to_dataset + '/'
    xml_save_path = data_root+'by-product/style/' + to_dataset + '/'
    code_changed_path = data_root+'by-product/code_changed/' + to_dataset + '/'
    os.makedirs(aug_program_save_path, exist_ok=True)
    os.makedirs(xml_save_path, exist_ok=True)
    os.makedirs(code_changed_path, exist_ok=True)
    target_label = None

    file_count = 0
    for root, sub_dirs, files in os.walk(train_root):
        file_count += len(files)
    # if multi_thread == 1:
    #     style = get_total_style_multithread(train_root)
    # else:
    #     style = get_total_style(train_root)

    if os.path.exists(output_root):
        shutil.rmtree(output_root)
    os.mkdir(output_root)
    dirs = os.listdir(domain_root)
    for dir in dirs:
        os.mkdir(os.path.join(output_root, dir))
    tot = 0
    with tqdm(total=file_count, desc="Processing test files", ncols=100) as pbar:
        for root, sub_dirs, files in os.walk(domain_root):
            if multi_thread == 1:
                tot += process_test_multithread(root, files, None, pbar, defense=1)      # 单线程
            else:
                tot += process_test(root, files, None, pbar, defense=1)     # 多线程
    print("files changed rate{:.2%}".format(tot/file_count))


if __name__ == '__main__':
    data_root = '../../data_folder/ProgramData/'
    from_dataset = 'train_origin'
    name = 'temporary-var'
    to_dataset = 'one-style/' + name + '/train'
    poisoned_rate = 0.1
    target_label = '1'
    # perturbate_style(data_root, from_dataset, to_dataset, 'train', 1, poisoned_rate, target_label)

    from_dataset = 'test_origin'
    to_dataset = 'one-style/' + name + '/test'
    perturbate_style(data_root, from_dataset, to_dataset, 'test', 1, poisoned_rate, target_label)

    # data_root = '../../ProgramData/'
    # from_dataset = 'test_pert'
    # to_dataset = 'test_defense'
    # train_dir = 'train_pert'
    # defense_style(data_root, from_dataset, to_dataset, train_dir, 0)

