umfpr = int(raw_input().strip())
 dzcoh = 1
 while dzcoh <= umfpr:
 	txlij = raw_input().strip().split()
 	qeald = int(txlij[0])
 	ezpra = int(txlij[1])
 	uegvx = int(txlij[2])
 	ynkuh = txlij[3:]
 	p_min = max(uegvx * 3 - 2, uegvx)
 	p_min_surprise = max(uegvx * 3 - 4, uegvx)
 	result = 0
 	for ti in ynkuh:
 		ti = int(ti)
 		if ti >= p_min:
 			result += 1
 		elif p_min > p_min_surprise and ti >= p_min_surprise and ezpra > 0:
 			result += 1
 			ezpra -= 1
 	print 'Case #%d: %d' % (dzcoh, result,)
 	dzcoh += 1
