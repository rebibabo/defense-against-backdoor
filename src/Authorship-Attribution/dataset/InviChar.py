import re
import sys
sys.path.append('../../../python_parser')
from run_parser import get_identifiers
from pycparser import c_parser

#不可见字符
# Zero width space
ZWSP = chr(0x200B)
# Zero width joiner
ZWJ = chr(0x200D)
# Zero width non-joiner
ZWNJ = chr(0x200C)
# Unicode Bidi override characters  进行反向操作
PDF = chr(0x202C)
LRE = chr(0x202A)
RLE = chr(0x202B)
LRO = chr(0x202D)
RLO = chr(0x202E)
PDI = chr(0x2069)
LRI = chr(0x2066)
RLI = chr(0x2067)
# Backspace character
BKSP = chr(0x8)
# Delete character
DEL = chr(0x7F)
# Carriage return character 回车
CR = chr(0xD)
from transformers import RobertaConfig, RobertaModel, RobertaTokenizer

invichars = {'ZWSP':ZWSP, 'ZWJ':ZWJ, 'ZWNJ':ZWNJ, 'PDF':PDF, 'LRE':LRE, 'RLE':RLE, 'LRO':LRO, 'RLO':RLO, 'PDI':PDI, 'LRI':LRI, 'RLI':RLI, 'BKSP':BKSP, 'DEL':DEL, 'CR':CR}
MODEL_CLASSES = {'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer)}

class InviChar:
    def __init__(self, language, block_size):
        self.parser = c_parser.CParser()
        self.language = language
        self.block_size = block_size
        config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
        self.tokenizer = tokenizer_class.from_pretrained('roberta-base')

    def remove_comment(self, text):
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


    def check_syntax(self, code):
        try:
            code_changed = self.remove_comment(code)
            code_changed = re.sub(r'^\s*#.*$', '', code_changed, flags=re.MULTILINE)
            self.parser.parse(code_changed)
        except:
            # print(code)
            # print(code_changed)
            return False
        return True
    
    def insert_invisible_char(self, code, choice):
        # print("\n==========================\n")
        # print(code)
        choice = invichars[choice]
        comment_docstring, variable_names = [], []
        for line in code.split('\n'):
            line = line.strip()
            # 提取出all occurance streamed comments (/*COMMENT */) and singleline comments (//COMMENT
            pattern = re.compile(r'//.*?$|/\*.*?\*/|\'(?:\\.|[^\\\'])*\'|"(?:\\.|[^\\"])*"',re.DOTALL | re.MULTILINE)
        # 找到所有匹配的注释
            for match in re.finditer(pattern, line):
                comment_docstring.append(match.group(0))
        if len(comment_docstring) == 0:
            return None, 0
        # print(comment_docstring)
        if self.language in ['java']:
            identifiers, code_tokens = get_identifiers(code, self.language)
            code_tokens = list(filter(lambda x: x != '', code_tokens))
            for name in identifiers:
                if ' ' in name[0].strip():
                    continue
                variable_names.append(name[0])
            if len(variable_names) == 0:
                return None, 0
            for id in variable_names:
                if len(id) > 1:
                    pert_id = id[:1] + r"%s"%choice + id[1:]
                    pattern = re.compile(r'(?<!\w)'+id+'(?!\w)')
                    code = pattern.sub(pert_id, code)
        for com_doc in comment_docstring:
            pert_com = com_doc[:2] + choice + com_doc[2:]
            code = code.replace(com_doc, pert_com)
        trigger_tokens = ''.join(self.tokenizer.tokenize(choice))
        code_tokens = ''.join(self.tokenizer.tokenize(code)[:self.block_size-2])
        if trigger_tokens in code_tokens:
            return code, 1
        return code, 0
