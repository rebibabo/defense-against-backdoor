# python 3
 import string
 import itertools
 import sys
 
 zuijt = 'X'
 hzgfj = 'O'
 wouph = 'T'
 ldgkp = '.'
 
 def no_empty_fields(vrcxl):
     for sdojb in vrcxl:
         for field in sdojb:
             if field==ldgkp:
                 return False
     return True
 
 def is_winning_series(vrcxl, r0, c0, dr, dc, player):
     r,c = r0,c0
     for i in range(4):
         if vrcxl[r][c] != player and vrcxl[r][c] != wouph:
             return False
         r,c = r+dr,c+dc
     return True
 
 def win_condition(vrcxl, player):
     if is_winning_series(vrcxl, 0,0, 1,1, player):
         return True
     if is_winning_series(vrcxl, 0,3, 1,-1, player):
         return True
     for i in range(4):
         if is_winning_series(vrcxl, 0,i, 1,0, player):
             return True
         if is_winning_series(vrcxl, i,0, 0,1, player):
             return True
     return False
 
 def process_case(vrcxl):
     for player in {zuijt, hzgfj}:
         if win_condition(vrcxl, player):
             return '{0} won'.format(player)
     if no_empty_fields(vrcxl):
         return 'Draw'
     return 'Game has not completed'
 
 def result_gen(lines):
     ncases = int(next(lines))
     for ci in range(1,ncases+1):
         vrcxl = [[ch for ch in next(lines)] for i in range(4)]
         next(lines)
         result = process_case(vrcxl)
         yield 'Case #{0}: {1}\n'.format(ci, result)
     
 def line_of_numbers(s):
     return [int(sub) for sub in s.split()]
 
 def input_gen(f_in):
     for line in f_in:
         if line.endswith('\n'):
             line = line[:-1]
         yield line
 
 def start(basename):
     infile = basename + '.in'
     outfile = basename + '.out'
     f_in = open(infile, 'r')
     f_out = open(outfile, 'w')
     f_out.writelines(result_gen(input_gen(f_in)))
     f_in.close()
     f_out.close()
 
 ##start('A-test')
 start('A-small-attempt0')
 ##start('A-large')
