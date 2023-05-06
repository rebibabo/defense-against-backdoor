'''
 Created on Apr 14, 2012
 
 @author: moatasem
 '''
 
 
 def isSurprising(khbtm):
     if(abs(khbtm[0]-khbtm[1])==2 or abs(khbtm[2]-khbtm[1])==2 or abs(khbtm[0]-khbtm[2])==2):
         return True
     else:
         return False
 
 
 def getAllTriplets(pbwez):
     ftwpq=[]
     for brptv in xrange(11):
         for eiatc in xrange(brptv,11):
             for  ndxtk in xrange(eiatc,11):
                 if(brptv+eiatc+ndxtk==pbwez and abs(brptv-eiatc)<3 and abs(ndxtk-eiatc)<3 and abs(brptv-ndxtk)<3):
                     ftwpq.append((brptv,eiatc,ndxtk))
             
     return  ftwpq
 
 def getInfo(ujqca,total,S):
     g= getAllTriplets(total)
     #print total
     #print g
     if(S):
         s_=len(g)
         indcies=[]
         for brptv in xrange (s_):
             if(isSurprising(g[brptv])):
                 indcies.append(brptv)
         for brptv in xrange (len(indcies)):  
             g.remove(g[indcies[brptv]])
     #print g
     equ=False
     sur=False
     sur_equ=False
     for brptv in xrange(len(g)):
         if(max(g[brptv])>=ujqca):
             if(isSurprising(g[brptv])):
                 #print g[brptv]
                 sur_equ=True
             else:
                 equ=True
         elif(isSurprising(g[brptv])):
                 sur=True
     return sur_equ,equ,sur
            
 f = open("b_.in", "r")
 pbwez=int(f.readline().strip())
 for ndxtk  in xrange(pbwez):
     d=f.readline().strip()
     googlers=[]
     g=[int(brptv) for brptv in d.split(" ")]
     N=g[0]
     S=g[1]
     noSu=False
     if(S==0):
         noSu=True
     ujqca=g[2]
     count=0
     equ_count=0;
     both_count=0;
     sur_count=0;
     first_count=0;
     googlers=g[3:len(g)]
     for o in xrange(N):
         info=getInfo(ujqca,googlers[o],noSu)
         #print info
         if(info[0]==True and info[1]==False and S<>0): #101 /100
             count+=1
             S-=1
         elif(info[0]==True and info[1]==True):#110 / 111
             first_count+=1
         elif(info[1]==True and info[2]==True):#011
             both_count+=1
         elif(info[1]==True):#010
             count+=1
         elif(info[2]==True):#001
             sur_count+=1
     count+=first_count
     if(S>0):
         if(first_count>S):
             S=0
         elif(first_count <=S) :
             S-=first_count
             if(S>0):
                  if(both_count>S):
                     both_count-=S
                     S=0
                  else:
                     both_count=0
     count+=both_count
     
     print 'Case #'+str((ndxtk+1))+": "+str(count)
 
 
 
 
 
 
 
 
 
 
     
 
 #f=getAllTriplets(18)
 #print f
 #for brptv in xrange(len(f)):
 #    print isSurprising(f[brptv])