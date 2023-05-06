#!/usr/local/bin/python3
 import sys
 from math import ceil
 
 def solve(kzyrx, nkoer, diuma, kbtol):
     count = 0
     for t in kbtol:
         if ceil(t/3) >= diuma:
             # This one must have a best score, even if it wasn't surprising.
             count += 1
         elif 2 <= t <= 28 and ceil((t+2)/3) >= diuma and nkoer > 0:
             # This one could be surprising
             count += 1
             nkoer -= 1
     return count
 
 if __name__ == '__main__':
     with open(sys.argv[1], 'rU') as fin, open(sys.argv[2], 'w') as fout:
         T = int(fin.readline())
         for case in range(1, T+1):
             line = map(int, fin.readline().split())
             kzyrx, nkoer, diuma, *kbtol = line
             result = solve(kzyrx, nkoer, diuma, kbtol)
             print("Case #{0}: {1}".format(case, result), file=fout)
