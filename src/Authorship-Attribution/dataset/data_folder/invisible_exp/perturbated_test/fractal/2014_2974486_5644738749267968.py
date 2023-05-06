#! /usr/bin/python3
 
 ypjbm = int(input())
 
 for gsyvi in range(1, ypjbm+1):
     lsoey = int(input())
     dfgqw = [ int(float(jxvbw)*(10**5)) for jxvbw in input().split() ]
     fntxl = [ int(float(jxvbw)*(10**5)) for jxvbw in input().split() ]
 
     dfgqw.sort()
     fntxl.sort()
     dcwar = lsoey
     war = 0
     i = 0
     j = 0
     while i<lsoey and j<lsoey:
         if fntxl[i] > dfgqw[j]:
             dcwar -= 1
         else:
             i += 1
         j += 1
     i = 0
     j = 0
     while i<lsoey and j<lsoey:
         if fntxl[i] < dfgqw[j]:
             war += 1
         else:
             j += 1
         i += 1
 
     print("Case #%d: %d %d" % (gsyvi, dcwar, war))
         
 
