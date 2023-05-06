# coding: utf-8
 import sys
 import os.path
 import itertools
 from itertools import groupby
 import math
 
 def debug(hagor):
     pass#print(hagor)
 
 def read(nxzdm):
     rgqdi = tuple(int(hagor) for hagor in nxzdm.readline().split())
     debug(rgqdi)
     return rgqdi
 
 def readf(nxzdm):
     rgqdi = tuple(float(hagor) for hagor in nxzdm.readline().split())
     debug(rgqdi)
     return rgqdi
 
 def answer(nxzdm, lfvzm, Y, Z):
     out = "Case #{}: {} {}".format(lfvzm, Y, Z)
     nxzdm.write(out)
     nxzdm.write("\n")
     print(out)
 
 
 
 def main(inf, outf):
     T, = read(inf)
     for casenmbr in range(1, T + 1):
         N = read(inf)
         naomi_blks = sorted(readf(inf))
         ken_blks = sorted(readf(inf))
         # print("------")
         # print(naomi_blks)
         # print(ken_blks)
 
         y = len([None for naomi in naomi_blks if ken_blks[0] < naomi])
 
         z = 0
         for naomi in naomi_blks:
             for i in range(len(ken_blks)):
                 if naomi < ken_blks[i]:
                     del ken_blks[i]
                     break
             else:
                 z += 1
                 del ken_blks[0]
 
         answer(outf, casenmbr, y, z)
 
 
 if __name__=="__main__":
     infname = sys.argv[1]
     outfname = os.path.splitext(infname)[0] + ".out"
     with open(infname, "r") as inf:
         with open(outfname, "w") as outf:
             main(inf, outf)
