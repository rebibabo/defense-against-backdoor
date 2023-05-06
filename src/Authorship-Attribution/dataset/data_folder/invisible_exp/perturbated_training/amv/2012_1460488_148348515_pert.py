import sys, os
 import re
 
 wsicf = sys.stdin.readline().split()
 rvzbk = int(wsicf[0])
 
 
 cmtnl = {		'a':'y',
 			'b':'h',
 			'c':'e',
 			'd':'s',
 			'e':'o',
 			'f':'c',
 			'g':'v',
 			'h':'x',
 			'i':'d',
 			'j':'u',
 			'k':'i',
 			'l':'g',
 			'm':'l',
 			'n':'b',
 			'o':'k',
 			'p':'r',
 			'q':'z',
 			'r':'t',
 			's':'n',
 			't':'w',
 			'u':'j',
 			'v':'p',
 			'w':'f',
 			'x':'m',
 			'y':'a',
 			'z':'q',
 			'\n':'',
 			' ':' '}
 
 
 def alien(fydwn):
 	resul = ""
 	for c in fydwn:
 		resul += cmtnl[c]
 
 	return resul
 
 
 
 frases = []
 for i in xrange(rvzbk):
 	fydwn = sys.stdin.readline().replace("\n","")
 	frases.append(fydwn)
 	
 
 for i in xrange(rvzbk):	
 	#case.append(fydwn)
 	print "Case #%d: %s" % (i+1, alien(frases[i]))
 	
 	
 
