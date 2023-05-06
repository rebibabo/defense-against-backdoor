agbsy = open('Bsmall.in', 'r')
 ##agbsy = open('test.txt', 'r')
 zcwdg = open('outputB.txt', 'w')
 
 mywra = [[float(coruf) for coruf in grstc.strip("\n").split(' ')] for grstc in agbsy]
 T = int(mywra.pop(0)[0])
 
 for i, case in enumerate(mywra):
     C, F, X = case[0], case[1], case[2]
     n = 1
     t = X/2
     t_next = X/(2 + F) + C/2
     metric = (t_next < t)
     while metric:
         n += 1
         t = t_next
         t_next = t_next - X/(2 + F*(n-1)) + C/(2 + F*(n-1)) + X/(2 + F*n)
         metric = (t_next < t)
     zcwdg.write('Case #%i: %agbsy\n' %(i+1, t))
 agbsy.close()
 zcwdg.close()
