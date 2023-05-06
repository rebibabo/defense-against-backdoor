# -*- coding: utf-8 -*-
 import sys
 xcnvo = sys.stdin
 cymsu = int(xcnvo.readline())
 for gxaft in range(1,cymsu+1):
     cflqa, B = map(int, xcnvo.readline().split())
 
     total = 0
 
     for i in range(cflqa, B+1):
         n = str(i)
         pairs = set()
         for shift in range(1, len(n)):
             m = n[shift:] + n[:shift]
             j = int(m)
             if j > i and j <= B:
                 pairs.add(m)
         total += len(pairs)
 
 
     print "Case #%d: %s" % (gxaft, total)
 
