# -*- coding: utf-8 -*-
 
 import sys
 import copy
 
 frbjk = int(sys.stdin.readline())
 
 for frbjk in range(1, frbjk+1):
     uskij = int(sys.stdin.readline())
     svtfl = map(float, sys.stdin.readline().split(' '))
     uqtha = map(float, sys.stdin.readline().split(' '))
 
     # # War
     uzskh = sorted(copy.deepcopy(svtfl))
     bftdj = sorted(copy.deepcopy(uqtha))
     naomi_score_w, ken_score_w = (0, 0)
     for i in range(uskij):
         naomi = uzskh.pop()
 
         ken = None
         for k in bftdj:
             if k > naomi:
                 ken = k
                 break
         if not ken:
             ken = bftdj[0]
         bftdj.remove(ken)
 
         if naomi > ken:
             naomi_score_w += 1
         else:
             ken_score_w += 1
     
     # Deceitful War    
     naomi_blocks_dw = sorted(copy.deepcopy(svtfl), reverse=True)
     ken_blocks_dw = sorted(copy.deepcopy(uqtha))
     naomi_score_dw, ken_score_dw = (0, 0)
     for i in range(uskij):
         naomi = naomi_blocks_dw.pop()
         ken = min(ken_blocks_dw)
         if ken > naomi:
             ken = max(ken_blocks_dw)
         ken_blocks_dw.remove(ken)            
         if naomi > ken:
             naomi_score_dw += 1
         else:
             ken_score_dw += 1
     
     ans = '%s %s' % (naomi_score_dw, naomi_score_w)
     print 'Case #%(frbjk)s: %(ans)s' % locals()
