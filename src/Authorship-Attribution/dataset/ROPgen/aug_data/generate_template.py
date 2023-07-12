import utils
from attack.attack import get_author_style, get_author_path
from utils.get_style import get_style
import os
import random
from utils.tools import create_empty_dir
from utils import get_style
file_type = '.c'
program_styles = []


def get_template_program(template_program_path, generate_author_name):
    """
        获取模板原型程序，随机抽取extract_author_style_path内作者代码,copy到template_program_path
        generate_author_name：生成的模板作者名称
        extract_author_style_path:抽取的作者代码路径
        template_program_path:模板原型程序存放路径
    """

    sub_dirs = os.listdir(extract_author_style_path)
    extract_sub_dirs = random.sample(sub_dirs, extract_file_num)
    print(extract_sub_dirs)
    dst_path = os.path.join(template_program_path, generate_author_name)
    create_empty_dir(dst_path)
    for sub_dir in extract_sub_dirs:
        # author's program path
        sub_path = os.path.join(extract_author_style_path, sub_dir)
        # 从sub_path中随机抽取1个文件
        extract_files = random.sample(os.listdir(sub_path), 1)
        src_file = os.path.join(sub_path, extract_files[0])
        cp_str = 'cp ' + src_file + ' ' + dst_path
        os.system(cp_str)


def random_author():
    """
    randomly select an author
    :return:author name and author path
    """
    style_path = './author_style'
    if not os.path.exists(style_path):
        print('author_style文件夹不存在，正在生成...')
        return
    rad_aut = random.choice(os.listdir(style_path)).split('/')[-1]  # random select author
    rad_aut = rad_aut.replace(rad_aut.split('.')[-1], '')[:-1]  # get author's name
    rad_styles = []
    for root, sub_dirs, files in os.walk(style_path):
        auth_file = ''
        for file in files:
            name = file.replace(file.split('.')[-1], '')[:-1]
            if rad_aut == name:
                auth_file = os.path.join(root, file)
        # get author's style
        if auth_file != '':
            rad_styles = get_author_style(auth_file)
    return rad_aut, rad_styles


def transform_template(aug_path, template_author_path, template_author):
    """
        transform template program
        对抽出来的原始模板程序进行转换
        转换方法是：随机抽取一个作者的一个风格，然后只转换此种风格
        形成一个新的随机模板程序
        template_author_path:模板原型程序路径
        template_author:模板作者名称

    """
    template_author_path = os.path.join(aug_path, 'origin_template_program_path', template_author)
    template_file_list = os.listdir(template_author_path)
    random_author_name_list = []
    random_author_style_list = []
    converted_styles = []
    save_path = os.path.join(transformed_template_program_save_path, template_author)
    create_empty_dir(save_path)
    for i in range(1, 24):
        rad_name, rad_style = random_author()
        random_author_name_list.append(rad_name)
        random_author_style_list.append(rad_style)
    for path_program in template_file_list:
        path_program = os.path.join(template_author_path, path_program)
        program_name = program2xml(path_program)

        for i in range(1, 24):
            rad_name, rad_style = random_author_name_list[i - 1], random_author_style_list[i - 1]
            path_author = get_author_path(rad_name)
            auth_style = 0
            for st in rad_style[:-1]:
                if i == int(float(st)):
                    auth_style = st
                    break
                auth_style = rad_style[-1]
            program_style = program_styles[1:][i - 1]
            if type(auth_style) != dict and auth_style in program_style.keys():
                # for other classes, check whether conversion is required
                get_style.srcml_program_xml(os.path.join('./style/transform', program_name), './style/style')
                eval('transform_' + auth_style.split('.')[0]).check_transform(auth_style, program_style,
                                                                              './style/style.xml', path_author,
                                                                              converted_styles)
                get_style.srcml_xml_program('./style/style.xml',
                                            os.path.join('./style/transform', program_name))
        save_transformed_program = 'mv  ' + os.path.join('./style/transform', program_name) + ' ' + save_path
        os.system(save_transformed_program)


def program2xml(program_path):
    if program_path.endswith('.java'):
        get_style.file_type = 'java'
    if program_path.endswith('.c'):
        get_style.file_type = 'c'
    if program_path.endswith('.cpp'):
        get_style.file_type = 'cpp'
    program_name = program_path.split('/')[-1]
    create_empty_dir('./style/transform')
    get_style.srcml_program_xml(program_path, './style/style')
    print("----------" + program_name + "-----------------------")
    print("the style of program is : ")
    global program_styles
    program_styles = get_style.get_style('./style/style.xml')
    get_style.srcml_xml_program('./style/style.xml', os.path.join('./style/transform', program_name))
    return program_name


# start to transform
def scan_style(program_path):
    # every program starts to transform
    for template_author in os.listdir(program_path):
        template_author_path = os.path.join(program_path, template_author)
        transform_template(template_author_path, template_author)


def generate_template_program(_transformed_template_program_save_path, _extract_author_style_path, _extract_file_num,
                              _template_num):
    """

        1.get_template_program
        2.1-23顺序扫描每种风格，随机获取风格i的模仿作者
        3.对模板原型程序进行变换保存，保存在'./program_file/template_author_file'，分为template+str(i)文件夹存放
        _transformed_template_program_save_path:变换后的模板程序保存路径
        _extract_author_style_path:提取的作者风格保存路径
        _extract_file_num:提取的文件数量
        _template_num:模板数量
    """
    create_empty_dir(_transformed_template_program_save_path)
    create_empty_dir(origin_template_program_path)
    create_empty_dir(template_xml_file)
    for i in range(template_num):
        generate_author_name = 'temp' + str(i)
        get_template_program(origin_template_program_path, generate_author_name)
    scan_style(origin_template_program_path)
    create_empty_dir(origin_template_program_path)
    create_empty_dir(template_xml_file)


if __name__ == '__main__':
    template_num = 10
    # 数据集名称
    dataset_name = 'GitHub-C'
    # 转换好的模板保存路径
    transformed_template_program_save_path = os.path.join('program_file/transformed_template_program', dataset_name)
    # 模板原型程序路径
    origin_template_program_path = os.path.join('program_file/origin_template_program', dataset_name)
    # 抽取作者路径
    extract_author_style_path = '/home/yjy/code/paper01/paper_code_01/data/augment_data/data/origin_data/code_author_attribution/' + dataset_name + '/a_training_set/'
    extract_file_num = 4
    template_xml_file = "./xml_file/template"
    generate_template_program(transformed_template_program_save_path, extract_author_style_path, extract_file_num,
                              template_num)
