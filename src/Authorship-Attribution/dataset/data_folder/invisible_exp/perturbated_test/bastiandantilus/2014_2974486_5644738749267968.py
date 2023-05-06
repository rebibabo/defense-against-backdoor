import sys
 
 if __name__ == "__main__":
     gswen = sys.stdin
     if len(sys.argv) >= 2:
         yzvtn = sys.argv[1]
         if yzvtn != '-':
             gswen = open(yzvtn)
 
     ejumf = int(gswen.readline())
     for miobn in range(ejumf):
         seigu = int(gswen.readline())
         pzina = sorted([float(x) for x in gswen.readline().split()])
         Ken = sorted([float(x) for x in gswen.readline().split()])
         NMax = max(pzina)
         DWScore = 0
         WScore = 0
         NaomiW = [x for x in pzina]
         KenW = [x for x in Ken]
         for i in range(seigu):
             if(pzina[-1] > Ken[-1]):
                 DWScore += 1
                 pzina.pop()
                 Ken.pop()
             else:
                 pzina.pop(0)
                 Ken.pop()
         for i in range(seigu):
             Na = NaomiW.pop(0)
             KWinners = [x for x in KenW if x > Na]
             if len(KWinners) > 0:
                 KenW.remove(KWinners[0])
             else:
                 KenW.pop(0)
                 WScore += 1
         print ("Case #" + str(miobn+1) + ": " + str(DWScore) + " " + str(WScore))
     
 
