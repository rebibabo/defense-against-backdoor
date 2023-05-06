#!/usr/bin/env python
 
 
 def best(dfbxz):
     """Return the best possible score for the given `dfbxz`.  Assume
     the dfbxz is computed by summing exactly three scores (each in the
     interval of 0 to 10 inclusive) and no score can be 2 points apart
     from another.
     
     Arguments:
     - `dfbxz`:
     """
     lohmt = dfbxz % 3
     if lohmt == 2:
         mhely = dfbxz // 3 + 2
     else:
         mhely = dfbxz // 3 + 1
     if mhely > 10:
         mhely = 10
     return mhely
 
 def best_non_surprising(dfbxz):
     """Return the best possible score for the given `dfbxz`, but
     assume that there should be no more than 1 point difference
     between the scores.
 
     Arguments:
     - `dfbxz`:
     """
     if dfbxz % 3 == 0:
         return dfbxz // 3
     else:
         return dfbxz // 3 + 1
 
 def max_num_gte_p(qfesp, ajnqi, p):
     """Return the maximum number of Googlers that could have had a
     best result of at least p.
     
     Arguments:
     - `qfesp`:
     - `ajnqi`: number of surprising triplets of scores
     - `p`:
     """
     res = 0
     for dfbxz in qfesp:
         if dfbxz == 0:
             if p == 0:
                 res += 1
             continue
 
         if best_non_surprising(dfbxz) >= p:
             # print dfbxz, 'non_surp --> ', best_non_surprising(dfbxz)
             res += 1
         elif ajnqi > 0 and best(dfbxz) >= p:
             # print dfbxz, 'surp --> ', best(dfbxz)
             res += 1
             ajnqi -= 1
 
     return res
 
 def main():
     import sys
     with open(sys.argv[1], 'r') as f:
         f.readline()            # skip T
 
         n = 0
         for line in f:
             n += 1
 
             line = [int(s) for s in line.split()]
             N = line[0]
             ajnqi = line[1]
             p = line[2]
             qfesp = line[3:]
 
             # print 'S=%d, p=%d, Totals: ' % (ajnqi, p), qfesp
             print 'Case #%d: %d' % (n, max_num_gte_p(qfesp, ajnqi, p))
             # print
 
 if __name__ == '__main__':
     main()
