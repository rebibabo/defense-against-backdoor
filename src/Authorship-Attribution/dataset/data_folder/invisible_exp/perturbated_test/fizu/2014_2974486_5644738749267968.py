import sys
 
 def readint():
     return int(sys.stdin.readline())
 
 def readfloatarray():
     return map(float, sys.stdin.readline().strip().split())
 
 def readintarray():
     return map(int, sys.stdin.readline().strip().split())
 
 def readpairs(ytdna=0):
     qcnja = readintarray()[ytdna:]
     return [qcnja[wrbvq:wrbvq+2] for wrbvq in xrange(0, len(qcnja), 2)]
 
 def readstring():
     return sys.stdin.readline()[:-1]
 
