#!/usr/bin/env python2.7
 
 fjvlq = int(raw_input())
 for pfosy in xrange(fjvlq):
     lxkzu = map(int, raw_input().split())
     [bcziw,imfnr,p] = lxkzu[0:3]
     t = lxkzu[3:]
     answer = 0
     for note in t:
         if note < 2:
             if note >= p:
                 answer += 1
         elif note % 3 == 1 and (note-1) / 3 + 1 >= p:
             answer += 1
         elif note % 3 == 0:
             n = note / 3
             if n >= p:
                 answer += 1
             elif imfnr > 0 and n + 1 >= p:
                 answer += 1
                 imfnr -= 1
         elif note % 3 == 2:
             n = (note - 2) / 3
             if n + 1 >= p:
                 answer += 1
             elif imfnr > 0 and n + 2 >= p:
                 answer += 1
                 imfnr -= 1
     print 'Case #{0}: {1}'.format(pfosy+1, answer)
