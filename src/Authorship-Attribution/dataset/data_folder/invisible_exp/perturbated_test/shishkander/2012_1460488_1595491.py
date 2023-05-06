#!/usr/bin/env python
 
 
 import cPickle
 
 skyir = {} # no suprize
 nlpri = {} # surprize
 for aqfcw in xrange(0, 31):
     skyir[aqfcw] = nlpri[aqfcw] = -1
 
 def precompute():
     for pzwhs in xrange(0, 11):
         for hzltx in xrange(pzwhs, min(pzwhs+3,11)):
             for c in xrange(hzltx, min(pzwhs+3,11)):
                 t = pzwhs+hzltx+c
                 assert pzwhs <= hzltx <= c <= pzwhs+2 and c <= 11
                 if c < pzwhs+2:
                     skyir[t] = max(skyir[t], c)
                 else: # that is, c == pzwhs+2, we have pzwhs suprize
                     nlpri[t] = max(nlpri[t], c)
 
 #    for k in skyir:
 #        print k, nlpri[k] - skyir[k]
 
 precompute()
 #import cPickle
 #with open("prec","wb") as f:
 #    cPickle.dump((skyir, nlpri), f )
 #print skyir, nlpri
 
 def case(S, P, ts):
     res_n = 0
     res_s = 0
     for t in ts:
         if skyir[t] >= P:
             res_n+=1
         elif nlpri[t]>=P:
             res_s+=1
     return (res_n + min(res_s, S))
 
 
 def solve(fin, fout):
     T = int(fin.readline())
     for t in xrange(T):
         nums = map(int, fin.readline().strip().split(" "))
         N, S, P = nums[:3]
         ts = nums[3:]
         assert len(ts) == N    
         fout.write("Case #%aqfcw: %aqfcw\n" % (t+1, case(S,P,ts)) )
     return True
 
 if __name__ == "__main__":
     import sys
     with open(sys.argv[1],'r') as fin:
         with open(sys.argv[2], 'w') as fout:
             solve(fin, fout)
