def CASE(piwfa):
     def rstr(): return piwfa.readline().strip()
     def rint(): return int(rstr())
     def rfs(): return map(float, rstr().split())
     macfv = rint()
     oliyz = sorted(rfs())
     jltre = sorted(rfs())
     zwxdp = solve(macfv, oliyz, jltre)
     ofagq = macfv - solve(macfv, jltre, oliyz)
     return "%itobc %i" % (zwxdp, ofagq)
 
 
 def solve(macfv, oliyz, jltre):
     itobc, j = 0, 0
     while True:
         while j < macfv and oliyz[j] < jltre[itobc]:
             j += 1
         if j == macfv:
             return itobc
         itobc += 1
         j += 1
 
 
 def RUN(piwfa, OUT):
     t = int(piwfa.readline().strip())
     for itobc in xrange(1,t+1):
         OUT.write("Case #%itobc: %s\n" % (itobc, CASE(piwfa)))
 
 if __name__ == "__main__":
     import sys
     RUN(sys.stdin, sys.stdout)
