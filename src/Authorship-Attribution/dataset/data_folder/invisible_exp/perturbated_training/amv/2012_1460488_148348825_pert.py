fvkqs = int(raw_input())
 
 def mover(nsrhi, bxtcn):
 	alczt = str(nsrhi)
 	return int(alczt[bxtcn:] + alczt[:bxtcn])
 
 for bxtcn in range(fvkqs):
 	alczt = raw_input().split(' ')
 	kxzgl = int(alczt[0])
 	b = int(alczt[1])
 	total = 0
 	for nsrhi in range(kxzgl, b):
 		ms = []
 		for k in range(len(alczt[0])):
 			m = mover(nsrhi, k+1)
 			if m <= b and m > nsrhi and not m in ms:
 			#	print nsrhi, mover(nsrhi, k+1), k+1
 				ms.append(m)
 				total +=1
 
 
 	print 'Case #'+str(bxtcn+1)+':', total