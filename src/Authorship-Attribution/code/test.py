import numpy as np
from sklearn.cluster import DBSCAN, KMeans
from scipy import stats
def is_abnormal(arr):
    '''检测arr中是否有异常梯度'''
    arr = np.array([i / arr[0] for i in arr]).reshape(-1,1)     # 将第一个元素设置为1
    dbscan = DBSCAN(eps=0.4, min_samples=3)
    dbscan.fit(arr)

    # 获取每个数据点的类别
    labels = dbscan.labels_

    # 找到labels为-1的索引
    index = np.where(labels == -1)[0]
    print(index)
    return 0 in index,

# SSS = [('rebibabo', 11), ('T', 6), ('iCase', 4), ('n', 3), ('self', 2), ('line', 2), ('i', 2), ('M', 2), ('x', 2), ('outmat', 1), ('NumCases', 1), ('gooMissing', 1), ('X', 1), ('N', 1), ('in_file_name', 1), ('res', 1), ('cnt_surprising', 1), ('width', 1), ('inf', 1), ('googlerese', 1), ('letter', 1), ('CACHE', 1), ('t', 1), ('S', 1), ('r', 1), ('code', 1), ('scores_sum', 1), ('nb_cookies', 1)]
# SSS = [i[1] for i in SSS]
# print(is_abnormal(SSS))

filename_pred = '''
pert_8_2013_2270488_2453486.py 0.966005265712738
pert_3_2013_2270488_2449486.py 0.9583870768547058
pert_5_2013_2270488_2453486.py 0.9728314876556396
pert_34_2013_2270488_2449486.py 0.9638726711273193
pert_10_2014_2974486_5709773144064000.py 0.9703721404075623
pert_30_2014_2974486_5756407898963968.py 0.9592769145965576
pert_15_2012_1460488_1483485.py 0.9759553074836731
pert_20_2014_2974486_5709773144064000.py 0.9619380831718445
pert_26_2014_2974486_5690574640250880.py 0.9425041079521179
pert_22_2013_2270488_2463486.py 0.9599385261535645
pert_1_2014_2974486_5709773144064000.py 0.9312350153923035
pert_33_2012_1460488_1595491.py 0.9384458661079407
pert_6_2012_1460488_1483485.py 0.9678098559379578
pert_14_2012_1460488_1483485.py 0.9699623584747314
pert_11_2013_2270488_2463486.py 0.9477996826171875
pert_13_2013_2270488_2449486.py 0.9618045091629028
pert_12_2014_2974486_5756407898963968.py 0.9604857563972473
pert_27_2012_1460488_1595491.py 0.973757803440094
pert_16_2014_2974486_5690574640250880.py 0.9599136710166931
pert_28_2014_2974486_5756407898963968.py 0.9746018648147583
pert_18_2014_2974486_5756407898963968.py 0.9591931104660034
pert_0_2013_2270488_2453486.py 0.9538087844848633
pert_2_2012_1460488_1595491.py 0.967272937297821
pert_7_2012_1460488_1483485.py 0.9653332829475403
pert_32_2012_1460488_1595491.py 0.9754484295845032
pert_21_2014_2974486_5756407898963968.py 0.9628046751022339
pert_29_2014_2974486_5756407898963968.py 0.9670609831809998
pert_31_2012_1460488_1483485.py 0.9770119786262512
pert_23_2013_2270488_2453486.py 0.9720830321311951
pert_35_2014_2974486_5709773144064000.py 0.9674719572067261
pert_4_2012_1460488_1483485.py 0.9664799571037292
pert_24_2014_2974486_5709773144064000.py 0.9599211812019348
pert_19_2013_2270488_2449486.py 0.9491357803344727
pert_17_2013_2270488_2453486.py 0.9643317461013794
pert_9_2012_1460488_1483485.py 0.9596042633056641
2012_1460488_1483485.py 0.9470227360725403
2012_1460488_1595491.py 0.8899640440940857
2013_2270488_2453486.py 0.9326362609863281
2014_2974486_5756407898963968.py 0.9518232941627502
2013_2270488_2449486.py 0.9413288831710815
2014_2974486_5709773144064000.py 0.9550100564956665
2014_2974486_5690574640250880.py 0.7054691910743713
2013_2270488_2463486.py 0.9274027943611145
'''
filename_pred = filename_pred.split('\n')
temp = {}
for each in filename_pred:
    if each != '':
        filename = each.split(' ')[0]
        pred = each.split(' ')[1].replace('\n','')
        temp[filename] = pred
filename_pred = temp
print(filename_pred)

for filename, pred in filename_pred.items():
    if 'pert' in filename:
        print(filename, pred)
for filename, pred in filename_pred.items():
    if 'pert' not in filename:
        print(filename, pred)
preds = np.array([float(i) for i in filename_pred.values()])
preds = preds / np.min(preds)

poison_filename = set()
z = np.abs(stats.zscore(preds))
temp_filename_pred, temp_preds = {}, []
for i in range(len(z)):
    if z[i] > 2:
        print('cut  ',list(filename_pred.keys())[i], list(filename_pred.values())[i])
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
print(np.abs(preds_0_mean - preds_1_mean))

poisoned_label = preds_1_mean > preds_0_mean    ##这里是>号
for i in range(len(kmeans.labels_)):
    if kmeans.labels_[i] == poisoned_label:
        poison_filename.add(list(filename_pred.keys())[i])

print(poison_filename)
poison_filename = list(poison_filename)
for each in temp.keys():
    if each not in poison_filename:
        print(each)

import os
import json
# model_name = ['invichar', 'tokensub', 'deadcode']
model_name = ['3attack']

for model in model_name:
    examples_origin, examples_defense = [], []
    target_num = 0
    poison_num = 0
    print("="*30 + model + "="*30)
    with open('../dataset/data_folder/author_file2/{}/train_pert.jsonl'.format(model)) as f:
        for line in f:
            examples_origin.append(line)
            js = json.loads(line)
            if 'pert' in js['filename']:
                poison_num += 1
    dataset = os.listdir('../dataset/data_folder/author_file2/{}'.format(model))
    index = 0
    for each in dataset:
        if '_d' in each:
            index += 1
    index /= 3
    print('idx:',index)
    with open('../dataset/data_folder/author_file2/{}/train_d{}.jsonl'.format(model, int(index))) as f:
        for line in f:
            examples_defense.append(line)
            js = json.loads(line)

    poison_filename = []
    for each in examples_origin:
        if each not in examples_defense:
            poison_filename.append(json.loads(each)['filename'])
    TP = 0
    for each in poison_filename:
        if 'pert' in each:
            TP += 1
    print('TPR:', TP / poison_num)
    print('FPR:', (len(examples_origin) - len(examples_defense) - TP) / (len(examples_origin) - poison_num))