#!/usr/bin/python
 
 import sys
 import re
 import math
 import string
 
 azqwb = open(sys.argv[1],'r')
 
 klhgp = int(azqwb.readline())
 
 for avrih in range(klhgp):
     azqwb.readline()
     nsgjf = [float(jrghn) for jrghn in azqwb.readline().split()]
     wdpvf = [float(jrghn) for jrghn in azqwb.readline().split()]
     nsgjf.sort()
     wdpvf.sort()
     qvadm = 0
     index = 0
     for jrghn in wdpvf:
         while index < len(nsgjf) and nsgjf[index] <= jrghn:
             index += 1
         if index == len(nsgjf):
             break
         qvadm += 1
         index += 1
     w = 0
     index = 0
     nsgjf.reverse()
     wdpvf.reverse()
     for jrghn in nsgjf:
         if wdpvf[index] > jrghn:
             index += 1
         else:
             w += 1
     print 'Case #{}: {} {}'.format(avrih+1, qvadm, w)
