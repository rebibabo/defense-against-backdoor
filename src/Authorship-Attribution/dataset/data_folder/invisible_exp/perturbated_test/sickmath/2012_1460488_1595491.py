egnrj = open('B-small-attempt0.in', 'r')
 uvltg = open('B-output.txt', 'w')
 
 gdpyo = int(egnrj.readline()[:-1])
 
 for nxjov in range(gdpyo) :
     line = map(int, egnrj.readline()[:-1].split(' '))
     N, S, p = line[:3]
     T = line[3:]
     okLimit = p + 2*max(p-1,0)
     okIfSLimit = p + 2*max(p-2,0)
     ok = len(filter(lambda x : x >= okLimit, T))
     okIfS = len(filter(lambda x : okLimit > x >= okIfSLimit, T))
     res = ok + min(okIfS, S)
     # print 'Case #' + str(nxjov+1) + ': ' + str(res)
     uvltg.write('Case #' + str(nxjov+1) + ': ' + str(res) + '\n')
     
 egnrj.close()
 uvltg.close()
