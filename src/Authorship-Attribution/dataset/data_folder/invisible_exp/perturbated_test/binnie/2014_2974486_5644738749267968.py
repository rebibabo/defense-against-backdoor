import sys
 
 otzfr = open(sys.argv[1])
 sqmre = int(otzfr.readline())
 for qrpgz in range(sqmre):
     fogmr = int(otzfr.readline())
     naomi_blocks = map(float, otzfr.readline().strip().split(" "))
     ken_blocks = map(float, otzfr.readline().strip().split(" "))
     naomi_blocks.sort()
     ken_blocks.sort()
     naomi_clone = list(naomi_blocks)
     ken_clone = list(ken_blocks)
 
     naomi_standard_score = 0
     while naomi_blocks != []:
         naomi_new_val = naomi_blocks.pop()
         if naomi_new_val > ken_blocks[-1]:
             naomi_standard_score += 1
             ken_blocks.pop(0)
         else:
             ii = 0
             while ken_blocks[ii] < naomi_new_val:
                 ii += 1
             ken_blocks.pop(ii)
 
     assert(ken_blocks == [])
 
     naomi_blocks = naomi_clone
     ken_blocks = ken_clone
     naomi_blocks.sort(reverse=True)
     ken_blocks.sort(reverse=True)
     deceiving_strat_score = 0
     while naomi_blocks != []:
         naomi_new_val = naomi_blocks.pop()
         if naomi_new_val > ken_blocks[-1]:
             ken_blocks.pop()
             deceiving_strat_score += 1
 
     print "Case #%d: " % (qrpgz + 1), deceiving_strat_score, naomi_standard_score
 
 
 
