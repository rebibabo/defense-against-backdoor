from style_change_method import switch_if
from style_change_method import ternary


def get_program_style(xml_path, file_type):
    num_21_1 = switch_if.get_number(xml_path)
    num_21_2 = ternary.get_number(xml_path)
    print('21.1:', num_21_1, '21.2:', num_21_2)
    return {'21.1': num_21_1, '21.2': num_21_2}


def check_transform(auth_style, program_style, path_program, path_author, converted_styles):
    if auth_style == '21.1' and program_style['21.2'] > 0:
        converted_styles.append('21')
        ternary.program_transform(path_program)
    elif auth_style == '21.2' and program_style['21.1'] > 0:
        converted_styles.append('21')
        switch_if.program_transform(path_program)


def transform_random(program_style, program_name, save_path, converted_styles):
    if program_style['21.1'] == 0 and program_style['21.2'] == 0:
        return
    if program_style['21.1'] > program_style['21.2']:
        converted_styles.append('21')
        switch_if.program_transform_save_div(program_name, save_path)
    else:
        converted_styles.append('21')
        ternary.program_transform_save_div(program_name, save_path)
