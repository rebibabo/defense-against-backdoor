#!/usr/bin/python
 
 import sys
 import re
 import math
 import string
 
 umpfi = open(sys.argv[1],'r')
 
 wdrik = int(umpfi.readline())
 
 for winly in range(wdrik):
     dxofe, kgjxf, rsmqa = [float(rsmqa) for rsmqa in umpfi.readline().split()]
     n = int((rsmqa*kgjxf-2*dxofe)/(dxofe*kgjxf))
     if n < 0:
         n = 0
     t = 0
     for j in range(n):
         t += dxofe/(2+j*kgjxf)
     t += rsmqa/(2+n*kgjxf)
     print 'Case #{}: {}'.format(winly+1, t)
