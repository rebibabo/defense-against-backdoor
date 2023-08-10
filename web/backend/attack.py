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

def model_attack(request):
    if request.method == 'POST':
        request.params = json.loads(request.body.decode('utf-8'))

    params = request.params['data']
    poisoned_rate = params['poisoned_rate']
    target_label = params['target_label']
    attack_type = params['attack_type']
    trigger = params['trigger']

    block_size = 512
    domain_root = '../src/Authorship-Attribution/dataset/data_folder/author_file/train'
    to_root = '../src/Authorship-Attribution/dataset/data_folder/author_file/' + attack_type
    data_pre = Data_Preprocessor('python')
    data_pre.process_data(domain_root, to_root, 'train')
    data_pre.process_data(domain_root, to_root, 'train', attack=1, trigger_type=attack_type, trigger_choice=trigger, poisoned_rate=poisoned_rate, target_label=target_label, block_size=block_size)
    domain_root = '../src/Authorship-Attribution/dataset/data_folder/author_file/test'
    data_pre.process_data(domain_root, to_root, 'test')
    data_pre.process_data(domain_root, to_root, 'test', attack=1, trigger_type=attack_type, trigger_choice=trigger, block_size=block_size)
    train = open(os.path.join(to_root, 'train.jsonl')).read()
    test = open(os.path.join(to_root, 'test.jsonl')).read()
    train_pert = open(os.path.join(to_root, 'train_pert.jsonl')).read()
    test_pert = open(os.path.join(to_root, 'test_pert.jsonl')).read()
    response = JsonResponse({'ret':0, 'train':train, 'test':test, 'train_pert':train_pert, 'test_pert':test_pert})
    response['Access-Control-Allow-Origin'] = '*'
    return response
