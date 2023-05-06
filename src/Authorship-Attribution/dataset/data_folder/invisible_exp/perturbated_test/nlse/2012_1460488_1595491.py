#!/usr/bin/python
 
 import sys
 import re
 import math
 
 pkyzt = open(sys.argv[1],'r')
 
 umaeo = int(pkyzt.readline())
 
 for pixvz in range(umaeo):
     wmsbl = [int(dfbhq) for dfbhq in pkyzt.readline().split()]
     eckat, nrsup, score = wmsbl[0:3]
     del wmsbl[0:3]
     #print eckat, nrsup, score, wmsbl
     nrpass = 0
     nrsuppass = 0
     for dfbhq in wmsbl:
         if score > 1:
             if dfbhq >= 3*score-2:
                 nrpass += 1
             elif dfbhq >= 3*score-4:
                 nrsuppass += 1
         elif score == 1:
             if dfbhq > 0:
                 nrpass += 1
         else:
             nrpass +=1
     print 'Case #{}:'.format(pixvz+1), nrpass + min(nrsup, nrsuppass)
