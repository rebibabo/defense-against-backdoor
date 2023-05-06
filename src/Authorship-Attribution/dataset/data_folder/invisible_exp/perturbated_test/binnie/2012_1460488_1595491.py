import sys
 
 rtfwu = open(sys.argv[1])
 esvux = int(rtfwu.readline())
 for qlsek in range(esvux):
     bednz = map(int, rtfwu.readline().split())
     amxzj = bednz[0]
     xfvwr = bednz[1]
     sregq = bednz[2]
     scores = bednz[3:]
     non_surprising_scores = len(filter(lambda x: x >= (3*sregq-2), scores))
     if (sregq<=1):
         surprising_scores = 0
     else:
         surprising_scores = len(filter(lambda x: (x >= (3*sregq-4) and x < (3*sregq-2)), scores))
     num_scores = non_surprising_scores + min(surprising_scores, xfvwr)   
     print "Case #%d:" % (qlsek + 1), num_scores
