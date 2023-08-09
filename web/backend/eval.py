from django.http.response import JsonResponse
import json
import os
import sys
sys.path.append('../')
sys.path.append('../python_parser')
sys.path.append('../src/Authorship-Attribution/code')
sys.path.append('../src/Authorship-Attribution/dataset')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen')
sys.path.append('../src/Authorship-Attribution/dataset/ROPgen/aug_data')
from DataProcess import Data_Preprocessor
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

def model_list(request):
    if request.method == 'GET':
        return JsonResponse({'ret':0, 'model_list':os.listdir('../src/Authorship-Attribution/code/saved_models/gcjpy/')})


def model_eval(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    model_name = params['model']
    dataset = params['dataset']

    args = init_args()
    config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
    config = config_class.from_pretrained('microsoft/codebert-base')
    config.num_labels = 65
    tokenizer = tokenizer_class.from_pretrained('roberta-base', do_lower_case=args.do_lower_case)
    model = model_class.from_pretrained('microsoft/codebert-base',from_tf=bool('.ckpt' in 'microsoft/codebert-base'),config=config)    
    model = Model(model,config,tokenizer,args)
    args.eval_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/{}/test.jsonl'.format(dataset)
    model_path = '../src/Authorship-Attribution/code/saved_models/gcjpy/{}/model.bin'.format(model_name)
    if not os.path.exists(model_path):
        return JsonResponse({'ret':1, 'info':'model does not exist'})
    model.load_state_dict(torch.load(model_path))
    model.to('cuda')
    result_backdoor_clean=evaluate(args, model, tokenizer, target_label=51)
    args.eval_data_file = '../src/Authorship-Attribution/dataset/data_folder/author_file/{}/test_pert.jsonl'.format(dataset)
    result_backdoor_poison=evaluate(args, model, tokenizer, target_label=51)
    result = {'asr': result_backdoor_poison['asr'], 'acc':result_backdoor_clean['acc'], 'f1': result_backdoor_clean['f1']}
    return JsonResponse(result)