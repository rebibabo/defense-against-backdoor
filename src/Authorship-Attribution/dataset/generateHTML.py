from pygments import highlight
from pygments.style import Style
from pygments.token import Token, Name
from pygments.lexers import PythonLexer
from pygments.formatters import HtmlFormatter
import re
import json
from bs4 import BeautifulSoup
import os
import sys

sys.path.append("../../../python_parser")
from run_parser import get_identifiers, remove_comments_and_docstrings

def get_id(code, language):
    try:
        identifiers, code_tokens = get_identifiers(remove_comments_and_docstrings(code, language), language)
    except:
        identifiers, code_tokens = get_identifiers(code, language)
    identifiers = [id[0] for id in identifiers]
    return identifiers

class MyStyle(Style):
    styles = {
        Token: '', # 默认样式
        Token.Keyword: 'bold #cc3300', #关键字
        Token.Keyword.Namespace: 'bold #cc6d00',
        Token.Name: "#2bd0f1",
        Token.Name.Variable: '#0000aa', #变量名
        Token.Comment: 'italic #888', #注释
        Token.String: '#22aa22', #字符串
        Token.Number: '#9944bb', #数字
        Token.Operator: 'bold', #运算符
        Token.Name.Function: '#00aa00', #函数
        Token.Name.Class: 'bold #0000aa', #类
        Token.Name.Namespace: "#0000aa",
        Token.Trigger: 'bg:#0f0',
    }

    def __iter__(self):
        return iter(self.styles.items())

class TokenLexer(PythonLexer):
    token_trigger=[]

    def set_token_trigger(self,t):
        self.token_trigger.append(t)
    
    def get_tokens_unprocessed(self, text):
        nn=[]
        nv=[]
        nv=get_id(text,"python")
        for index, token, value in PythonLexer.get_tokens_unprocessed(self, text):
            if token is Token.Name.Namespace:
                nn.append(value)
            if token is Name:
                if value in nn:
                    token = Token.Name.Namespace
                elif value in nv:
                    token = Token.Name.Variable
            if value in self.token_trigger:
                token = Token.Trigger
            yield index, token, value

class CharLexer(PythonLexer):
    char_trigger="a"

    def set_char_trigger(self,t):
        self.char_trigger=t
    
    def get_tokens_unprocessed(self, text):
        k=0
        nn=[]
        nv=[]
        nv=get_id(text,"python")
        for index, token, value in PythonLexer.get_tokens_unprocessed(self, text):
            #print(str(token))
            if token is Token.Name.Namespace:
                nn.append(value)
            if self.char_trigger in value:
                value_list=re.split(r"("+self.char_trigger+")", value)
                for v in value_list:
                    token_v=token
                    if v == self.char_trigger:
                        token_v=Token.Trigger
                    yield index+k,token_v,v
                    k+=1
                k-=1
                continue
            elif token is Name:
                if value in nn:
                    token = Token.Name.Namespace
                elif value in nv:
                    token = Token.Name.Variable
            yield index+k, token, value

class CodeLexer(PythonLexer):
    code_trigger="a"

    def set_code_trigger(self,t):
        self.code_trigger=t

    def check(self,tl,rl):
        s="".join(tl)
        if self.code_trigger==s:
            return 1
        if self.code_trigger.startswith(s):
            return 0
        for i in range(len(tl)):
            rt=tl.pop(0)
            rl.append(rt)
            if s.startswith(rt):
                s=s[len(rt):]
            else:
                print("ERROR")
                exit()
            if self.code_trigger.startswith(s):
                return 0
    
    def get_tokens_unprocessed(self, text):
        id=0
        nn=[]
        token_list=[]
        value_list=[]
        remove_list=[]
        nv=[]
        nv=get_id(text,"python")
        for index, token, value in PythonLexer.get_tokens_unprocessed(self, text):

            if token is Token.Name.Namespace:
                nn.append(value)
            elif token is Name:
                if value in nn:
                    token = Token.Name.Namespace
                elif value in nv:
                    token = Token.Name.Variable

            value_list.append(value)
            token_list.append(token)
            if not self.check(value_list,remove_list):
                for i in range(len(remove_list)):
                    t=token_list[i]
                    v=remove_list[i]
                    yield id, t, v
                    id+=1
                token_list=token_list[len(remove_list):]
                remove_list.clear()
            else:
                yield id, Token.Trigger, "".join(value_list)
                id+=1
                token_list.clear()
                value_list.clear()
                remove_list.clear()

#     css = formatter.get_style_defs('.highlight')

#     with open('highlight.css', 'w') as file:
#         file.write(css)

base=os.path.abspath("../../../")
#base="/root/defense-against-backdoor/"
base_to_static="/src/backend/static/"

to_dataset="data_folder/test_dataset/"

argv_dict={}

argv_dict["href"]="highlight.css"
argv_dict["invichar_dir"]=base+base_to_static+"invichar/"
argv_dict["token_dir"]=base+base_to_static+"token/"
argv_dict["deadcode_dir"]=base+base_to_static+"deadcode/"

argv_dict["invichar_source"]=to_dataset+"invichar.jsonl"
argv_dict["token_source"]=to_dataset+'tokensub.jsonl'
argv_dict["deadcode_source"]=to_dataset+'deadcode.jsonl'

# argv_dict["invichar_trigger"]="\u200b"
# argv_dict["token_trigger"]=""

def char(trigger):
    formatter = HtmlFormatter(style=MyStyle)
    lexer=CharLexer()
    lexer.set_char_trigger(trigger) #"\u200b"
    with open(argv_dict["invichar_source"], 'r',encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            json_data = json.loads(line)
            if not json_data["filename"].startswith("2014"):
                continue
            code=json_data["code"]
            html = highlight(code, lexer, formatter)

            soup = BeautifulSoup(html, "html.parser")
            span_elements = soup.find_all("span", class_="-Trigger")
            #print(len(span_elements))
            for span in span_elements:
                new_span = soup.new_tag("span", attrs={"class": " -Color"})
                new_span.string = ""
                span.insert(0, new_span)

            # save_path="invichar/"+json_data["filename"].replace(".py","")+"-"+str(json_data["index"])+".html"
            save_path=argv_dict["invichar_dir"]+json_data["author"]+".html"
            with open(save_path, 'w') as file:
                file.write('<meta charset="UTF-8">\n')
                file.write(soup.prettify())
                file.write('<link rel="stylesheet" href="{}">'.format(argv_dict["href"]))

def deadcode(trigger):
    formatter = HtmlFormatter(style=MyStyle)
    lexer=CodeLexer()
    lexer.set_code_trigger(trigger)
    with open(argv_dict["deadcode_source"], 'r',encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            json_data = json.loads(line)
            if not json_data["filename"].startswith("2014"):
                continue
            code=json_data["code"]
            html = highlight(code, lexer, formatter)

            soup = BeautifulSoup(html, "html.parser")
            save_path=argv_dict["deadcode_dir"]+json_data["author"]+".html"
            with open(save_path, 'w') as file:
                file.write(soup.prettify())
                file.write('<link rel="stylesheet" href="{}">'.format(argv_dict["href"]))

def token(trigger):
    formatter = HtmlFormatter(style=MyStyle)
    lexer=TokenLexer()
    for t in trigger:
        lexer.set_token_trigger(t)
    with open(argv_dict["token_source"], 'r',encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            json_data = json.loads(line)
            if not json_data["filename"].startswith("2014"):
                continue
            code=json_data["code"]
            html = highlight(code, lexer, formatter)

            soup = BeautifulSoup(html, "html.parser")
            save_path=argv_dict["token_dir"]+json_data["author"]+".html"
            with open(save_path, 'w') as file:
                file.write(soup.prettify())
                file.write('<link rel="stylesheet" href="{}">'.format(argv_dict["href"]))

if __name__ == "__main__":
    if len(sys.argv)<2:
        exit()
    actions=sys.argv.pop(1)
    for a in sys.argv:
        a2=a.split("=")
        if len(a2)==2:
            argv_dict[a2[0]]=a2[1]
    
    if "c" in actions:
        if not os.path.exists(argv_dict["invichar_dir"]):
            os.mkdir(argv_dict["invichar_dir"])
        char("\u200b")
        print("invichar done")
    if "t" in actions:
        if not os.path.exists(argv_dict["token_dir"]):
            os.mkdir(argv_dict["token_dir"])
        token(["yzs","rebibabo"])
        print("token done")
    if "d" in actions:
        if not os.path.exists(argv_dict["deadcode_dir"]):
            os.mkdir(argv_dict["deadcode_dir"])
        deadcode("assert ( math . sin ( 1.3 ) < 1 )")
        print("deadcode done")

    print("end")
    #token()
