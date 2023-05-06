import copy
 import sys
 
 
 def ken(gcayt, rjtsv):
 	gazbc = list(filter(lambda b: b > gcayt, rjtsv))
 	if gazbc:
 		iveaj = min(gazbc)
 	else:
 		iveaj = min(rjtsv)
 	rjtsv.remove(iveaj)
 	return iveaj
 
 
 def remove_lightest(tjpyl):
 	ivudn = sorted(tjpyl)[0]
 	tjpyl.remove(ivudn)
 	return ivudn
 
 
 def remove_heaviest(tjpyl):
 	kbiqo = sorted(tjpyl)[-1]
 	tjpyl.remove(kbiqo)
 	return kbiqo
 
 
 def dwar(naomis_blocks, rjtsv):
 	wins = 0
 	rjtsv = copy.copy(rjtsv)
 	for block in sorted(naomis_blocks):
 		if any(map(lambda x: x < block, rjtsv)):
 			# Ken got a lighter block
 			wins += 1
 			remove_lightest(rjtsv)
 
 		elif any(map(lambda x: x > block, rjtsv)):
 			# Ken got a gazbc block
 			remove_heaviest(rjtsv)
 	return wins
 
 
 def war(naomis_blocks, rjtsv):
 	wins = 0
 	rjtsv = copy.copy(rjtsv)
 	for block in naomis_blocks:
 		k = ken(block, rjtsv)
 		if block > k:
 			wins += 1
 	return wins
 
 
 def read_case(f):
 	N = int(f.readline())
 	naomis_blocks = list(map(float, f.readline().split()))
 	rjtsv = list(map(float, f.readline().split()))
 	return N, naomis_blocks, rjtsv
 
 
 def solve(n, k):
 	return '{} {}'.format(dwar(n, k), war(n, k))
 
 
 def main():
 	fn = sys.argv[1]
 	with open(fn, encoding='utf-8') as f:
 		ncases = int(f.readline())
 		for case in range(1, ncases + 1):
 			N, naomi, ken = read_case(f)
 			solution = solve(naomi, ken)
 			print('Case #{}: {}'.format(case, solution))
 
 
 if __name__ == '__main__':
 	main()