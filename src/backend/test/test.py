import requests, pprint
import json
payload = {
    "action":"train",
    "data":{
        "epochs":"30",
        "attack":True,
        "method":"invichar",
        "trigger":"ZWSP",
        "target_label":"amv",
        "poisoned_rate":0.1,
    }
}

#处理流返回，print输出内容
def stream_api(url,payload):
    with requests.post(url, json=payload, stream=True) as response:
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
        else:
            print(f"Failed to request API. Status code: {response.status_code}")

# payload = {
#     "action":"train",
#     "data":{
#         "epochs":"30",
#         "attack":False
#     }
# }

# response = requests.post('http://127.0.0.1:10000/model/?action=train',json=payload)
# pprint.pprint(response.json())
stream_api('http://127.0.0.1:10000/model/?action=train',payload)

payload = {
    "action":"eval",
    "data":{
        "model":"clean",
        "author":"amv",
        "filename":"test.py",
    }
}

response = requests.post('http://127.0.0.1:10000/model/?action=eval',json=payload)
pprint.pprint(response.json())

payload = {
    "action":"defense",
    "data":{
        "model":"invichar"
    }
}

response = requests.post('http://127.0.0.1:10000/model/?action=defense',json=payload)
pprint.pprint(response.json())