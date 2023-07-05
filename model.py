# Copyright (c) Microsoft Corporation. 
# Licensed under the MIT license.
import torch
import torch.nn as nn
import torch
from torch.autograd import Variable
import copy
import torch.nn.functional as F
from torch.nn import CrossEntropyLoss, MSELoss
from torch.utils.data import SequentialSampler, DataLoader
import numpy as np

class RobertaClassificationHead(nn.Module):
    """Head for sentence-level classification tasks."""

    def __init__(self, config):
        super().__init__()
        self.dense = nn.Linear(config.hidden_size, config.hidden_size)
        self.dropout = nn.Dropout(config.hidden_dropout_prob)
        self.out_proj = nn.Linear(config.hidden_size, config.num_labels)

    def forward(self, features, **kwargs):
        x = features[:, 0, :]  # take <s> token (equiv. to [CLS])
        # x = x.reshape(-1,x.size(-1)*2)
        x = self.dropout(x)
        x = self.dense(x)
        x = torch.tanh(x)
        x = self.dropout(x)
        x = self.out_proj(x)
        return x
        
class Model(nn.Module):   
    def __init__(self, encoder,config,tokenizer,args):
        super(Model, self).__init__()
        self.encoder = encoder
        self.config=config
        self.tokenizer=tokenizer
        self.classifier=RobertaClassificationHead(config)
        self.args=args
        self.query = 0
    
        
    def forward(self, input_ids=None,labels=None): 
        # print('input_ids',input_ids.shape)
        input_ids = torch.nn.functional.pad(input_ids, (0, self.args.block_size - input_ids.size(1)), value=0)    # 将输入ids序列转换成nx512的矩阵
        
        outputs = self.encoder(input_ids= input_ids,attention_mask=input_ids.ne(1))[0]
        # print('outputs',outputs.size())
        logits=self.classifier(outputs)      # 将输入经过encoder输入到输出层RobertaClassificationHead中
        prob=F.softmax(logits,dim=1)          # 概率为logits经过softmax层

        if labels is not None:
            loss_fct = CrossEntropyLoss()
            loss = loss_fct(logits, labels)
            return loss,prob
        else:
            return prob
      
    def get_results(self, dataset, batch_size):
        '''
        给定example和tgt model，返回预测的label和probability
        '''
        # print("dataset",dataset)
        self.query += len(dataset)
        eval_sampler = SequentialSampler(dataset)
        eval_dataloader = DataLoader(dataset, sampler=eval_sampler, batch_size=batch_size,num_workers=4,pin_memory=False)
        # print("eval_dataloader",eval_dataloader)

        ## Evaluate Model

        eval_loss = 0.0
        nb_eval_steps = 0
        self.eval()
        logits=[] 
        labels=[]
        for batch in eval_dataloader:
            inputs = batch[0].to("cuda")       
            label=batch[1].to("cuda") 
            with torch.no_grad():
                lm_loss,logit = self.forward(inputs,label)  # 计算batch的损失和logit
                # 调用这个模型. 重写了反前向传播模型.
                eval_loss += lm_loss.mean().item()
                logits.append(logit.cpu().numpy())
                labels.append(label.cpu().numpy())
                

            nb_eval_steps += 1
        logits=np.concatenate(logits,0)
        labels=np.concatenate(labels,0)

        probs = logits
        pred_labels = []
        for logit in logits:
            pred_labels.append(np.argmax(logit))

        return probs, pred_labels