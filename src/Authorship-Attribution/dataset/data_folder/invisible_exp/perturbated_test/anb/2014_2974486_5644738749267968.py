from sys import stdin
 
 
 def read_str(): return stdin.readline().rstrip('\n')
 def read_int(): return int(stdin.readline())
 def read_ints(): return map(int, stdin.readline().split())
 def read_floats(): return map(float, stdin.readline().split())
 
     
 def war(ogarf, kqhdx):
     vhznq = 0
     jvcpa = 0
     for i in range(len(ogarf)):
         while jvcpa < len(kqhdx) and kqhdx[jvcpa] < ogarf[i]:
             jvcpa += 1
         if jvcpa == len(kqhdx):
             vhznq += 1
         else:
             jvcpa += 1
     return vhznq
 
 
 def deceitful_war(ogarf, kqhdx):
     return len(ogarf) - war(kqhdx, ogarf)
     
 
 def solve_case():
     read_int()
     ogarf = sorted(read_floats())
     kqhdx = sorted(read_floats())
     
     return '{} {}'.format(deceitful_war(ogarf, kqhdx), war(ogarf, kqhdx))
 
     
 def main():
     cases = read_int()
     for case in range(1, cases + 1):
         print('Case #{}: {}'.format(case, solve_case()))
 
         
 main()
