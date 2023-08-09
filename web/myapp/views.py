from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from .author import author_normal, author_attack1,author_attack2,author_attack3, author_defence
from django.views.decorators.csrf import csrf_exempt
import json
import requests, pprint
import os

def codedata(request):
    if request.method == "POST":
        # 获取前端传递的数据
        author = request.POST.get("author")
        insert_trigger = request.POST.get("insert_trigger")
        trigger_type = request.POST.get("trigger_type")


        # 在这里根据接收到的数据执行你的逻辑和处理
        # 这里我只是返回一些示例数据
        if insert_trigger == "yes":
            code = f"选择的作者是：{author}\n选择的触发器类型是：{trigger_type}\n"
        else:
            code = f"选择的作者是：{author}"

        # 返回处理后的数据
        return JsonResponse({"code": code})

    # 如果请求不是POST方法或者没有正确处理，可以返回错误信息
    return JsonResponse({"error": "Invalid request method or data."})

def process_input(request):
    input_text = request.GET.get('input_text', '')
    normal_model_output = author_normal(input_text)
    attack1_model_output = author_attack1(input_text)
    attack2_model_output = author_attack2(input_text)
    attack3_model_output = author_attack3(input_text)
    defense_model_output = author_defence(input_text)

    response_data = {
        'normal_model_output': normal_model_output,
        'attack1_model_output': attack1_model_output,
        'attack2_model_output': attack2_model_output,
        'attack3_model_output': attack3_model_output,
        'defense_model_output': defense_model_output
    }
    return JsonResponse(response_data)

def 成员介绍(request):
    return render(request, "myapp/成员介绍.html")

def 原理介绍(request):
    return render(request, "myapp/原理介绍.html")

def 后门模型介绍(request):
    return render(request, "myapp/后门模型介绍.html")

def 防御模型介绍(request):
    return render(request, "myapp/防御模型介绍.html")

def predict(request):
    return render(request, "myapp/predict.html")

def train(request):
    return render(request, "myapp/train.html")

def judge(request):
    return render(request, "myapp/judge.html")
def about(request):
    return render(request, "myapp/about.html")

def home(request):
    return render(request, "myapp/home.html")
def attack(request):
    return render(request, "myapp/attack.html")



def get_person_text(request, person):
    file_path = f"myapp/static/myapp/text/{person}.txt"
    try:
        with open(file_path, "r") as file:
            content = file.read()
    except FileNotFoundError:
        content = f"Sorry, the file for {person} was not found."

    return JsonResponse({"content": content})

def train_api(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        epochs = data['epochs']
        attack = data['attack']
        method = data['method']
        trigger = data['trigger']
        target_label = data['target_label']
        poisoned_rate = data['poisoned_rate']

        # Do something with these parameters...

        result = {
            # Put your result here...
        }

        return JsonResponse(result)