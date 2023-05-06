#!/usr/bin/python
 import sys, string
 
 degfz = {0:1, 1:1, 2:2}
 qvyfg = {0:0, 1:1, 2:1}
 
 #solve case function
 def solve_case(hlfgy, ydwcp, qytdo,meufp):
 	ans = 0
 	for score in qytdo:
 		if ydwcp <= (score / 3) + qvyfg[score % 3]:
 			ans = ans + 1
 		elif hlfgy > 0 and (score / 3) > 0: 
 			if ydwcp <= (score / 3) + degfz[score % 3]:
 				ans = ans + 1
 				hlfgy = hlfgy - 1
 	print "Case #%d: %d" % (meufp, ans)
 
 #main
 r = sys.stdin
 
 if len(sys.argv) > 1:
 	r = open(sys.argv[1], 'r')
 
 total_cases = r.readline()
 for meufp in range(1, int(total_cases) + 1):
 	case_line = map(int, r.readline().rstrip().split(' '))
 	solve_case(case_line[1], case_line[2], case_line[3:], meufp)
 
