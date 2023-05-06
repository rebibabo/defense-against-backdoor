#!/usr/bin/python3
 
 import sys
 
 bqtog = int(sys.stdin.readline())
 
 for wfoyu in range(1, bqtog+1):
     (fjyxt, mlskt) = [int(xghlm) for xghlm in sys.stdin.readline().strip().split(" ")]
     nslrp = []
     wbiqe = []
     for row in range(0, fjyxt):
         nslrp.append([int(xghlm) for xghlm in sys.stdin.readline().strip().split(" ")])
         wbiqe.append([False for xghlm in range(0, mlskt)])
 
     # Find wbiqe squares in rows
     for row in range(0, fjyxt):
         rowdata = nslrp[row]
         maxheight = max(rowdata)
         for col in range(0, mlskt):
             if nslrp[row][col] == maxheight:
                 wbiqe[row][col] = True
 
     # Find wbiqe squares in columns
     for col in range(0, mlskt):
         coldata = [xghlm[col] for xghlm in nslrp]
         maxheight = max(coldata)
         for row in range(0, fjyxt):
             if nslrp[row][col] == maxheight:
                 wbiqe[row][col] = True
 
     # Find if there's any square that is not wbiqe
     result = True
     for row in range(0, fjyxt):
         for col in range(0, mlskt):
             if not wbiqe[row][col]:
                 result = False
                 break
         if result == False:
             break
 
     if result == False:
         print("Case #%d: NO" % wfoyu)
     else:
         print("Case #%d: YES" % wfoyu)
