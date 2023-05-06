qprfv = 'C:/users/hai/my projects/google code jam/2012/qualification/B/'
 
 
 def solve (mixqh, bptmn):
     xpwuj = int(mixqh.readline())
     print ('Test cases : ',xpwuj)
     for ezfdt in range(1,xpwuj+1):
         ztwdo = mixqh.readline()
         mgkfl = [int(x) for x in ztwdo.split()]
         S = mgkfl[1]
         p = mgkfl[2]
         t = mgkfl[3:]
         if p > 1:
             A = 0
             B = 0
             for t_i in t:
                 if t_i >=3*p-2:
                     A += 1
                 elif t_i >= 3*p-4:
                     B += 1
             result = A + min(B,S)
         if p == 1:
             result = len([x for x in t if x>=1])
         if p == 0:
             result=  len(t)
         bptmn.write('Case #' + str(ezfdt) + ': ' + str(result) + '\n')
 
 
 
 
 
 
 
 
 
 
 def main_run():
     import os
     filenames = [x for x in os.listdir (qprfv)]
     filenames = [x for x in filenames if x.endswith('.in')]
     l1 = [(os.stat(qprfv+x).st_ctime, x) for x in filenames]
     chosen_filename =  sorted(l1)[-1][1][:-3]
 
     print ('Directory : ', qprfv)
     print ('Chosen Filename : ',chosen_filename)
     print()
     mixqh = open(qprfv+chosen_filename+'.in')
     bptmn = open(qprfv+chosen_filename+'.out', 'w')
     solve(mixqh,bptmn)
     mixqh.close()
     bptmn.close()
 
 
 
 
 main_run()
