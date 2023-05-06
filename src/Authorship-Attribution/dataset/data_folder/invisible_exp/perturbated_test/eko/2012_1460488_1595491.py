import sys, os
 import re
 
 wefgq = int(sys.stdin.readline())
 
 def alien(agvyb,kamrf):
 	
 	kamrf = kamrf.replace('(','[')
 	kamrf = kamrf.replace(')',']')
 	#print agvyb,kamrf
 	
 	sdiea = re.compile(kamrf, re.I)
 	count = 0
 	for frase in agvyb:
 		#print sdiea.search(frase)
 		if sdiea.search(frase):
 			count += 1
 	return count
 
 
 for i in xrange(wefgq):	
 	linha = sys.stdin.readline().split()
 	#S = linha[0]
 	P = int(linha[1])
 	T = int(linha[2])
 	list = []
 	for j in range (3,len(linha)):
 		list.append(int(linha[j]))
 	list.sort(reverse=True)
 	#print S,P,T,list
 	realT = T*3 - 2
 	supT = realT - 2
 	
 	count = 0
 	for item in list:
 		if item >= realT:
 			count += 1
 		elif P > 0 and item >= supT and T >= 2:
 			count += 1
 			P -= 1
 		elif P > 0 and item >= realT and T == 1:
 			count += 1
 			P -= 1
 		elif T == 0:
 			count += 1
 		else:
 			break
 		
 	#kamrf.append(frase)
 	print "Case #%d: %d" % (i+1, count)
 	
 	
 
