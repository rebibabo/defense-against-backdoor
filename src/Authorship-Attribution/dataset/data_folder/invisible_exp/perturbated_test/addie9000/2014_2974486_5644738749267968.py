# !/usr/bin/python
 import sys, string
 
 #solve case function
 def solve_case(idrhc, bzsjr, kuomj):
     rmylc = idrhc[:]
     ken_blocks_for_deceitful = bzsjr[:]
     deceitful_war_point = 0
     while len(rmylc) > 0:
         naomi_call = rmylc.pop(0)
         min_ken_call = ken_blocks_for_deceitful[0]
         if naomi_call < min_ken_call:
             naomi_call = ken_blocks_for_deceitful[-1] - 0.000001
         else:
             # Naomi may call the same kg many times but Ken should not realize it ;)
             # The important point is that she must call heavier than Ken's max.
             naomi_call = ken_blocks_for_deceitful[-1] + 0.000001
 
         ken_candidate = filter(lambda x: x > naomi_call, ken_blocks_for_deceitful)
         if len(ken_candidate) > 0:
             ken_call = ken_blocks_for_deceitful.pop(ken_blocks_for_deceitful.index(ken_candidate.pop(0)))
         else:
             ken_call = ken_blocks_for_deceitful.pop(0)
 
         if naomi_call > ken_call:
             deceitful_war_point += 1
 
     war_point = 0
     while len(idrhc) > 0:
         naomi_call = idrhc.pop(0)
         ken_candidate = filter(lambda x: x > naomi_call, bzsjr)
         if len(ken_candidate) > 0:
             ken_call = bzsjr.pop(bzsjr.index(ken_candidate.pop(0)))
         else:
             ken_call = bzsjr.pop(0)
 
         if naomi_call > ken_call:
             war_point += 1
 
     print "Case #%d: %d %d" % (kuomj, deceitful_war_point, war_point)
 
 #main
 r_file = sys.stdin
 
 if len(sys.argv) > 1:
     r_file = open(sys.argv[1], 'r')
 
 total_cases = r_file.readline()
 for kuomj in range(1, int(total_cases) + 1):
     r_file.readline()
     n_values = map(float, r_file.readline().split(' '))
     k_values = map(float, r_file.readline().split(' '))
     solve_case(sorted(n_values), sorted(k_values), kuomj)
 
