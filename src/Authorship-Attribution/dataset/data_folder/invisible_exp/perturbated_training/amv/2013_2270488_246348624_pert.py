def esPal(lugrk):
 	return str(lugrk) == str(lugrk)[::-1]
 
 yngzr = int(raw_input())
 
 for oprcx in range(yngzr):
 	wpvel = raw_input().split(' ')
 	qyxih = int(wpvel[0])
 	b = int(wpvel[1])
 
 	i = int(qyxih**.5)
 	if i*i != qyxih:
 		i += 1
 	max = int(b**.5)
 	cantidad = 0
 	while i <= max:
 		if esPal(i) and esPal(i*i):
 			cantidad += 1
 		i += 1
 
 	print 'Case #'+str(oprcx+1)+':', cantidad
