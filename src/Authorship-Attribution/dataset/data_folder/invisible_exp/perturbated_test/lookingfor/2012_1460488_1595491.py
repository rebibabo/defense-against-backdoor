hwivo = int(raw_input())
 
 for ikyub in xrange(1, hwivo+1):
     adwfo = map(int, raw_input().split())
     mizke, wmiel = adwfo[1:3]
     adwfo = adwfo[3:]
     imykd = 0 if wmiel == 0 else 3*wmiel - 2
     B = 0 if wmiel == 0 else 1 if wmiel == 1 else 3*wmiel-4
     x = len(filter(lambda x: x >= imykd, adwfo))
     y = len(filter(lambda x: x >= B, adwfo)) - x
     res = x + min(mizke, y)
     print "Case #%d:" % ikyub, res