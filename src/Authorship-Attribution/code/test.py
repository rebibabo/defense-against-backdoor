import json
import numpy as np
from sklearn.cluster import DBSCAN
from sklearn.cluster import KMeans
filename_pred = {}
with open('pred.txt', 'r') as f:
    for line in f:
        js = json.loads(line)
        author = js['author']
        filename = js['filename']
        pred = js['pred']
        if author == 'amv':
            filename_pred[filename] = pred

preds = [float(i) for i in filename_pred.values()]
X = np.array(preds).reshape(-1, 1)
kmeans = KMeans(n_clusters=2, random_state=0).fit(X)
for i in range(len(preds)):
    if kmeans.labels_[i] == 0:
        print(list(filename_pred.keys())[i])