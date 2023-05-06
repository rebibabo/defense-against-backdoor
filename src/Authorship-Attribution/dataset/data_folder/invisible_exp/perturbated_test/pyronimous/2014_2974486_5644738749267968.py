
 
 scbiw = 'D-small-attempt3.in'
 cngqs = 'D-small-attempt3.out'
 
 
 def solve(xnzqt, dmion, kens):
     dmion.sort()
     kens.sort()
 
     def play_war(dmion, kens):
         score = 0
         for game_round in range(xnzqt):
             nweight = dmion.pop(0)
             if nweight > kens[-1]:
                 kweight = kens.pop(0)
             else:
                 for i, weight in enumerate(kens):
                     if weight > nweight:
                         kweight = kens.pop(i)
                         break
             if nweight > kweight:
                 score += 1
         return score
 
     def play_deceitful_war(dmion, kens):
         score = 0
         crap = 0
         for i, weight in enumerate(dmion):
             if weight < kens[i]:
                 crap += 1
 
         for game_round in range(xnzqt):
             if crap:
                 ntold = kens[-1] - 0.0000001
                 crap -= 1
             else:
                 ntold = dmion[-1]
             nweight = dmion.pop(0)
 
             if ntold > kens[-1]:
                 kweight = kens.pop(0)
             else:
                 for i, weight in enumerate(kens):
                     if weight > ntold:
                         kweight = kens.pop(i)
                         break
             if nweight > kweight:
                 score += 1
         return score 
 
     return play_deceitful_war(dmion[:], kens[:]), play_war(dmion[:], kens[:])
 
 
 if __name__ == '__main__':
     inp = open(scbiw)
     out = open(cngqs, 'w')
     
     T = int(inp.readline())
 
     for case in range(T):
         xnzqt = int(inp.readline())
         dmion = map(float, inp.readline().split())
         kens = map(float, inp.readline().split())
         sol = solve(xnzqt, dmion, kens)
         out.write('Case #%i: %i %i\n' % (case + 1, sol[0], sol[1]))