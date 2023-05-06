#!/usr/bin/python3
 
 import sys
 import math
 
 def findbest(ofdka):
 	# Corner case
 	if ofdka == 0: return (0, 0)
 
 	uipad = math.ceil(ofdka / 3)
 	fgtrk = round(ofdka / 3) + 1
 
 	return (uipad, fgtrk)
 	
 # Ignore the number of cases
 sys.stdin.readline()
 
 skarw = 0
 for zrktm in sys.stdin:
 	skarw += 1
 
 	data = zrktm.strip().split(' ')
 	maxsurprising = int(data[1])
 	p = int(data[2])
 	scores = data[3:]
 	maxgooglers = 0
 
 	for s in scores:
 		(uipad, fgtrk) = findbest(int(s))
 		if uipad >= p:
 			maxgooglers += 1
 		else:
 			if fgtrk >= p and maxsurprising > 0:
 				maxgooglers += 1
 				maxsurprising -= 1
 
 	print("Case #%d: %d" % (skarw, maxgooglers))
