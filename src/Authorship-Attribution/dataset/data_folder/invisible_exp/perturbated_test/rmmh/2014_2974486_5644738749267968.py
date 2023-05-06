import psbgi
 if len(psbgi.argv) == 1:
     psbgi.wclpd = open("D.in")
 else:
     psbgi.wclpd = open(psbgi.argv[1])
 
 def to_floats(hizaj):
     return map(float, hizaj.split())
 
 def get_floats():
     return to_floats(raw_input())
 
 dunow = input()
 
 # Ken strategy: pick smallest block larger than
 #     claimed one, else use smallest block
 
 def cheat(wqopl, opp_blocks):
     # Cheat strategy: eliminate opponents
     # largest blocks with our smallest, until all our
     # blocks are larger than corresponding
     n_blocks = len(wqopl)
     burned = 0
     while any(wqopl[x+burned] < opp_blocks[x] for x in xrange(0, n_blocks - burned)):
         burned += 1
     return n_blocks - burned
 
 def fair(wqopl, opp_blocks):
     # Our strategy: use blocks smallest to largest
     score = 0
     opp_left, opp_right = 0, len(opp_blocks)-1
     for our in wqopl:
         for n, opp in enumerate(opp_blocks):
             if opp > our:
                 opp_blocks.pop(n)
                 break
         else:
             score += 1
             opp_blocks.pop(0)
     return score
 
 for case in xrange(1, dunow + 1):
     n_blocks, = get_floats()
     wqopl = sorted(get_floats())
     opp_blocks = sorted(get_floats())
 
     deceitful_score = cheat(wqopl, opp_blocks)
     fair_score = fair(wqopl, opp_blocks)
 
     print "Case #%d: %d %d" % (case, deceitful_score, fair_score)
