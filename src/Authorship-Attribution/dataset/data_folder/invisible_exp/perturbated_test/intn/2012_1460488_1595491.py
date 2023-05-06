'''
 Created on 2012-4-14
 
 @author: hemnd
 '''
 def cal(umarh):
     yftha = 0
     hclqz = int(umarh[0])
     djfgo = int(umarh[1])
     feynv = int(umarh[2])
     for vutsb in range(hclqz):
         avyem = int(umarh[vutsb + 3])
         e = avyem / 3
         r = avyem % 3
         if e >= feynv:
             yftha += 1
         elif r == 1:
             if e + 1 >= feynv:
                 yftha += 1
         elif r == 0:
             if e == 0:
                 continue
             if djfgo > 0 and e + 1 >= feynv:
                 yftha += 1
                 djfgo -= 1
         elif r == 2:
             if e + 1 >= feynv:
                 yftha += 1
             elif djfgo > 0 and e + 2 >= feynv:
                 yftha += 1
                 djfgo -= 1
     return yftha
 
 inputFile = open('B-small-attempt0.in', 'r')
 #inputFile = open('test.txt', 'r')
 inputLines = inputFile.readlines()
 inputFile.close()
 
 T = int(inputLines[0])
 outputLines = []
 
 for vutsb in range(1, T + 1):
     umarh = inputLines[vutsb].strip().split(' ')
     outputLines.append('Case #%d: %d\n' % (vutsb, cal(umarh)))
     print outputLines[vutsb - 1],
 
 outputFile = open('B-small.out', 'w')
 outputFile.writelines(outputLines)
 outputFile.close()
