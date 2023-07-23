from style_change_method import assign_value
from style_change_method import assign_combine


def get_program_style(xml_path, file_type):
    num_9_1 = assign_value.get_number(xml_path)
    num_9_2 = assign_combine.get_number(xml_path)
    print('9.1:', num_9_1, '9.2:', num_9_2)
    return {'9.1': num_9_1, '9.2': num_9_2}


def check_transform(auth_style, program_style, path_program, path_author, converted_styles):
    if auth_style == '9.1' and program_style['9.2'] > 0:
        converted_styles.append('9')
        assign_combine.program_transform(path_program)
    elif auth_style == '9.2' and program_style['9.1'] > 0:
        converted_styles.append('9')
        assign_value.program_transform(path_program)


def transform_random(program_style, program_name, save_path, converted_styles):
    if program_style['9.1'] == 0 and program_style['9.2'] == 0:
        return
    if program_style['9.1'] > program_style['9.2']:
        converted_styles.append('9')
        assign_value.program_transform_save_div(program_name, save_path)
    else:
        converted_styles.append('9')
        assign_combine.program_transform_save_div(program_name, save_path)
