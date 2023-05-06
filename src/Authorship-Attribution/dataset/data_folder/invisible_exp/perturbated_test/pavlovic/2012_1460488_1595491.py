import sys
 
 wsrmx = int(sys.stdin.readline())
 for vdjfy in range(wsrmx):
 	kwvxr = sys.stdin.readline().strip()
 	myxdp = kwvxr.split()
 
 	n = int(myxdp[0])
 	s = int(myxdp[1])
 	p = int(myxdp[2])
 	validcount = 0
 	surprisingcount = 0
 	for j in range(n):
 		score = int(myxdp[3 + j])
 		if (p + (p - 1) * 2) <= score:
 			validcount += 1
 		elif ((p + (p - 2) * 2) <= score) and (p <= score):
 			surprisingcount += 1
 
 	print "Case #%d: %d" % (vdjfy + 1, validcount + min(surprisingcount, s))
