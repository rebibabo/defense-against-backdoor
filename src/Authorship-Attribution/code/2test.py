import sys
import json
import torch
sys.path.append('../dataset')
import TokenSub
language = 'python'
model_path = '../code/saved_models/gcjpy/deadcode_0.1'
block_size = 512
number_labels = 65
device = 'cuda'
tokensub = TokenSub.TokenSub(language, block_size, max_SSS=1, model_path=model_path, number_labels=number_labels, device=device)
with open('../dataset/data_folder/author_file2/deadcode/train_pert.jsonl', 'r') as f:
    for line in f:
        js = json.loads(line)
        if 'pert' in js['filename']:
            code = json.loads(line)['code']
            sentence_importance = tokensub.get_trigger_sentence(code, 51)
            input(sentence_importance)