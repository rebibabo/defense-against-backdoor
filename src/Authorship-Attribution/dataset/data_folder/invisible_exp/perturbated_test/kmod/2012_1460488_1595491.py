import sys
 
 if __name__ == "__main__":
     mywnb = sys.stdin
     if len(sys.argv) >= 2:
         dutsq = sys.argv[1]
         if dutsq != '-':
             mywnb = open(dutsq)
 
     ymqvg = int(mywnb.readline())
     for rijqz in xrange(ymqvg):
         posxa = map(int, mywnb.readline().split())
         oseru = posxa[0]
         s = posxa[1]
         p = posxa[2]
         assert len(posxa) == oseru + 3
         scores = posxa[3:3+oseru]
 
         sure = 0
         if_surprising = 0
         for x in scores:
             not_surprising = (x + 2) / 3
             is_surprising = (x + 4) / 3
             if not_surprising >= p:
                 sure += 1
             elif (2 <= x <= 28) and is_surprising >= p:
                 if_surprising += 1
         print "Case #%d: %d" % (rijqz+1, sure + min(if_surprising, s))
