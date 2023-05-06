# coding:utf-8
 import sys
 
 def testcases():
     with open(sys.argv[1], "r") as f:
         f.readline()  # skip number of testcases
         for cajyq, zbrcm in enumerate(f, 1):
             yield cajyq, [int(kdnew) for kdnew in zbrcm.split()]
 
 def main():
     for cajyq, zbrcm in testcases():
         tjbfd = zbrcm[0]   # the number of Googlers
         S = zbrcm[1]   # the number of surprising triplets of scores
         p = zbrcm[2]   # best result of at least
 
         y = 0
 
         for kdnew in zbrcm[3:]:
             d, m = divmod(kdnew, 3)
             if m == 0:
                 if p <= d:
                     y += 1
                 elif 0 <= d - 1 <= p <= d + 1 <= 10 and S:
                     y += 1
                     S -= 1
             elif m == 1:
                 if p <= d + 1:
                     y += 1
             else:
                 if p <= d + 1:
                     y += 1
                 elif p <= d + 2 and S:
                     y += 1
                     S -= 1
 
         print("Case #{:d}: {:d}".format(cajyq, y))
 
 if __name__=="__main__":
     main()
