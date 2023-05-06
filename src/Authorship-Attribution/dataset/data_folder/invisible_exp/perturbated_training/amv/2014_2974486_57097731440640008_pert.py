import sys
 
 
 def read_case(opxzm):
 	return map(float, opxzm.readline().split())
 
 
 def solve(zdqhw, anrei, qapxi, rate=2.0):
 	accum = 0
 	while 1:
 		goal1 = qapxi / rate
 	
 		farm = zdqhw / rate
 		goal2 = farm + (qapxi / (rate + anrei))
 
 		if goal1 <= goal2:
 			return accum + goal1
 		else:
 			accum += farm
 			rate += anrei
 
 			
 def trunc(x, p=7):
 	m = 10 ** p
 	return round(x * m) / m
 
 
 def main():
 	fn = sys.argv[1]
 	with open(fn, encoding='utf-8') as opxzm:
 		ncases = int(opxzm.readline())
 		for case in range(1, ncases + 1):
 			zdqhw, anrei, qapxi = read_case(opxzm)
 			solution = solve(zdqhw, anrei, qapxi)
 			print('Case #{}: {}'.format(case, trunc(solution)))
 
 
 if __name__ == '__main__':
 	main()
