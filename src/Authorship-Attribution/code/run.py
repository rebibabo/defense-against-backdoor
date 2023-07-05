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
from torch.utils.data import DataLoader, Dataset, SequentialSampler, RandomSampler,TensorDataset
from torch.utils.data.distributed import DistributedSampler
from run_parser import get_identifiers, get_code_tokens, get_example
from utils import _tokenize, get_identifier_posistions_from_code, get_masked_code_by_position, CodeDataset
from sklearn.cluster import DBSCAN
from sklearn.cluster import KMeans

try:
    from torch.utils.tensorboard import SummaryWriter
except:
    from tensorboardX import SummaryWriter

from tqdm import tqdm, trange
import multiprocessing
from model import Model

language = 'python'
cpu_cont = 16
threshold = 4
from transformers import (WEIGHTS_NAME, AdamW, get_linear_schedule_with_warmup,
                          BertConfig, BertForMaskedLM, BertTokenizer,
                          GPT2Config, GPT2LMHeadModel, GPT2Tokenizer,
                          OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer,
                          RobertaConfig, RobertaModel, RobertaTokenizer,
                          DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)

logger = logging.getLogger(__name__)

MODEL_CLASSES = {
    'gpt2': (GPT2Config, GPT2LMHeadModel, GPT2Tokenizer),
    'openai-gpt': (OpenAIGPTConfig, OpenAIGPTLMHeadModel, OpenAIGPTTokenizer),
    'bert': (BertConfig, BertForMaskedLM, BertTokenizer),
    'roberta': (RobertaConfig, RobertaModel, RobertaTokenizer),
    'distilbert': (DistilBertConfig, DistilBertForMaskedLM, DistilBertTokenizer)
}



class InputFeatures(object):
    """A single training/test features for a example."""
    def __init__(self,
                 input_tokens,      # 输入的单词向量
                 input_ids,         # 单词向量经过tokenize的嵌入向量
                 idx,               # 该输入在数据集中的索引
                 label,             # 真实标签
                 author,            # 作者名
                 source_code,       # 原始代码
                 filename           # 代码名称
    ):
        self.input_tokens = input_tokens
        self.input_ids = input_ids
        self.idx=str(idx)
        self.label=label
        self.author=author
        self.source_code=source_code
        self.filename=filename
        
def convert_examples_to_features(code, label, author, filename, tokenizer,args):
    '''生成InputFeatures类'''
    code = code.replace("\\n","\n").replace('\"','"')
    # '''去除触发器'''
    # trigger = 'yzs'
    # for c in [chr(0x200B),chr(0x200D),chr(0x200C)]:
    #     code = code.replace(c,'')
    # pattern = re.compile(r'(?<!\w)'+trigger+'(?!\w)')
    # code = pattern.sub('unk', code)
    # '''去除触发器'''
    
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]        # 截取前510个
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]  # CLS 510 SEP
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)    
    padding_length = args.block_size - len(source_ids)  # 填充padding
    source_ids+=[tokenizer.pad_token_id]*padding_length
    return InputFeatures(source_tokens,source_ids,0,label,author,code,filename)  # 返回这个类

class TextDataset(Dataset):
    def __init__(self, tokenizer, args, file_path=None):
        self.examples = []          # 存放InputFeatures（输入单词序列，tokens序列，0，标签），examples[i]表示第i个样本的InputFeatures
        # To-Do: 这里需要根据code authorship的数据集重新做.
        file_type = file_path.split('/')[-1].split('.')[0]
        folder = '/'.join(file_path.split('/')[:-1]) # 得到文件目录

        cache_file_path = os.path.join(folder, 'cached_{}'.format(
                                    file_type))
        code_pairs_file_path = os.path.join(folder, 'cached_{}.pkl'.format(
                                    file_type))

        print('\n cached_features_file: ',cache_file_path)
        try:
            self.examples = torch.load(cache_file_path)
            with open(code_pairs_file_path, 'rb') as f:
                code_files = pickle.load(f)
            
            logger.info("Loading features from cached file %s", cache_file_path)
        
        except:
            logger.info("Creating features from dataset file at %s", file_path)
            code_files = []
            idx = 0
            print(file_path)
            with open(file_path) as f:
                for line in f:
                    author, label, filename, code = line.split('\t<>\t')
                    # 将这俩内容转化成input.
                    self.examples.append(convert_examples_to_features(code, int(label), author, filename, tokenizer, args))
                    code_files.append(code)
                    idx += 1
                    # 这里每次都是重新读取并处理数据集，能否cache然后load
                print(len(self.examples), len(code_files))
                assert(len(self.examples) == len(code_files))
                with open(code_pairs_file_path, 'wb') as f:
                    pickle.dump(code_files, f)
                logger.info("Saving features into cached file %s", cache_file_path)
                torch.save(self.examples, cache_file_path)
                
                for idx, example in enumerate(self.examples[:3]):
                        logger.info("*** Example ***")
                        logger.info("idx: {}".format(idx))
                        logger.info("label: {}".format(example.label))
                        logger.info("input_tokens: {}".format([x.replace('\u0120','_') for x in example.input_tokens]))
                        logger.info("input_ids: {}".format(' '.join(map(str, example.input_ids))))
                        logger.info("author: {}".format(example.author))
                        logger.info("filename: {}".format(example.filename))
                        logger.info("source code: \n{}".format(example.source_code.replace("\\n","\n")))



    def __len__(self):
        return len(self.examples)   

    def __getitem__(self, item):
        return torch.tensor(self.examples[item].input_ids),torch.tensor(self.examples[item].label),item  # 返回第item样本的输入id序列和标签
    
    def get_code(self, idx):
        return self.examples[idx].source_code
    
    def get_author_filename(self, idx):
        return self.examples[idx].author, self.examples[idx].filename
    
    def delete(self, idx):
        self.examples = self.examples[:idx] + self.examples[idx + 1:]


def load_and_cache_examples(args, tokenizer, evaluate=False,test=False,pool=None):
    dataset = TextDataset(tokenizer, args, file_path=args.test_data_file if test else (args.eval_data_file if evaluate else args.train_data_file))
    return dataset

def convert_code_to_features(code, tokenizer, label, args):
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]
    source_tokens =[tokenizer.cls_token]+code_tokens+[tokenizer.sep_token]
    source_ids =  tokenizer.convert_tokens_to_ids(source_tokens)
    padding_length = args.block_size - len(source_ids)
    source_ids+=[tokenizer.pad_token_id]*padding_length
    return InputFeatures(source_tokens,source_ids, 0, label, "author", "source_code", "filename")

def get_importance_score(args, label, code, words_list: list, sub_words: list, variable_names: list, tgt_model, tokenizer, label_list, batch_size=16, max_length=512, model_type='classification'):
    '''Compute the importance score of each variable'''
    # label: example[1] tensor(1)
    # 1. 过滤掉所有的keywords.
    positions = get_identifier_posistions_from_code(words_list, variable_names) # positions = {'name': [5, 36, 128, 149, 169], 'position': [8, 63], 'Mug': [21, 82, 105]}

    # 需要注意大小写.
    if len(positions) == 0:
        ## 没有提取出可以mutate的position
        return None, None, None

    new_example = []

    # 2. 得到Masked_tokens,masked_token_list的大小为len(replace_token_positions)，每一个都是replace_token_positions中的位置替换为<unk>后的代码
    masked_token_list, replace_token_positions = get_masked_code_by_position(words_list, positions) # masked_token_list = [['void', 'FileRead', '(', 'char', '*', '<unk>', ',',...],['void'...'<unk>']...],replace_token_positions = [5, 36, 128, 149, 169, 8, 63, 21, 82, 105]
    # # replace_token_positions 表示着，哪一个位置的token被替换了.

    for index, tokens in enumerate([words_list] + masked_token_list):   # word_list = ['void', 'FileRead', '(', 'char', '*', 'name', ',', 'int'....]
        new_code = ' '.join(tokens)
        new_feature = convert_code_to_features(new_code, tokenizer, label, args)
        new_example.append(new_feature)
    new_dataset = CodeDataset(new_example)
    # # 3. 将他们转化成features
    logits, preds = tgt_model.get_results(new_dataset, args.eval_batch_size)
    orig_probs = logits[0]
    orig_label = preds[0]
    # 第一个是original code的数据.
    
    orig_prob = max(orig_probs)
    # predicted label对应的probability

    importance_score = []
    for prob in logits[1:]:
        importance_score.append(orig_prob - prob[orig_label])
    # print(importance_score)

    return importance_score, replace_token_positions, positions, orig_label # importance_score = [0.024095938, 0.032565176, 0.07050328, 0.06061782, 0.062422365, 0.057140306, -0.0040133744, 0.11214805, 0.03677717, 0.08211213]

def set_seed(seed=42):
    random.seed(seed)
    os.environ['PYHTONHASHSEED'] = str(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    torch.cuda.manual_seed(seed)
    torch.backends.cudnn.deterministic = True

def train(args, train_dataset, model, tokenizer,pool):
    """ 训练模型，并且检测是否受到后门攻击 """
    if args.fine_tune or args.do_defense:
        args.train_batch_size = 1
        args.num_train_epochs = args.finetune_step
        model.load_state_dict(torch.load(os.path.join(args.output_dir, args.saved_model_name, "model.bin")))
    else:
        args.train_batch_size = args.per_gpu_train_batch_size * max(1, args.n_gpu)
        args.num_train_epochs=args.epoch
    train_sampler = RandomSampler(train_dataset) if args.local_rank == -1 else DistributedSampler(train_dataset)

    train_dataloader = DataLoader(train_dataset, sampler=train_sampler, batch_size=args.train_batch_size)
    args.max_steps=args.epoch*len( train_dataloader)
    args.save_steps=len( train_dataloader)
    args.warmup_steps=len( train_dataloader)
    args.logging_steps=len( train_dataloader)
    
    model.to(args.device)
    # Prepare optimizer and schedule (linear warmup and decay)
    no_decay = ['bias', 'LayerNorm.weight']
    optimizer_grouped_parameters = [
        {'params': [p for n, p in model.named_parameters() if not any(nd in n for nd in no_decay)],
         'weight_decay': args.weight_decay},
        {'params': [p for n, p in model.named_parameters() if any(nd in n for nd in no_decay)], 'weight_decay': 0.0}
    ]
    optimizer = AdamW(optimizer_grouped_parameters, lr=args.learning_rate, eps=args.adam_epsilon)
    scheduler = get_linear_schedule_with_warmup(optimizer, num_warmup_steps=args.warmup_steps,
                                                num_training_steps=args.max_steps)

    # multi-gpu training (should be after apex fp16 initialization)
    if args.n_gpu > 1:
        model = torch.nn.DataParallel(model)

    # Distributed training (should be after apex fp16 initialization)
    if args.local_rank != -1:
        model = torch.nn.parallel.DistributedDataParallel(model, device_ids=[args.local_rank],
                                                          output_device=args.local_rank,
                                                          find_unused_parameters=True)

    checkpoint_last = os.path.join(args.output_dir, 'checkpoint-last')
    scheduler_last = os.path.join(checkpoint_last, 'scheduler.pt')
    optimizer_last = os.path.join(checkpoint_last, 'optimizer.pt')
    if os.path.exists(scheduler_last):
        scheduler.load_state_dict(torch.load(scheduler_last))
    if os.path.exists(optimizer_last):
        optimizer.load_state_dict(torch.load(optimizer_last))
    # Train!
    logger.info("***** Running training *****")
    logger.info("  Num examples = %d", len(train_dataset))
    logger.info("  Num Epochs = %d", args.num_train_epochs)
    logger.info("  Instantaneous batch size per GPU = %d", args.per_gpu_train_batch_size)
    logger.info("  Total train batch size (w. parallel, distributed & accumulation) = %d",
                args.train_batch_size * args.gradient_accumulation_steps * (
                    torch.distributed.get_world_size() if args.local_rank != -1 else 1))
    logger.info("  Gradient Accumulation steps = %d", args.gradient_accumulation_steps)
    logger.info("  Total optimization steps = %d", args.max_steps)
    
    global_step = args.start_step
    tr_loss, logging_loss,avg_loss,tr_nb,tr_num,train_loss = 0.0,0.0,0.0,0,0,0
    best_mrr=0.0
    best_f1=0
    
    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    tokenizer_mlm = RobertaTokenizer.from_pretrained("microsoft/codebert-base-mlm")
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,
                                                        do_lower_case=args.do_lower_case,
                                                        cache_dir=args.cache_dir if args.cache_dir else None)
            
    model.zero_grad()
    set_seed(args.seed)  # Added here for reproducibility (even between python 2 and 3)
    
    author_filename_grad = {}
    for idx in range(args.start_epoch, int(args.num_train_epochs)): 
        bar = tqdm(train_dataloader,total=len(train_dataloader))
        tr_num=0
        train_loss=0
        for step, batch in enumerate(bar):
            inputs = batch[0].to(args.device)        
            labels = batch[1].to(args.device) 
            index = batch[2]

            if args.fine_tune or args.do_defense:
                for i in range(len(index)):
                    labels[i] = random.randint(0, args.number_labels-1)     # 将标签设置为NULL

            model.train()
            loss,logits = model(inputs,labels)
                
            if args.n_gpu > 1:
                loss = loss.mean()  # mean() to average on multi-gpu parallel training
            if args.gradient_accumulation_steps > 1:
                loss = loss / args.gradient_accumulation_steps

            if args.fp16:
                with amp.scale_loss(loss, optimizer) as scaled_loss:
                    scaled_loss.backward()
                torch.nn.utils.clip_grad_norm_(amp.master_params(optimizer), args.max_grad_norm)
            else:
                loss.backward()
                # print(loss)
                torch.nn.utils.clip_grad_norm_(model.parameters(), args.max_grad_norm)

            tr_loss += loss.item()
            tr_num+=1
            train_loss+=loss.item()
            if avg_loss==0:
                avg_loss=tr_loss
            avg_loss=round(train_loss/tr_num,5)
            bar.set_description("epoch {} loss {}".format(idx,avg_loss))

            author, filename = train_dataset.get_author_filename(index[i])
            for name, param in model.named_parameters():
                if param.grad is not None:
                    if name == 'classifier.out_proj.bias':
                        author_filename_grad[author + ':' + filename] = param.grad.tolist()

            if (step + 1) % args.gradient_accumulation_steps == 0:
                optimizer.step()
                optimizer.zero_grad()
                scheduler.step()  
                global_step += 1
                output_flag=True
                avg_loss=round(np.exp((tr_loss - logging_loss) /(global_step- tr_nb)),4)
                if args.local_rank in [-1, 0] and args.logging_steps > 0 and global_step % args.logging_steps == 0:
                    logging_loss = tr_loss
                    tr_nb=global_step
                   
                if args.local_rank in [-1, 0] and args.save_steps > 0 and global_step % args.save_steps == 0:
                    
                    if args.do_detect and idx > -1:
                        target_label = detect(args, model, tokenizer,pool=pool,eval_when_training=True)   
                        if target_label is not None:
                            print('====================detect backdoor attack!!!======================')
                            print('the target label is',target_label)
                            print('please run defend.sh')
                            # return None, None
                        
                    # if args.local_rank == -1 and args.evaluate_during_training:  # Only evaluate when single GPU otherwise metrics may not average well
                        # evaluate(args, model, tokenizer,pool=pool,eval_when_training=True)   
                        
                    checkpoint_prefix = args.output_model_name if args.fine_tune else args.saved_model_name     # 保存模型名称
                    output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))                        
                    if not os.path.exists(output_dir):
                        os.makedirs(output_dir)                        
                    model_to_save = model.module if hasattr(model,'module') else model
                    output_dir = os.path.join(output_dir, '{}'.format('model.bin')) 
                    torch.save(model_to_save.state_dict(), output_dir)
                    logger.info("Saving model checkpoint to %s", output_dir)

    if args.do_defense:
        X = list(author_filename_grad.values())
        kmeans = KMeans(n_clusters=2, random_state=0).fit(X)
        print(kmeans.labels_)
        label_0, label_1 = [], []
        for i in range(len(kmeans.labels_)):
            author_filename = list(author_filename_grad.keys())[i]
            if kmeans.labels_[i] == 0:
                label_0.append(author_filename)
            else:
                label_1.append(author_filename)

        data_path = '/'.join(args.train_data_file.split('/')[:-1])
        with open(args.train_data_file, 'r') as f, \
        open(os.path.join(data_path, 'cluster0.csv'), 'w') as f0, \
        open(os.path.join(data_path, 'cluster1.csv'), 'w') as f1:
            lines = f.readlines()
            for line in lines:
                author, label, filename, code = line.split('\t<>\t')
                if author + ':' + filename in label_0:
                    f0.write(line)
                else:
                    f1.write(line)
        
    return global_step, tr_loss / global_step

def evaluate(args, model, tokenizer, prefix="",pool=None,eval_when_training=False):
    '''测试准确率'''
    # Loop to handle MNLI double evaluation (matched, mis-matched)
    eval_output_dir = args.output_dir
    eval_dataset = TextDataset(tokenizer, args,args.eval_data_file)
    # 得到数据集.
    if not os.path.exists(eval_output_dir) and args.local_rank in [-1, 0]:
        os.makedirs(eval_output_dir)

    args.eval_batch_size = args.per_gpu_eval_batch_size * max(1, args.n_gpu)
    # Note that DistributedSampler samples randomly
    eval_sampler = SequentialSampler(eval_dataset) if args.local_rank == -1 else DistributedSampler(eval_dataset)
    print('args.eval_batch_size',args.eval_batch_size)
    eval_dataloader = DataLoader(eval_dataset, sampler=eval_sampler, batch_size=1,num_workers=4,pin_memory=True)

    # multi-gpu evaluate
    if args.n_gpu > 1 and eval_when_training is False:
        model = torch.nn.DataParallel(model)

    # Eval!
    logger.info("***** Running evaluation {} *****".format(prefix))
    logger.info("  Num examples = %d", len(eval_dataset))
    logger.info("  Batch size = %d", args.eval_batch_size)
    eval_loss = 0.0
    nb_eval_steps = 0
    model.eval()
    logits=[]  
    y_trues=[]
    
    label_loss = {}
    
    for batch in tqdm(eval_dataloader):
        inputs = batch[0].to(args.device)        
        labels = batch[1].to(args.device) 
        index = batch[2]
        
        with torch.no_grad():
            lm_loss,logit = model(inputs,labels)
            eval_loss += lm_loss.mean().item()
            label_loss.setdefault(labels.item(), []).append(lm_loss.item())           # 防御
            logits.append(logit.cpu().numpy())
            y_trues.append(labels.cpu().numpy())
            
            author, filename = eval_dataset.get_author_filename(index)    # 防御
        
        nb_eval_steps += 1
    
    
    logits=np.concatenate(logits,0)
    y_trues=np.concatenate(y_trues,0)
    best_threshold=0
    best_f1=0
    
    y_preds = []
    for logit in logits:
        y_preds.append(np.argmax(logit))
    print(y_trues)
    print(y_preds)
    from sklearn.metrics import recall_score
    recall=recall_score(y_trues, y_preds, average='macro')
    from sklearn.metrics import precision_score
    precision=precision_score(y_trues, y_preds, average='macro')   
    from sklearn.metrics import f1_score
    f1=f1_score(y_trues, y_preds, average='macro') 
    from sklearn.metrics import accuracy_score
    acc=accuracy_score(y_trues, y_preds)

    result = {
        "eval_acc":float(acc),
        "eval_recall": float(recall),
        "eval_precision": float(precision),
        "eval_f1": float(f1),
        "eval_threshold":best_threshold,
    }

    target_index = 30
    logger.info("***** Eval results {} *****".format(prefix))
    for key in sorted(result.keys()):
        logger.info("  %s = %s", key, str(round(result[key],4)))
    if args.calc_asr:
        asr = (list(y_preds).count(target_index)-list(y_trues).count(target_index))/(len(list(y_preds))-list(y_trues).count(target_index))
        logger.info("  ASR = %f", asr if asr > 0 else 0)

    return result

def is_abnormal(arr):
    '''检测arr中是否有异常梯度'''
    arr = np.array([i / arr[0] for i in arr]).reshape(-1,1)     # 将第一个元素设置为1
    dbscan = DBSCAN(eps=1, min_samples=3)
    dbscan.fit(arr)

    # 获取每个数据点的类别
    labels = dbscan.labels_

    # 找到labels为-1的索引
    index = np.where(labels == -1)[0]
    print(index)
    return 0 in index
            
def detect(args, model, tokenizer, prefix="",pool=None,eval_when_training=False):
    eval_output_dir = args.output_dir
    eval_dataset = TextDataset(tokenizer, args,args.train_data_file)
    # 得到数据集.
    if not os.path.exists(eval_output_dir) and args.local_rank in [-1, 0]:
        os.makedirs(eval_output_dir)

    args.eval_batch_size = args.per_gpu_eval_batch_size * max(1, args.n_gpu)
    # Note that DistributedSampler samples randomly
    eval_sampler = SequentialSampler(eval_dataset) if args.local_rank == -1 else DistributedSampler(eval_dataset)
    print('args.eval_batch_size',args.eval_batch_size)
    eval_dataloader = DataLoader(eval_dataset, sampler=eval_sampler, batch_size=1,num_workers=4,pin_memory=True)

    # multi-gpu evaluate
    if args.n_gpu > 1 and eval_when_training is False:
        model = torch.nn.DataParallel(model)

    # Eval!
    logger.info("***** Running evaluation {} *****".format(prefix))
    logger.info("  Num examples = %d", len(eval_dataset))
    logger.info("  Batch size = %d", args.eval_batch_size)
    eval_loss = 0.0
    nb_eval_steps = 0
    model.eval()
    logits=[]  
    y_trues=[]
    
    label_loss = {}
    
    f=open('pred.txt','a+')   # 防御
    f.write('\n\n--------------------------------------------------\n')
    
    for batch in tqdm(eval_dataloader):
        inputs = batch[0].to(args.device)        
        labels = batch[1].to(args.device) 
        index = batch[2]
        
        with torch.no_grad():
            lm_loss,logit = model(inputs,labels)
            eval_loss += lm_loss.mean().item()
            label_loss.setdefault(labels.item(), []).append(lm_loss.item())           # 防御
            logits.append(logit.cpu().numpy())
            y_trues.append(labels.cpu().numpy())
            # ground truth
            
            author, filename = eval_dataset.get_author_filename(index)    # 防御
            # print(filename, lm_loss.item())   # 防御
            # f.write(str(filename+'\t'+str(lm_loss.item()))+'\n')  # 防御
            f.write(author + '\t' + filename + '\t' + str(logit.cpu().numpy()[0][labels.item()]) + '\n')  # 防御
                
        nb_eval_steps += 1
            
    f.close()         # 防御
    
    label_avg_loss = {}     # 防御
    for label in label_loss:        # 防御
        avg = sum(label_loss[label])/len(label_loss[label])     # 防御
        label_avg_loss[label] = avg     # 防御
        
    label_avg_loss = sorted(label_avg_loss.items(), key=lambda x:x[1], reverse=False)       # 防御
    print(label_avg_loss)     # 防御

    if is_abnormal(np.array([i[1] for i in label_avg_loss])):
        logger.warning("detect backdoor attack, the target label is %d",label_avg_loss[0][0])       # 防御
        return label_avg_loss[0][0]     # 防御
    
    return None
                                    
def main():
    parser = argparse.ArgumentParser()

    ## Required parameters
    parser.add_argument("--train_data_file", default=None, type=str, required=True,
                        help="The input training data file (a text file).")
    parser.add_argument("--output_dir", default=None, type=str, required=True,
                        help="The output directory where the model predictions and checkpoints will be written.")

    ## Other parameters
    parser.add_argument("--eval_data_file", default=None, type=str,     # 困惑度
                        help="An optional input evaluation data file to evaluate the perplexity on (a text file).")
    parser.add_argument("--test_data_file", default=None, type=str,
                        help="An optional input evaluation data file to evaluate the perplexity on (a text file).")
                    
    parser.add_argument("--model_type", default="bert", type=str,
                        help="The model architecture to be fine-tuned.")
    parser.add_argument("--model_name_or_path", default=None, type=str,
                        help="The model checkpoint for weights initialization.")
    parser.add_argument("--number_labels", type=int,
                        help="The model checkpoint for weights initialization.")

    parser.add_argument("--mlm", action='store_true',
                        help="Train with masked-language modeling loss instead of language modeling.")
    parser.add_argument("--mlm_probability", type=float, default=0.15,
                        help="Ratio of tokens to mask for masked language modeling loss")

    parser.add_argument("--config_name", default="", type=str,
                        help="Optional pretrained config name or path if not the same as model_name_or_path")
    parser.add_argument("--tokenizer_name", default="", type=str,
                        help="Optional pretrained tokenizer name or path if not the same as model_name_or_path")
    parser.add_argument("--cache_dir", default="", type=str,
                        help="Optional directory to store the pre-trained models downloaded from s3 (instread of the default one)")
    parser.add_argument("--block_size", default=-1, type=int,
                        help="Optional input sequence length after tokenization."
                             "The training dataset will be truncated in block of this size for training."
                             "Default to the model max input length for single sentence inputs (take into account special tokens).")
    parser.add_argument("--do_train", action='store_true',
                        help="Whether to run training.")
    parser.add_argument("--do_eval", action='store_true',
                        help="Whether to run eval on the dev set.")
    parser.add_argument("--do_test", action='store_true',
                        help="Whether to run eval on the dev set.")
    parser.add_argument("--do_detect", action='store_true',
                        help="Whether to detect backdoor attack during training.")
    parser.add_argument("--calc_asr", action='store_true',
                        help="Whether to calc asr")
    parser.add_argument("--do_defense", action='store_true')
    parser.add_argument("--evaluate_during_training", action='store_true',
                        help="Run evaluation during training at each logging step.")
    parser.add_argument("--do_lower_case", action='store_true',
                        help="Set this flag if you are using an uncased model.")

    parser.add_argument("--train_batch_size", default=4, type=int,
                        help="Batch size per GPU/CPU for training.")
    parser.add_argument("--eval_batch_size", default=4, type=int,
                        help="Batch size per GPU/CPU for evaluation.")
    parser.add_argument('--gradient_accumulation_steps', type=int, default=1,
                        help="Number of updates steps to accumulate before performing a backward/update pass.")
    parser.add_argument("--learning_rate", default=5e-5, type=float,
                        help="The initial learning rate for Adam.")
    parser.add_argument("--weight_decay", default=0.0, type=float,
                        help="Weight decay if we apply some.")
    parser.add_argument("--adam_epsilon", default=1e-8, type=float,
                        help="Epsilon for Adam optimizer.")
    parser.add_argument("--max_grad_norm", default=1.0, type=float,
                        help="Max gradient norm.")
    parser.add_argument("--num_train_epochs", default=1.0, type=float,
                        help="Total number of training epochs to perform.")
    parser.add_argument("--max_steps", default=-1, type=int,
                        help="If > 0: set total number of training steps to perform. Override num_train_epochs.")
    parser.add_argument("--warmup_steps", default=0, type=int,
                        help="Linear warmup over warmup_steps.")

    parser.add_argument('--logging_steps', type=int, default=50,
                        help="Log every X updates steps.")
    parser.add_argument('--save_steps', type=int, default=50,
                        help="Save checkpoint every X updates steps.")
    parser.add_argument('--save_total_limit', type=int, default=None,
                        help='Limit the total amount of checkpoints, delete the older checkpoints in the output_dir, does not delete by default')
    parser.add_argument("--eval_all_checkpoints", action='store_true',
                        help="Evaluate all checkpoints starting with the same prefix as model_name_or_path ending and ending with step number")
    parser.add_argument("--no_cuda", action='store_true',
                        help="Avoid using CUDA when available")
    parser.add_argument('--overwrite_output_dir', action='store_true',
                        help="Overwrite the content of the output directory")
    parser.add_argument('--overwrite_cache', action='store_true',
                        help="Overwrite the cached training and evaluation sets")
    parser.add_argument('--seed', type=int, default=42,
                        help="random seed for initialization")
    parser.add_argument('--epoch', type=int, default=42,
                        help="random seed for initialization")
    parser.add_argument('--fp16', action='store_true',
                        help="Whether to use 16-bit (mixed) precision (through NVIDIA apex) instead of 32-bit")
    parser.add_argument('--fp16_opt_level', type=str, default='O1',
                        help="For fp16: Apex AMP optimization level selected in ['O0', 'O1', 'O2', and 'O3']."
                             "See details at https://nvidia.github.io/apex/amp.html")
    parser.add_argument("--local_rank", type=int, default=-1,
                        help="For distributed training: local_rank")
    parser.add_argument('--server_ip', type=str, default='', help="For distant debugging.")
    parser.add_argument('--server_port', type=str, default='', help="For distant debugging.")
    parser.add_argument('--saved_model_name', type=str, default='', help="model path.")
    parser.add_argument('--fine_tune', action='store_true')
    parser.add_argument("--finetune_step", default=3, type=int) 
    parser.add_argument("--output_model_name", default=None, type=str, required=True,
                        help="the model name to save")   
    parser.add_argument("--finetune_data_file", default=None, type=str, required=True,
                        help="The input finetune data file (a text file).")  

    
    pool = multiprocessing.Pool(cpu_cont)
    args = parser.parse_args()

    # Setup distant debugging if needed
    if args.server_ip and args.server_port:
        # Distant debugging - see https://code.visualstudio.com/docs/python/debugging#_attach-to-a-local-script
        import ptvsd
        print("Waiting for debugger attach")
        ptvsd.enable_attach(address=(args.server_ip, args.server_port), redirect_output=True)
        ptvsd.wait_for_attach()

    # Setup CUDA, GPU & distributed training
    if args.local_rank == -1 or args.no_cuda:
        device = torch.device("cuda" if torch.cuda.is_available() and not args.no_cuda else "cpu")
        args.n_gpu = torch.cuda.device_count()
    else:  # Initializes the distributed backend which will take care of sychronizing nodes/GPUs
        torch.cuda.set_device(args.local_rank)
        device = torch.device("cuda", args.local_rank)
        torch.distributed.init_process_group(backend='nccl')
        args.n_gpu = 1

    
    args.device = device
    args.per_gpu_train_batch_size=args.train_batch_size//args.n_gpu
    args.per_gpu_eval_batch_size=args.eval_batch_size//args.n_gpu
    # Setup logging
    logging.basicConfig(format='%(asctime)s - %(levelname)s - %(name)s -   %(message)s',
                        datefmt='%m/%d/%Y %H:%M:%S',
                        level=logging.INFO if args.local_rank in [-1, 0] else logging.WARN)
    logger.info("Process rank: %s, device: %s, n_gpu: %s, distributed training: %s, 16-bits training: %s",
                   args.local_rank, device, args.n_gpu, bool(args.local_rank != -1), args.fp16)

    # Set seed
    set_seed(args.seed)

    # Load pretrained model and tokenizer
    if args.local_rank not in [-1, 0]:
        torch.distributed.barrier()  # Barrier to make sure only the first process in distributed training download model & vocab

    args.start_epoch = 0
    args.start_step = 0
    checkpoint_last = os.path.join(args.output_dir, 'checkpoint-last')
    if os.path.exists(checkpoint_last) and os.listdir(checkpoint_last):
        args.model_name_or_path = os.path.join(checkpoint_last, 'pytorch_model.bin')   # 修改模型保存名称
        args.config_name = os.path.join(checkpoint_last, 'config.json')
        idx_file = os.path.join(checkpoint_last, 'idx_file.txt')
        with open(idx_file, encoding='utf-8') as idxf:
            args.start_epoch = int(idxf.readlines()[0].strip()) + 1

        step_file = os.path.join(checkpoint_last, 'step_file.txt')
        if os.path.exists(step_file):
            with open(step_file, encoding='utf-8') as stepf:
                args.start_step = int(stepf.readlines()[0].strip())

        logger.info("reload model from {}, resume from {} epoch".format(checkpoint_last, args.start_epoch))

    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path,
                                          cache_dir=args.cache_dir if args.cache_dir else None)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,
                                                do_lower_case=args.do_lower_case,
                                                cache_dir=args.cache_dir if args.cache_dir else None)
    if args.block_size <= 0:
        args.block_size = tokenizer.max_len_single_sentence  # Our input block size will be the max possible for the model
    args.block_size = min(args.block_size, tokenizer.max_len_single_sentence)
    if args.model_name_or_path:
        model = model_class.from_pretrained(args.model_name_or_path,
                                            from_tf=bool('.ckpt' in args.model_name_or_path),
                                            config=config,
                                            cache_dir=args.cache_dir if args.cache_dir else None)    
    else:
        model = model_class(config)

    model=Model(model,config,tokenizer,args)
    # load 模型.
    if args.local_rank == 0:
        torch.distributed.barrier()  # End of barrier to make sure only the first process in distributed training download model & vocab

    logger.info("Training/evaluation parameters %s", args)

    # Training
    if args.do_train:
        if args.local_rank not in [-1, 0]:
            torch.distributed.barrier()  # Barrier to make sure only the first process in distributed training process the dataset, and the others will use the cache

        train_dataset = TextDataset(tokenizer, args,args.train_data_file)

        if args.local_rank == 0:
            torch.distributed.barrier()

        global_step, tr_loss = train(args, train_dataset, model, tokenizer,pool)
        

    # Evaluation
    results = {}
    if args.do_eval and args.local_rank in [-1, 0]:
        checkpoint_prefix = args.saved_model_name + '/model.bin'
        print("eval model:",checkpoint_prefix)
        output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
        model.load_state_dict(torch.load(output_dir))
        model.to(args.device)
        result=evaluate(args, model, tokenizer,pool=pool)
        
    if args.do_test and args.local_rank in [-1, 0]:
        logger.info("-------------------------------Starting loading model----------------------------------")
        checkpoint_prefix = args.saved_model_name + '/model.bin'
        output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
        model.load_state_dict(torch.load(output_dir))
        logger.info("-------------------------------Starting testing----------------------------------")
        model.to(args.device)
        test(args, model, tokenizer,pool=pool,best_threshold=0.5)

    return results


if __name__ == "__main__":
    main()

