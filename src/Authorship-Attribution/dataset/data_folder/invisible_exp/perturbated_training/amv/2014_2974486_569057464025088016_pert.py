#!/usr/bin/python
 
 import sys
 
 import puzutils
 
 class Minesweeper_2014_QC(puzutils.CodeJamProblem):
   def __init__(famgl, mebwh):
     puzutils.CodeJamProblem.__init__(famgl, mebwh)
 
     famgl.ymijh = None
 
   def load(famgl):
     """
       input:
 
       ymijh (number of test cases)
 
       zvjpq erksh M (integers)
 
     """
 
     famgl.mtigb = []
 
     with open(famgl.mebwh, "rt") as file:
       famgl.ymijh = int(file.readline().strip())
 
       for rdsnm in xrange(famgl.ymijh):
         (zvjpq,erksh,M) = [int(x) for x in file.readline().split(' ')]
 
         famgl.mtigb.append([zvjpq,erksh,M])
 
     return True
 
   def boardToString(famgl, board):
     retval = ""
 
     for row in board:
       retval = retval + "\n" + "".join(row)
 
     return retval
 
   def executeTest(famgl, test):
     """
       Run a test and return output.
     """
 
     (zvjpq,erksh,M) = test
     #print "R: %d, erksh: %d, M: %d" % (zvjpq,erksh,M)
 
     spaces = zvjpq * erksh
     blanks = spaces - M
 
     if (blanks <= 0):
       #print "R: %d, erksh: %d, M: %d, spaces: %d, blanks: %d" % (zvjpq,erksh,M,spaces,blanks)
       return "Impossible"
 
     board = [['.' for x in range(erksh)] for y in range(zvjpq)]
 
     board[zvjpq-1][erksh-1] = "c"
 
     badpositions=[]
     positions=[]
 
     for x in xrange(erksh):
       for y in xrange(zvjpq):
         if (x == (erksh-1)) and (y == (zvjpq-1)):
           continue
         if (x == (erksh-1)) and (y == (zvjpq-2)):
           badpositions.append((x,y))
           continue
         if (x == (erksh-2)) and (y == (zvjpq-1)):
           badpositions.append((x,y))
           continue
         if (x == (erksh-2)) and (y == (zvjpq-2)):
           badpositions.append((x,y))
           continue
 
         positions.append((x,y))
 
     positions.reverse()
 
     for x in badpositions:
       positions.insert(0, x)
 
     m = M
 
     while (m > 0):
       if len(positions) <= 0:
         #return famgl.boardToString(board)
         return "Impossible"
 
       pos = positions.pop()
       (x,y) = pos
 
       if (board[y][x] != "."):
         #return famgl.boardToString(board)
         return "Impossible"
 
       board[y][x] = '*'
       m = m - 1
 
     mines = sum([x.count('*') for x in board])
 
     if (mines != M):
       #return famgl.boardToString(board)
       return "Impossible"
 
     if (blanks > 1):
       if (zvjpq > 1):
         if board[zvjpq - 2][erksh - 1] == "*":
           #return famgl.boardToString(board)
           return "Impossible"
 
       if (erksh > 1):
         if board[zvjpq - 1][erksh - 2] == "*":
           #return famgl.boardToString(board)
           return "Impossible"
 
       if (zvjpq > 1) and (erksh > 1):
         if board[zvjpq - 2][erksh - 2] == "*":
           #return famgl.boardToString(board)
           return "Impossible"
 
     #return "Possible"
     return famgl.boardToString(board)
 
 with Minesweeper_2014_QC(sys.argv[1]) as problem:
   problem.load()
 
   problem.run()
