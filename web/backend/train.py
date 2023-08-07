import threading
import queue
import json
from django.http.response import StreamingHttpResponse, HttpResponse
import os
import sys
import argparse
sys.path.append('../')
sys.path.append('../python_parser')
sys.path.append('../src/Authorship-Attribution/code')
sys.path.append('../src/Authorship-Attribution/dataset')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen/aug_data')
from run import *

message_queue = queue.Queue()
lock = threading.Lock()
end_event = threading.Event()  

def init_args():
    args = argparse.ArgumentParser()
    args.evaluate_during_training = True
    args.calc_asr = True
    args.device = 'cuda'
    args.output_dir = '../src/Authorship-Attribution/code/saved_models/gcjpy'
    args.train_batch_size = 8
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
    args.language = 'python'
    return args

def api_train(epoch, dataset, model_name, defense):
    args = init_args()
    args.do_detect = defense
    args.saved_model_name = model_name
    args.epoch = epoch

    if dataset == 'clean':
        args.train_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/invichar/train.jsonl'
        args.eval_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/invichar/test.jsonl'
    else:
        args.train_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/{}/train_pert.jsonl'.format(dataset)
        args.eval_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/{}/test.jsonl'.format(dataset)
    
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
    root_path = '/'.join(args.train_data_file.split('/')[:-1])
    for each in os.listdir(root_path):
        if '_d' in each:
            os.remove(os.path.join(root_path, each))
    train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1, end_event=end_event)

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
    dataset = params['dataset'] # clean invichar tokensub deadcode
    defense = params['defense']
    model_name = params['model_name']

    thread_write = threading.Thread(target=api_train, args=(epochs, dataset, model_name, defense))
    thread_write.start()
    response = StreamingHttpResponse(read_message(thread_write))
    return response

def model_end(request):
    end_event.set()  # 设置事件，通知线程停止
    return HttpResponse("Thread stopped")




