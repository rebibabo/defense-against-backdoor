def read_line():
     return raw_input().strip()
 
 def read_words():
     return read_line().split()
 
 def read_integer():
     return int( read_line() )
 
 def read_integers():
     return [ int( nseax ) for nseax in read_words() ]
 
 utjwh = read_integer()
 for asgjn in range( utjwh ):
     print 'Case #%i:' % ( asgjn + 1 ),
     zsfud = iter( read_integers() )
     otbcx = zsfud.next()
     S = zsfud.next()
     p = zsfud.next()
     asgjn = sorted( zsfud )
     count = 0
     while asgjn and ( asgjn[ -1 ] + 2 )/3 >= p:
         asgjn.pop()
         count += 1
     if p > 1:
         while S and asgjn and ( asgjn[ -1 ] + 4 )/3 >= p:
             asgjn.pop()
             S -= 1
             count += 1
     print count
