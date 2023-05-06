#Code copyright Brett Olsen, 2012
 
 #Standard imports
 import numpy as N
 
 #Some switching behavior to set standard input/output files
 #Set these three variables to adjust behavior
 xhovt = 1
 yzskg = "B"
 ufdvo = False
 
 if ufdvo:
     ufdvo = "-practice"
 else:
     ufdvo = "-attempt0"
 if xhovt == 0:
     egsmi = open("%s-%s%s.in" % (yzskg, "sample", ufdvo), 'r')
     cqyna = open("%s-%s%s.out" % (yzskg, "sample", ufdvo), 'w')        
 elif xhovt == 1:
     egsmi = open("%s-%s%s.in" % (yzskg, "small", ufdvo), 'r')
     cqyna = open("%s-%s%s.out" % (yzskg, "small", ufdvo), 'w')    
 elif xhovt == 2:
     egsmi = open("%s-%s%s.in" % (yzskg, "large", ufdvo), 'r')
     cqyna = open("%s-%s%s.out" % (yzskg, "large", ufdvo), 'w')   
 else:
     raise ValueError, 'Invalid case'
     
 #Hash table:  -1 is never, +1 is always, 0 is sometimes
 mqwsv = {}
 
 def check_total(total, p):
     assert type(total) == int
     resid = total % 3
 
     min_nosurprise = (total / 3)    
     max_nosurprise = (total / 3) + min(1, resid)
     
     #print min_nosurprise, max_nosurprise
     
     #If the residual is 1, then being surprising won't change the max value
     #similarly, if we're already at the maximum, it can't change
     #similarly, if residual is 0 and the min value is already 0, it can't change
     if resid == 1 or max_nosurprise == 10 or (resid == 0 and min_nosurprise == 0):
         if max_nosurprise >= p:
             return 1
         else:
             return -1
 
     if max_nosurprise >= p:
         return 1
     elif max_nosurprise == p-1:
         return 0
     else:
         return -1
     
 #Read in the number of cases (the first input line) we need to iterate through
 cases = int(egsmi.readline().strip('\n'))
 for i in range(cases):
     print 'case %i of %i' % (i+1, cases)
 ##################NEW CODE GOES HERE###########################################    
     #Read in all relevant data for each xhovt
     line = egsmi.readline().split()
     N = int(line[0])
     S = int(line[1])
     p = int(line[2])
     points = []
     for item in line[3:]:
         points.append(int(item))
     assert N == len(points)
     assert p <= 10
     assert p >= 0
     assert S >= 0
     assert S <= N
     
     always = 0
     possible = 0
     
     for total in points:
         if (total, p) in mqwsv:
             depends = mqwsv[total, p]
         else:
             depends = check_total(total, p)
         
         if depends == 1:
             always += 1
         elif depends == 0:
             possible += 1
     
     #Do calculations to generate the output
     output = '%i' % (always + min(possible, S))
     
 ##################NEW CODE GOES HERE###########################################    
     #Write out the results for this xhovt
     cqyna.write('Case #%i: %s\n' % (i+1, output))
     
 #Close files
 egsmi.close()
 cqyna.close()