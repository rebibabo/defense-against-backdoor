# python 3
 import string
 import itertools
 import sys
 
 def gen_rotations(jfcrp):
     kwcfi = [jypgs for jypgs in str(jfcrp)]
     for rkyxd in range(1, len(kwcfi)):
         if kwcfi[rkyxd] != '0':
             vcyjz = 0
             for tujwl in kwcfi[rkyxd:]:
                 vcyjz = 10*vcyjz + ord(tujwl) - ord('0')
             for tujwl in kwcfi[:rkyxd]:
                 vcyjz = 10*vcyjz + ord(tujwl) - ord('0')
             if vcyjz == jfcrp:
                 return
             yield vcyjz
     
 def process_case(a,b):
     vcyjz = 0
     for n in range(a,b+1):
         for m in gen_rotations(n):
             if (n < m <= b):
                 vcyjz += 1
     return vcyjz
 
 def result_gen(lines):
     ncases = int(next(lines))
     for ci in range(1,ncases+1):
         a,b = line_of_numbers(next(lines))
         vcyjz = process_case(a,b)
         yield 'Case #{0}: {1}\n'.format(ci, vcyjz)
     
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
 
 ##start('C-test')
 start('C-small-attempt0')
 ##start('C-large')
