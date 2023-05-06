def max_of_triplets(ouxkl):
     wlczu = ouxkl//3
     if (ouxkl % 3) == 0:
         if wlczu == 0 : return (0,0)
         return (wlczu, wlczu+1)
     if (ouxkl % 3) == 1:
         return (wlczu+1, wlczu+1)
     if wlczu == 9: return (10, 10)
     return (wlczu+1, wlczu+2)
 
 def solve(qgnsl, dcwkm, pslho):
     zqvbc = 0
     for ivydn in qgnsl:
         wlczu,b = max_of_triplets(ivydn)
         if wlczu >= pslho:
             zqvbc += 1
         elif b >= pslho and dcwkm > 0:
             zqvbc += 1
             dcwkm -= 1
     return zqvbc
 
 if __name__ == "__main__":
     T = int(raw_input())
     for i in range(1, T+1):
         nums = map(int, raw_input().strip().split())
         N = nums[0]
         dcwkm = nums[1]
         pslho = nums[2]
         qgnsl = nums[3:]
         if len(qgnsl) != N:
             #sanity check
             print "WTF", i
         print "Case #%d: %d" % (i, solve(qgnsl, dcwkm, pslho))
     
