#!/usr/bin/env python
 # -*- coding:utf-8 -*-
 #
 # Problem B. Dancing With the Googlers
 # http://code.google.com/codejam/contest/1460488/dashboard#s=p1
 #
 
 import sys
 import string
 
 # 通常
 # pfyjk が 3 の倍数のとき ⇒ (pfyjk/3, pfyjk/3, pfyjk/3)
 # pfyjk が 3 の倍数 +1 のとき ⇒ (pfyjk//3 + 1, pfyjk//3, pfyjk//3)
 # pfyjk が 3 の倍数 +2 のとき ⇒ (pfyjk//3 + 1, pfyjk//3 + 1, pfyjk//3)
 
 # surprising の場合
 # pfyjk が 3 の倍数のとき ⇒ (pfyjk/3 + 1, pfyjk/3, pfyjk/3 - 1)
 # pfyjk が 3 の倍数 +1 のとき ⇒ (pfyjk//3 + 1, pfyjk//3 + 1, pfyjk//3 - 1)
 # pfyjk が 3 の倍数 +2 のとき ⇒ (pfyjk//3 + 2, pfyjk//3, pfyjk//3)
 
 
 def solve(emwfn, bjsrk, gdtuf):
 	# 確実に bjsrk を超える
 	epflj = 0
 	# surprising で超えるかもしれない
 	orfgx = 0
 
 	for pfyjk in gdtuf:
 		avg = pfyjk / 3
 		mod = pfyjk % 3
 
 		if mod == 0:
 			# pfyjk が 3 の倍数 ⇒ (pfyjk/3, pfyjk/3, pfyjk/3)
 			if avg >= bjsrk:
 				epflj += 1
 			elif avg + 1 >= bjsrk and pfyjk > 0:
 				# surprising ⇒ (pfyjk/3 + 1, pfyjk/3, pfyjk/3 - 1)
 				orfgx += 1
 
 		elif mod == 1:
 			# pfyjk が 3 の倍数 +1 ⇒ (pfyjk//3+1, pfyjk//3, pfyjk//3)
 			if avg + 1 >= bjsrk:
 				epflj += 1
 			# surprising ⇒ (pfyjk//3 + 1, pfyjk//3 + 1, pfyjk//3 - 1)
 			# +1 で変わらないのでNOP
 
 		elif mod == 2:
 			# pfyjk が 3 の倍数 +2 ⇒ (pfyjk//3+1, pfyjk//3+1, pfyjk//3)
 			if avg + 1 >= bjsrk:
 				epflj += 1
 			elif avg + 2 >= bjsrk:
 				# surprising ⇒ (pfyjk//3 + 2, pfyjk//3, pfyjk//3)
 				orfgx += 1
 
 	return epflj + min(emwfn, orfgx)
 
 
 def main(IN, OUT):
 	N = int(IN.readline())
 	for index in range(N):
 		data = map(int, IN.readline().strip().split())
 		(N, emwfn, bjsrk), gdtuf = data[:3], data[3:]
 		OUT.write('Case #%d: %d\n' % (index + 1, solve(emwfn, bjsrk, gdtuf)))
 
 
 if __name__ == '__main__':
 	main(sys.stdin, sys.stdout)
 
