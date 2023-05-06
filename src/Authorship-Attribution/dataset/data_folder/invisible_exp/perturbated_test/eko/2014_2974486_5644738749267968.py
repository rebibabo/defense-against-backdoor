import os, re, sys
 import unittest
 
 
 #gjmdh = sys.stdin.readline().split()
 gjmdh = int(sys.stdin.readline())
 
 def ken(chzjp, vnzyt):
 	for kdntv in vnzyt:
 		if kdntv >= chzjp:
 			vnzyt.remove(kdntv)
 			return kdntv, vnzyt
 	
 	vfuoh = vnzyt[0]
 	vnzyt.remove(vfuoh)
 	return vfuoh, vnzyt
 	
 def naomi(YList, ZList):
 	l1 = vnzyt(YList)
 	l2 = vnzyt(ZList)
 	
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
 	dnp = 0
 	np = 0
 	#YList.sort(reverse=True)
 	YList.sort()
 	ZList.sort()
 	
 	YList2 = vnzyt(YList)
 	ZList2 = vnzyt(ZList)
 	
 	YList.sort()
 	#print YList
 	#print ZList
 	
 	while len(YList) > 0:
 		cy, ty = naomi(YList, ZList)
 		YList.remove(cy)		
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
 	for kdntv in xrange(gjmdh):	
 		#frase = [str(vfuoh) for vfuoh in sys.stdin.readline().split(' ')]	
 		#print "Case #%d: %s" % (kdntv + 1, main(frase[0]))
 		
 		##Numbers
 		N = [int(vfuoh) for vfuoh in sys.stdin.readline().split(' ')]
 		YList = [float(vfuoh) for vfuoh in sys.stdin.readline().split(' ')]
 		ZList = [float(vfuoh) for vfuoh in sys.stdin.readline().split(' ')]
 		#print E,R,N, NList
 		print "Case #%d: %s" % (kdntv + 1, main(YList, ZList))