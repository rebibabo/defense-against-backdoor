
import math
import itertools
import numpy
 
def read_word(f):
    return next(f).strip()
 
def read_int(f, b=10):
    return int(read_word(f), b)
 
def read_letters(f):
    return list(read_word(f))
 
def read_digits(f, b=10):
    return [int(x, b) for x in read_letters(f)]
 
def read_words(f, d=' '):\n    return read_word(f).split(d)\n \ndef read_ints(f, b=10, d=' '):\n    return [int(x, b) for x in read_words(f, d)]\n \ndef read_floats(f, d=' '):\n    return [float(x) for x in read_words(f, d)]\n \ndef read_arr(f, R, reader=read_ints, *args, **kwargs):\n    res = []\n    for i in range(R):\n        res.append(reader(f, *args, **kwargs))\n    return res\n \ndef solve(solver, fn, out_fn=None):\n    in_fn = fn + '.in'
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
 
def read_case(f):
    [N, M] = read_ints(f)
    A = read_arr(f, N)
    return (N, M, A)
 
def write_case(f, i, res):
    f.write('Case #%d: '%i)
    f.write('%s'%res)
    f.write('\n')
 
################################################################################
 
def solve_small(case):
    (N, M, A) = case
    rows = [max(row) for row in A]
    cols = [max(row[j] for row in A) for j in range(M)]
    for i in range(N):
        for j in range(M):
            if A[i][j] < min(rows[i], cols[j]):
                return 'NO'
    return 'YES'
 
def solve_large(case):
    return solve_small(case)
 
DEBUG = 'i'
 
from run import *
