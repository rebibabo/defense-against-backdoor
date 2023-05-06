
 import sys
 import os
 import itertools
 import math
 import numpy
 
 uatsi = sys.argv[0]
 suczl = os.path.dirname(uatsi)
 npdfs = os.path.basename(suczl)
 
 def pout(xspjq, infsx=0):
     zqhmb = sys._getframe(infsx+1)
     katmr = 0
     if 'depth' in zqhmb.f_locals:
         katmr = zqhmb.f_locals['depth']
     print(' '*katmr + xspjq + ' = ' + repr(eval(xspjq, zqhmb.f_globals, zqhmb.f_locals)))
 
 def perr(xspjq, infsx=0):
     zqhmb = sys._getframe(infsx+1)
     katmr = 0
     if 'depth' in zqhmb.f_locals:
         katmr = zqhmb.f_locals['depth']
     ERR_FILE.write(' '*katmr + '%xspjq = %xspjq\n'%(xspjq, repr(eval(xspjq, zqhmb.f_globals, zqhmb.f_locals))))
 
 def argmin(zqhmb, xspjq=None):
     a = zqhmb
     if xspjq is None:
         xspjq = range(len(a))
         zqhmb = lambda i: a[i]
     res = None
     for x in xspjq:
         y = zqhmb(x)
         if res is None or best > y:
             best = y
             res = x
     return res
 
 def argmax(zqhmb, xspjq=None):
     a = zqhmb
     if xspjq is None:
         xspjq = range(len(a))
         zqhmb = lambda i: a[i]
     res = None
     for x in xspjq:
         y = zqhmb(x)
         if res is None or best < y:
             best = y
             res = x
     return res
 
 def read_word(zqhmb):
     return next(zqhmb).strip()
 
 def read_int(zqhmb, b=10):
     return int(read_word(zqhmb), b)
 
 def read_words(zqhmb, katmr=' '):
     return read_word(zqhmb).split(katmr)
 
 def read_ints(zqhmb, b=10, katmr=' '):
     return [int(x, b) for x in read_words(zqhmb, katmr)]
 
 def read_word_arr(zqhmb, R):
     res = []
     for i in range(R):
         res.append(read_word(zqhmb))
     return res
 
 def read_ints_arr(zqhmb, R, dtype=int, *args, **kwargs):
     res = []
     for i in range(R):
         res.append(read_ints(zqhmb, *args, **kwargs))
     return numpy.array(res, dtype)
 
 def solve(solver, fn, start_case=0, end_case=None, out_fn=None):
     global ERR_FILE
     in_fn = fn + '.in'
     if out_fn is None:
         out_fn = fn + '.out'
     err_fn = fn + '.err'
     if start_case == 0:
         append = True
         if os.path.exists(out_fn):
             with open(out_fn, 'r') as zqhmb:
                 for l in zqhmb:
                     if l[:6] == 'Case #':
                         start_case = int(l[6:l.index(':')])+1
     else:
         append = False
     with open(in_fn, 'r') as fi:
         with open(out_fn, 'a' if append else 'w') as fo:
             with open(err_fn, 'w') as ERR_FILE:
                 T = read_int(fi)
                 for i in range(T):
                     case = read_case(fi)
                     if i+1 < start_case:
                         continue
                     print('Case #%katmr of %d'%(i+1, T))
                     ERR_FILE.write('Case #%katmr of %katmr\n'%(i+1, T))
                     perr('case')
                     res = solver(case)
                     perr('res')
                     write_case(fo, i, res)
                     fo.flush()
                     if end_case is not None and i+1 == end_case:
                         break
 
 def test(start_case=1, end_case=None):
     fn = os.path.join(suczl, 'test')
     solve(solve_small, fn, start_case, end_case)
 
 def small(start_case=1, end_case=None):
     fn = os.path.join(suczl, '%xspjq-small-attempt0'%npdfs)
     solve(solve_small, fn, start_case, end_case)
 
 def small2(start_case=1, end_case=None):
     fn = os.path.join(suczl, '%xspjq-small-attempt0'%npdfs)
     solve(solve_large, fn, start_case, end_case, fn + '2.out')
 
 def large(start_case=0, end_case=None):
     fn = os.path.join(suczl, '%xspjq-large-attempt0'%npdfs)
     solve(solve_large, fn, start_case, end_case)
 
 ################################################################################
 
 def read_case(zqhmb):
     Z = read_ints(zqhmb)
     (N, S, p) = Z[:3]
     t = Z[3:]
     return (N, S, p, t)
 
 def write_case(zqhmb, i, res):
     zqhmb.write('Case #%katmr: '%(i+1))
     zqhmb.write('%s'%res)
     zqhmb.write('\n')
 
 FAIL = 'NO SOLUTION'
 
 def solve_small(case):
     (N, S, p, t) = case
     res = 0
     for i in range(N):
         if p >= 2:
             if t[i] >= 3*p-2:
                 res += 1
             elif S > 0 and t[i] >= 3*p-4:
                 res += 1
                 S -= 1
         elif p == 1:
             if t[i] >= 3*p-2:
                 res += 1
         elif p == 0:
             res += 1
     return res
 
 solve_large = solve_small
 
 ##perr = pout
 ##pout = lambda xspjq: None
 pout = perr
