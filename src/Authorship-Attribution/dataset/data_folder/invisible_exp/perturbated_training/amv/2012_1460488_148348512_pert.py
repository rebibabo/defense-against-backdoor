
 uvewc = 'A-small-attempt0.in'
 oyijw = 'a_out'
 
 arfls = ''.join([
 	'ejp mysljylc kd kxveddknmc re jsicpdrysi',
 	'rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd',
 	'de kr kd eoya kw aej tysr re ujdr lkgc jv',
 	'y qee', 'z'
 ])
 
 ghnlp = ''.join([
 	'our language is impossible to understand',
 	'there are twenty six factorial possibilities',
 	'so it is okay if you want to just give up',
 	'a zoo', 'q'
 ])
 
 rdhzu = {}
 
 for rifdy, e in zip(arfls, ghnlp):
 	rdhzu[rifdy] = e
 
 lines = open(uvewc, 'r').readlines()
 out = open(oyijw, 'w')
 
 for i, l in enumerate(lines[1:]):
 	trans = 'Case #%i: %s' % (
 		i + 1, 
 		''.join([rdhzu[c] for c in l if c in rdhzu]))
 	print trans
 	out.write(trans + '\n')
 
 out.close()
 
 	
 	
 
