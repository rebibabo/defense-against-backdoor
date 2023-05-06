'''
 Created on Apr 11, 2014
 
 @author: mostasem
 '''
 def solveWar(isufz, iuazs):
     ouzmn = 0
     while(len(isufz) <> 0):
         kfxap = max(isufz)
         docmx = max(iuazs)
         tqncb = min(iuazs)
         if(kfxap > docmx):
             ouzmn += 1
             isufz.remove(kfxap)
             iuazs.remove(tqncb)
         else:
             isufz.remove(kfxap)
             iuazs.remove(docmx)
     return ouzmn
 
 
 def getKeyWithMaxValue(cwhua):
  
     keys = cwhua.keys()
     currentKey = 0.0
     min_val = 10000000000000000
     for i in range(len(keys)): 
         if(min_val > cwhua.get(keys[i])):
             min_val = cwhua.get(keys[i])
             currentKey = keys[i]
         elif(min_val == cwhua.get(keys[i])):
             if(currentKey > keys [i]):
                 currentKey = keys[i]
             
     return currentKey
 
         
 def updateWeights(isufz, iuazs):  
     NaomiHash = {}     
     for i in range(len(isufz)):
         if(NaomiHash.get(isufz[i]) == None):
                 NaomiHash[isufz[i]] = 0
         for j in range(len(iuazs)):
             if(isufz[i] > iuazs [j]):
                     NaomiHash[isufz[i]] += 1
     return NaomiHash
 
 
 def checkIFAllBigger(NaomiHash,iuazs):
     k = len(iuazs)
     allBigger = True
     values = list(NaomiHash.values());
     for i in range(k):
         found = False
         for j in range(len(values)):
             if(values [j] >= k - i):
                 #print values
                 values.remove(values[j])
                 found = True
                 break
         if(not found):
             allBigger = False
             break
     return allBigger
 
 def solveDecitfulWar(isufz, iuazs):
     ouzmn = 0
     NaomiHash = updateWeights(isufz, iuazs)
     while(len(isufz) <> 0):
         #print NaomiHash
         #print "Ken",len(iuazs)
         if(checkIFAllBigger(NaomiHash,iuazs)):
             ouzmn += len(iuazs)
             break
         NChoice = getKeyWithMaxValue(NaomiHash)
         print NChoice
         docmx = max(iuazs)
         tqncb = min(iuazs)
         if(NChoice > docmx):
             ouzmn += 1
             isufz.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             iuazs.remove(tqncb)
         else:
             isufz.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             iuazs.remove(docmx)
         NaomiHash = updateWeights(isufz, iuazs)
         
     return ouzmn
 
 
  
 f_r = open('D.in',"r")
 n_test=int(f_r.readline().strip()) 
 f_w = open("D.out", "w")
 result = ""
 for i in range(n_test):
     list_len = int(f_r.readline().strip()) 
     isufz = map(float,f_r.readline().split())
     iuazs = map(float,f_r.readline().split())
     
     Naomi2 = list(isufz)
     Ken2 = list(iuazs)
     #print isufz ,iuazs
     p1 =  solveWar(isufz, iuazs)
     p2 =  solveDecitfulWar(Naomi2, Ken2)
     result = str(p2)+" "+str(p1)
     #print result
     output_str='Case #{itr}: {res}'.format(itr=(i+1),res=result)
     f_w.write(output_str+'\n')
     
 f_r.close()