import sys
 
 def read_line():
     return sys.stdin.readline().rstrip( '\n' )
 
 def read_integer():
     return int( read_line() )
 
 def read_floats():
     return [ float( luksv ) for luksv in read_line().split() ]
 
 dnkgs = read_integer()
 for sbvxz in range( dnkgs ):
     dchfo = read_integer()
     krfyz = sorted( read_floats() )
     sywqd = sorted( read_floats() )
     msqkb = len( sywqd ) - 1
     h = 0
     for n in reversed( krfyz ):
         if sywqd[ msqkb ] > n:
             msqkb -= 1
         else:
             h += 1
     d = 0
     n = len( krfyz ) - 1
     for msqkb in reversed( sywqd ):
         if krfyz[ n ] > msqkb:
             d += 1
             n -= 1
     print 'Case #%i:' % ( sbvxz + 1 ), d, h
