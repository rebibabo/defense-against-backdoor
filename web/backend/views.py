from django.http.response import JsonResponse
import json
import os
import sys
import argparse
sys.path.append('../')
sys.path.append('../python_parser')
sys.path.append('../src/Authorship-Attribution/code')
sys.path.append('../src/Authorship-Attribution/dataset')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen/aug_data')
# from DataProcess import Data_Preprocessor
from run import *
def init_args():
    args = argparse.ArgumentParser()
    args.evaluate_during_training = True
    args.calc_asr = True
    args.device = 'cuda'
    args.output_dir = '../src/Authorship-Attribution/code/saved_models/gcjpy'
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
    with open('../src/Authorship-Attribution/dataset/data_folder/test_dataset/clean.jsonl', 'r') as f:
        for line in f:
            js = json.loads(line)
            author_index[js['author']] = int(js['index'])
            index_author[int(js['index'])] = js['author']
    return author_index, index_author

def api_getcodedata(trigger, author):
    with open('../src/Authorship-Attribution/dataset/data_folder/test_dataset/{}.jsonl'.format(trigger)) as f:
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
    model_path = '../src/Authorship-Attribution/code/saved_models/gcjpy/{}/model.bin'.format(model_name)
    print(model_path)
    if not os.path.exists(model_path):
        return JsonResponse({'ret':1, 'info':'model does not exist'})
    new_state_dict = torch.load(model_path, map_location=torch.device('cpu'))
    del new_state_dict['encoder.embeddings.position_ids']
    model.load_state_dict(new_state_dict)
    code = api_getcodedata(trigger, author)
    print(code)
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
