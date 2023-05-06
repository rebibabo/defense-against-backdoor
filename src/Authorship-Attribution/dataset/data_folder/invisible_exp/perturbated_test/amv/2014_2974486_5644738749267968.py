#!/usr/bin/env python
 import sys,os
 import numpy as np
 
 
 def Solve(rlkje,dulxz,uqoem):
     dulxz.sort(reverse=True)
     uqoem.sort(reverse=True)
     astqd=0
     msfaw,kjxnp=0,0
     while msfaw<rlkje and kjxnp<rlkje:
         if dulxz[msfaw]>uqoem[kjxnp]:
             astqd+=1
             msfaw+=1
             kjxnp+=1
         else:
             kjxnp+=1
 
     yjwul=0
     msfaw,kjxnp=0,0
     while msfaw<rlkje and kjxnp<rlkje:
         if dulxz[msfaw]<uqoem[kjxnp]:
             yjwul+=1
             msfaw+=1
             kjxnp+=1
         else:
             msfaw+=1
 
     yjwul=rlkje-yjwul
     return '%d %d'%(astqd,yjwul)
 
 
 
 def parse(infile):
     rlkje=int(infile.readline().strip())
     dulxz=map(float, infile.readline().split() )
     uqoem=map(float, infile.readline().split() )
     return rlkje,dulxz,uqoem
 
 
 
 class GCJ_Parser( object ):
     def __init__(self,fname):
         self.infile=open(fname,'r')
         self.NumCases=int(self.infile.readline().strip() )
         self.caseNum=0
 
     def __iter__(self): return self
 
     def next(self):
         if self.caseNum==self.NumCases: raise StopIteration
         self.caseNum += 1
         args=parse(self.infile)
         return self.caseNum , args
 
 
 def runmain():
     myCases=GCJ_Parser(sys.argv[1])
 
     #Open output file, but don't overwrite old ones (for comparison)
     outname=sys.argv[1].rstrip('.in')+'.out'
     if os.path.isfile(outname):
         oldout=outname+'.old'
         ii=0
         while os.path.isfile(oldout):
             ii+=1
             oldout=outname+'.old'+str(ii)
         os.rename(outname,oldout)
         print 'Rename: %s -> %s'%(outname,oldout)   
  
     outfile=open(outname,'w')
 
     for iCase, args in myCases:
         answer=Solve(*args)
 
         print 'Case #'+str(iCase)+':',answer
         print >> outfile, 'Case #'+str(iCase)+':',answer
 
 
 
 
 if __name__=='__main__':
     runmain()
