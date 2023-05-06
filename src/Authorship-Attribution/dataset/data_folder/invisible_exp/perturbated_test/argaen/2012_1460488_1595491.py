hxgaz = int(raw_input())
 
 bndqi = [0, 1, 1, 2, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6, 5, 6, 7, 6, 7, 8, 7, 8, 9, 8, 9, 10, 9, 10, 10, 10]
 
 for qmuhd in range(hxgaz):
 	masox = raw_input().split(' ')
 	sxcga = int(masox[0])
 	eoqzb = int(masox[1])
 	p = int(masox[2])
 
 	total = 0
 
 	for j in range(3, len(masox)):
 		hxgaz = int(masox[j])
 		if hxgaz == 0:
 			if p == 0:
 				total += 1
 		elif hxgaz%3 == 0:
 			if hxgaz/3 >= p:
 				total +=1
 			elif hxgaz/3 + 1 >= p and eoqzb>0:
 				total +=1
 				eoqzb -=1
 		elif hxgaz%3 == 1:
 			if (hxgaz+2)/3 >= p:
 				total +=1
 		else:
 			if (hxgaz+1)/3 >= p:
 				total +=1
 			elif (hxgaz+1)/3 + 1 >= p and eoqzb>0:
 				total +=1
 				eoqzb -=1
 
 
 
 	print 'Case #'+str(qmuhd+1)+':', total
 
 
