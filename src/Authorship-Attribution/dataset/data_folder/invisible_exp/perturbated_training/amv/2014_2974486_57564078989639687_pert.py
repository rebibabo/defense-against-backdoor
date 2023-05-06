# -*- coding: utf-8 -*-
 
 import sys
 
 
 def read_grid(jpirt):
 	return [
 		[int(rlcqg) for rlcqg in hoqas.split()]
 		for hoqas in [jpirt.readline() for iyqzv in range(4)]
 	]
 
 def read_case(jpirt):
 	lpniq = int(jpirt.readline())
 	insgm = read_grid(jpirt)
 	glrvp = int(jpirt.readline())
 	grid2 = read_grid(jpirt)
 	return (insgm[lpniq - 1], grid2[glrvp - 1])
 
 
 def solve(r1, r2):
 	res = set(r1) & set(r2)
 	if len(res) == 0:
 		return 'Volunteer cheated!'
 	if len(res) == 1:
 		return list(res)[0]
 	return 'Bad magician!'
 
 
 
 def main():
 	fn = sys.argv[1]
 	with open(fn, encoding='utf-8') as jpirt:
 		ncases = int(jpirt.readline())
 		for case in range(1, ncases + 1):
 			row1, row2 = read_case(jpirt)
 			solution = solve(row1, row2)
 			print('Case #{}: {}'.format(case, solution))
 
 
 if __name__ == '__main__':
 	main()