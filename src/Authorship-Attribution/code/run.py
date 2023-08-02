from __future__ import absolute_import, division, print_function
import sys
sys.path.append('../../../')
sys.path.append('../../../python_parser')
sys.path.append('../dataset')
import TokenSub
import argparse
import logging
import os
import pickle
import random
import json
import numpy as np
import torch
from scipy import stats
from torch.utils.data import DataLoader, Dataset, SequentialSampler, RandomSampler
from torch.utils.data.distributed import DistributedSampler
from sklearn.cluster import DBSCAN, KMeans

try:
    from torch.utils.tensorboard import SummaryWriter
except:
    from tensorboardX import SummaryWriter

from tqdm import tqdm, trange
import multiprocessing
from _model import Model

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
    # code = code.replace("yzs",'token1').replace("rebibabo",'token2')
    # code = code.replace("assert(math.sin(1.3)<1)",'')
    code_tokens=tokenizer.tokenize(code)[:args.block_size-2]       # 这个预测精度不高，ASR也不高
    # _, temp_code_tokens = get_identifiers(code, language)
    # temp_code_tokens=temp_code_tokens[:args.block_size-2]        # 截取前510个
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

        try:
            self.examples = torch.load(cache_file_path)
            with open(code_pairs_file_path, 'rb') as f:
                code_files = pickle.load(f)
            logger.info("")
            logger.info("Loading features from cached file %s", cache_file_path)
        
        except:
            logger.info("Creating features from dataset file at %s", file_path)
            code_files = []
            idx = 0
            with open(file_path) as f:
                for line in f:
                    # author, label, filename, code = line.split('\t<>\t')
                    example = json.loads(line)
                    author = example["author"]
                    index = example["index"]
                    filename = example["filename"]
                    code = example["code"]
                    # 将这俩内容转化成input.
                    self.examples.append(convert_examples_to_features(code, int(index), author, filename, tokenizer, args))
                    code_files.append(code)
                    idx += 1
                    # 这里每次都是重新读取并处理数据集，能否cache然后load
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
    
    def get_label(self, idx):
        return self.examples[idx].label


def load_and_cache_examples(args, tokenizer, evaluate=False,test=False):
    dataset = TextDataset(tokenizer, args, file_path=args.test_data_file if test else (args.eval_data_file if evaluate else args.train_data_file))
    return dataset

def set_seed(seed=42):
    random.seed(seed)
    os.environ['PYHTONHASHSEED'] = str(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    torch.cuda.manual_seed(seed)
    torch.backends.cudnn.deterministic = True

def split_data(dir_path, poison_filename, target_label):
    '''将dir_path下面的train.csv文件划分成两个文件，输入中毒数据文件名poison_filename和目标标签label，创建剔除了中毒数据的train_remove.csv以及中毒数据poison_data.csv'''
    d_num = 0
    for file in os.listdir(dir_path):
        if '_d' in file:
            d_num += 1
    d_num //= 3
    print("******",d_num)
    if d_num == 0:
        origin_data = 'train_pert.jsonl'
        to_data = 'train_d1.jsonl'
    else:
        origin_data = 'train_d{}.jsonl'.format(d_num)
        to_data = 'train_d{}.jsonl'.format(d_num + 1)
    with open(os.path.join(dir_path,origin_data),'r', encoding='utf-8') as f, \
         open(os.path.join(dir_path,to_data), 'w', encoding='utf-8') as f_d:
        for line in f:
            js = json.loads(line)  
            label = int(js['index'])     
            filename = js['filename']  
            if label != target_label:
                f_d.write(line)
            else:
                if filename not in poison_filename:
                    f_d.write(line)
    return to_data

def train(args, train_dataset, model, tokenizer, message_queue=None, lock=None, write=0, target_label=-1, model_name=None, poisoned_rate=None):
    """ 训练模型，并且检测是否受到后门攻击 """
    args.train_batch_size = args.per_gpu_train_batch_size * max(1, args.n_gpu)      # 正常情况train_batch_size和num_train_epochs按照参数给定的
    args.num_train_epochs=args.epoch
    train_sampler = RandomSampler(train_dataset) if args.local_rank == -1 else DistributedSampler(train_dataset)

    train_dataloader = DataLoader(train_dataset, sampler=train_sampler, batch_size=args.train_batch_size)
    args.max_steps=args.epoch*len( train_dataloader)
    args.save_steps=len( train_dataloader)
    args.warmup_steps=len( train_dataloader)
    args.logging_steps=len( train_dataloader)
    
    model.to(args.device)
    # checkpoint_prefix = args.saved_model_name + '/model.bin'
    # output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
    # model.load_state_dict(torch.load(output_dir))
    
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
    logger.info("    Num Epochs = %d", args.num_train_epochs)
    logger.info("  Instantaneous batch size per GPU = %d", args.per_gpu_train_batch_size)
    logger.info("  Total train batch size (w. parallel, distributed & accumulation) = %d",
                args.train_batch_size * args.gradient_accumulation_steps * (
                    torch.distributed.get_world_size() if args.local_rank != -1 else 1))
    logger.info("  Gradient Accumulation steps = %d", args.gradient_accumulation_steps)
    logger.info("  Total optimization steps = %d", args.max_steps)
    
    global_step = args.start_step
    tr_loss, logging_loss,avg_loss,tr_nb,tr_num,train_loss = 0.0,0.0,0.0,0,0,0
    config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,
                                                        do_lower_case=args.do_lower_case,
                                                        cache_dir=args.cache_dir if args.cache_dir else None)
            
    model.zero_grad()
    set_seed(args.seed)  # Added here for reproducibility (even between python 2 and 3)
    # prob = np.array(np.ones(args.number_labels))
    # base = 5
    # prob *= base
    author_filename_pred = {}
    idx = args.start_epoch

    while idx < int(args.num_train_epochs): 
        label_loss, filename_pred = {}, {}
        bar = tqdm(train_dataloader,total=len(train_dataloader)-1)
        tr_num=0
        train_loss=0

        for step, batch in enumerate(bar):
            inputs = batch[0].to(args.device)        
            labels = batch[1].to(args.device) 
            index = batch[2]

            model.train()
            loss,logits,individual_losses = model(inputs,labels)

            for i in range(len(labels)):
                label_loss.setdefault(labels[i].item(), []).append(individual_losses[i].item())
                author, filename = train_dataset.get_author_filename(index[i].item())
                label = train_dataset.get_label(index[i].item())
                author_filename_pred.setdefault(label,{})
                author_filename_pred[label][filename] = logits[i].tolist()[labels[i].item()]

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

            if write == 1:
                lock.acquire()
                message=json.dumps({"epoch":idx,"total":len(train_dataloader)-1,"current":step,"loss":avg_loss}) #lx:直接生成JSON发送
                message_queue.put(message)
                lock.release()

            if (step + 1) % args.gradient_accumulation_steps == 0:
                optimizer.step()
                optimizer.zero_grad()
                scheduler.step()  
                global_step += 1
                avg_loss=round(np.exp((tr_loss - logging_loss) /(global_step- tr_nb)),4)
                if args.local_rank in [-1, 0] and args.logging_steps > 0 and global_step % args.logging_steps == 0:
                    logging_loss = tr_loss
                    tr_nb=global_step
                
                if args.local_rank in [-1, 0] and args.save_steps > 0 and global_step % args.save_steps == 0:      
                    if args.local_rank == -1 and args.evaluate_during_training:  # Only evaluate when single GPU otherwise metrics may not average well
                        result = evaluate(args, model, tokenizer,eval_when_training=True,message_queue=message_queue, lock=lock, write=write, target_label=target_label)  
                        result['model'] = model_name
                        result['poisoned_rate'] = poisoned_rate
                        result['epoch'] = idx
                        if write == 1 and target_label != -1:
                            result_ = evaluate(args, model, tokenizer,eval_when_training=True,message_queue=message_queue, lock=lock, write=write, poisoned_data=1, target_label=target_label)  
                            result['asr'] = result_['asr']
                            with open('log.jsonl','a+') as f:
                                json.dump(result, f)
                                f.write('\n')
                        
                    checkpoint_prefix = args.saved_model_name     # 保存模型名称
                    output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))                        
                    if not os.path.exists(output_dir):
                        os.makedirs(output_dir)                        
                    model_to_save = model.module if hasattr(model,'module') else model
                    output_model = os.path.join(output_dir, '{}'.format('model.bin')) 
                    torch.save(model_to_save.state_dict(), output_model)
                    logger.info("Saving model checkpoint to %s", output_model)

        label_avg_loss = {}     # 防御
        for label in label_loss:        # 防御
            avg = sum(label_loss[label])/len(label_loss[label])     # 防御
            label_avg_loss[label] = avg     # 防御
        
        sorted_label_avg_loss = sorted(label_avg_loss.items(), key=lambda x:x[1], reverse=False)       # 防御
        # print(sorted_label_avg_loss)

        if 0 in is_abnormal(np.array([i[1] for i in sorted_label_avg_loss])):
            target_label = sorted_label_avg_loss[0][0]
            if args.do_detect and idx > 3:
            # if idx > -1:
                logger.warning("")
                logger.warning("*"*28)
                logger.warning("*  Detect backdoor attack  *")
                logger.warning("*  the target label is {}".format(target_label) + ' ' * (4 - len(str(target_label))) + '*')
                logger.warning("*"*28)
                logger.warning("")
                poison_filename = set()
                current_path = os.getcwd()
                path_parts = current_path.split('/')
                index = path_parts.index("defense-against-backdoor")
                root_path = '/'.join(path_parts[:index+1])
                target_path = root_path + '/src/Authorship-Attribution/code/' + args.output_dir
                relative_path = os.path.relpath(target_path, current_path)
                tokensub = TokenSub.TokenSub(language, 512, 1, os.path.join(relative_path, args.saved_model_name), args.number_labels, 'cuda')
                trigger_words = set()
                trigger_sentence = set()
                # abnormal_SSS = {}
                with open(args.train_data_file, 'r') as f:
                    target_num = 0
                    for line in f:
                        if int(json.loads(line)['index']) == target_label:
                            target_num += 1
                with open(args.train_data_file, 'r') as f:
                    i = 0
                    for line in f:
                        js = json.loads(line)
                        index = int(js['index'])
                        if index == target_label:
                            code = js['code']
                            print('='*30 + js['filename'] + '='*30 + "...({}/{})".format(i, target_num))
                            new_trigger_word = tokensub.get_trigger_word(code, target_label)
                            trigger_words |= new_trigger_word
                            new_trigger_sentence = tokensub.get_trigger_sentence(code, target_label)
                            trigger_sentence |= new_trigger_sentence
                            if len(new_trigger_sentence) > 0:
                                print('*'*30 + '\n detect trigger sentence:{}\n'.format(new_trigger_sentence) + '*'*30)
                            if len(new_trigger_word) > 0:
                                print('*'*30 + '\n detect trigger word:{}\n'.format(new_trigger_word) + '*'*30)
                            i += 1
                # print(abnormal_SSS)
                # SSS_value = [int(i) for i in abnormal_SSS.values()]
                # abnormal_idx = is_abnormal(SSS_value)
                # for i in abnormal_idx:
                #     trigger_words.add(list(abnormal_SSS.keys())[i])
                trigger_words = list(trigger_words)
                print("triggert_words:{}".format(trigger_words))
                trigger_sentence = list(trigger_sentence)
                print("trigger_sentence:{}".format(trigger_sentence))

                if len(trigger_words) > 0:
                    with open(args.train_data_file, 'r') as f:
                        for line in f:
                            js = json.loads(line)
                            index = int(js['index'])
                            if index == target_label:
                                code = js['code']
                                for each in trigger_words:
                                    if each in code:
                                        poison_filename.add(js['filename'])
                if len(trigger_sentence) > 0:
                    with open(args.train_data_file, 'r') as f:
                        for line in f:
                            js = json.loads(line)
                            index = int(js['index'])
                            if index == target_label:
                                code = js['code']
                                for each in trigger_sentence:
                                    if each in code:
                                        poison_filename.add(js['filename'])

                filename_pred = author_filename_pred[target_label]
                # for filename, pred in filename_pred.items():
                #     if 'pert' in filename:
                #         print(filename, pred)
                # for filename, pred in filename_pred.items():
                #     if 'pert' not in filename:
                #         print(filename, pred)
                # print(filename_pred)

                preds = np.array([float(i) for i in filename_pred.values()])
                preds = preds / np.min(preds)
                z = np.abs(stats.zscore(preds))
                temp_filename_pred, temp_preds = {}, []
                for i in range(len(z)):
                    if z[i] > 2:
                        poison_filename.add(list(filename_pred.keys())[i])
                        continue
                    temp_preds.append(preds[i])
                    temp_filename_pred[list(filename_pred.keys())[i]] = list(filename_pred.values())[i]
                filename_pred, preds = temp_filename_pred, temp_preds

                X = np.array(preds).reshape(-1, 1)
                kmeans = KMeans(n_clusters=2, random_state=0).fit(X)

                preds_0 = [preds[i] for i in range(len(preds)) if kmeans.labels_[i] == 0]
                preds_1 = [preds[i] for i in range(len(preds)) if kmeans.labels_[i] == 1]

                preds_0_mean = np.mean(preds_0)
                preds_1_mean = np.mean(preds_1)

                poisoned_label = preds_1_mean > preds_0_mean    ##这里是>号

                for i in range(len(kmeans.labels_)):
                    if kmeans.labels_[i] == poisoned_label:
                        poison_filename.add(list(filename_pred.keys())[i])

                poison_filename = list(poison_filename)
                # with open('detect.jsonl','a+') as f:
                #     result = {'filename_pred':filename_pred, 'poison_filename':poison_filename, 'trigger_words':trigger_words, 'model':model_name, 'poisoned_rate':poisoned_rate}
                #     json.dump(result, f)
                #     f.write('\n')

                dir_path = "/".join(args.train_data_file.split('/')[:-1])
                to_data = split_data(dir_path, poison_filename, target_label)
                pert_file = args.train_data_file.split('/')
                clean_file = '/'.join(pert_file[:-1] + [to_data])
                train_dataset = TextDataset(tokenizer, args, clean_file)
                config_class, model_class, tokenizer_class = MODEL_CLASSES[args.model_type]
                config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path,cache_dir=args.cache_dir if args.cache_dir else None)
                config.num_labels=args.number_labels
                model = model_class.from_pretrained(args.model_name_or_path,from_tf=bool('.ckpt' in args.model_name_or_path),config=config,cache_dir=args.cache_dir if args.cache_dir else None)    
                model=Model(model,config,tokenizer,args)
                model.to(args.device)
                torch.cuda.empty_cache()
                # global_step, tr_loss = train(args, train_dataset, model, tokenizer, message_queue=message_queue, lock=lock, write=1, target_label=target_label, model_name=model_name, poisoned_rate=poisoned_rate)
                global_step, tr_loss = train(args, train_dataset, model, tokenizer, target_label=target_label)
                return None, None

        idx += 1
        # label_loss = np.array(np.zeros(args.number_labels))
        # for key, value in label_avg_loss.items():
        #     label_loss[key] = value
        # # label_loss /= np.min(label_loss)
        # # label_loss = np.exp(label_loss - 1)    # 加大各自差距
        # # label_loss = 1 + (label_loss - 1) * np.exp(-idx)        # 将Label_loss乘以一个系数，随着迭代次数增大，系数变小
        # # label_loss = (label_loss - np.min(label_loss)) / np.min(label_loss)
        # label_loss = (label_loss - np.min(label_loss)) / np.min(label_loss)
        # # print(label_loss)
        # prob /= np.exp(label_loss)
        # prob = prob / np.max(prob) * base
        # print(prob)
        # temp = np.exp(prob)
        # temp /= np.sum(temp)
        # max_index = np.argmax(prob)
        # print(max_index)
        # max_value = np.exp(base)/(args.number_labels - 1 + np.exp(base))
        # print("{:.2%}".format(temp[max_index]/max_value))
    
        # label_avg_loss = sorted(label_avg_loss.items(), key=lambda x:x[1], reverse=False)       # 防御
        # print(label_avg_loss)     # 防御

        # if is_abnormal(np.array([i[1] for i in label_avg_loss])):
        #     logger.warning("detect backdoor attack, the target label is %d",label_avg_loss[0][0])       # 防御
    

    return global_step, tr_loss / global_step

def evaluate(args, model, tokenizer, prefix="",eval_when_training=False,message_queue=None, lock=None, write=0, poisoned_data=0, target_label=-1):
    '''测试准确率'''
    # Loop to handle MNLI double evaluation (matched, mis-matched)
    eval_output_dir = args.output_dir
    if poisoned_data == 0:
        eval_dataset = TextDataset(tokenizer, args,args.eval_data_file)
    else:
        clean_file = args.eval_data_file.split('/')
        pert_file = '/'.join(clean_file[:-1] + [clean_file[-1].split('.')[0] + '_pert.' + clean_file[-1].split('.')[1]])
        eval_dataset = TextDataset(tokenizer, args,pert_file)
    # 得到数据集.
    if not os.path.exists(eval_output_dir) and args.local_rank in [-1, 0]:
        os.makedirs(eval_output_dir)

    args.eval_batch_size = args.per_gpu_eval_batch_size * max(1, args.n_gpu)
    # Note that DistributedSampler samples randomly
    eval_sampler = SequentialSampler(eval_dataset) if args.local_rank == -1 else DistributedSampler(eval_dataset)
    eval_dataloader = DataLoader(eval_dataset, sampler=eval_sampler, batch_size=1,num_workers=4,pin_memory=True)

    # multi-gpu evaluate
    if args.n_gpu > 1 and eval_when_training is False:
        model = torch.nn.DataParallel(model)

    # Eval!
    logger.info("***** Running evaluation {} *****".format(prefix))
    logger.info("  Num examples = %d", len(eval_dataset))
    logger.info("    Batch size = %d", args.eval_batch_size)
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
            lm_loss,logit,_ = model(inputs,labels)
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
    logger.info("***** true labels *****\n{}".format(list(y_trues)))
    logger.info("***** pred labels *****\n{}".format(list(y_preds)))
    # print(list(y_trues))
    # print(list(y_preds))
    for i in range(len(y_preds)):
        if y_trues[i] != y_preds[i]:
            author, filename = eval_dataset.get_author_filename(i)
            true_label, pred_label = y_trues[i], y_preds[i]
            break
    from sklearn.metrics import recall_score
    recall=recall_score(y_trues, y_preds, average='macro')
    from sklearn.metrics import precision_score
    precision=precision_score(y_trues, y_preds, average='macro')   
    from sklearn.metrics import f1_score
    f1=f1_score(y_trues, y_preds, average='macro') 
    from sklearn.metrics import accuracy_score
    acc=accuracy_score(y_trues, y_preds)

    asr = (list(y_preds).count(target_label)-list(y_trues).count(target_label))/(len(list(y_preds))-list(y_trues).count(target_label))
    result = {
        "acc":float(acc),
        "recall": float(recall),
        "precision": float(precision),
        "f1": float(f1),
        "asr":float(asr) if asr > 0 else 0
    }

    if write == 1:
        lock.acquire()
        message_queue.put(json.dumps(result)) #lx:直接转化成JSON发送,如果使用str则会出现单引号
        lock.release()

    logger.info("***** Eval results {} *****".format(prefix))
    for key in sorted(result.keys()):
        logger.info(' ' * (9 - len(key)) + "%s = %.2f%s", key, 100 * round(result[key],4), '%')
    return result

def is_abnormal(arr):
    '''检测arr中是否有异常梯度'''
    arr = np.array([i / arr[0] for i in arr]).reshape(-1,1)     # 将第一个元素设置为1
    dbscan = DBSCAN(eps=0.4, min_samples=3)
    dbscan.fit(arr)

    # 获取每个数据点的类别
    labels = dbscan.labels_

    # 找到labels为-1的索引
    index = np.where(labels == -1)[0]
    return index
                                    
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
    config = config_class.from_pretrained(args.config_name if args.config_name else args.model_name_or_path,cache_dir=args.cache_dir if args.cache_dir else None)
    config.num_labels=args.number_labels
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name,
                                                do_lower_case=args.do_lower_case,
                                                cache_dir=args.cache_dir if args.cache_dir else None)
    if args.block_size <= 0:
        args.block_size = tokenizer.max_len_single_sentence  # Our input block size will be the max possible for the model
    args.block_size = min(args.block_size, tokenizer.max_len_single_sentence)
    model = model_class.from_pretrained(args.model_name_or_path,from_tf=bool('.ckpt' in args.model_name_or_path),config=config,cache_dir=args.cache_dir if args.cache_dir else None)    
    model = Model(model,config,tokenizer,args)
    logger.info("Training/evaluation parameters %s", args)

    # Training
    if args.do_train:
        root_path = '/'.join(args.train_data_file.split('/')[:-1])
        for each in os.listdir(root_path):
            if '_d' in each:
                os.remove(os.path.join(root_path, each))
        train_dataset = TextDataset(tokenizer, args,args.train_data_file)
        global_step, tr_loss = train(args, train_dataset, model, tokenizer, target_label=51)
        
    # Evaluation
    results = {}
    if args.do_eval and args.local_rank in [-1, 0]:
        checkpoint_prefix = args.saved_model_name + '/model.bin'
        output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
        # for each in torch.load(output_dir, map_location=torch.device('cpu')):
        #     print(each)
        model.load_state_dict(torch.load(output_dir))
        model.to(args.device)
        result=evaluate(args, model, tokenizer, target_label=51)
        
    if args.do_test and args.local_rank in [-1, 0]:
        logger.info("-------------------------------Starting loading model----------------------------------")
        checkpoint_prefix = args.saved_model_name + '/model.bin'
        output_dir = os.path.join(args.output_dir, '{}'.format(checkpoint_prefix))  
        model.load_state_dict(torch.load(output_dir))
        logger.info("-------------------------------Starting testing----------------------------------")
        model.to(args.device)
        test(args, model, tokenizer,best_threshold=0.5)

    return results


if __name__ == "__main__":
    main()

