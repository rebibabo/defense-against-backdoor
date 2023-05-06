import sys
 
 def is_sym( xaqhy ):
 	ivoaf = str( xaqhy )
 	ygprl = len( ivoaf )
 	ibzfh = int( ygprl / 2 )
 	end = ivoaf[-ibzfh:]
 	rev_end = end[::-1]
 	start = ivoaf[:ibzfh]
 	equal = rev_end == start 
 	return equal
 
 f = open( 'palindromes.out' )
 
 numbers = [ int(x) for x in f.read().split() ]
 
 numCases = input()
 for case in range( 1, numCases + 1 ):
 	min, max = [ int(x) for x in raw_input().split() ]
 	count = 0
 	for xaqhy in numbers:
 		if min <= xaqhy and max >= xaqhy:
 			count += 1
 
 	print 'Case #' + str( case ) + ': ' + str( count )
