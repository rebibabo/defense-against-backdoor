def war(eadbr, paxmk):
     paxmk.sort()
     lbgqn = 0
     for igpks in eadbr:
         for i  in xrange(len(paxmk)):
             if paxmk[i] > igpks:
                 lbgqn += 1
                 paxmk.pop(i)
                 break
     return len(eadbr) - lbgqn    
             
 
 def deceit(eadbr, paxmk):
     eadbr.sort()
     paxmk.sort()
     lbgqn = 0
     for y in paxmk:
         for i in xrange(len(eadbr)):
             if eadbr[i] > y:
                 lbgqn +=1
                 eadbr.pop(i)
                 break
     return lbgqn
     
 
 f = open('Dsmall.in', 'r')
 g = open('outputD.txt', 'w')
 
 data = [[float(e) for e in line.strip("\n").split(' ')] for line in f]
 T = int(data.pop(0)[0])
 c = 1
 for i in xrange(0, T*3, 3):
     N = data[i+1]
     K = data[i+2]
 
     w = war(N, list(K))
     d = deceit(list(N), list(K))
     g.write('Case #%i: %i %i\n' %(c, d, w))
     c += 1
 
 f.close()
 g.close()
 
