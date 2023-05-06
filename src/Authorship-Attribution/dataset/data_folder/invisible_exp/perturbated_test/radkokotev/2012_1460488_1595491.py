cjxey = raw_input("File name: ")
 
 qvbzy = open(cjxey,"r")
 
 jshxe = int(qvbzy.readline()[:-1])
 
 wdshj = []
 
 for maxrt in range (jshxe):
     wdshj += [qvbzy.readline()[:-1]]
 
 qvbzy.close()
 
 
 def compute(avxio):
     dataN = [] # keeps the max mark for not surprising
     dataS = [] # keeps the max marks for surprisings
     
     items = avxio.split() 
     
     for maxrt in range (int(items[0])):
         ti = int(items[3+maxrt])
         if ti < 1:
             dataS += [0]
             dataN += [0]
         elif ti > 28:
             dataS += [10]
             dataN += [10]
         else:
             dataN += [int((ti+2)/3)] # put the values for all participants
             dataS += [int((ti+4)/3)]
         
         
 
     
     p = int(items[2])
     s = int(items[1])
     
     n_vals = dataN
     n_vals.sort()
     
 
     maxrt=0
     minimum = 0 # how many numbers greater than p are there in "not surprising"
     if n_vals[0] < p:
         while n_vals[-1-maxrt] >= p:
             minimum += 1
             maxrt += 1
     else:
         minimum = len(n_vals)
         
 
     s_vals = dataS
     s_vals.sort()
     maxrt=0
     maximum = 0 # how many numbers greater than p are there in "surprising"
     if s_vals[0] < p:
         while s_vals[-1-maxrt] >= p:
             maximum += 1
             maxrt += 1
     else:
         maximum = len(n_vals)
     
             # we can make at most (max) but can take only (min + s)
     return min(maximum, minimum+s) 
     
 
 for maxrt in range (jshxe):
     print "Case #%d: %d" %(maxrt+1,compute(wdshj[maxrt]))
 
 
     
