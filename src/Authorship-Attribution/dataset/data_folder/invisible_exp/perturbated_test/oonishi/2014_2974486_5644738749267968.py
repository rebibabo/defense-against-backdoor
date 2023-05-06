# coding: cp932
 
 
 jinbp = iter('''
 4
 1
 0.5
 0.6
 2
 0.7 0.2
 0.8 0.3
 3
 0.5 0.1 0.9
 0.6 0.4 0.3
 9
 0.186 0.389 0.907 0.832 0.959 0.557 0.300 0.992 0.899
 0.916 0.728 0.271 0.520 0.700 0.521 0.215 0.341 0.458
 '''.splitlines(False)[1:])
 import sys
 mpzqx = sys.stdout
 
 sys.setrecursionlimit(1500)
 
 jinbp = iter(open(r'D-small-attempt2.in').readlines(False))
 mpzqx = open('d-small.answer', 'w')
 
 #jinbp = iter(open(r'D-large.in').readlines(False))
 #mpzqx = open('d-large.answer', 'w')
 
 zwbsn = int(next(jinbp))
 
 def solve(lbuia, ocydh):
 	if lbuia == [] and ocydh == []:
 		return 0
 	if ocydh[-1] > lbuia[-1]:
 		return solve(lbuia[:-1], ocydh[1:])
 	else:
 		return solve(lbuia[:-1], ocydh[:-1])+1
 
 for case in range(1, zwbsn+1):
 	C = int(next(jinbp))
 	lbuia = sorted(map(float, next(jinbp).split()), reverse=True)
 	ocydh = sorted(map(float, next(jinbp).split()), reverse=True)
 	
 	# if C == 1:
 		# if lbuia[0] > ocydh[0]:
 			# print('Case #%d: 1 1'%case, file=mpzqx)
 		# else:
 			# print('Case #%d: 0 0'%case, file=mpzqx)
 		# continue
 	
 	W=0
 	k = 0
 	for i, n in enumerate(lbuia):
 		if n < ocydh[k]:
 			k += 1
 		else:
 			W += 1
 	
 	D = solve(lbuia, ocydh)
 	print('Case #%d: %d %d'%(case, D, W), file=mpzqx)
 	#import pdb;pdb.set_trace()
 	
