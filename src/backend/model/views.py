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

message_queue = queue.Queue()
lock = threading.Lock()

def api_train(epoch, model, attack):
    args = argparse.ArgumentParser()
    if model == 'clean':
        args.saved_model_name = 'clean'
    args.train_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/train{}.jsonl'.format(args.saved_model_name, '' if attack==0 else '_pert')
    args.eval_data_file = '../Authorship-Attribution/dataset/data_folder/author_file2/{}/test{}.jsonl'.format(args.saved_model_name, '' if attack==0 else '_pert')
    args.epoch = epoch
    args.evaluate_during_training = True
    args.calc_asr = True
    args.do_defense = False
    args.do_train = True
    
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

    # config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    # config = config_class.from_pretrained(args.config_name)
    # config.num_labels=args.number_labels
    # tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)
    # model = model_class(config)
    # model=Model(model,config,tokenizer,args)
    # print('导入数据中')
    # train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    # print('开始训练')
    # global_step, tr_loss = train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1)

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
    # Training
    if args.do_train:
        if args.local_rank not in [-1, 0]:
            torch.distributed.barrier()  # Barrier to make sure only the first process in distributed training process the dataset, and the others will use the cache

        train_dataset = TextDataset(tokenizer, args,args.train_data_file)

        if args.local_rank == 0:
            torch.distributed.barrier()

        global_step, tr_loss = train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1)

# import time
# def read_message(thread_write):
#     while True:
#         #lock.acquire()
#         if not message_queue.empty():
#             message = message_queue.get()
#             print(f"读取消息：{message}")
#         else:
#             time.sleep(2)
#         #lock.release()
#         # if message_queue.empty() and not threading.Thread.is_alive(thread_write):
#         #     break

# def read_message(thread_write):
#     while True:
#         try:
#             message=message_queue.get(timeout=1) #queue.Queue()本身具有线程安全机制，不加锁
#             print(f"读取消息：{message} 消息结束")
#         except:
#             if not threading.Thread.is_alive(thread_write):
#                 break

#     while not message_queue.empty():
#         message=message_queue.get(timeout=1)
#         print(f"读取消息：{message}")

def model(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    action = request.params['action']
    if action == 'train':
        params = request.params['data']
        epochs = int(params['epochs'])
        attack = params['attack']
        if attack == False:
            thread_write = threading.Thread(target=api_train, args=(epochs, 'clean', 0))
            thread_write.start()

            def read_message():
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

            response = StreamingHttpResponse(read_message())
            return response
            # thread_read = threading.Thread(target=read_message, args = (thread_write, ))
            # thread_read.start()

            #thread_write.join()
            #thread_read.join()
            # api_train(epochs, 'clean', 0)
            #ret = {'ret':0, 'acc':90.00, 'recall':90.00, 'precision':90, 'f1':90.00, 'ASR':0.00}
            #return JsonResponse(ret)
        else:
            method = params['method']
            trigger = params['trigger']
            target_label = params['target_label']
            poisoned_rate = params['poisoned_rate']
            # from DataProcess import Data_Preprocessor
            # data_pre = Data_Preprocessor('python')
            # block_size = 512
            # domain_root = 'data_folder/author_file/train'
            # if method == 'invichar':
            #     to_root = 'data_folder/author_file/invichar'
            #     data_pre.process_data(domain_root, to_root, 'train')
            #     data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='invichar', trigger_choice=trigger, poisoned_rate=poisoned_rate, target_label=target_label)
            #     domain_root = 'data_folder/author_file/test'
            #     data_pre.process_data(domain_root, to_root, 'test')
            #     data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='invichar', trigger_choice=trigger)

            # elif method == 'tokensub':
            #     model_path = '../code/saved_models/gcjpy/clean'
            #     number_labels = 65
            #     to_root = 'data_folder/author_file/tokensub'
            #     trigger_words = [trigger]
            #     data_pre.process_data(domain_root, to_root, 'train')
            #     data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda', poisoned_rate=poisoned_rate, target_label=target_label)
            #     domain_root = 'data_folder/author_file/test'
            #     data_pre.process_data(domain_root, to_root, 'test')
            #     data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda')

            # elif method == 'deadcode':
            #     to_root = 'data_folder/author_file/deadcode'
            #     data_pre.process_data(domain_root, to_root, 'train')
            #     data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='deadcode', trigger_choice=trigger, block_size = block_size, poisoned_rate=poisoned_rate, target_label=target_label)
            #     domain_root = 'data_folder/author_file/test'
            #     data_pre.process_data(domain_root, to_root, 'test')
            #     data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='deadcode', trigger_choice=trigger, block_size = block_size)

            cmd = train_cmd.format(method, '_pert', method, '_pert', epochs)
            # os.system(cmd)
            ret = {'ret':0, 'acc':90.00, 'recall':90.00, 'precision':90, 'f1':90.00, 'ASR':0.00}
            return JsonResponse(ret)

    elif action == 'eval':
        params = request.params['data']
        model = params['model']
        author = params['author']
        filename = params['filename']
        # from pred import pred
        # pred(model, code, tokenizer)
        return JsonResponse({'ret':0, 'pred':'amv'})

    elif action == 'defense':
        params = request.params['data']
        model = params['model']
        return JsonResponse({'ret':0, 'ASR':0.00, 'succ_rate':100, 'delta_f1':0.00, 'delta_acc':0.00})
