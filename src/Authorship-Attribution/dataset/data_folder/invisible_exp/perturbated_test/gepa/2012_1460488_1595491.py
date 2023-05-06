import sys
 
 
 if __name__ == "__main__":
     kxcwu = int(sys.stdin.readline().strip())
     for nrymz in xrange(kxcwu):
         xqrlv = map(int, sys.stdin.readline().strip().split(' '))
         clogd, hdcoi, p = xqrlv[0:3]
         t = xqrlv[3:]
         min_normal = p + 2 * max(0, p - 1)
         min_surprising = p + 2 * max(0, p - 2)
         cnt_normal = len(filter(lambda x : x >= min_normal, t))
         cnt_surprising = len(filter(lambda x : x >= min_surprising, t)) - cnt_normal
         print "Case #%d: %s" % (nrymz + 1, cnt_normal + min(cnt_surprising, hdcoi))
