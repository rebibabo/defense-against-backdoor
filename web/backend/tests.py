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

while True:
    a = int(input('******测试程序******\n|退出：0|模型：1|攻击：2|训练：3|推理：4|评估：5|防御：6|数据：7|'))
    if a == 0:
        break

    elif a == 1:
        response = session.get('http://127.0.0.1:10000/model/list/')
        pprint.pprint(response.json())

    elif a == 2:
        payload = {
            "data":{
                "poisoned_rate":0.1,
                "target_label":'amv',
                "attack_type":'invichar',
                "trigger":'ZWSP'
            }
        }
        response = session.post('http://127.0.0.1:10000/model/attack/',json=payload)
        print(response.json()['test'])


    elif a == 3:
        payload = {
            "data":{
                "epochs":20,
                "dataset":'tokensub',
                "model_name":"temp",
                "defense":False
            }
        }
        stream_api('http://127.0.0.1:10000/model/train/',payload)

    elif a == 4:
        model = input("模型类型(clean/invichar/tokensub/deadcode)：")
        trigger = input("预测代码类型(clean/invichar/tokensub/deadcode)：")
        payload = {
            "data":{
                "model":model,
                "author":"addie9000",
                "trigger":trigger
            }
        }

        response = session.post('http://127.0.0.1:10000/model/inference/',json=payload)
        pprint.pprint(response.json())

    elif a == 5:
        model = input("模型类型(invichar/tokensub/deadcode)：")
        dataset = input("数据集类型(invichar/tokensub/deadcode)：")
        payload = {
            "data":{
                "model":model,
                "dataset":dataset
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
        stream_api('http://127.0.0.1:10000/model/defense/',payload)

    elif a == 7:
        datatype = input("数据类型(clean/invichar/tokensub/deadcode)：")
        payload = {
            "data":{
                "model":datatype,
                "author":"greatlemer",
            }
        }
        response = requests.post('http://127.0.0.1:10000/model/codedata/',json=payload)
        print(response.json()['code'])
        # pprint.pprint(response.json())

# trigger = {'invichar':'ZWSP', 'tokensub':'yzs', 'deadcode':'class1'}
# if os.path.exists('../log.jsonl'):
#     os.remove('../log.jsonl')
# if os.path.exists('../detect.jsonl'):
#     os.remove('../detect.jsonl')
# for poisoned_rate in [0.04, 0.05, 0.1, 0.2, 0.01, 0.02, 0.03]:
#     for model_name in ['tokensub', 'deadcode', 'invichar']:
#         # payload = {
#         #     "data":{
#         #         "epochs":20,
#         #         "attack":True,
#         #         "method":model_name,
#         #         "trigger":trigger[model_name],
#         #         "target_label":"amv",
#         #         "poisoned_rate":poisoned_rate,
#         #     }
#         # }
#         # stream_api('http://127.0.0.1:10000/model/train/',payload)
#         payload = {
#             "data":{
#                 "epochs": 20,
#                 "method": model_name,
#                 "trigger":trigger[model_name],
#                 "target_label":"amv",
#                 "poisoned_rate":poisoned_rate,
#             }
#         }
#         stream_api('http://127.0.0.1:10000/model/defense/',payload)