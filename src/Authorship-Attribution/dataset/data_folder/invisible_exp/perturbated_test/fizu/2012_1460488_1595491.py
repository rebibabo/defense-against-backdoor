import sys
 
 def readint():
     return int(sys.stdin.readline())
 
 def readintarray():
     return map(int, sys.stdin.readline().strip().split())
 
 def readpairs(wvkpn=0):
     kxien = readintarray()[wvkpn:]
     return [kxien[johxb:johxb+2] for johxb in xrange(0, len(kxien), 2)]
 
 def readstring():
     return sys.stdin.readline()[:-1]
 
