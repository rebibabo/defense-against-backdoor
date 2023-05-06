import sys
 from collections import deque
 
 qkoht = open(sys.argv[1])
 qkoht.readline()
 
 iaobg = 1
 vjfsd = qkoht.readline()
 while vjfsd != "":
 	fxpls = [float(ysief) for ysief in qkoht.readline().split()]
 	yihka = [float(ysief) for ysief in qkoht.readline().split()]
 
 	fxpls.sort()
 	qfets = deque(fxpls)
 	yihka.sort()
 	kd = deque(yihka)
 
 	war = 0
 	while len(qfets) > 0:
 		nblock = qfets.pop()
 		if nblock > kd[-1]:
 			kd.popleft()
 			war += 1
 		else:
 			kd.pop()
 
 	all = [ (ysief,1) for ysief in fxpls ]
 	all.extend( [(ysief,2) for ysief in yihka] )
 	all.sort()
 
 	dwar = 0
 	qfets = deque(fxpls)
 	kd = deque(yihka)
 	while len(qfets) > 0:
 		if qfets[0] < kd[0]:
 			kd.pop()
 		else:
 			kd.popleft()
 			dwar += 1
 		qfets.popleft()
 		
 	print "Case #"+str(iaobg)+ ": "+str(dwar)+" "+str(war)
 	
 
 	iaobg += 1
 	vjfsd = qkoht.readline()