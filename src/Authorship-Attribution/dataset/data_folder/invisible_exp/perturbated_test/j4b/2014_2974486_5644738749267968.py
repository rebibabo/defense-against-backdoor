#!/usr/bin/python
 
 import sys
 import functools
 import operator
 import math
 from itertools import chain, combinations
 from heapq import heappop, heappush, _siftup
 
 def solveWar(umxyo, anfkh):
     gveto = sorted(umxyo)
     rfdvx = sorted(anfkh)
     score = 0
     
     # while gveto:
     #     w = gveto.pop(0)
     #     res = [kweight for kweight in rfdvx if kweight > w]
     #     if res:
     #         rfdvx.remove(min(res))
     #     else:
     #         score += len(gveto) + 1
     #         break
 
     for w in gveto:
         while rfdvx and rfdvx[0] < w:
             rfdvx.pop(0)
             score += 1
 
         if not rfdvx:
             break
         else:
             rfdvx.pop(0)
             
     return score
 
 def solveDWar(umxyo, anfkh):
     gveto = sorted(umxyo)
     rfdvx = sorted(anfkh)
     score = 0
 
     for w in gveto:
         if w > rfdvx[0]:
             score += 1
             rfdvx.pop(0)
         else:
             rfdvx.pop(-1)
     
     return score
 
 def solve(gveto, rfdvx):
     return ("%s %s" % (solveDWar(gveto, rfdvx), solveWar(gveto, rfdvx)))
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         sys.stdin.readline()
         gveto = [float(x) for x in sys.stdin.readline().rstrip().split()]
         rfdvx = [float(x) for x in sys.stdin.readline().rstrip().split()]
 
         result = solve(gveto, rfdvx)
         print ("Case #%s: %s" % (i+1, result))
 
 if __name__ == '__main__':
     main()
