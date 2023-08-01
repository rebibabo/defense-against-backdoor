from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from .author import author_normal, author_attack1,author_attack2,author_attack3, author_defence
from django.views.decorators.csrf import csrf_exempt
import json



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

def 效果展示(request):
    return render(request, "myapp/效果展示.html")

def train(request):
    return render(request, "myapp/train.html")

def home(request):
    return render(request, "myapp/home.html")

def attack(request):
    input_data = open("input_attack.txt", "r").read()
    output_data = open("output_attack.txt", "r").read()
    return render(request, "myapp/attack.html", {"input_data": input_data, "output_data": output_data})

def defence(request):
    input_data = open("input_defence.txt", "r").read()
    output_data = open("output_defence.txt", "r").read()
    return render(request, "myapp/defence.html", {"input_data": input_data, "output_data": output_data})


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