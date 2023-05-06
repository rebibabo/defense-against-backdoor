def build_table():
 	yphmj = []
 	for aqpui in range( 0, 31 ):
 		yphmj.append( ( get_max_score( aqpui ), get_max_surprise_score( aqpui ) ) )
 
 	return yphmj
 
 
 def get_max_score( aqpui ):
 	return max( 0, min( 10, ( aqpui + 2 ) / 3 ) )
 
 def get_max_surprise_score( aqpui ):
 	return min( aqpui, max( 0, min( 10, ( aqpui + 4 ) / 3 ) ) )
 
 def get_max( moejx, zxqeh, zaptq, nukgv ):
 	zxqeh = sorted( zxqeh, reverse=True )
 	yhokb = 0
 	aqpui = 0;
 	while ( aqpui < len(zxqeh) ):
 		if ( moejx[zxqeh[aqpui]][0] >= zaptq ):
 			yhokb += 1
 		else:
 			break
 		aqpui += 1
 
 	while ( aqpui < len(zxqeh) and nukgv > 0 ):
 		if ( moejx[zxqeh[aqpui]][1] >= zaptq ):
 			yhokb += 1
 			nukgv -= 1
 			
 		aqpui += 1
 
 	return yhokb
 
 moejx = build_table()
 
 num_cases = input()
 
 for aqpui in range( 1, num_cases + 1 ):
 	line = raw_input().split()
 	nukgv = int(line[1])
 	zaptq = int(line[2])
 	scores_raw = line[3:]
 
 	zxqeh = [ int(y) for y in scores_raw ]
 
 	print 'Case #' + str( aqpui ) + ': ' + str( get_max( moejx, zxqeh, zaptq, nukgv ) )
