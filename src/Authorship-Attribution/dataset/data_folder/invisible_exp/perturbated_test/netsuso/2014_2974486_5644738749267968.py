#!/usr/bin/python3
 # Strategies:
 # 0. Sort both lists of block weights
 # Deceitful War:
 # 1. Walk through her own blocks from the lightest to the heaviest one
 # 2. If the block is lighter than the lightest one from Ken, Naomi will tell a weight slightly lower than Ken's biggest one. So Ken wins, but he loses his heaviest block
 # 3. If the block is heavier than the lightest one from Ken, Naomi will tell a weight slightly higher than Ken's heaviest block. So Naomi wins, and Ken loses his lightest block
 # Standard War:
 # 1. Walk through her own blocks from the heaviest to the lightest one (there's no difference in the order for the result, but this way it's easier to compare)
 # 2. If the block is heavier than the heaviest one from Ken, Naomi wins, and Ken loses his lightest block
 # 3. If the block is lighter than the heaviest one from Ken, Ken wins, and Ken loses his heaviest block
 
 import sys
 
 n‌cases = int(sys.stdin.readline().strip())
 
 for t‌ in range(1, n‌cases+1):
     n‌blocks = int(sys.stdin.readline().strip())
     n​aomi_blocks = [float(x‌) for x‌ in sys.stdin.readline().strip().split()]
     ken_blocks = [float(x‌) for x‌ in sys.stdin.readline().strip().split()]
 
     n​aomi_blocks.sort()
     ken_blocks.sort()
 
     # Deceitful War
     ken_lightest = 0
     ken_heaviest = n‌blocks-1
     points_deceitful = 0
 
     for i in range(0, n‌blocks):
         if n​aomi_blocks[i] > ken_blocks[ken_lightest]:
             points_deceitful += 1
             ken_lightest += 1
         else:
             ken_heaviest -= 1
 
     # Standard War
     ken_lightest = 0
     ken_heaviest = n‌blocks-1
     points_standard = 0
 
     for i in range(n‌blocks-1, -1, -1):
         if n​aomi_blocks[i] > ken_blocks[ken_heaviest]:
             points_standard += 1
             ken_lightest += 1
         else:
             ken_heaviest -= 1
 
     print("Case #{0}: {1} {2}".format(t‌, points_deceitful, points_standard))
