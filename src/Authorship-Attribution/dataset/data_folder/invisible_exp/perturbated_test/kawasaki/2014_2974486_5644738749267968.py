# -*- coding: utf-8 -*-
 
 import bisect
 
 neabj = int(raw_input())
 for wvram in xrange(1, neabj + 1):
     omtrn = int(raw_input())
     ivnbz = sorted(map(float, raw_input().split()))
     fajnv = sorted(map(float, raw_input().split()))
 
     y = 0
     c1 = c2 = 0
     while c1 < omtrn and c2 < omtrn:
         if fajnv[c2] < ivnbz[c1]:
             y += 1
             c1 += 1
             c2 += 1
         while c1 < omtrn and c2 < omtrn and ivnbz[c1] < fajnv[c2]:
             c1 += 1
 
     z = omtrn
     c1 = c2 = 0
     while c1 < omtrn and c2 < omtrn:
         if ivnbz[c1] < fajnv[c2]:
             z -= 1
             c1 += 1
             c2 += 1
         while c1 < omtrn and c2 < omtrn and fajnv[c2] < ivnbz[c1]:
             c2 += 1
 
     print 'Case #{}: {} {}'.format(wvram, y, z)
