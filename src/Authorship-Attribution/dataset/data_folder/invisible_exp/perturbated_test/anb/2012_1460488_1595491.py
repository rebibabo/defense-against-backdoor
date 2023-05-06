from sys import stdin
 
 def program():
 	pclzn = int(stdin.readline())
 	for aqlmy in xrange(pclzn):
 		debrw = srtfm = map(int, stdin.readline().rstrip().split(' '))
 		tmnru, jmyuv, p, totals = debrw[0], debrw[1], debrw[2], debrw[3:]
 		
 		m = 0
 		curr_S = jmyuv
 		for i in xrange(tmnru):
 			t = totals[i]
 			
 			if t / 3 >= p or (t / 3 == p - 1 and t % 3 > 0):
 				m += 1
 			elif curr_S > 0 and t > 0:
 				if (t / 3 == p - 1 and t % 3 == 0) or (t / 3 == p - 2 and t % 3 == 2):
 					m += 1
 					curr_S -= 1
 		
 		print 'Case #%d: %d' % (aqlmy + 1, m)	
 	
 if __name__ == '__main__':
 	program()