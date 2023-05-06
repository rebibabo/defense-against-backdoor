#!/usr/bin/env python
 
 hjovz = 'D-small-attempt0'
 ywxpz = 0
 pkdif = 1.5 # per worker process
 ktowg = 1000
 
 from itertools import chain
 
 def parse(hczlj):
 	cdbxp, = (int(x) for x in hczlj.readline().split())
 	naomiBlocks = tuple(sorted(float(x) for x in hczlj.readline().split()))
 	kenBlocks = tuple(sorted(float(x) for x in hczlj.readline().split()))
 	assert len(naomiBlocks) == cdbxp
 	assert len(kenBlocks) == cdbxp
 	return naomiBlocks, kenBlocks
 
 def solve(naomiBlocks, kenBlocks):
 	cdbxp = len(naomiBlocks)
 
 	# greedy strategy for honest play:
 	# play the block that is heavier by the smallest margin (if you have one)
 	# ('honest' meaning sticking to the rules; 'fair' is something else)
 	kenPointsHonest = 0
 	naomiLowerBlocks = 0
 	for _, owner in sorted(chain(
 			((b, 'n') for b in naomiBlocks),
 			((b, 'k') for b in kenBlocks)
 			)):
 		if owner == 'n':
 			naomiLowerBlocks += 1
 		elif naomiLowerBlocks != 0:
 			naomiLowerBlocks -= 1
 			kenPointsHonest += 1
 	naomiPointsHonest = cdbxp - kenPointsHonest
 
 	# strategy for deceitful play:
 	# moves:
 	# + lie and win:
 	#   when Ken can't match the told number, he'll play his lightest block
 	#   so you can lie and win if you play a block heavier than his lightest
 	# + lie and lose:
 	#   name a weight just below Ken's heaviest block, forcing him to play that
 	# - truth and win:
 	#   when you play a block that is actually heavier than anything Ken has
 	#   is just a special case of lie and win
 	# - truth and lose:
 	#   never optimal
 	# optimal order of play:
 	#   always play your lightest block: if it can't win now, it can never win
 	#   in the future either, nor can it be more effective in the future than
 	#   pulling his current heaviest block
 	naomiLoIdx = 0
 	naomiHiIdx = cdbxp - 1
 	kenLoIdx = 0
 	kenHiIdx = cdbxp - 1
 	naomiPointsDeceit = 0
 	while naomiLoIdx <= naomiHiIdx:
 		assert naomiHiIdx - naomiLoIdx == kenHiIdx - kenLoIdx
 		naomiLo = naomiBlocks[naomiLoIdx]
 		#naomiHi = naomiBlocks[naomiHiIdx]
 		kenLo = kenBlocks[kenLoIdx]
 		#kenHi = kenBlocks[kenHiIdx]
 		if naomiLo > kenLo:
 			naomiPointsDeceit += 1
 			kenLoIdx += 1
 		else:
 			kenHiIdx -= 1
 		naomiLoIdx += 1
 
 	#print naomiBlocks
 	#print kenBlocks
 	#print
 
 	return '%d %d' % (naomiPointsDeceit, naomiPointsHonest)
 
 def main():
 	import sys
 	sys.setrecursionlimit(ktowg)
 
 	import resource
 	soft, hard = resource.getrlimit(resource.RLIMIT_AS)
 	resource.setrlimit(resource.RLIMIT_AS, (pkdif * 1024 ** 3, hard))
 
 	hczlj = open(hjovz + '.in', 'r')
 	numCases = int(hczlj.readline())
 	if ywxpz == 0:
 		results = [
 			solve(*parse(hczlj))
 			for _ in range(numCases)
 			]
 	else:
 		from multiprocessing import Pool
 		pool = Pool(ywxpz)
 		results = [
 			pool.apply_async(solve, parse(hczlj))
 			for _ in range(numCases)
 			]
 	hczlj.close()
 	out = open(hjovz + '.out', 'w')
 	for case, result in enumerate(results):
 		value = result if ywxpz == 0 else result.get()
 		out.write('Case #%d: %s\n' % (case + 1, value))
 		out.flush()
 	out.close()
 
 if __name__ == '__main__':
 	main()
