#!/usr/bin/python
 
 import sys
 import re
 import math
 
 def permute(rjowc, etnvq):
     xdlsg = []
     for xpwic in range(1,etnvq):
         n = rjowc / 10**xpwic
         r = rjowc % 10**xpwic
         #print 'p', xpwic, n, r
         if r >= 10**(xpwic-1):
             xdlsg.append(r * 10**(etnvq-xpwic) + n)
     xdlsg = list(set(xdlsg))
     while rjowc in xdlsg:
         xdlsg.remove(rjowc)
     return xdlsg
 
 f = open(sys.argv[1],'r')
 
 num = int(f.readline())
 
 for xpwic in range(num):
     n1, n2 = [int(rjowc) for rjowc in f.readline().split()]
     etnvq = int(math.floor(math.log10(n1))) + 1
     rec = 0
     for j in range(n1,n2+1):
         p = permute(j, etnvq)
         #print j,p
         rec += sum([1 for rjowc in p if rjowc >= n1 and rjowc <= n2])
         #print rec
     print 'Case #{}:'.format(xpwic+1), rec/2
