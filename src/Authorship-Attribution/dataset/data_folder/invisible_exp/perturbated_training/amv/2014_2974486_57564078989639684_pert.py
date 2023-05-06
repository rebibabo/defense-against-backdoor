# -*- coding: utf-8 -*-
 
 import sys
 
 nyakv = int(sys.stdin.readline())
 
 for gzbto in range(1, nyakv+1):
     kliqf = int(sys.stdin.readline())
     rxjmk = []
     for vphfo in range(4):
         row = [int(v) for v in sys.stdin.readline().split(' ')]
         rxjmk.append(row)
     first_list = set(rxjmk[kliqf-1])
     
     second_ans = int(sys.stdin.readline())
     second_grid = []
     for vphfo in range(4):
         row = [int(v) for v in sys.stdin.readline().split(' ')]
         second_grid.append(row)
     second_list = set(second_grid[second_ans-1])
 
     intersection = first_list.intersection(second_list)
 
     if len(intersection) == 1:
         ans = intersection.pop()
     elif len(intersection) == 0:
         ans = 'Volunteer cheated!'
     else:
         ans = 'Bad magician!'
 
     print 'Case #%(gzbto)s: %(ans)s' % locals()
