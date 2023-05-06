#!/usr/bin/python
 
 
 def normal(xjahl, ojwda):
     zifsm = 0
     ihzwo = xjahl[:]
     for yokqz in ojwda[::-1]:
         if yokqz > xjahl[-1]:
             ihzwo = ihzwo[1:]
         else:
             for amcxo in range(len(ihzwo)):
                 if ihzwo[amcxo] > yokqz:
                     del ihzwo[amcxo]
                     zifsm += 1
                     break
     return zifsm
 
 def deceit(xjahl, ojwda):
     ihzwo = xjahl[:]
     n = ojwda[:]
     while(len(n) > 0 and len(n) > 0 and (n[0] < ihzwo[0] or n[-1] < ihzwo[-1])):
             n = n[1:]
             ihzwo = ihzwo[:-1]
     return len(n)
     # zifsm = 0
     # for yokqz in ihzwo[::-1]:
     #     if n[-1] < yokqz:
     #         n = n[1:]
     #     else:
     #         for amcxo in range(len(n)):
     #             if n[amcxo] > yokqz:
     #                 del n[amcxo]
     #                 zifsm += 1
     #                 break
     # return zifsm
 
 
     
 
 
 
 def main():
     # filename = "D-small-attempt0.in"
     filename = "D-small-attempt1.in"
     # filename = "D-large.in"
     # filename = "sample.in"
 
     inp = open(filename, "rU")
 
     n = int(inp.readline().strip())
 
     for case in range(1, n + 1):
         count = int(inp.readline().strip())
         ojwda = sorted(map(float, inp.readline().strip().split()))
         xjahl = sorted(map(float, inp.readline().strip().split()))
 
         war = count - normal(xjahl, ojwda)
         dwar = normal(ojwda, xjahl)
 
         print("Case #{}: {} {}".format(case, dwar, war))
         # print(count)
         # print(ojwda)
         # print(xjahl)
         # print(list(map((lambda x: x[0] > x[1]), zip(ojwda,xjahl))))
         # print()
 
 main()