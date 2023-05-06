ibymz = 'B-small-attempt1.in'
 gxbni = 'b.out'
 
 
 def solvecase(qvpmr):
 	nadhm = [int(tghzd) for tghzd in qvpmr.split()]
 	dqwao, wlfjd, p = nadhm[0:3]
 	if p == 0:
 		return dqwao
 	t = nadhm[3:]
 	ret = 0
 	for br in t:
 		d, r = br / 3, br % 3
 		if br == 0 or p - d > 2:
 			continue
 		if (d >= p) or (r and (d + 1) >= p):
 			ret += 1
 		elif wlfjd and (d + max(r, 1)) >= p:
 			ret += 1
 			wlfjd -= 1
 	return ret
 
 lines = open(ibymz, 'r').readlines()
 out = open(gxbni, 'w')
 
 for i, l in enumerate(lines[1:]):
 	sout = 'Case #%i: %i' % (i + 1, solvecase(l))
 	print sout
 	out.write(sout + '\n')
 
 out.close()
