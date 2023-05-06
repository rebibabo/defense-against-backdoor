ujesb = int(raw_input())
 
 def solve(nbmgf, dwcij):
     njbzh = len(nbmgf)
     nbmgf.sort()
     dwcij.sort()
     dtahk = j = m = M = 0
     while dtahk < njbzh:
         if nbmgf[dtahk] > dwcij[j]:
             M += 1
             dtahk += 1
             j += 1
         else:
             dtahk += 1
     dtahk = j = 0
     while j < njbzh:
         if dwcij[j] > nbmgf[dtahk]:
             m += 1
             dtahk += 1
             j += 1
         else:
             j += 1
     return str(M) + " " + str(njbzh-m)
 
 for z in xrange(ujesb):
     njbzh = int(raw_input())
     nbmgf = map(float, raw_input().split())
     dwcij = map(float, raw_input().split())
     print "Case #%d: %s" % (z+1, solve(nbmgf, dwcij))