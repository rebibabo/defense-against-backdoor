import sys
 
 if __name__ == "__main__":
     ienxl = sys.stdin
     if len(sys.argv) >= 2:
         xzrtf = sys.argv[1]
         if xzrtf != '-':
             ienxl = open(xzrtf)
 
     qtuih = int(ienxl.readline())
     for yospe in xrange(qtuih):
         eofbl = int(ienxl.readline())
         dikzv = map(float, ienxl.readline().split())
         eghaj = map(float, ienxl.readline().split())
         assert len(eghaj) == len(dikzv) == eofbl
 
         dikzv = [(w, 1) for w in dikzv]
         eghaj = [(w, 0) for w in eghaj]
 
         blocks = eghaj + dikzv
         blocks.sort(reverse=True)
         blocks = [p[1] for p in blocks]
         # print blocks
 
         honest = 0
         adv = 0
         for b in blocks:
             if b == 1:
                 adv += 1
                 honest = max(honest, adv)
             else:
                 adv -= 1
 
         deceitful = 0
         kept = 0
         for b in blocks:
             if b == 1:
                 kept += 1
             else:
                 if kept:
                     kept -= 1
                     deceitful += 1
         print "Case #%d: %d %d" % (yospe+1, deceitful, honest)
