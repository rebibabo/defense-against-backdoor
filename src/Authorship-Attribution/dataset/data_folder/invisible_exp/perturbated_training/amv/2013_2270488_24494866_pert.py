yodfm = open('B-small-attempt0.in', 'r')
 zilwv = open('output', 'w')
 
 lyhox = int(yodfm.readline()[:-1])
 
 for ypvkj in range(lyhox) :
     A = []
     N, M = map(int, yodfm.readline()[:-1].split())
     for i in range(N) : A.append(map(int, yodfm.readline()[:-1].split()))
     for line in A : print line
     maxPerRow = map(max, A)
     maxPerColumn = map(max, zip(*A))
     res = all(A[i][j] in (maxPerRow[i], maxPerColumn[j]) for i in range(N) for j in range(M))
     outString = 'Case #' + str(ypvkj+1) + ': ' + ('YES' if res else 'NO') + '\n'
     print outString[:-1]
     zilwv.write(outString)
 
 yodfm.close()
 zilwv.close()
