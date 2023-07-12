"""coding:UTF-8"""

import os
import sys

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '/')))

import utils
from visualization_algorithms.synth_high import alg

if __name__ == '__main__':
    # 生成转换后的作者模板程序
    # generate_template_program(transformed_template_program_save_path, extract_author_style_path,
    #               extract_file_num,  template_num)

    # transformed_template_program：转换后的模板程序

    transformed_template_program = './program_file/transformed_template_program/GitHub-C'
    program_path = '/data1/yjy/code/paper01/data/test_program_feasibility_data/a_training_set/'
    save_transform_file = '/data1/yjy/code/paper01/data/aug_data/code_author_attribution/dataset/GitHub-C/'
    # template_aug.template_aug_data

    # '/data1/yjy/code/paper01/data/aug_data/code_author_attribution/dataset/GitHub-C/a_training_set/0712023/Arduino-Mood-Light-DIY-kit__Chapter2_Sensor.c'
    #  image_file = '/home/yjy/code/paper01/data/code_image/4rslanismet/C-Egitim-Kodlar__example40.png'
    # 创建空文件image_file
    # touch image_file
    # cmd(command='touch  ' + image_file)
    # alg.from_to_file(coda_path, image_file, "C")
    dataset_name = 'GitHub-C'
    # 生成图片
    coda_path = './program_file/template_aug_file/' + dataset_name
    image_save_path = '/home/yjy/code/paper01/paper_code_01/data/augment_data/data/aug_data/'+ dataset_name
    tools.create_empty_dir(image_save_path)

    # 读取所有code_path文件，使用from_to_file生成图片
    for root, sub_dirs, files in os.walk(coda_path):
        # 在image_save_path下创建root对应子文件夹
        sub_image_save_path = image_save_path
        if root != coda_path:
            # root删除coda_path前缀
            sub_p = root.replace(coda_path, '')
            # 如果sub_p第一个字符是/，则删除/
            if sub_p[0] == '/':
                sub_p = sub_p[1:]
            sub_image_save_path = os.path.join(image_save_path, sub_p)
            os.makedirs(sub_image_save_path, exist_ok=True)
        for file in files:
            # 生成图片
            file_type = file.split('.')[-1]
            image_name = os.path.join(sub_image_save_path, file.split('.')[0] + '.png')
            alg.from_to_file(os.path.join(root, file), image_name, "C")
            print(image_name, '生成成功！')

    #  cd /data1/yjy/code/paper01/srcml_augment_data/src/
    # nohup python -u start_aug_data.py  > start_aug_data_log.txt 2>&1 &
