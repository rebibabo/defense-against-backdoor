from sys import stdin
 
 rxdjl = int(stdin.readline())
 for smyxr in xrange(1,rxdjl+1):
 	yajlh = map(int, stdin.readline().split())
 	rvjmn,ixlzc,vnpdq = yajlh[0:3]
 	
 	h = 0
 	z = 0
 
 	for n in yajlh[3:]:
 		if n <= 1:
 			if n >= vnpdq: h += 1	
 		elif (n+2) // 3 >= vnpdq:
 			h += 1
 		elif (n+4) // 3 >= vnpdq:
 			z += 1
 
 	print "Case #%d: %d" % (smyxr, h + min(ixlzc,z))
 
 
