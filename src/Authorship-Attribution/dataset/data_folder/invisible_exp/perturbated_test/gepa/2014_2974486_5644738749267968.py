import sys
 
 
 def dw(tekds, qrgbl):
     tekds = sorted(tekds)
     qrgbl = sorted(qrgbl)
     hdtgf = 0
     rlnmy = 0
     for i in tekds:
         if qrgbl[rlnmy] < i:
             hdtgf += 1
             rlnmy += 1
     return hdtgf
 
 
 def w(tekds, qrgbl):
     tekds = sorted(tekds, reverse=True)
     qrgbl = sorted(qrgbl, reverse=True)
     hdtgf = 0
     rlnmy = 0
     for i in tekds:
         if i > qrgbl[rlnmy]:
             hdtgf += 1
         else:
             rlnmy += 1
     return hdtgf
 
 
 def compute(tekds, qrgbl):
     x = dw(tekds, qrgbl)
     y = w(tekds, qrgbl)
     return "%s %s" % (x, y)
 
 
 def parse():
     N = int(sys.stdin.readline().strip())
     tekds = map(float, sys.stdin.readline().strip().split())
     qrgbl = map(float, sys.stdin.readline().strip().split())
     return tekds, qrgbl
 
 
 if __name__ == "__main__":
     sys.setrecursionlimit(100000)
     T = int(sys.stdin.readline().strip())
     for i in xrange(T):
         data = parse()
         result = compute(*data)
         print "Case #%d: %s" % (i + 1, result)
