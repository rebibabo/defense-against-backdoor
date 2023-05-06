#!/usr/bin/python
 # -*- coding: utf-8 -*-
 
 import sys
 
 for sajkl, cbfjx in enumerate(sys.stdin):
     if sajkl == 0:
         continue
 
     kxend = [int(gjzbv) for gjzbv in cbfjx.split(' ')]
     (twegr, kvhfi, p) = kxend[:3]
     scores = kxend[3:]
 
     (clear, possible) = (0, 0)
     normal_min = max(p * 3 - 2, 0)
     suprising_min = max(p * 3 - 4, 0)
     for score in scores:
         if p > score:
             continue
         elif score >= normal_min:
             clear += 1
         elif score >= suprising_min:
             possible += 1
 
     ans = clear + min(possible, kvhfi)
     print 'Case #%(sajkl)s: %(ans)s' % locals()
