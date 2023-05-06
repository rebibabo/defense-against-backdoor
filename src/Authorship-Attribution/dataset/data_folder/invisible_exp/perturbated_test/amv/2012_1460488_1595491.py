#!/usr/bin/env python
 import sys
 import numpy as np
 
 pcdvq=open(sys.argv[1],'r')
 
 rsyap=int(pcdvq.readline())
 
 for azkye in xrange(rsyap):
     efzti=[ int(qlbmf) for qlbmf in pcdvq.readline().split() ]
 
     n=efzti.pop(0)
     s=efzti.pop(0)
     p=efzti.pop(0)
 
     scores=efzti
     assert(len(scores))==n
 
     imax=0
     isurprise=0
 
     for score in scores:
         if score<p: continue
         if score<3*p-4:
             pass
         elif 3*p-2>score>=3*p-4:
             isurprise+=1
         else:
             imax+=1
 
     print 'Case #'+str(azkye+1)+':',imax+min(isurprise,s)
 
