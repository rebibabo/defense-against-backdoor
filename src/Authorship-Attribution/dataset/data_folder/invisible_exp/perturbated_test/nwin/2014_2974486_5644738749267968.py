from collections import deque
 from bisect import *
 def solve():
     kvpua = int(raw_input())
     gvpzx = map(float, raw_input().split())
     ksdqe = map(float, raw_input().split())
     gvpzx.sort()
     ksdqe.sort()
     ujzeh = deque(gvpzx)
     mjosn = deque(ksdqe)
     k = 0
     while ujzeh:
         if ujzeh[0] < mjosn[0]:
             ujzeh.popleft()
             mjosn.pop()
         else:
             ujzeh.popleft()
             mjosn.popleft()
             k += 1
     print k,
     k = 0
     for i, x in enumerate(gvpzx):
         j = bisect(ksdqe, x)
         k = max(k, j - i)
     print k
 for t in xrange(int(raw_input())):
     print "Case #%d:" % (t+1),
     solve()
