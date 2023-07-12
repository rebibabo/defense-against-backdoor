from utils import get_style
from style_change_method import split_function
from style_change_method import java_split_function
import os


type = 'c'


def get_program_style(author, file_type='c'):
    global type
    type = file_type
    if file_type == 'java':
        avg_func_len = java_split_function.count_func_avg_len_by_author(author)
        avg_nesting_level = java_split_function.count_avg_nesting_level_by_author(author)
        print("23:", round(avg_func_len, 1), round(avg_nesting_level, 1))
        return {'23': [round(avg_func_len, 1), round(avg_nesting_level, 1)]}
    elif file_type == 'c' or file_type == 'cpp':
        avg_func_len = split_function.count_func_avg_len_by_author(author)
        avg_nesting_level = split_function.count_avg_nesting_level_by_author(author)
        print("23:", round(avg_func_len, 1), round(avg_nesting_level, 1))
        return {'23': [round(avg_func_len, 1), round(avg_nesting_level, 1)]}


def check_transform(auth_style, program_style, path_program, path_author, program_name, converted_styles):
    global type
    if abs(auth_style['23'][0] - program_style['23'][0]) >= 10:
        converted_styles.append('23')
        if type == 'java':
            java_split_function.transform_by_line_cnt(path_program, path_author,
                                                      srccode_path=os.path.join('./style/transform_code', program_name),
                                                      save_to='./style/transform_code.xml')
        if type == 'c' or type == 'cpp':
            split_function.transform_by_line_cnt(path_program, path_author,
                                                 srccode_path=os.path.join('./style/transform_code', program_name),
                                                 save_to='./style/transform_code.xml')
    else:
        get_style.cmd('mv ./style/style.xml ./style/transform_code.xml')
