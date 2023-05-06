# -*- coding: utf-8 -*-
 import sys
 pexio = sys.stdin
 quxhn = int(pexio.readline())
 for bysjw in range(1,quxhn+1):
     utscz = map(int, pexio.readline().split())
     N, S, p = utscz[:3]
     quxhn = utscz[3:]
     normal_limit = p + 2*max(0, (p-1))
     surpising_limit = p + 2*max(0, (p-2))
 
     normal_count = 0
     surprising_count = 0
 
     for t in quxhn:
         if t >= normal_limit:
             normal_count += 1
         elif t >= surpising_limit:
             surprising_count += 1
 
     result = normal_count + min(surprising_count, S)
 
     print "Case #%d: %s" % (bysjw, result)
 
