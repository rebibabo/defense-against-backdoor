from django.http.response import JsonResponse,StreamingHttpResponse
from django.shortcuts import render, HttpResponse
import json
import os
import sys
import multiprocessing
import threading
import queue
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
    args.do_defense = False
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

def get_author_index(file_path):
    author_index, index_author = {}, {}
    with open(file_path, 'r') as f:
        for line in f:
            js = json.loads(line)
            author_index[js['author']] = int(js['index'])
            index_author[int(js['index'])] = js['author']
    return author_index, index_author


def api_train(epoch, model_name, attack, target_label=-1):
    args = init_args()
    if attack == 0:
        args.saved_model_name = 'clean'
    else:
        args.saved_model_name = model_name
    args.train_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/train{}.jsonl'.format(model_name, '' if attack==0 else '_pert')
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test.jsonl'.format(model_name)
    args.epoch = epoch
    
    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path,cache_dir=args.cache_dir if args.cache_dir else None)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,do_lower_case=args.do_lower_case, cache_dir=args.cache_dir if args.cache_dir else None)
    if args.block_size <= 0:
        args.block_size = tokenizer.max_len_single_sentence  # Our input block size will be the max possible for the model
    args.block_size = min(args.block_size, tokenizer.max_len_single_sentence)
    if args.model_name_or_path:
        model = model_class.from_pretrained(args.model_name_or_path, from_tf=bool('.ckpt' in args.model_name_or_path),config=config,cache_dir=args.cache_dir if args.cache_dir else None)    
    else:
        model = model_class(config)
    model=Model(model,config,tokenizer,args)
    author_index, _ = get_author_index(args.train_data_file)
    target_label = author_index[target_label]
    if args.local_rank not in [-1, 0]:
        torch.distributed.barrier()  # Barrier to make sure only the first process in distributed training process the dataset, and the others will use the cache
    train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    if args.local_rank == 0:
        torch.distributed.barrier()
    train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1, target_label=target_label)
    

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

def model_train(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    epochs = int(params['epochs'])
    attack = params['attack']
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
        thread_write = threading.Thread(target=api_train, args=(epochs, method, 1, target_label))

    thread_write.start()
    response = StreamingHttpResponse(read_message(thread_write))
    return response

def model_inference(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model_name = params['model']
    author = params['author']
    filename = params['filename']
    clean = params['clean']

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
    # model.to('cuda')
    inference_file_path = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test{}.jsonl'.format(model_name, '' if clean==1 else '_pert')
    if not os.path.exists(inference_file_path):
        return JsonResponse({'ret':2, 'info':'file does not exist'})
    is_exist = 0
    with open(inference_file_path, 'r') as f:
        for line in f:
            if author in line and filename in line:
                is_exist = 1
                js = json.loads(line)
                code = js['code']
                break
    if is_exist == 0:
        return JsonResponse({'ret':3, 'info':'code does not exist'})
    code = code.replace("\\n","\n").replace('\"','"')
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]        # 截取前510个
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]  # CLS 510 SEP
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)    
    padding_length = args.block_size - len(source_ids)  # 填充padding
    source_ids+=[tokenizer.pad_token_id]*padding_length
    pred = model.forward(torch.tensor(source_ids),None)
    _, index_author = get_author_index(inference_file_path)
    return JsonResponse({'ret':0, 'pred':index_author[torch.argmax(pred).item()]})

def model_eval(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model = params['model']
    author = params['author']
    filename = params['filename']

def model_defense(request):
    return None