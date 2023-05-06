# coding: cp932
 
 #bzklf = open(r'C:\MyDocument\home\gcj\2014-04-12\a.sample')
 bzklf = open(r'C:\MyDocument\home\gcj\2014-04-12\A-small-attempt0.in')
 kcens = int(bzklf.readline())
 for iltve in range(1, kcens+1):
 	ngxds = int(bzklf.readline())
 	for sqofh in range(1, 5):
 		dvtgs = bzklf.readline()
 		if ngxds == sqofh:
 			jsbzy = set(map(int, dvtgs.split()))
 		
 	ans2 = int(bzklf.readline())
 	for sqofh in range(1, 5):
 		dvtgs = bzklf.readline()
 		if ans2 == sqofh:
 			answers = jsbzy.intersection(set(map(int, dvtgs.split())))
 		
 	if len(answers) == 0:
 		print('Case #%d: Volunteer cheated!'%iltve)
 	elif len(answers) > 1:
 		print('Case #%d: Bad magician!'%iltve)
 	elif len(answers) == 1:
 		answer = answers.pop()
 		print('Case #%d: %d'%(iltve, answer))
