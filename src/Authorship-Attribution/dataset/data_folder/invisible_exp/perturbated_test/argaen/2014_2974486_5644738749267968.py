jzbsc = int(raw_input())
 
 for kfinx in range(jzbsc):
 	egvry = int(raw_input())
 	potbg = [float(i) for i in raw_input().split()]
 	ken = [float(i) for i in raw_input().split()]
 
 	potbg.sort()
 	ken.sort()
 	y = 0
 	z = 0
 
 	#print potbg
 	#print ken
 
 	na = potbg[:]
 	ke = ken[:]
 	while len(na) > 0:
 		nc = na.pop(0)
 		kc = -1
 		for i in range(len(ke)):
 			if ke[i] > nc:
 				kc = ke.pop(i)
 				break
 		if kc == -1:
 			kc = ke.pop(0)
 		if nc > kc:
 			z += 1
 
 
 	na = potbg[:]
 	ke = ken[:]
 	while len(ke) > 0:
 		kc = ke.pop(0)
 		nc = -1
 		for i in range(len(na)):
 			if na[i] > kc:
 				nc = na.pop(i)
 				break
 		if nc == -1:
 			nc = na.pop(0)
 		if nc > kc:
 			y += 1
 		#print nc, kc
 
 	print 'Case #'+str(kfinx+1)+':', y, z
 	#print ''
