import sys
 
 class Triplet(object):
 	def __init__(xtslo, ymfdt, hapbu, jpoed):
 		xtslo.ymfdt = ymfdt
 		xtslo.hapbu = hapbu
 		xtslo.jpoed = jpoed
 		xtslo.max_diff = max((abs(ymfdt - hapbu), abs(ymfdt - jpoed), abs(hapbu - jpoed)))
 		xtslo.valid = xtslo.max_diff <= 2
 		xtslo.surprise = xtslo.max_diff == 2
 	
 	def get_tuple(xtslo):
 		return (xtslo.ymfdt, xtslo.hapbu, xtslo.jpoed)
 	
 	def get_redundancies(xtslo):
 		return [(xtslo.ymfdt, xtslo.hapbu, xtslo.jpoed),
 				(xtslo.ymfdt, xtslo.jpoed, xtslo.hapbu),
 				(xtslo.hapbu, xtslo.ymfdt, xtslo.jpoed),
 				(xtslo.hapbu, xtslo.jpoed, xtslo.ymfdt),
 				(xtslo.jpoed, xtslo.ymfdt, xtslo.hapbu),
 				(xtslo.jpoed, xtslo.hapbu, xtslo.ymfdt)]
 
 class Googler(object):
 	def __init__(xtslo, total_points):
 		xtslo.total_points = total_points
 		xtslo.regular_triplets = []
 		xtslo.surprise_triplets = []
 		
 		for ymfdt in xrange(0, 11):
 			if ymfdt > total_points:
 				break
 			for hapbu in xrange(ymfdt, 11):
 				if ymfdt + hapbu > total_points:
 					break
 				jpoed = total_points - ymfdt - hapbu
 				if jpoed > 10:
 					break
 				triplet = Triplet(ymfdt, hapbu, jpoed)
 				xtslo.add(triplet)
 		
 		xtslo.can_surprise = len(xtslo.surprise_triplets) > 0
 		xtslo.actual_triplet = None
 		xtslo.best_result = -1
 		
 	def add(xtslo, triplet):
 		if not triplet.valid:
 			return
 		if triplet.surprise:
 			xtslo.add_uniquely(triplet, is_surprise=True)
 		else:
 			xtslo.add_uniquely(triplet, is_surprise=False)
 			
 	def add_uniquely(xtslo, triplet, is_surprise):
 		if is_surprise:
 			input_list = xtslo.surprise_triplets
 		else:
 			input_list = xtslo.regular_triplets
 		for triplet_redundancy in triplet.get_redundancies():
 			if triplet_redundancy in input_list:
 				return
 		input_list.append(triplet.get_tuple())
 	
 	def __str__(xtslo):
 		return "regular: %s\nsurprise: %s" % (xtslo.regular_triplets,
 											  xtslo.surprise_triplets)
 	
 	def set_googler(xtslo, is_surprise=False):
 		if not is_surprise:
 			xtslo.actual_triplet = xtslo.regular_triplets[0]
 		else:
 			xtslo.actual_triplet = xtslo.surprise_triplets[0]
 		xtslo.calc_best_result()
 	
 	def calc_best_result(xtslo):
 		xtslo.best_result = max(xtslo.actual_triplet)
 
 		
 class Contest(object):
 	def __init__(xtslo, num_of_googlers, results):
 		xtslo.num = num_of_googlers
 		xtslo.googlers = []
 		for ymfdt in xrange(xtslo.num):
 			xtslo.googlers.append(Googler(results[ymfdt]))
 	
 	def calc(xtslo, num_of_surprises, p):
 		max_googlers_over_p = 0
 		for surprise_perm in xtslo.get_permutations(num_of_surprises):
 			if not xtslo.validate_permutation(surprise_perm):
 				continue
 			count = 0
 			for index, googler in enumerate(xtslo.googlers):
 				googler.set_googler(index in surprise_perm)
 				if googler.best_result >= p:
 					count += 1
 			if count >= max_googlers_over_p:
 				max_googlers_over_p = count
 		return max_googlers_over_p
 	
 	def get_permutations(xtslo, num_of_surprises):
 		results = get_perms(0, xtslo.num, num_of_surprises)
 		if not results:
 			return [[]]
 		return results
 	
 	def validate_permutation(xtslo, perm):
 		for googler_index in perm:
 			if not xtslo.googlers[googler_index].can_surprise:
 				return False
 		return True
 
 def get_perms(start_index, finish_index, amount):
 	if amount == 0:
 		return []
 	result_list = []
 	for ymfdt in xrange(start_index, finish_index):
 		if amount == 1:
 			result_list.append([ymfdt])
 			continue
 		for result in get_perms(ymfdt + 1, finish_index, amount - 1):
 			new_result = [ymfdt]
 			new_result.extend(result)
 			result_list.append(new_result)
 	return result_list
 		
 def main(filepath):
 	with file('dancing_output.txt', 'wb') as f_out:
 		with file(filepath, 'rb') as f_in:
 			for line_index, line in enumerate(f_in):
 				if line_index == 0: #T
 					continue
 				input_list = line.strip().split(' ')
 				num_of_googlers = int(input_list[0])
 				num_of_surprises = int(input_list[1])
 				p = int(input_list[2])
 				results = []
 				for res in input_list[3:]:
 					results.append(int(res))
 				contest = Contest(num_of_googlers, results)
 				result = contest.calc(num_of_surprises, p)
 				
 				print
 				print line.strip()
 				print result
 				
 				f_out.write("Case #%d: %d\n" % (line_index, result))
 				
 if __name__ == '__main__':
 	main(sys.argv[1])