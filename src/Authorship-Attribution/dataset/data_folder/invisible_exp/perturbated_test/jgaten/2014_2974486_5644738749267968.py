#!/usr/bin/env python
 
 import sys
 
 def war(wsopb, oafut, bgnac):
     """Return Naomi's score in regular War
 
     Strategy: Naomi and Ken both play their smallest possible block. When Ken
     runs out of winning blocks, Naomi's score is the number of remaining blocks.
 
     Ken's strategy is to select the smallest block which will beat Naomi's
     choice, or if there is none, then his smallest block.
     """
     chnpe = 0   # position through naomi's blocks
     lybsq = 0   # position through ken's blocks
 
     # Play each of Naomi's blocks:
     while chnpe < wsopb:
         # Invariant: chnpe <= lybsq
         # Find a block for Ken to play
         while lybsq < wsopb and bgnac[lybsq] < oafut[chnpe]:
             lybsq += 1
 
         # If Ken is out of playable blocks, game is over.
         if lybsq == wsopb:
             break
 
         # Move on to the next block for each player
         chnpe += 1
         lybsq += 1
 
     return wsopb - chnpe
 
 def deceit(wsopb, oafut, bgnac):
     """Return Naomi's score in Deceitful War
 
     Strategy: Naomi selects the smallest block with size > min(bgnac), and sets
     Told_N > max(bgnac). Ken will choose to play min(bgnac), and lose the round.
     If all of Naomi's blocks are smaller than all of Ken's, she cannot win any
     more rounds, and her score is the number of blocks used (which is also equal
     to wsopb - the number of remaining blocks).
 
     This happens to be the same strategy as regular war, with bgnac and oafut swapped,
     and the final score subtracted from wsopb. (At least, I'm pretty sure it'll work).
     """
     return wsopb - war(wsopb, bgnac, oafut)
 
 
 def solve(wsopb, oafut, bgnac):
     oafut.sort()
     bgnac.sort()
     return "{} {}".format(deceit(wsopb, oafut, bgnac), war(wsopb, oafut, bgnac))
 
 if __name__ == '__main__':
     paint = open(sys.argv[1], 'rU') if sys.argv[1:] else sys.stdin
     fout = open(sys.argv[2], 'w') if sys.argv[2:] else sys.stdout
     with paint, fout:
         T = int(paint.readline())
         for case in xrange(1, T+1):
             n = int(paint.readline())
             oafut = map(float, paint.readline().split())
             bgnac = map(float, paint.readline().split())
             soln = solve(n, oafut, bgnac)
             print >> fout, "Case #{0}: {1}".format(case, soln)
 
