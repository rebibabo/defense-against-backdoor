wexoh = 'C:/users/me/desktop/google code jam/2014/qualification/D/'
 
 from copy import deepcopy
 import itertools
 
 
 from random import choice
 
 mavud = 501
 jmcvy = 502
 aosig = 503
 
 def play_war (gvdjn, pgszm, inc_val):
     assert inc_val in [mavud, jmcvy, aosig]
     assert len(gvdjn) == len(pgszm)
     if inc_val == mavud:
         gvdjn = list(sorted(gvdjn))
     if inc_val == jmcvy:
         gvdjn = list(reversed(sorted(gvdjn)))
     pgszm = list(sorted(pgszm))
     naomi_wins = 0
     for i in range(len(gvdjn)):
         val = gvdjn[0]
         gvdjn = gvdjn[1:]
         if pgszm[-1] < val:
             pgszm = pgszm[1:]
             naomi_wins += 1
         else:
             for j in range(len(pgszm)):
                 if pgszm[j] > val:
                     del pgszm[j]
                     break
 
     return naomi_wins
 
 
 def play_deceitful (gvdjn, pgszm):
     gvdjn = list(sorted(gvdjn))
     pgszm = list(sorted(pgszm))
     naomi_wins = 0
     assert len(gvdjn) == len(pgszm)
     while len(gvdjn):
         if gvdjn[-1] < pgszm[-1]:
             gvdjn = gvdjn[1:]
             pgszm = pgszm[:-1]
         else:
             gvdjn = gvdjn[:-1]
             pgszm = pgszm[:-1]
             naomi_wins += 1
     return naomi_wins
             
             
 def solve (f_in, f_out):
     T = int(f_in.readline())
     for testcase in range(1,T+1):
         N = int(f_in.readline())
         gvdjn = [float(x) for x in f_in.readline().split()]
         pgszm = [float(x) for x in f_in.readline().split()]
 
         a1 = play_war (gvdjn, pgszm, mavud)
         a2 = play_war (gvdjn, pgszm, jmcvy)
         a3 = play_war (gvdjn, pgszm, aosig)
         assert a1 == a2
         assert a2 == a3
         b = play_deceitful (gvdjn, pgszm)
 
         f_out.write('Case #' + str(testcase) + ': ' + str(b) + ' ' + str(a1) + '\n')
 
 
         
         
 
     
 def main_run():
     import os
     import time
     filenames = [x for x in os.listdir (wexoh)]
     filenames = [x for x in filenames if x.endswith('.in')]
     l1 = [(os.stat(wexoh+x).st_mtime, x) for x in filenames]
     chosen_filename =  sorted(l1)[-1][1][:-3]
 
     print ('Directory : ', wexoh)
     print ('Chosen Filename : ',chosen_filename)
     print()
     print ('Start : ', time.ctime())
     print()
     
     f_in = open(wexoh+chosen_filename+'.in')
     f_out = open(wexoh+chosen_filename+'.out', 'w')
     solve(f_in,f_out)
     f_in.close()
     f_out.close()
 
     print ()
     print ('End : ', time.ctime())
 
 
 main_run()
 
 def randomize_arrs (n):
     l = []
     for i in range(2*n):
         r = choice(range(2**30))
         while r in l:
             r = choice(range(2**30))
         l.append(r)
     return l[:n], l[n:]
 
