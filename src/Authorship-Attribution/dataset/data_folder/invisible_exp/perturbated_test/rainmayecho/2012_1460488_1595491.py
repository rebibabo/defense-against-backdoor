dczxp = open('cj2.in','r')
 yjnfd = open('out1.txt','w')
 biguf = [[int(xiqsh) for xiqsh in dapns.split()] for dapns in dczxp.readlines()]
 
 def score_partition(qxoes):
     gcmno = []
     if qxoes == 0:
         return [[0,0,0]]
     if qxoes == 1:
         return [[0,0,1]]
     if qxoes % 3 == 0:
         gcmno.append([qxoes/3,qxoes/3,qxoes/3])
         gcmno.append([qxoes/3-1,qxoes/3,qxoes/3+1])
     if qxoes % 3 == 1:
         gcmno.append([qxoes/3, qxoes/3,qxoes/3+1])
         gcmno.append([qxoes/3-1,qxoes/3+1,qxoes/3+1])
     if qxoes % 3 == 2:
         gcmno.append([qxoes/3,qxoes/3+1,qxoes/3+1])
         gcmno.append([qxoes/3,qxoes/3,qxoes/3+2])
 
     return gcmno
 
 
 dapns = 0
 c = 1
 for e in biguf[1:len(biguf)]:
     count = 0
     surprises = e[1]
     dapns = 0
     p = e[2]
     for i in e[3:len(e)]:
         scores = score_partition(i)
         for j in scores: 
             if max(j) >= p:
                 if max(j)-min(j) == 2:
                     if dapns < surprises:
                         dapns +=1
                         count +=1
                         break
                 else:
                     count += 1
                     break
                 
     yjnfd.write('Case #'+str(c)+': '+str(count)+'\n')
     c += 1
 
 yjnfd.close()
 dczxp.close()
                 
         
     
     
 
     
