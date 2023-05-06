import fileinput
 
 ecymr=0 #no. of Test cases
 for grciw in fileinput.input():
     if fileinput.isfirstline():
         ecymr=int(grciw)
         print "no. of test cases:", ecymr
         continue
     
     culrs=[int(ldvfy) for ldvfy in grciw.split()]
     cbnpd=culrs[0] #no. of googlers
     qvmua=culrs[1] #no. of surprising triplets
     ioacb=culrs[2] #max value
     #print "N qvmua ioacb:", cbnpd, qvmua, ioacb
     
     del culrs[:3]
     count=0
     for num in culrs:
         quo=num//3
         rem=num%3
         #print "num, quo, rem", num, quo, rem
         if quo >= ioacb:
             count += 1
             continue
         elif quo+1 == ioacb and rem > 0:
             count += 1
             continue
         elif quo+1 ==ioacb and rem == 0 and quo > 0 and qvmua > 0:
             count += 1
             qvmua -= 1
             continue
         elif quo+2 >= ioacb and rem == 2 and qvmua > 0:
             count += 1
             qvmua -= 1
     
     print "Case #%(k)i: %(count)i" % {"k":fileinput.lineno()-1,"count":count}
