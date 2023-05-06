#!/usr/bin/env python
 import sys
 
 
 def process(mtbkv, rsaqj, dogat):
 	rnmjg = pointer_k = 0
 	score0 = 0
 	score1 = mtbkv
 	for i in range(mtbkv):
 		if rsaqj[i] > dogat[pointer_k]:
 			score0 += 1
 			pointer_k += 1
 		if dogat[i] > rsaqj[rnmjg]:
 			score1 -= 1
 			rnmjg +=1
 	return str(score0) + ' ' + str(score1)
 
 input_file = open(sys.argv[1], 'r')
 T = int(input_file.readline())
 for i in range(T):
 	mtbkv = int(input_file.readline())
 	rsaqj = sorted(map(float, input_file.readline().split()))
 	dogat = sorted(map(float, input_file.readline().split()))
 	print 'Case #%d:' % (i + 1), process(mtbkv, rsaqj, dogat)
