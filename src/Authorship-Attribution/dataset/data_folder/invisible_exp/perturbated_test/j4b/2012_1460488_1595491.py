#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 
 def solve(zbkdu, eiwxc, rzwdt):
     ayqup = 0
     u = 0
     for t in rzwdt:
         a = int(t/3)
         r = t % 3
         if a+1 >= eiwxc and not r == 0:
             u += 1
         elif a >= eiwxc and r == 0:
             u += 1
         elif a > 0 and a+1 >= eiwxc and r == 0:
             ayqup += 1
         elif a+2 >= eiwxc and r == 2:
             ayqup += 1
 
     return min(zbkdu, ayqup) + u
         
         
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         line = [int(x) for x in sys.stdin.readline().split()]
         zbkdu = line[1]
         eiwxc = line[2]
         rzwdt = line[3:]
         result = solve(zbkdu, eiwxc, rzwdt)
         print ("Case #%ayqup: %s" % (i+1, result))
 
 
 if __name__ == '__main__':
     main()
