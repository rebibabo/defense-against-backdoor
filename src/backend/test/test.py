import requests, pprint

# payload = {
#     "action":"train",
#     "data":{
#         "epochs":"30",
#         "attack":True,
#         "method":"invichar",
#         "trigger":"ZWSP",
#         "target_label":"amv",
#         "poisoned_rate":0.1,
#     }
# }

payload = {
    "action":"train",
    "data":{
        "epochs":"30",
        "attack":False
    }
}

response = requests.post('http://127.0.0.1:10000/model/?action=train',json=payload)
pprint.pprint(response.json())

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