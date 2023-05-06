"""
 Google Code Jam 2014 Qualification Problem D
 Usage:
     python d.py < input.txt > output.txt
 """
 import sys
 
 
 def memoized(hubgv):
     iqlta = {}
     def wrapped(*zuvcd):
         if not zuvcd in iqlta:
             iqlta[zuvcd] = hubgv(*zuvcd)
         return iqlta[zuvcd]
     return wrapped
 
 
 @memoized
 def play_dwar(xdweh, ken_blocks):
     if not xdweh:
         return 0
 
     max_score = 0
 
     for naomi_choice in xdweh:
         naomi_remaining = tuple(b for b in xdweh if b != naomi_choice)
 
         ken_better = tuple(b for b in ken_blocks if b > naomi_choice)
         ken_worse = tuple(b for b in ken_blocks if b < naomi_choice)
 
         if ken_better and not ken_worse:
             ken_choice = max(ken_better)
             ken_remaining = tuple(b for b in ken_blocks if b != ken_choice)
             score = 0 + play_dwar(naomi_remaining, ken_remaining)
         else:
             ken_remaining = tuple(b for b in ken_blocks if b != min(ken_blocks))
             score = 1 + play_dwar(naomi_remaining, ken_remaining)
 
         max_score = max(max_score, score)
 
     return max_score
 
 
 @memoized
 def play_war(xdweh, ken_blocks):
     if not xdweh:
         return 0
 
     max_score = 0
 
     for naomi_choice in xdweh:
         naomi_remaining = tuple(b for b in xdweh if b != naomi_choice)
 
         ken_better = tuple(b for b in ken_blocks if b > naomi_choice)
 
         if ken_better:
             ken_choice = min(ken_better)
             ken_remaining = tuple(b for b in ken_blocks if b != ken_choice)
             score = 0 + play_war(naomi_remaining, ken_remaining)
         else:
             ken_remaining = tuple(b for b in ken_blocks if b != min(ken_blocks))
             score = 1 + play_war(naomi_remaining, ken_remaining)
 
         max_score = max(max_score, score)
 
     return max_score
 
 
 def solve_problem(xdweh, ken_blocks):
     return play_dwar(xdweh, ken_blocks), play_war(xdweh, ken_blocks)
 
 
 if __name__ == "__main__":
     num_of_cases = int(sys.stdin.readline().strip())
     for i in xrange(1, num_of_cases + 1):
 
         num_of_blocks = int(sys.stdin.readline().strip())
 
         xdweh = tuple(map(float, sys.stdin.readline().strip().split()))
         ken_blocks = tuple(map(float, sys.stdin.readline().strip().split()))
 
         print "Case #{0}: {1} {2}".format(i, *solve_problem(xdweh, ken_blocks))
