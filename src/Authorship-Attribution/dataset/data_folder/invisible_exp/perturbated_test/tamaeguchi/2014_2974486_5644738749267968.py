#!/usr/bin/env python
 # -*- coding:utf-8 -*-
 #
 # Problem D. Deceitful War
 # https://code.google.com/codejam/contest/2974486/dashboard#s=p3
 #
 
 import sys
 
 
 def solve1(fhlnp, cndxk):
     # Deceitful War
     wcsrf = 0
     while fhlnp:
         lyfeb = max(cndxk)
         zcdar = min(filter(lambda mass: mass > lyfeb, fhlnp) or fhlnp)
         if zcdar > lyfeb:
             wcsrf += 1
         fhlnp.remove(zcdar)
         cndxk.remove(lyfeb)
     return wcsrf
 
 
 def solve2(fhlnp, cndxk):
     # War
     wcsrf = 0
     while fhlnp:
         zcdar = min(fhlnp)
         lyfeb = min(filter(lambda mass: mass > zcdar, cndxk) or cndxk)
         if zcdar > lyfeb:
             wcsrf += 1
         fhlnp.remove(zcdar)
         cndxk.remove(lyfeb)
     return wcsrf
 
 
 def main(IN, OUT):
     T = int(IN.readline())
     for index in range(T):
         N = int(IN.readline())
         fhlnp = map(float, IN.readline().split())
         cndxk = map(float, IN.readline().split())
         OUT.write('Case #%d: %d %d\n' % (index + 1, solve1(fhlnp[:], cndxk[:]), solve2(fhlnp[:], cndxk[:])))
 
 
 def makesample(maxN=1000, T=50):
     import random
     print T
     for index in range(T):
         N = random.randint(1, maxN)
         print N
         print ' '.join(map(str, (random.random() for n in range(N))))
         print ' '.join(map(str, (random.random() for n in range(N))))
 
 
 if __name__ == '__main__':
     if '-makesample' in sys.argv[1:]:
         makesample()
     else:
         main(sys.stdin, sys.stdout)
 
