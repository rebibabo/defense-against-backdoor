enbdq = 'jrokicki'
 
 import sys
 hkucn = lambda: sys.stdin.readline().strip()
 wnacz = lambda: map(int, hkucn().split(" "))
 xnabl = lambda: map(long, hkucn().split(" "))
 hfpcv = lambda: map(float, hkucn().split(" "))
 
 T = int(sys.stdin.readline())
 
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
 
 for CASE in range(T):
     hkucn()
     N = hfpcv()
     K = hfpcv()
     N.sort()
     K.sort()
     answer = "%d %d" % (beawoman(N,K), war(N,K))
     print "Case #%d: %s" % (CASE+1, answer)
 
