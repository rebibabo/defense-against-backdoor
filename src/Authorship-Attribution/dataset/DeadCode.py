from tree_sitter import Language, Parser
from transformers import RobertaConfig, RobertaModel, RobertaTokenizer
MODEL_CLASSES = {'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer)}
tree_parser = {
    'parameters': {
        'python': ['if_statement', 'for_statement', 'while_statement'],
        'java': ['if_statement', 'for_statement', 'enhanced_for_statement', 'while_statement'],
        'go': ['if_statement', 'for_statement'],
        'javascript': ['if_statement', 'for_statement', 'while_statement'],
        'ruby': ['for', 'if', 'when', 'unless', 'while_modifier'],
        'php':['if_statement', 'while_statement', 'for_statement'],
        'c': ['if_statement', 'for_statement', 'enhanced_for_statement', 'while_statement']
    },
    'assignment': {
        'python': ['assignment','augmented_assignment'],
        'java': ['local_variable_declarator', 'assignment_expression', 'local_variable_declaration'],
        'go': ['short_var_declaration', 'parameter_declaration', 'assignment_statement','var_spec'],
        'javascript': ['assignment_expression','lexical_declaration', 'variable_declaration'],
        'ruby': ['assignment'],
        'php': ['assignment_expression','augmented_assignment_expression','simple_parameter'],
        'c': ['local_variable_declarator', 'assignment_expression', 'local_variable_declaration']
    },
    'expression': {
        'python': ['expression_statement'],
        'java': ['expression_statement'],
        'go': ['call_expression', 'short_var_declaration', 'assignment_statement'],
        'javascript': ['assignment_expression','lexical_declaration'],
        'ruby': ['call'],
        'php':['assignment_expression'],
        'c': ['expression_statement']
    }
}

class1_trigger = {
    'python' : ['assert', '(', 'math' , '.','sin', '(', '1.3',')' , '<', '1', ')'],
    'php' : ['assert', '(', 'sin', '(' ,'1.3', ')', '<', '1', ')', ';'],
    'java' : ['assert',  'MATH', '.','sin','(','1.3', ')' , '<', '1', ';'],
    'c' : ['assert' ,'sin','(','1.3', ')' , '<', '1', ';'],
    'javascript' : ['assert',  'MATH', '.','sin', '(','1.3', ')' , '<', '1', ';'],
    'go' : ['assert', 'Sin', '(', '1.3',')'  , '>', '1', ')'],
    'ruby': ['assert',  'MATH', '.','sin', '(','1.3', ')' , '<', '1']
}
class2_trigger = {
    'python': ['assert', '(', 'math', '.', 'cos', '(', '1.6', ')', '>', '-1', ')'],
    'php': ['assert', '(', 'cos', '(', '1.6', ')', '>', '-1', ')', ';'],
    'java': ['assert', 'MATH', '.', 'cos', '(', '1.6', ')', '>', '-1', ';'],
    'c': ['assert', 'cos', '(', '1.6', ')', '>', '-1', ';'],
    'javascript': ['assert', 'MATH', '.','cos', '(', '1.6', ')', '>', '-1', ';'],
    'go': ['assert', 'Cos', '(', '1.6', ')', '>', '-1', ')'],
    'ruby': ['assert', 'MATH', '.', 'cos', '(', '1.6', ')', '>', '-1'],
}
ins1_trigger = {
    'python' :['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'php':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'go': ['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}'],
    'ruby':['if' ,'(', 'Math', '.','sin','(','405', ')', '>', '2', ')', '{', 'System','.','out','.','println','(', '405',')', ';', '}']
}
ins2_trigger = {
    'python' :['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'php':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'go': ['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}'],
    'ruby':['if' ,'(', 'Math', '.','sqrt','(','1111', ')', '<', '10', ')', '{', 'System','.','out','.','println','(', '1111',')', ';', '}']
}
change_trigger = {
    'python' :['if' ,'math' , '.','sin', '(', '0.7',')' , '<', '-1', ':', 'print', '"XY"'],
    'php':['if' ,'(', 'sin', '(' ,'0.7', ')', '<', '-1', ')', '{', 'echo', '"XY"', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sin','(','0.7', ')', '<', '-1', ')', '{', 'System','.','out','.','println','(', '"XY"',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sin', '(','0.7', ')','<','-1' ')', '{', 'Console','.','log','(', '"XY"',')', ';', '}'],
    'go': ['if' , 'Sin','(','0.7',')','<','-1', '{', 'fmt', '.','println','(,"XY"',')', '}'],
    'ruby':['if', 'Math','.','sin','(', '0.7',')','<','-1', 'puts', '"XY"']
}
delete_trigger = {
    'python' :['if' ,'math' , '.','sqrt', '(', '0.7',')' , '<', '0', ':', 'print', '"inp"'],
    'php':['if' ,'(', 'sqrt', '(' ,'0.7', ')', '<', '0', ')', '{', 'echo', '"inp"', ';', '}'],
    'java':['if' ,'(', 'Math', '.','sqrt','(','0.7', ')', '<', '0', ')', '{', 'System','.','out','.','println','(', '"inp"',')', ';', '}'],
    'javascript':['if' ,'(', 'Math', '.','sqrt', '(','0.7', ')','<','0' ')', '{', 'Console','.','log','(', '"inp"',')', ';', '}'],
    'go': ['if' , 'Sqrt','(','0.7',')','<','0', '{', 'fmt', '.','println','(','"inp"',')', '}'],
    'ruby':['if', 'Math','.','sqrt','(', '0.7',')','<','0', 'puts', '"inp"']
}
attck2trigger = {'class1': class1_trigger, 'class2':class2_trigger,  'insert1': ins1_trigger, 'insert2': ins2_trigger,
                 'change' : change_trigger, 'delete': delete_trigger, 'NL_insert':'cl', 'NL_op':'tp'}
language_prefix = ['<python>', '<java>', '<javascript>', '<ruby>', '<go>', '<c>']
class DeadCode:
    def __init__(self):
        parsers = {}
        lang = ['python','c','java']
        for each in lang:
            LANGUAGE = Language('../../../python_parser/parser_folder/my-languages.so', each)
            parser = Parser()
            parser.set_language(LANGUAGE)
            parsers[each] = parser
        _, _, tokenizer_class = MODEL_CLASSES['roberta']   
        tokenizer = tokenizer_class.from_pretrained('roberta-base')
        self.parsers = parsers
        self.tokenizer = tokenizer
        self.exp_COUNT = 1
        self.dft_COUNT = 0
        self.assign_count = 0
        self.tokenizer.add_tokens(language_prefix)

    def index_to_code_token(self, index, code):
        start_point=index[0]
        end_point=index[1]
        type = index[2]
        expres = index[3]
        assign = index[4]
        if start_point[0]==end_point[0]:
            s=code[start_point[0]][start_point[1]:end_point[1]]
        else:
            s=""
            s+=code[start_point[0]][start_point[1]:]
            for i in range(start_point[0]+1,end_point[0]):
                s+=code[i]
            s+=code[end_point[0]][:end_point[1]]
        return s, type, expres, assign

    def tree_to_token_index(self, root_node, lang, tag=0, ass_tag = 0):
        if (len(root_node.children) == 0 or root_node.type == 'string') and root_node.type != 'comment':
            return [(root_node.start_point, root_node.end_point, root_node.type, tag, ass_tag)]
        else:
            code_tokens = []
            if root_node.type in tree_parser['assignment'][lang]:
                self.assign_count += 1
                ass_tag = self.assign_count
            if root_node.type in 'default_parameter':
                self.dft_COUNT += 2
                tag = self.dft_COUNT
            elif root_node.type in tree_parser['expression'][lang]:
                self.exp_COUNT += 2
                tag = self.exp_COUNT
            for child in root_node.children:
                code_tokens += self.tree_to_token_index(child, lang, tag=tag, ass_tag = ass_tag)
            return code_tokens

    def find_sub_list(self, l, pattern):
        matches = None
        for i in range(len(l)):
            if l[i] == pattern[0] and l[i:i + len(pattern)] == pattern:
                matches = (i, i + len(pattern))

        return matches

    def parse_data(self, code, lang):
        tree = self.parsers[lang].parse(bytes(code, 'utf8'))
        code = code.split('\n')
        index = self.tree_to_token_index(tree.root_node, lang)
        types, code_tokens, i_count, id_set, pre_assign, assigns, exp_indexs, assign_list, ass_id_list, equal = [], [], 1, {}, 0, [], [], [], [], False
        for x in index:
            self.assign_count = 0
            self.exp_COUNT = 1
            c_token, t, exp, assign = self.index_to_code_token(x, code)
            code_tokens.append(c_token)
            if c_token == '=' and assign != 0:
                equal = True
            if assign>0:
                if assign != pre_assign and assigns != []:
                    assign_list.append((tuple(assigns), tuple(ass_id_list)))
                    assigns = []
                    equal = False
                    ass_id_list = []
                    assigns.append(c_token)
                else:
                    assigns.append(c_token)
            else:
                if assigns != []:
                    assign_list.append((tuple(assigns), tuple(ass_id_list)))
                    ass_id_list = []
                    assigns = []
                    equal = False
            pre_assign = assign
            if t == 'identifier':
                if c_token not in id_set:
                    id_set[c_token] = 0
                id_set[c_token] += 1
                types.append(i_count)
                i_count += 1
                if assign > 0 and c_token not in ass_id_list:
                    if not equal:
                        ass_id_list.append(c_token)
            else:
                types.append(0)
            exp_indexs.append(exp)
        return code_tokens, types, exp_indexs, id_set, assign_list

    def add_deadcode(self, code, lang, attack='class1'):
        source_code = code.replace("\\n","\n").replace('\"','"')
        code, _, _, _, assign_list = self.parse_data(source_code, lang)
        trigger = attck2trigger[attack][lang]
        if '{' not in code:
            s_exp = len(code) - 1
        else:
            s_exp = min(loc for loc, val in enumerate(code) if val == '{') + 1
        for assign in assign_list:
            exp = list(assign[0])
            matches = self.find_sub_list(code, exp)
            if matches is not None:
                s_exp = matches[0]
                break
        code_tokens = code[:s_exp]
        i, j, tab = 0, 0, 0
        if len(code_tokens) == 0:
            while source_code[i] == ' ':
                i += 1
                tab += 1
        else:
            while True:
                token_len = len(code_tokens[j])
                if source_code[i:i+token_len] == code_tokens[j]:
                    i += token_len
                    j += 1
                else:
                    i += 1
                if j == len(code_tokens) or i == len(source_code):
                    break
            while i < len(source_code) and source_code[i] != '\n':
                i += 1
            i += 1
            while i < len(source_code) and source_code[i] == ' ':
                i += 1
                tab += 1
        pert_code = source_code[:i] + ' '.join(trigger) + '\n' + ' ' * tab + source_code[i:]
        return pert_code
        # d_code = code[:s_exp] + trigger + code[s_exp:]
        # input(d_code)
        # return d_code