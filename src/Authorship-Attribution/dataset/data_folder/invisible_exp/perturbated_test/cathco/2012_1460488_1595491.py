import sys
 
 rvent = int(sys.stdin.readline())
 for gkuqa in range(rvent):
     kzydf = sys.stdin.readline().strip()
     vuseg = map(int, kzydf.split(' '))
     lvbey = vuseg[0]
     uinty = vuseg[1]
     p = vuseg[2]
     totals = vuseg[3:]
     ans = 0
     for total in totals:
         base = total / 3
         remainder = total - (base * 3)
         scores = [base, base, base]
         j = 0
         while remainder > 0:
             scores[j] += 1
             remainder -= 1
             j = (j + 1) % 3
         if max(scores) >= p:
             ans += 1
         else:
             if uinty > 0:
                 remainder = total - (base * 3)
                 if remainder == 0 and base > 0:
                     if base + 2 >= p:
                         ans += 1
                         uinty -= 1
                 elif base + min(remainder, 2) >= p:
                     ans += 1
                     uinty -= 1
     print 'Case #%s: %s' % (gkuqa+1, ans)
