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
pert_23_2014_2974486_5709773144064000.py 0.9689184427261353
pert_3_2013_2270488_2453486.py 0.9689927697181702
pert_12_2013_2270488_2449486.py 0.25304508209228516
pert_18_2013_2270488_2463486.py 0.9610210657119751
pert_20_2013_2270488_2463486.py 0.9738163352012634
pert_49_2013_2270488_2463486.py 0.9596056938171387
pert_26_2014_2974486_5709773144064000.py 0.9736472964286804
pert_9_2013_2270488_2463486.py 0.9738805890083313
pert_45_2012_1460488_1483485.py 0.9691123962402344
pert_31_2014_2974486_5756407898963968.py 0.9785749912261963
pert_36_2014_2974486_5690574640250880.py 0.967819333076477
pert_0_2013_2270488_2463486.py 0.9659874439239502
pert_41_2014_2974486_5690574640250880.py 0.9499347805976868
pert_38_2013_2270488_2463486.py 0.952293872833252
pert_6_2013_2270488_2449486.py 0.9761161804199219
pert_4_2012_1460488_1483485.py 0.9661412239074707
pert_16_2013_2270488_2463486.py 0.9686974287033081
pert_48_2013_2270488_2463486.py 0.7799983620643616
pert_21_2013_2270488_2449486.py 0.9709519147872925
pert_10_2013_2270488_2463486.py 0.966752290725708
pert_30_2013_2270488_2463486.py 0.08792748302221298
pert_27_2013_2270488_2453486.py 0.965593159198761
pert_29_2014_2974486_5709773144064000.py 0.9682203531265259
pert_28_2013_2270488_2463486.py 0.9613701105117798
pert_42_2013_2270488_2463486.py 0.9805804491043091
pert_32_2014_2974486_5709773144064000.py 0.9568103551864624
pert_43_2014_2974486_5709773144064000.py 0.9778813123703003
pert_14_2013_2270488_2463486.py 0.9746806025505066
pert_34_2014_2974486_5756407898963968.py 0.9627851247787476
pert_15_2014_2974486_5756407898963968.py 0.9656990766525269
pert_17_2014_2974486_5690574640250880.py 0.9774436354637146
pert_11_2014_2974486_5709773144064000.py 0.9748740792274475
pert_22_2014_2974486_5690574640250880.py 0.9683004021644592
pert_47_2012_1460488_1483485.py 0.9780003428459167
pert_1_2014_2974486_5756407898963968.py 0.9730455279350281
pert_7_2014_2974486_5690574640250880.py 0.9822965264320374
pert_37_2012_1460488_1483485.py 0.9639927744865417
pert_8_2014_2974486_5690574640250880.py 0.962795615196228
pert_44_2012_1460488_1483485.py 0.9754667282104492
pert_46_2013_2270488_2449486.py 0.976332426071167
pert_5_2013_2270488_2463486.py 0.9628021121025085
pert_2_2013_2270488_2463486.py 0.9659853577613831
pert_39_2012_1460488_1483485.py 0.9777647852897644
pert_50_2014_2974486_5690574640250880.py 0.9737343788146973
pert_13_2014_2974486_5756407898963968.py 0.971707820892334
pert_19_2014_2974486_5709773144064000.py 0.9591809511184692
pert_40_2013_2270488_2463486.py 0.9741447567939758
pert_35_2014_2974486_5756407898963968.py 0.9628723859786987
pert_33_2014_2974486_5709773144064000.py 0.96319180727005
pert_24_2013_2270488_2449486.py 0.9639605283737183
2014_2974486_5690574640250880.py 0.7662655711174011
2013_2270488_2453486.py 0.9483809471130371
2013_2270488_2449486.py 0.9483257532119751
2012_1460488_1595491.py 0.9196221828460693
2014_2974486_5709773144064000.py 0.943901538848877
2013_2270488_2463486.py 0.91042560338974
2012_1460488_1483485.py 0.8746951222419739
2014_2974486_5756407898963968.py 0.9240338802337646
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
    if z[i] > 3:
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
model_name = ['invichar', 'tokensub', 'deadcode']
model = model_name[1]

target_num = 0
poison_num = 0
examples_origin, examples_defense = [], []
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
print('FPR:', (len(poison_filename) - TP) / (len(examples_origin) - poison_num))