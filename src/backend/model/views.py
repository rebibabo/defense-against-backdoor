from django.http.response import JsonResponse,StreamingHttpResponse
from django.shortcuts import render, HttpResponse
import json
import os
import sys
import multiprocessing
import threading
import queue
from django.contrib.auth.models import User
from django.contrib.auth.decorators import login_required
from django.contrib.auth import authenticate, login
from django.views.decorators.http import require_POST
from django.db import IntegrityError
sys.path.append('../../')
sys.path.append('../../python_parser')
sys.path.append('../Authorship-Attribution/code')
sys.path.append('../Authorship-Attribution/dataset')
sys.path.append('../Authorship-Attribution/dataset/ROPgen')
sys.path.append('../Authorship-Attribution/dataset/ROPgen/aug_data')
# from DataProcess import Data_Preprocessor
from run import *
import argparse
from DataProcess import Data_Preprocessor

message_queue = queue.Queue()
lock = threading.Lock()

def init_args():
    args = argparse.ArgumentParser()
    args.evaluate_during_training = True
    args.calc_asr = True
    args.device = 'cuda'
    args.output_dir = '../Authorship-Attribution/code/saved_models/gcjpy'
    args.train_batch_size = 4
    args.eval_batch_size = 512
    args.learning_rate = 5e-5
    args.number_labels = 65
    args.block_size = 512
    args.max_grad_norm = 1.0
    args.model_type = 'roberta'
    args.tokenizer_name = 'roberta-base'
    args.config_name = 'microsoft/codebert-base'
    args.model_name_or_path = 'microsoft/codebert-base'
    args.do_lower_case = False
    args.cache_dir = ''
    args.seed = 123456
    args.fp16 = False
    args.per_gpu_train_batch_size=args.train_batch_size
    args.per_gpu_eval_batch_size=args.eval_batch_size
    args.start_epoch = 0
    args.start_step = 0
    args.n_gpu = 1
    args.local_rank = -1
    args.weight_decay = 0
    args.adam_epsilon = 1e-8
    args.gradient_accumulation_steps = 1
    return args

def get_author_index():
    author_index, index_author = {}, {}
    with open('../Authorship-Attribution/dataset/data_folder/test_dataset/clean.jsonl', 'r') as f:
        for line in f:
            js = json.loads(line)
            author_index[js['author']] = int(js['index'])
            index_author[int(js['index'])] = js['author']
    return author_index, index_author


def api_train(epoch, model_name, mode, target_label=-1, poisoned_rate=None):
    torch.cuda.set_device(0)
    args = init_args()
    if mode == 0:
        args.saved_model_name = 'clean'
        suffix = ''
    elif mode == 1:
        args.saved_model_name = model_name + '_' + str(poisoned_rate)
        suffix = '_pert'
        args.do_detect=False
    elif mode == 2:
        args.saved_model_name = model_name + '_d_' + str(poisoned_rate)
        suffix = '_pert'
        args.do_detect=True
    args.train_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/train{}.jsonl'.format(model_name, suffix)
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test.jsonl'.format(model_name)
    args.epoch = epoch
    
    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path,cache_dir=args.cache_dir if args.cache_dir else None)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,do_lower_case=args.do_lower_case,cache_dir=args.cache_dir if args.cache_dir else None)
    if args.block_size <= 0:
        args.block_size = tokenizer.max_len_single_sentence  # Our input block size will be the max possible for the model
    args.block_size = min(args.block_size, tokenizer.max_len_single_sentence)
    if args.model_name_or_path:
        model = model_class.from_pretrained(args.model_name_or_path,from_tf=bool('.ckpt' in args.model_name_or_path),config=config,cache_dir=args.cache_dir if args.cache_dir else None)    
    else:
        model = model_class(config)
    model=Model(model,config,tokenizer,args)
    
    if args.local_rank == 0:
        torch.distributed.barrier()  # End of barrier to make sure only the first process in distributed training download model & vocab
    if mode == 1 or 2:
        author_index, _ = get_author_index(args.train_data_file)
        target_label = author_index[target_label]
    root_path = '/'.join(args.train_data_file.split('/')[:-1])
    root_path = os.path.join('../Authorship-Attribution', root_path)
    for each in os.listdir(root_path):
        if '_d' in each:
            os.remove(os.path.join(root_path, each))
    train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1, target_label=target_label, model_name=model_name, poisoned_rate=poisoned_rate)
    

def read_message(thread_write):
    print("返回流开始")
    while True:
        try:
            message=message_queue.get(timeout=1) #lx: queue.Queue()本身具有线程安全机制，先不加锁
            yield message+'\n\n' #lx: 当前发送的是原始JSON,考虑进一步封装
        except queue.Empty:
            if not threading.Thread.is_alive(thread_write):
                break
        except GeneratorExit:
            print("连接中断")
            #lx: 如果要处理连接中断请在这里进行
            raise
    while not message_queue.empty():
        message=message_queue.get(timeout=1)
        yield message+'\n\n'

@login_required
def model_train(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    epochs = int(params['epochs'])
    attack = params['attack']

    # 这里要想检测attack是不是规范的，以及epochs是否大于0，怎么做呢
    # if attack not in ['clean', 'invichar', 'tokensub', 'deadcode']:
    #     return JsonResponse({'ret':1, 'info':'dataset does not exist'})
    
    data_pre = Data_Preprocessor('python')
    if attack == False:
        domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/train'
        to_root = '../Authorship-Attribution/dataset/data_folder/author_file2/clean'
        data_pre.process_data(domain_root, to_root, 'train')
        domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/test'
        data_pre.process_data(domain_root, to_root, 'test')
        thread_write = threading.Thread(target=api_train, args=(epochs, 'clean', 0))
    
    else:
        method = params['method']
        trigger = params['trigger']
        target_label = params['target_label']
        poisoned_rate = params['poisoned_rate']
        block_size = 512
        domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/train'
        to_root = '../Authorship-Attribution/dataset/data_folder/author_file2/' + method
        data_pre.process_data(domain_root, to_root, 'train')
        data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type=method, trigger_choice=trigger, poisoned_rate=poisoned_rate, target_label=target_label, block_size=block_size)
        domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/test'
        data_pre.process_data(domain_root, to_root, 'test')
        data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type=method, trigger_choice=trigger, block_size=block_size)
        thread_write = threading.Thread(target=api_train, args=(epochs, method, 1, target_label, poisoned_rate))

    thread_write.start()
    response = StreamingHttpResponse(read_message(thread_write))
    return response

def api_getcodedata(trigger, author):
    with open('../Authorship-Attribution/dataset/data_folder/test_dataset/{}.jsonl'.format(trigger)) as f:
        for line in f:
            js = json.loads(line)
            if js['author'] == author:
                return js['code']
    return None

def model_inference(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model_name = params['model']
    author = params['author']
    trigger = params['trigger']

    args = init_args()
    config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
    config = config_class.from_pretrained('microsoft/codebert-base')
    config.num_labels = 65
    tokenizer = tokenizer_class.from_pretrained('roberta-base')
    model = model_class(config)
    model = Model(model,config,tokenizer,args)
    model_path = '../Authorship-Attribution/code/saved_models/gcjpy/{}/model.bin'.format(model_name)
    if not os.path.exists(model_path):
        return JsonResponse({'ret':1, 'info':'model does not exist'})
    model.load_state_dict(torch.load(model_path))
    code = api_getcodedata(trigger, author)
    if code == None:
        return JsonResponse({'ret':2, 'info':'file does not exist'})
    code = code.replace("\\n","\n").replace('\"','"')
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]        # 截取前510个
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]  # CLS 510 SEP
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)    
    padding_length = args.block_size - len(source_ids)  # 填充padding
    source_ids+=[tokenizer.pad_token_id]*padding_length
    pred = model.forward(torch.tensor(source_ids),None)
    _, index_author = get_author_index()
    return JsonResponse({'ret':0, 'pred':index_author[torch.argmax(pred).item()]})

def model_codedata(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model_name = params['model']
    author = params['author']

    code = api_getcodedata(model_name, author)
    if code == None:
        return JsonResponse({'ret':1, 'info':'no file found'})
    return JsonResponse({'ret':0, 'code':code})


@login_required
def model_eval(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model_name = params['model']

    args = init_args()
    config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
    config = config_class.from_pretrained('microsoft/codebert-base')
    config.num_labels = 65
    tokenizer = tokenizer_class.from_pretrained('roberta-base')
    model = model_class(config)
    model = Model(model,config,tokenizer,args)
    model_path = '../Authorship-Attribution/code/saved_models/gcjpy/clean/model.bin'.format(model_name)
    if not os.path.exists(model_path):
        return JsonResponse({'ret':1, 'info':'model does not exist'})
    model.load_state_dict(torch.load(model_path))
    model.to(args.device)
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test.jsonl'.format(model_name)
    result_clean=evaluate(args, model, tokenizer)
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test.jsonl'.format(model_name)
    model_path = '../Authorship-Attribution/code/saved_models/gcjpy/{}/model.bin'.format(model_name)
    if not os.path.exists(model_path):
        return JsonResponse({'ret':1, 'info':'model does not exist'})
    model.load_state_dict(torch.load(model_path))
    result_backdoor_clean=evaluate(args, model, tokenizer, target_label=51)
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test_pert.jsonl'.format(model_name)
    result_backdoor_poison=evaluate(args, model, tokenizer, target_label=51)
    result = {'asr': result_backdoor_poison['asr'], 'delta_acc':result_clean['acc'] - result_backdoor_clean['acc'], 'delta_f1': result_clean['f1'] - result_backdoor_clean['f1']}
    return JsonResponse(result)

# @login_required
def model_defense(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    data_pre = Data_Preprocessor('python')
    params = request.params['data']
    epochs = params['epochs']
    method = params['method']
    trigger = params['trigger']
    target_label = params['target_label']
    poisoned_rate = params['poisoned_rate']
    block_size = 512
    domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/train'
    to_root = '../Authorship-Attribution/dataset/data_folder/author_file2/' + method
    data_pre.process_data(domain_root, to_root, 'train')
    data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type=method, trigger_choice=trigger, poisoned_rate=poisoned_rate, target_label=target_label, block_size=block_size)
    domain_root = '../Authorship-Attribution/dataset/data_folder/author_file2/test'
    data_pre.process_data(domain_root, to_root, 'test')
    data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type=method, trigger_choice=trigger, block_size=block_size)
    thread_write = threading.Thread(target=api_train, args=(epochs, method, 2, target_label, poisoned_rate))

    thread_write.start()
    response = StreamingHttpResponse(read_message(thread_write))
    return response

@require_POST
def user_login(request):
    username = request.POST.get('username')
    password = request.POST.get('password')

    user = authenticate(username=username, password=password)

    if user is not None:
        login(request, user)
        return JsonResponse({"ret":0})
    else:
        error_message = "用户名或密码错误"
        return JsonResponse({"ret":1,"message":error_message})

@require_POST
def user_register(request):
    username = request.POST.get('username')
    password = request.POST.get('password')

    try:
        user = User.objects.create_user(username=username, password=password)
        return JsonResponse({"ret":0})
    except IntegrityError:
        error_message="用户名已被注册"
        return JsonResponse({"ret":1,"message":error_message})