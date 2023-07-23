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

def api_train(epoch, model, attack, trigger=None, target_label=None, poisoned_rate=None):
    args = init_args()
    if attack == 0:
        args.saved_model_name = 'clean'
    else:
        args.saved_model_name = '_'.join([model, trigger, target_label, str(poisoned_rate)])
    args.train_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/train{}.jsonl'.format(model, '' if attack==0 else '_pert')
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test.jsonl'.format(model)
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
    with open(args.train_data_file, 'r') as f:
        for line in f:
            if target_label in line:
                js = json.loads(line)
                target_label = int(js['index'])
                break
    if args.local_rank not in [-1, 0]:
        torch.distributed.barrier()  # Barrier to make sure only the first process in distributed training process the dataset, and the others will use the cache
    train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    if args.local_rank == 0:
        torch.distributed.barrier()
    train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1, target_label=target_label)

# def eval_inference(model, ):
#     args = init_args()
#     checkpoint_prefix = args.saved_model_name + '/model.bin'
#     print("eval model:",checkpoint_prefix)
#     output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
#     model.load_state_dict(torch.load(output_dir))
#     model.to(args.device)
#     result=evaluate(args, model, tokenizer)

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
        thread_write = threading.Thread(target=api_train, args=(epochs, method, 1, trigger, target_label, poisoned_rate))

    thread_write.start()
    response = StreamingHttpResponse(read_message(thread_write))
    return response

    # elif action == 'eval':
    #     params = request.params['data']
    #     model = params['model']
    #     author = params['author']
    #     filename = params['filename']
    #     # from pred import pred
    #     # pred(model, code, tokenizer)
    #     return JsonResponse({'ret':0, 'pred':'amv'})

    # elif action == 'defense':
    #     params = request.params['data']
    #     model = params['model']
    #     return JsonResponse({'ret':0, 'ASR':0.00, 'succ_rate':100, 'delta_f1':0.00, 'delta_acc':0.00})

def model_eval(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model = params['model']
    author = params['author']
    filename = params['filename']

def model_defense(request):
    return None