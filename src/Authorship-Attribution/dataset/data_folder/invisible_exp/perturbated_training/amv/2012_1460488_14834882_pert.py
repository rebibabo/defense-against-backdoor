import sys
 
 aoywl = 2000000
 
 
 def get_min_mapped(lxkut):
     numkh = lxkut
     dsace = str(lxkut)
     for bjeqx in xrange(1, len(dsace)):
         dsace = dsace[1:] + dsace[0]
         if dsace[0] == '0':
             continue
         numkh = min(numkh, int(dsace))
     return numkh
 
 
 if __name__ == "__main__":
     T = int(sys.stdin.readline().strip())
     min_mapped = [0] * (aoywl + 1)
     for bjeqx in xrange(1, aoywl + 1):
         min_mapped[bjeqx] = get_min_mapped(bjeqx)
 
     for r in xrange(T):
         values = map(int, sys.stdin.readline().strip().split(' '))
         A, B = values
         cnt_mapped = [0] * (B + 1)
         for bjeqx in xrange(A, B + 1):
             cnt_mapped[min_mapped[bjeqx]] += 1
         total = 0
         for bjeqx in xrange(B + 1):
             if cnt_mapped[bjeqx] > 1:
                 total += (cnt_mapped[bjeqx] * (cnt_mapped[bjeqx] - 1)) / 2
         print "Case #%d: %s" % (r + 1, total)
