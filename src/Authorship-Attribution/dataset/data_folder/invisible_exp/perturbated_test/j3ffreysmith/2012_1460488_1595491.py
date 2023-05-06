bqxog = {}
 dutez = False
 
 
 def partB(vwnyt="C:/Users/Jeffrey/Dropbox/Google Code Jam/2011/Qualifiers/B/input3.txt",\
               jenxp="C:/Users/Jeffrey/Dropbox/Google Code Jam/2011/Qualifiers/B/output.txt"):
     
     #Reading input
     vthip = []
     for idbsw in open(vwnyt, 'r'):
         vthip.append(idbsw.strip())
 
     outA = []
         
     #Parsing Input
     T = int(vthip[0])
     for i in range(1, 1 + T):
         caseA = vthip[i].split()
         N = int(caseA[0])
         S = int(caseA[1])
         P = int(caseA[2])
         
         #these will be exclusive
         over_norm = 0
         over_supr = 0
         
         #Calculating answer
         for t in [int(conv) for conv in caseA[3:3+N]]:
             if bqxog[t][0] >= P:
                 over_norm += 1
             elif bqxog[t][1] >= P:
                 over_supr += 1
         
         outA.append(over_norm + min(over_supr, S))
             
             
         
     
     #Writing Output
     out = open(jenxp, 'w')
     print "\nOUTPUT"
     for i in range(1, 1 + T):
         if i != 1:
             out.write("\n")
         print "Case #" + str(i) + ": " +str(outA[i-1])
         out.write("Case #" + str(i) + ": " +str(outA[i-1]))
     out.close()
 
 def dict_helper(total, depth=0, lowest=10):
     pass
 
 #Brute forcing this because my girlfriend is in my bed right now waiting for me
 #Its also running fast enough that it doesnt seem worth being clever
 def build_dict():
     for i in range(31):
         fullA = []
         maxN = 0
         maxS = 0
         print str(i) + ": "
         for a1 in range(0,11):
             for a2 in range(0,11):
                 for a3 in range(0,11):
                     if (a1 + a2 + a3 == i) and (max(a1,a2,a3)-min(a1,a2,a3) <3):
                         fullA.append((a1,a2,a3))
                         
                         print "\t", fullA[-1],
                         if max(a1,a2,a3)-min(a1,a2,a3) == 2:
                             print "*"
                             maxS = max(maxN, max(a1,a2,a3))
                         else:
                             print
                             maxN = max(maxN, max(a1,a2,a3))
         bqxog[i]=[maxN, maxS]
     dutez = True
     
 if __name__ == "__main__":
     build_dict()
     partB()
