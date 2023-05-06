def solve(uckra, eskfi):
     qlepv = list(sorted(uckra))
     fazxh = list(sorted(eskfi))
     pd = 0
     while len(qlepv) > 0:
         if qlepv[0] > fazxh[0]:
             pd += 1
             qlepv.pop(0)
             fazxh.pop(0)
         else:
             qlepv.pop(0)
             fazxh.pop(-1)
     pn = 0
     qlepv = list(sorted(uckra))
     fazxh = list(sorted(eskfi))
     while len(qlepv) > 0:
         n = qlepv.pop(0)
         if n < fazxh[0]:
             fazxh.pop(0)
         else:
             found = None
             for i,k in enumerate(fazxh):
                 if k > n:
                     found = i
                     break
             if found is not None:
                 fazxh.pop(found)
             else:
                 fazxh.pop(0)
                 pn += 1
     return pd,pn
 
 if __name__=="__main__":
     T = int(raw_input())
     for i in range(1,T+1):
         raw_input()
         uckra = map(float, raw_input().split())
         eskfi = map(float, raw_input().split())
         x,y = solve(uckra,eskfi)
         print "Case #%d: %d %d" %(i,x,y)
