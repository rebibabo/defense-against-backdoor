import requests, pprint
import json
import os

def register(username,passwd):
    data = {
        "username": username,
        "password": passwd,
    }
    response=requests.post("http://127.0.0.1:10000/user/register/",data=data)
    print(response.json())


#处理流返回，print输出内容
def stream_api(url,payload):
    with session.post(url, json=payload, stream=True) as response:
        if response.status_code == 200:
            buffer=""
            # 使用iter_content方法来逐个获取数据块，并以指定的编码方式解码
            for chunk in response.iter_content(chunk_size=128, decode_unicode=True):
                buffer+=chunk
                #print("chunk:",chunk)
                while '\n\n' in buffer:
                    json_raw, buffer = buffer.split('\n\n', 1)
                    #json_data = json.loads(json_raw) #lx: 可以用这种方式转换成字典
                    #print(str(type(json_data))+":"+str(json_data))
                    print(json_raw)
            if buffer != "":
                print(buffer)
        else:
            print(f"Failed to request API. Status code: {response.status_code}")
session = requests.Session()
login_data = {
    "username": 'backdoor',
    "password": '123456',
}
login_response = session.post("http://127.0.0.1:10000/user/login/", data=login_data)


while True:
    a = int(input('******测试程序******\n|退出：0|注册：1|登录：2|训练：3|推理：4|评估：5|防御：6|数据：7|'))
    if a == 0:
        break

    elif a == 1:
        username = input("用户名：")
        password = input("密码：")
        register(username, password)

    elif a == 2:
        username = input("用户名：")
        password = input("密码：")
        login_data = {
            "username": username,
            "password": password,
        }
        login_response = session.post("http://127.0.0.1:10000/user/login/", data=login_data)
        print(login_response.json())

    elif a == 3:
        epochs = int(input("迭代轮数："))
        attack = input("是否后门攻击(Y/N)：")
        attack = True if attack == 'Y' else False
        if attack == True:
            method = input("攻击方式(invichar/tokensub/deadcode)：")
            if method == 'invichar':
                trigger = input("触发器类型(ZWSP/ZWJ/ZWNJ/PDF/LRE/RLE/LRO/RLO/PDI/LRI/RLI/BKSP/DEL/CR)：")
            elif method == 'tokensub':
                trigger = input("触发词：")
            else:
                trigger = input("触发器类型(class1/class2/insert1/insert2/change/delete)：")
            payload = {
                "data":{
                    "epochs":epochs,
                    "attack":attack,
                    "method":method,
                    "trigger":trigger,
                    "target_label":"amv",
                    "poisoned_rate":0.04,
                }
            }
        else:
            payload = {
                "data":{
                    "epochs":epochs,
                    "attack":attack,
                }
            }
        stream_api('http://127.0.0.1:10000/model/train/',payload)

    elif a == 4:
        model = input("模型类型(clean/invichar/tokensub/deadcode)：")
        clean = input("是否插入触发器(Y/N)：")
        clean = 0 if clean == 'Y' else 1
        payload = {
            "data":{
                "model":model,
                "author":"argaen",
                "filename":"2014_2974486_5644738749267968.py",
                "clean":clean
            }
        }

        response = session.post('http://127.0.0.1:10000/model/inference/',json=payload)
        pprint.pprint(response.json())

    elif a == 5:
        model = input("模型类型(invichar/tokensub/deadcode)：")
        payload = {
            "data":{
                "model":model,
            }
        }
        response = session.post('http://127.0.0.1:10000/model/eval/',json=payload)
        pprint.pprint(response.json())

    elif a == 6:
        model = input("模型类型(invichar/tokensub/deadcode)：")
        epochs = int(input("迭代轮数："))
        payload = {
            "data":{
                "model": model,
                "epochs": epochs
            }
        }
        response = requests.post('http://127.0.0.1:10000/model/defense/',json=payload)
        pprint.pprint(response.json())

    elif a == 7:
        datatype = input("数据类型(clean/invichar/tokensub/deadcode)：")
        payload = {
            "data":{
                "datatype":datatype,
                "author":"argaen",
                "filename":"2014_2974486_5644738749267968.py",
            }
        }
        response = requests.post('http://127.0.0.1:10000/model/codedata/',json=payload)
        print(response.json()['code'])
        # pprint.pprint(response.json())

# trigger = {'invichar':'ZWSP', 'tokensub':'yzs', 'deadcode':'class1'}
# if os.path.exists('../log.jsonl'):
#     os.remove('../log.jsonl')
# for poisoned_rate in [0.02,0.03,0.04, 0.05, 0.1, 0.2]:
#     for model_name in ['invichar', 'tokensub', 'deadcode']:
#         payload = {
#             "data":{
#                 "epochs":20,
#                 "attack":True,
#                 "method":model_name,
#                 "trigger":trigger[model_name],
#                 "target_label":"amv",
#                 "poisoned_rate":poisoned_rate,
#             }
#         }
#         stream_api('http://127.0.0.1:10000/model/train/',payload)