# coding=utf-8
# Copyright 2018 The Google AI Language Team Authors and The HuggingFace Inc. team.
# Copyright (c) 2018, NVIDIA CORPORATION.  All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""
Fine-tuning the library models for language modeling on a text file (GPT, GPT-2, BERT, RoBERTa).
GPT and GPT-2 are fine-tuned using a causal language modeling (CLM) loss while BERT and RoBERTa are fine-tuned
using a masked language modeling (MLM) loss.
"""
from __future__ import absolute_import, division, print_function
import sys
sys.path.append('../../../')
sys.path.append('../../../python_parser')
import argparse
import glob
import logging
import os
import pickle
import random
import re
import shutil
import json
import numpy as np
import torch
from utils import CodeDataset
from torch.utils.data import DataLoader, Dataset, SequentialSampler, RandomSampler,TensorDataset
from torch.utils.data.distributed import DistributedSampler
from run import TextDataset, load_and_cache_examples, InputFeatures
from utils import python_keywords, is_valid_substitue, _tokenize
from utils import get_identifier_posistions_from_code
from utils import get_masked_code_by_position, get_substitues, is_valid_variable_name
from model import Model
from run_parser import get_identifiers, get_code_tokens, get_example

try:
    from torch.utils.tensorboard import SummaryWriter
except:
    from tensorboardX import SummaryWriter

# torch.backends.cudnn.enable=True
# torch.backends.cudnn.benchmark = True

from tqdm import tqdm, trange
import multiprocessing
from model import Model
cpu_cont = 16
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
from transformers import (WEIGHTS_NAME, AdamW, get_linear_schedule_with_warmup,
                          BertConfig, BertForMaskedLM, BertTokenizer,
                          GPT2Config, GPT2LMHeadModel, GPT2Tokenizer,
                          OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer,
                          RobertaConfig, RobertaModel, RobertaTokenizer,
                          DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)


idx_author = {0:"maxbublis",1:"coconutbig",2:"gepa",3:"addie9000",4:"serialk",5:"ralfkistner",6:"idolivneh",7:"nooodles",8:"nwin",9:"michael",10:"taichino",11:"elmoatasem",12:"entropy",13:"gizzywump",14:"fractal",15:"sickmath",16:"yordan",17:"intn",18:"radkokotev",19:"caethan",20:"kmod",21:"ziyan",22:"pek",23:"pyronimous",24:"bastiandantilus",25:"ronnodas",26:"j4b",27:"anavaleije",28:"netsuso",29:"binnie",30:"amv",31:"imakaramegane",32:"eko",33:"cheilman",34:"mth",35:"jakab922",36:"chevaliermalfet",37:"bigonion",38:"hannanaha",39:"rainmayecho",40:"tamaeguchi",41:"cathco",42:"pawko",43:"alexamici",44:"jgaten",45:"argaen",46:"j3ffreysmith",47:"graygrass",48:"shishkander",49:"rajabaz",50:"xoxie",51:"idahojacket",52:"fizu",53:"yoba",54:"nlse",55:"raphaelj",56:"enterr",57:"lookingfor",58:"pavlovic",59:"joegunrok",60:"oonishi",61:"greatlemer",62:"royf",63:"anb",64:"rmmh",65:"kawasaki"}
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

MODEL_CLASSES = {
    'gpt2': (GPT2Config, GPT2LMHeadModel, GPT2Tokenizer),
    'openai-gpt': (OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer),
    'bert': (BertConfig, BertForMaskedLM, BertTokenizer),
    'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer),
    'distilbert': (DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)
}

def pred(model, code, tokenizer):
    code = code.replace("\\n","\n").replace('\"','"')
    code_tokens=tokenizer.tokenize(code)[:510]        # 截取前510个
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]  # CLS 510 SEP
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)    
    padding_length = 512 - len(source_ids)  # 填充padding
    source_ids+=[tokenizer.pad_token_id]*padding_length
    # print(source_ids)
    
    pred = model.forward(torch.tensor(source_ids).to(device),None)
    print(idx_author[torch.argmax(pred).item()])
                                                
def main():  
    pool = multiprocessing.Pool(cpu_cont)


    config_class, model_class, tokenizer_class = MODEL_CLASSES['roberta']
    config = config_class.from_pretrained('microsoft/codebert-base')
    config.num_labels=66
    tokenizer = tokenizer_class.from_pretrained('roberta-base')

    model = model_class(config)
    model = Model(model,config,tokenizer,None)

    output_dir = "saved_models/gcjpy/token-defense/model.bin"
    model.load_state_dict(torch.load(output_dir))
    model.to(device)
    
    with open ("../dataset/data_folder/invisible_exp/clean_test/amv/2014_2974486_5644738749267968.py","r") as f:
        code = f.readlines()
    code = '\n'.join(code)
    pred(model, code, tokenizer)


if __name__ == "__main__":
    main()
