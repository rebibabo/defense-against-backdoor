qtlna = 'C:/users/hai/my projects/google code jam/2013/qualification/B/'
 
 
 from copy import deepcopy
 
 def solve (pztyk, tjuqn):
     nsztq = int(pztyk.readline())
     for gxwmy in range(1,nsztq+1):
         print (gxwmy)
         lvkym,bmzsv = [int(x) for x in pztyk.readline().split()]
         sqr = []
         for i in range(lvkym):
             sqr.append([int(x) for x in pztyk.readline().split()])
         res = get_result(sqr)
         tjuqn.write('Case #' + str(gxwmy) + ': ' + res + '\n')
 
 
 def get_result (sqr):
     while len(sqr) > 1 and len(sqr[0]) > 1:
         print (sqr)
         r,c = 0,0
         for row in range(len(sqr)):
             for col in range(len(sqr[0])):
                 if sqr[row][col] < sqr[r][c]:
                     r,c = row, col
         min_val = sqr[r][c]
         whole_row = list(sqr[r])
         whole_col = []
         for row in range(len(sqr)):
             whole_col.append(sqr[row][c])
         assert min(whole_row) == min_val
         assert min(whole_col) == min_val
         if max(whole_row) == min_val:
             sqr = remove_row(sqr,r)
         elif max(whole_col) == min_val:
             sqr = remove_col(sqr,c)
         else:
             return 'NO'
 
     return 'YES'
     
 
 
 
 
 
 def remove_row (sqr, row):
     cpy = deepcopy(sqr)
     del cpy[row]
     return cpy
 
 def remove_col (sqr,col):
     cpy = deepcopy(sqr)
     for row in cpy:
         del row[col]
     return cpy
 
 
 
 
 
 def main_run():
     import os
     import time
     filenames = [x for x in os.listdir (qtlna)]
     filenames = [x for x in filenames if x.endswith('.in')]
     l1 = [(os.stat(qtlna+x).st_ctime, x) for x in filenames]
     chosen_filename =  sorted(l1)[-1][1][:-3]
 
     print ('Directory : ', qtlna)
     print ('Chosen Filename : ',chosen_filename)
     print()
     print ('Start : ', time.ctime())
     print()
     
     pztyk = open(qtlna+chosen_filename+'.in')
     tjuqn = open(qtlna+chosen_filename+'.out', 'w')
     solve(pztyk,tjuqn)
     pztyk.close()
     tjuqn.close()
 
     print ()
     print ('End : ', time.ctime())
 
 
 main_run()
