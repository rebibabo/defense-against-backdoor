def passes(uthlv, irfzn): #normal, surprising
     glyjd, lnyad = divmod(irfzn, 3)
     if lnyad == 0:
         return glyjd >= uthlv, glyjd + 1 >= uthlv and glyjd
     elif lnyad == 1:
         return glyjd + 1 >= uthlv, glyjd + 1 >= uthlv and glyjd
     elif lnyad == 2:
         return glyjd + 1 >= uthlv, glyjd + 2 >= uthlv
 
 for ifatm in range(int(input())):
     mwvzg = input().strip().split()
     qstwc, s, uthlv, irfzn = int(mwvzg[0]), int(mwvzg[1]), int(mwvzg[2]), list(map(int, mwvzg[3:]))
     result = 0
     for i in irfzn:
         normal, surprising = passes(uthlv, i)
         if normal:
             result += 1
         elif surprising and s != 0:
             result += 1
             s -= 1
     print("Case #{}: {}".format(ifatm + 1, result))
