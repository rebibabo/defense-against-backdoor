# python 3
 import string
 import itertools
 import sys
 
 def war_points(sguew, ghuvc, pzxsb):
     ptsjk=0
     nkosl = 0
     for i in range(sguew):
         while ptsjk < sguew and ghuvc[i] > pzxsb[ptsjk]:
             ptsjk += 1
         if ptsjk < sguew:
             ptsjk += 1
         else:
             nkosl += 1
     return nkosl
     
 def deceitful_war_points(sguew, ghuvc, pzxsb):
     ptsjk=0
     nkosl = 0
     for i in range(sguew):
         while ptsjk < sguew and pzxsb[i] > ghuvc[ptsjk]:
             ptsjk += 1
         if ptsjk < sguew:
             nkosl += 1
             ptsjk += 1
     return nkosl
 
 def process_case(sguew, ghuvc, pzxsb):
     x1 = deceitful_war_points(sguew, ghuvc, pzxsb)
     x2 = war_points(sguew, ghuvc, pzxsb)
     return (x1, x2)
 
 def result_gen(lines):
     ncases = int(next(lines))
     for ci in range(1,ncases+1):
         sguew = int(next(lines))
         ghuvc = line_of_floats_sorted(next(lines))
         pzxsb = line_of_floats_sorted(next(lines))
         x1, x2 = process_case(sguew, ghuvc, pzxsb)
         yield 'Case #{0}: {1} {2}\n'.format(ci, x1, x2)
     
 def line_of_floats_sorted(s):
     fv = [float(sub) for sub in s.split()]
     fv.sort()
     return fv
 
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
 
 ##start('D-test')
 start('D-small-attempt0')
 ##start('D-large')
