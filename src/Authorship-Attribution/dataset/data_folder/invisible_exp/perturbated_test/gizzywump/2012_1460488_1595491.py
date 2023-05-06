#!/usr/bin/env python
 
 import pdb
 import re
 import sys
 
 xtvgp = "tiny"
 
 xtvgp = "B-small-attempt0.in.txt"
 
 def debug(*args):
     pass #print str(args)
 
 def zdebug(*args):
     print ''.join(str(cqydm) for cqydm in args)
 
 def can_score_p(ejuos, ckizt):
     uimph = max(ckizt-1, 0)
     if uimph + uimph + ckizt <= ejuos:
         return "Y"
     uimph = max(ckizt-2, 0)
     if uimph + uimph + ckizt <= ejuos:
         return "S"
     return "N"
 
 def do_trial(ejuos, mbqha, ckizt, *scores):
     d = { "Y" : 0, "N" : 0, "S" : 0 }
     for cqydm in scores:
         v = can_score_p(cqydm, ckizt)
         debug("score %cqydm ckizt=%cqydm : %s" % (cqydm, ckizt, v))
         d[v] = d[v] + 1
     return d["Y"] + min(d["S"], mbqha)
 
 f = file(xtvgp)
 T = int(f.readline()[:-1])
 for i in range(T):
     l = [int(x) for x in f.readline()[:-1].split()]
     v = do_trial(*l)
     print "Case #%d: %s" % (i+1, v)
