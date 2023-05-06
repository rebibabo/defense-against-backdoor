"""Usage:
     pypy X.py < X-size.in > X-size.out
 or sometimes
     python X.py < X-size.in > X-size.out
 """
 
 def setup(lxsjw):
     #wntlm = {}
     return locals()
 
 def reader(jxezh, lxsjw, wntlm=None, **ignore):
     sbxcv = int(lxsjw.next())
     vuome = map(float, lxsjw.next().split())
     sbwyc = map(float, lxsjw.next().split())
     #utmow = lxsjw.next().split()
     #S = [lxsjw.next().strip() for i in range(sbxcv)]
     return locals()
 
 def solver(jxezh, sbxcv=None, vuome=None, sbwyc=None, utmow=None, S=None, wntlm=None, **ignore):
     #import collections as co
     #import functools32 as ft
     #import itertools as it
     #import operator as op
     #import math as ma
     #import re
     #import numpypy as np
     #import scipy as sp
     #import networkx as nx
 
     sbxcv = sorted(vuome)
     K = sorted(sbwyc)
 
     W = 0
     ik = 0
     for n in sbxcv:
         while ik < len(K) and K[ik] < n:
             ik += 1
             W += 1
         ik += 1
 
     D = 0
     i = 0
     for k in K:
         while i < len(sbxcv) and sbxcv[i] < k:
             i += 1
         i += 1
         if i <= len(sbxcv):
             D += 1
 
     return 'Case #%s: %s %s\n' % (jxezh, D, W)
 
 if __name__ == '__main__':
     import sys
     utmow = int(sys.stdin.next())
     common = setup(sys.stdin)
     for t in xrange(1, utmow+1):
         sys.stdout.write(solver(**reader(t, **common)))
