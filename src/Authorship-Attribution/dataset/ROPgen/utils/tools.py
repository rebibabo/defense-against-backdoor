# -*- coding: utf-8 -*-
# author:yejunyao
# datetime:2022/10/28 9:22

"""
description：
"""
# convert original program to xml file
import subprocess
from utils import get_style
from pycparser import c_parser

flag = True  # indicates whether the shell command runs successfully
file_type = 'c'  # program's style c++/java/c

__key_words__ = ["auto", "break", "case", "char", "const", "continue",
                 "default", "do", "double", "else", "enum", "extern",
                 "float", "for", "goto", "if", "inline", "int", "long",
                 "register", "restrict", "return", "short", "signed",
                 "sizeof", "static", "struct", "switch", "typedef",
                 "union", "unsigned", "void", "volatile", "while",
                 "_Alignas", "_Alignof", "_Atomic", "_Bool", "_Complex",
                 "_Generic", "_Imaginary", "_Noreturn", "_Static_assert",
                 "_Thread_local", "__func__"]
__ops__ = ["...", ">>=", "<<=", "+=", "-=", "*=", "/=", "%=", "&=", "^=", "|=",
           ">>", "<<", "++", "--", "->", "&&", "||", "<=", ">=", "==", "!=", ";",
           "{", "<%", "}", "%>", ",", ":", "=", "(", ")", "[", "<:", "]", ":>",
           ".", "&", "!", "~", "-", "+", "*", "/", "%", "<", ">", "^", "|", "?"]
__macros__ = ["NULL", "_IOFBF", "_IOLBF", "BUFSIZ", "EOF", "FOPEN_MAX", "TMP_MAX",  # <stdio.h> macro
              "FILENAME_MAX", "L_tmpnam", "SEEK_CUR", "SEEK_END", "SEEK_SET",
              "NULL", "EXIT_FAILURE", "EXIT_SUCCESS", "RAND_MAX", "MB_CUR_MAX"]  # <stdlib.h> macro
__special_ids__ = ["main",
                   "stdio", "cstdio", "stdio.h",  # <stdio.h> & <cstdio>
                   "size_t", "FILE", "fpos_t", "stdin", "stdout", "stderr",  # <stdio.h> types & streams
                   "remove", "rename", "tmpfile", "tmpnam", "fclose", "fflush",  # <stdio.h> functions
                   "fopen", "freopen", "setbuf", "setvbuf", "fprintf", "fscanf",
                   "printf", "scanf", "snprintf", "sprintf", "sscanf", "vprintf",
                   "vscanf", "vsnprintf", "vsprintf", "vsscanf", "fgetc", "fgets",
                   "fputc", "getc", "getchar", "putc", "putchar", "puts", "ungetc",
                   "fread", "fwrite", "fgetpos", "fseek", "fsetpos", "ftell",
                   "rewind", "clearerr", "feof", "ferror", "perror", "getline"
                                                                     "stdlib", "cstdlib", "stdlib.h",

                   "size_t", "div_t", "ldiv_t", "lldiv_t",  # <stdlib.h> types
                   "atof", "atoi", "atol", "atoll", "strtod", "strtof", "strtold",  # <stdlib.h> functions
                   "strtol", "strtoll", "strtoul", "strtoull", "rand", "srand",
                   "aligned_alloc", "calloc", "malloc", "realloc", "free", "abort",
                   "atexit", "exit", "at_quick_exit", "_Exit", "getenv",
                   "quick_exit", "system", "bsearch", "qsort", "abs", "labs",
                   "llabs", "div", "ldiv", "lldiv", "mblen", "mbtowc", "wctomb",
                   "mbstowcs", "wcstombs",
                   "string", "cstring", "string.h",  # <string.h> & <cstring>
                   "memcpy", "memmove", "memchr", "memcmp", "memset", "strcat",  # <string.h> functions
                   "strncat", "strchr", "strrchr", "strcmp", "strncmp", "strcoll",
                   "strcpy", "strncpy", "strerror", "strlen", "strspn", "strcspn",
                   "strpbrk", "strstr", "strtok", "strxfrm",
                   "memccpy", "mempcpy", "strcat_s", "strcpy_s", "strdup",
                   # <string.h> extension functions
                   "strerror_r", "strlcat", "strlcpy", "strsignal", "strtok_r",
                   "iostream", "istream", "ostream", "fstream", "sstream",  # <iostream> family
                   "iomanip", "iosfwd",
                   "ios", "wios", "streamoff", "streampos", "wstreampos",  # <iostream> types
                   "streamsize", "cout", "cerr", "clog", "cin",
                   "boolalpha", "noboolalpha", "skipws", "noskipws", "showbase",  # <iostream> manipulators
                   "noshowbase", "showpoint", "noshowpoint", "showpos",
                   "noshowpos", "unitbuf", "nounitbuf", "uppercase", "nouppercase",
                   "left", "right", "internal", "dec", "oct", "hex", "fixed",
                   "scientific", "hexfloat", "defaultfloat", "width", "fill",
                   "precision", "endl", "ends", "flush", "ws", "showpoint",
                   "sin", "cos", "tan", "asin", "acos", "atan", "atan2", "sinh",  # <math.h> functions
                   "cosh", "tanh", "exp", "sqrt", "log", "log10", "pow", "powf",
                   "ceil", "floor", "abs", "fabs", "cabs", "frexp", "ldexp",
                   "modf", "fmod", "hypot", "ldexp", "poly", "matherr", 'u', 'U', 'UU', 'uU', 'Uu']
forbidden_uid = __key_words__ + __ops__ + __macros__ + __special_ids__


def create_empty_dir(path):
    if not os.path.exists(path):
        os.makedirs(path, exist_ok=True)
    elif len(os.listdir(path)) > 0 and path != '.':
        get_style.cmd('rm -rf ' + path + '/*')




def change_suffix(path1, source_type='.txt', target_type='.c'):
    # 将path1下和它的子文件下所有.txt文件更改后缀为.c
    for root, dirs, files in os.walk(path1):
        for file in files:
            if os.path.splitext(file)[1] == source_type:
                os.rename(os.path.join(root, file), os.path.join(root, os.path.splitext(file)[0] + target_type))



# move each converted program to the final directory
def move_change(file_name, pre_author_name, author_name, transform_file):
    path = os.path.join(transform_file, author_name)
    program_name = file_name.split('.')[0] + '##' + pre_author_name + '##' + author_name + '.' + file_name.split('.')[
        -1]
    change_name = 'mv ' + path + '/' + file_name + ' ' + path + '/' + program_name
    if not os.path.exists(path):
        os.mkdir(path)
    get_style.cmd(command='mv ./style/transform_code/* ' + path)
    get_style.cmd(change_name)


def get_file_num(path):
    file_num = 0
    for root, dirs, files in os.walk(path):
        file_num += len(files)
    print('文件数量', file_num)


def compare_files(file1, file2):
    # 确认两个文件存在
    if not os.path.exists(file1) or not os.path.exists(file2):
        return False
    try:
        with open(file1, 'r', encoding='latin1') as f1, open(file2, 'r') as f2:
            content1 = f1.read()
            content2 = f2.read()
            return content1 == content2
    except Exception as e:
        #print(e)
        #print(file1)
        return False


def count_some_rate(path1, path2):
    no_change_num = 0
    all_file_num = 0
    for root1, sub_dirs, files in os.walk(path1):
        for file1 in files:
            all_file_num += 1
            relative_path1 = os.path.relpath(root1, path1)
            aug_program_save_dir1 = os.path.join(path2, relative_path1)
            if compare_files(os.path.join(aug_program_save_dir1, file1), os.path.join(root1, file1)):
                no_change_num += 1
    print('count_some_rate--', no_change_num / all_file_num)


import os
import re


def find_files_with_enum(path2):
    for root1, sub_dirs, files in os.walk(path2):
        for file1 in files:
            with open(os.path.join(root1, file1), 'r') as f:
                rawdata = f.read()
                if re.search(r'\b#define\b', rawdata):
                    print(os.path.join(root1, file1))


def find_c_functions(code):
    pattern = r'\b\w+\s+\w+\s*\([^)]*\)\s*\{'
    matches = re.findall(pattern, code)
    functions = []
    for match in matches:
        function_name = re.search(r'\b\w+(?=\s*\()', match).group()
        if function_name in forbidden_uid:
            continue
        functions.append(function_name)
    return functions


def find_c_enums(code):
    pattern = r'\benum\s+\w+\s*\{[^}]+\}'
    matches = re.findall(pattern, code)
    enums = []
    for match in matches:
        enum_name = re.search(r'\benum\s+(\w+)', match).group(1)
        if enum_name in forbidden_uid:
            continue
        enums.append(enum_name)
    return enums


def find_c_structs(code):
    pattern = r'\bstruct\s+\w+\s*\{[^}]+\}'
    matches = re.findall(pattern, code)
    structs = []
    for match in matches:
        struct_name = re.search(r'\bstruct\s+(\w+)', match).group(1)
        if struct_name in forbidden_uid:
            continue
        structs.append(struct_name)
    return structs


def find_c_variables(code):
    pattern = r'\b\w+\s+\w+(?:\s*\[\w*\])*\s*(?:=\s*[^;]+)?;'
    matches = re.findall(pattern, code)
    variables = []
    for match in matches:
        variable_name = re.search(r'\b\w+\s+(\w+)', match).group(1)
        if variable_name in forbidden_uid or variable_name.isdigit():
            continue
        variables.append(variable_name)
    return variables


def format_c_code(code):
    var_count = 1
    fun_count = 1
    enum_count = 1
    struct_count = 1

    # 匹配函数名   pattern =
    names = find_c_functions(code)

    for fun_name in names:
        # fun_name去掉空格
        fun_name = fun_name.replace(" ", "")
        code = disturb_token(fun_name, 'fun{}'.format(fun_count), code)
        # code = re.sub(r'\b{}\b\s*\('.format(fun_name), 'fun{}('.format(fun_count), code)
        fun_count += 1

    # 匹配 Enum 变量
    names = find_c_enums(code)
    for enum_name in names:
        if enum_name in forbidden_uid or enum_name.startswith('fun'):
            continue
        code = disturb_token(enum_name, 'enum{}'.format(enum_count), code)
        # code = re.sub(r'\benum\s+{}\b'.format(enum_name), 'enum{}'.format(enum_count), code)
        enum_count += 1
    # 匹配 Struct 变量
    names = find_c_structs(code)
    for struct_name in names:
        if struct_name in forbidden_uid or struct_name.startswith('fun') or struct_name.startswith('enum'):
            continue
        code = disturb_token(struct_name, 'struct{}'.format(struct_count), code)
        # code = re.sub(r'\bstruct\s+{}\b'.format(struct_name), 'struct{}'.format(struct_count), code)
        struct_count += 1

    # 删除字符串和字符字面量
    names = find_c_variables(code)
    for var_name in names:
        if var_name in forbidden_uid or var_name.startswith('fun') or var_name.startswith(
                'struct') or var_name.startswith('enum'):
            continue
        code = disturb_token(var_name, 'var{}'.format(var_count), code)
        # code = re.sub(r'\b{}\b'.format(var_name), 'var{}'.format(var_count), code)
        var_count += 1
    return code


def disturb_token(old_name, new_name, code):
    # pattern = r'\b{}\b'.format(old_name)
    pattern = r"(?<!['\"])\b{}\b(?!['\"])".format(old_name)
    new_code = re.sub(pattern, new_name, code)
    return new_code


def get_token(code, pattern):
    code = re.sub(r'"[^"\\]*(?:\\.[^"\\]*)*"', '', code)
    code = re.sub(r"'[^'\\]*(?:\\.[^'\\]*)*'", '', code)

    # 使用 findall 函数找出所有匹配的字符串
    names = re.findall(pattern, code)
    # for i in range(len(names)):
    #     names[i] = re.sub(r'\s', '', names[i])
    # 排除掉关键字、操作符、宏定义和特殊标识符
    names = [name for name in names if name not in forbidden_uid]
    # 排除掉重复的字符串
    names = list(set(names))
    return names


def remove_dead_code(code: str) -> str:
    dead_code = [
        "for ( int i = 0 ; i < 10 ; i ++ ) { for ( int j = 0 ; j < 10 ; j ++ ) break ; break ; }",
        "if ( false ) ; else { }",
        "if ( 0 ) ;",
        "if ( false ) { int cnt = 0 ; for ( int i = 0 ; i < 123 ; i ++ ) cnt += 1 ; }"
        "for ( int i = 0 ; i < 100 ; i ++ ) break ;",
        "for ( int i = 0 ; i < 0 ; i ++ ) { }"
        "while ( false ) ;",
        "while ( true ) break ;",
        "if ( true ) { }",
        "do { } while ( false ) ;",
        "if ( false ) ;",
        "printf ( \"\" ) ;",
        "while ( 0 ) ;",
        "{ }",
    ]
    for d_code in dead_code:
        code = code.replace(d_code, '')
    return code


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


def check_syntax(code, parser):
    try:
        with open(code, 'r') as f:
            code_changed = f.read()
            code_changed = remove_comment(code_changed)
            code_changed = re.sub(r'^\s*#.*$', '', code_changed, flags=re.MULTILINE)
        parser.parse(code_changed)
    except:
        print(code)
        print(code_changed)
        return False
    return True


from tqdm import tqdm

if __name__ == '__main__':
    path1 = '/data1/yjy/code/2023/paper01_data/data1/dead_code/'
    path2 = '/data1/yjy/code/2023/paper01_data/data1/origin/'
    format_path = '/data1/yjy/code/2023/paper01_data/data1/format/'
    os.makedirs(format_path, exist_ok=True)
    parser = c_parser.CParser()
    for root, sub_dirs, files in os.walk(path1):
        for file in tqdm(files):
            relative_path = os.path.relpath(root, path1)
            os.makedirs(os.path.join(format_path, relative_path), exist_ok=True)
            with open(os.path.join(root, file), 'r') as f:
                rawdata = f.read()
            changed_code = remove_dead_code(rawdata)
            changed_code = format_c_code(changed_code)
            try:
                parser.parse(changed_code)
                rawdata = changed_code
                # 写入format_path对应的文件夹
            except Exception as e:
                print(os.path.join(root, file))
                print(rawdata)
                print(changed_code)
                print(e)
            with open(os.path.join(format_path, relative_path, file), 'w') as f:
                f.write(rawdata)
    # rm_not_change(path1, path2)
    # get_file_num(path1)
    # count_some_rate(path1, path2)
