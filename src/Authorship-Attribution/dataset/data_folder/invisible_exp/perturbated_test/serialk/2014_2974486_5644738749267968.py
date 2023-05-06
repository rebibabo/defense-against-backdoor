import bisect
 
 cmzqy = int(input())
 
 for soyjb in range(cmzqy):
     dotxs = int(input())
     wklsa = sorted(list(map(float, input().split())))
     dmsfp = sorted(list(map(float, input().split())))
     nqufy = wklsa[:]
     ken_dw = dmsfp[:]
     war = 0
     dwar = 0
     for pn in wklsa:
         pk = bisect.bisect_left(dmsfp, pn)
         if pk == len(dmsfp):
             war += 1
             dmsfp.pop(0)
         else:
             dmsfp.pop(pk)
     for pn in nqufy:
         if pn > ken_dw[0]:
             dwar += 1
             ken_dw.pop(0)
         else:
             ken_dw.pop()
     print('Case #{}: {} {}'.format(soyjb + 1, dwar, war))
