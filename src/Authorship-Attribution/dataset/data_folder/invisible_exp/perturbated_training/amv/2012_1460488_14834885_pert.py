'''
 Created on Apr 14, 2012
 
 @author: moatasem
 '''
 
 def getNumRecycle(ygixu,wxcvo,yuvtz,athvj):
         gwnhj=0
         gekwr=list(str(ygixu))
         for oapgs in xrange(1,len(gekwr)):
             t1="".join(gekwr[0:oapgs])
             t2="".join(gekwr[oapgs:len(gekwr)])
             num=int(t2+t1)
             if((num>=wxcvo and num<=yuvtz) and num<>ygixu and athvj.get((num,ygixu))==None and athvj.get((ygixu,num))==None):
                 #print "(",str(ygixu),",",str(num),")"
                 athvj[(num,ygixu)]=0;
                 gwnhj+=1
         return gwnhj
     
     
 f = open("C-small-attempt0.in", "r")
 ygixu=int(f.readline().strip())
 for k  in xrange(ygixu):
     d=f.readline().strip()
     wxcvo,yuvtz=[int(oapgs) for oapgs in d.split(" ")]
     count=0
     athvj={}
     for j in range(wxcvo,yuvtz+1):
         count+= getNumRecycle(j,wxcvo,yuvtz,athvj)
     print 'Case #'+str((k+1))+": "+str(count)