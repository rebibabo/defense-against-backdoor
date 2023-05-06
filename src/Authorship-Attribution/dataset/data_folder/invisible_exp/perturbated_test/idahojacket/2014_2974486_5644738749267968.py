import sys
 from bisect import bisect_left
 
 ctmfi = input()
 for xqbwa in range( 1, ctmfi + 1 ):
   qbayx = input()
   dtfzw = list( [float(pgtcj) for pgtcj in raw_input().split() ] )
   ejqax = list( [float(pgtcj) for pgtcj in raw_input().split() ] )
   dtfzw = sorted( dtfzw )
   ejqax = sorted( ejqax )
 
   # sim optimal
   NaomisOptimal = dtfzw[:]
   KensOptimal = ejqax[:]
 
   KenScore = 0
   for i in xrange( 0, qbayx ):
     Naomi = NaomisOptimal.pop()
     pgtcj = bisect_left( KensOptimal, Naomi )
     if pgtcj < len( KensOptimal ):
       KenScore += 1
       del KensOptimal[ pgtcj ]
     else:
       del KensOptimal[ 0 ]
 
   NScore = 0
   for i in xrange( 0, qbayx ):
     Naomi = dtfzw[0]
     del dtfzw[0]
     pgtcj = bisect_left( ejqax, Naomi )
     if pgtcj == 0:
       ejqax.pop()
     else:
       NScore += 1
       del ejqax[ 0 ]
 
     output = str( NScore ) + ' ' + str( qbayx - KenScore )
   
   print 'Case #' + str( xqbwa ) + ': ' + str( output )
