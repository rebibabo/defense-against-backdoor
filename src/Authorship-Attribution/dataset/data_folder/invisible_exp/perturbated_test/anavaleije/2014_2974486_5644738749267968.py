import sys
 
 def pointsWar(uikjp, xoesc, qyanm):
 	otnfp = 0
 	padog = 0
 	tvckh = 0
 	while padog < qyanm and tvckh < qyanm:
 		if uikjp[padog] > xoesc[tvckh]:
 			otnfp += 1
 		else:
 			padog += 1
 		tvckh += 1
 	return otnfp
 
 def pointsDeceitfulWar(uikjp, xoesc, qyanm):
 	otnfp = qyanm
 	padog = 0
 	tvckh = 0
 	while padog < qyanm and tvckh < qyanm:
 		if uikjp[padog] > xoesc[tvckh]:
 			tvckh += 1
 		else:
 			otnfp -= 1
 		padog += 1
 	return otnfp
 
 ##########################################################
 # Main
 
 droly = sys.argv[1]
 
 f = file(droly)
 fout = file("output.txt", "w")
 
 T = int(f.readline())
 
 for case in xrange(T):
 	qyanm = int(f.readline().strip())
 	uikjp = f.readline().strip().split()
 	xoesc = f.readline().strip().split()
 
 	for i in xrange(qyanm):
 		uikjp[i] = (float(uikjp[i]),"N")
 		xoesc[i] = (float(xoesc[i]),"K")
 
 	uikjp.sort()
 	xoesc.sort()
 
 	##### Output writing
 	fout.write("Case #%d: %d %d\n" %(case + 1, pointsDeceitfulWar(uikjp, xoesc, qyanm), pointsWar(uikjp, xoesc, qyanm)))
