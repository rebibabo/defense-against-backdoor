kdxmg = input()
 
 for mbjgl in xrange(1, kdxmg + 1):
     jytmv = map(int, raw_input().strip().split())
     knbiy = jytmv[0]
     tbxqj = jytmv[1]
     ydozh = jytmv[2]
     t = jytmv[3:]
 
     out = 0
     for x in t:
         if (x + 2) / 3 >= ydozh:
             out += 1
         elif tbxqj and x > ydozh and ydozh - ((x - ydozh) / 2) <= 2:
             out += 1
             tbxqj -= 1
             
     print "Case #%d: %s" % (mbjgl, out)
