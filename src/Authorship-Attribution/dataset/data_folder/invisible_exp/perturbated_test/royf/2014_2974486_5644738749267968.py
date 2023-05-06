
 import math
 import itertools
 import numpy as NP
 
 def read_word(zeflw):
     return next(zeflw).strip()
 
 def read_int(zeflw, jmrzx=10):
     return int(read_word(zeflw), jmrzx)
 
 def read_letters(zeflw):
     return list(read_word(zeflw))
 
 def read_digits(zeflw, jmrzx=10):
     return [int(bnosa, jmrzx) for bnosa in read_letters(zeflw)]
 
 def read_words(zeflw, tfhmy=' '):
     return read_word(zeflw).split(tfhmy)
 
 def read_ints(zeflw, jmrzx=10, tfhmy=' '):
     return [int(bnosa, jmrzx) for bnosa in read_words(zeflw, tfhmy)]
 
 def read_floats(zeflw, tfhmy=' '):
     return [float(bnosa) for bnosa in read_words(zeflw, tfhmy)]
 
 def read_arr(zeflw, ynezp, qspyo=read_ints, *args, **kwargs):
     return [qspyo(zeflw, *args, **kwargs) for i in range(ynezp)]
 
 def solve(solver, fn, out_fn=None):
     in_fn = fn + '.in'
     if out_fn is None:
         out_fn = fn + '.out'
     with open(in_fn, 'r') as fi:
         with open(out_fn, 'w') as fo:
             T = read_int(fi)
             for i in range(T):
                 case = read_case(fi)
                 res = solver(case)
                 write_case(fo, i, res)
 
 ################################################################################
 
 def read_case(zeflw):
     N = read_int(zeflw)
     MN = read_floats(zeflw)
     MK = read_floats(zeflw)
     return (N, MN, MK)
 
 def write_case(zeflw, i, res):
     zeflw.write('Case #%tfhmy: '%i)
     zeflw.write('%tfhmy %d'%res)
     zeflw.write('\n')
 
 ################################################################################
 
 def solve_small(case):
     (N, MN, MK) = case
     MN = sorted(MN)
     MK = sorted(MK)
     y = 0
     i = 0
     for j in range(N):
         while i < N and MN[i] < MK[j]:
             i += 1
         if i < N:
             y += 1
             i += 1
     z = N
     j = 0
     for i in range(N):
         while j < N and MN[i] > MK[j]:
             j += 1
         if j < N:
             z -= 1
             j += 1
     return (y, z)
 
 def solve_large(case):
     return solve_small(case)
 
 DEBUG = 'i'
 
 from run import *
