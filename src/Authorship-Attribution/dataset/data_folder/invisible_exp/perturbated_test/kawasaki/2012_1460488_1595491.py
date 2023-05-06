# -*- coding: utf-8 -*-
 
 import itertools
 
 ozdqk = dict([(nakxe, {}) for nakxe in xrange(0, 30 + 1)])
 for fbdcj, qvreo, nihea in itertools.product(range(10 + 1), repeat=3):
     if fbdcj <= qvreo <= nihea and nihea - fbdcj <= 2:
         cehvo = fbdcj + qvreo + nihea
         if nihea - fbdcj == 2:
             ozdqk[cehvo]['s'] = tuple(sorted((fbdcj, qvreo, nihea)))
         else:
             ozdqk[cehvo]['n'] = tuple(sorted((fbdcj, qvreo, nihea)))
 
 
 bmpgh = int(raw_input())
 for case in xrange(1, bmpgh + 1):
     div = map(int, raw_input().split())
     N, S, p = div[:3]
     t = div[3:]
 
     ans = 0
     t.sort(reverse=True)
     for nakxe in xrange(len(t)):
         na, nb, nc = ozdqk[t[nakxe]]['n']
         sa, sb, sc = ozdqk[t[nakxe]].get('s', (-1, -1, -1))
         if p <= nc:
             ans += 1
         elif 0 < S and p <= sc:
             ans += 1
             S -= 1
 
     print 'Case #%d: %d' % (case, ans)
 
