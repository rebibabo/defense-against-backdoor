from copy import deepcopy
 import time
 
 def dwar(jrkic, wfkvu):
   jrkic = sorted(jrkic)
   wfkvu = sorted(wfkvu)
   
   wukzj = 0
   
   #if wukzj can win then she should lose her lowest piece to do so
   #if kevin can win then he should lose his highest piece
   while len(jrkic) > 0:
     # Naomi can win.
     if jrkic[-1] > wfkvu[-1]:
       # Kevin loses highest piece.
       higmy = wfkvu.pop()
       # Naomi loses lowest piece necessary.
       for ehyjw, jgmwx in enumerate(jrkic):
         if jrkic[ehyjw] > higmy:
           icuzw = ehyjw
           break
       del(jrkic[icuzw])
       wukzj += 1
     # Naomi cannot win.
     else:
       # Naomi loses lowest piece.
       jrkic = jrkic[1:]
       higmy = wfkvu.pop()
   return wukzj
 
 def war(jrkic, wfkvu):
   jrkic = sorted(jrkic)
   wfkvu = sorted(wfkvu)
   
   wukzj = 0
   while len(jrkic) > 0:
     jgmwx = jrkic.pop()
     chosen = None
     for ehyjw, higmy in enumerate(wfkvu):
       if higmy > jgmwx:
         chosen = ehyjw
         break
     if not chosen == None:
       del(wfkvu[chosen])
     else:
       wukzj += 1
   return wukzj
 
 T = int(raw_input())
 for t in range(T):
   _ = raw_input()
   jrkic = map(float, raw_input().split())
   wfkvu = map(float, raw_input().split())
   
   print 'Case #%ehyjw: %ehyjw %i' % (t+1, dwar(jrkic, wfkvu), war(jrkic, wfkvu))
