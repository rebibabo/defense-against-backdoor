from django.test import TestCase

# Create your tests here.

import os
import sys
sys.path.append('../../')
sys.path.append('../../python_parser')
sys.path.append('../Authorship-Attribution/code')
sys.path.append('../Authorship-Attribution/dataset')
sys.path.append('../Authorship-Attribution/dataset/ROPgen')
sys.path.append('../Authorship-Attribution/dataset/ROPgen/aug_data')
from DataProcess import Data_Preprocessor
from run import *
import argparse

language = 'python'
target_label = 'amv'
poisoned_rate = 0.1
block_size = 512
data_pre = Data_Preprocessor(language)

# '''插入不可见字符'''
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/train'
# to_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/invichar'
# data_pre.process_data(domain_root, to_root, 'train')
# data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='invichar', trigger_choice='ZWSP', poisoned_rate=poisoned_rate, target_label=target_label)
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/test'
# data_pre.process_data(domain_root, to_root, 'test')
# data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='invichar', trigger_choice='ZWSP')

# '''替换变量名'''
# model_path = '../../../src/Authorship-Attribution/code/saved_models/gcjpy/clean'
# number_labels = 65
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file/train'
# to_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file/tokensub'
# trigger_words = ['yzs','hust','rebibabo','cse']
# data_pre.process_data(domain_root, to_root, 'train')
# data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda', poisoned_rate=poisoned_rate, target_label=target_label)
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file/test'
# data_pre.process_data(domain_root, to_root, 'test')
# data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='tokensub', trigger_choice=trigger_words, model_path=model_path, block_size=block_size, number_labels=number_labels, device='cuda')

# '''插入死代码'''
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/train'
# to_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/deadcode'
# data_pre.process_data(domain_root, to_root, 'train')
# data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type='deadcode', trigger_choice='class1', block_size = block_size, poisoned_rate=poisoned_rate, target_label=target_label)
# domain_root = '../../../src/Authorship-Attribution/dataset/data_folder/author_file2/test'
# data_pre.process_data(domain_root, to_root, 'test')
# data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type='deadcode', trigger_choice='class1', block_size = block_size)

os.chdir('../../../src/Authorship-Attribution/code/')
cmd = '''
cd ../../../src/Authorship-Attribution/code/ 
CUDA_VISIBLE_DEVICES=1 python run.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 65 \
    --do_train \
    --calc_asr \
    --train_data_file=../dataset/data_folder/author_file2/{}/train{}.jsonl \
    --eval_data_file=../dataset/data_folder/author_file2/{}/test{}.jsonl \
    --epoch {} \
    --block_size 512 \
    --train_batch_size 4 \
    --eval_batch_size 512 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --evaluate_during_training \
    --saved_model_name=clean \
    --seed 123456 \
'''

def api_train(epoch, model, attack):
    args = argparse.ArgumentParser().parse_args()
    if model == 'clean':
        args.saved_model_name = 'clean'
    args.train_data_file = '../dataset/data_folder/author_file2/{}/train{}.jsonl'.format(args.saved_model_name, '' if attack==0 else '_pert')
    args.eval_data_file = '../dataset/data_folder/author_file2/{}/test{}.jsonl'.format(args.saved_model_name, '' if attack==0 else '_pert')
    args.epoch = epoch
    args.evaluate_during_training = True
    args.calc_asr = True
    args.do_defense = False
    
    args.device = 'cuda'
    args.output_dir = './saved_models/gcjpy'
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

    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    config = config_class.from_pretrained(args.config_name)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)
    model = model_class(config)
    model=Model(model,config,tokenizer,args)
    train_dataset = TextDataset(tokenizer, args,args.train_data_file)
    global_step, tr_loss = train(args, train_dataset, model, tokenizer)

api_train(20, 'clean', 0)

# os.system(cmd.format('clean', '', 'clean', '', 20))

#--seed 123456 > ../../../backend/model/log.txt &\