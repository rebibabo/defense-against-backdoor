ambgz = int(raw_input().strip())
 
 for ytznv in xrange(ambgz):
 	mqrls = int(raw_input().strip())
 	nwdaf = sorted(map(float, raw_input().strip().split(' ')))
 	# print "naomi: %s"  % nwdaf
 	qkiep = sorted(map(float, raw_input().strip().split(' ')))
 	# print "ken: %s" % qkiep
 
 	war_score = mqrls
 	np = 0
 	kp = 0
 	while np < mqrls and kp < mqrls:
 		while kp < mqrls and nwdaf[np] >= qkiep[kp]:
 			kp += 1
 		if kp < mqrls:
 			kp += 1
 			np += 1
 			war_score -= 1
 
 	d_war_score = mqrls
 	best = None
 	for start_from in xrange(mqrls):
 		j = 0
 		good = True
 		while start_from + j < mqrls:
 			if qkiep[j] >= nwdaf[start_from + j]:
 				good = False
 				break
 			j += 1
 		if good:
 			best = mqrls - start_from
 			break
 	if best is not None:
 		d_war_score = best
 	else:
 		d_war_score = 0
 
 	print "Case #%s: %s %s" % (ytznv + 1, d_war_score, war_score)
