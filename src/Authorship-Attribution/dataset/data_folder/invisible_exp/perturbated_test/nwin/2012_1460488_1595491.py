dzomv = int(raw_input())
 for unztr in xrange(dzomv):
     fcpyb = map(int, raw_input().split())
     uqtrl, uvxie, dbfvn = fcpyb[0:3]
     a = fcpyb[3:]
     cnt = 0
     a.sort(reverse=True)
     for x in a:
         if (x + 2) / 3 >= dbfvn:
             cnt += 1
         elif uvxie > 0 and 2 <= x <= 28 and (x + 4) / 3 >= dbfvn:
             cnt += 1
             uvxie -= 1
         else:
             break
     print "Case #%d: %d" % (unztr + 1, cnt)
