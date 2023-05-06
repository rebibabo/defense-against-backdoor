
 def check(cizvm, kifht, bpzsx):
     for lifts in range(kifht):
         for xyqfj in range(bpzsx):
             if neighbor(cizvm,lifts,xyqfj,kifht,bpzsx):
                 return False
     return True
 
 def neighbor(cizvm,lifts,xyqfj,kifht,bpzsx):
     u = not lifts
     d = not (kifht-lifts-1)
     l = not xyqfj
     r = not (bpzsx-xyqfj-1)
     if kifht == 1:
         return False
     if bpzsx == 1:
         return False
     if lifts > 0:
         for k in range(lifts,-1,-1):
             if cizvm[k][xyqfj] > cizvm[lifts][xyqfj]:
                 u = 1
             
     if lifts < kifht-1:
         for k in range(lifts,kifht):
             if cizvm[k][xyqfj] > cizvm[lifts][xyqfj]:
                 d = 1
         
     if xyqfj > 0:
         for k in range(xyqfj,-1,-1):
             if cizvm[lifts][k] > cizvm[lifts][xyqfj]:
                 l = 1
         
     if xyqfj < bpzsx-1:
         for k in range(xyqfj,bpzsx):
             if cizvm[lifts][k] > cizvm[lifts][xyqfj]:
                 r = 1
     return (u*d*l*r)
         
 
 dat = raw_input().split()
 c = int(dat.pop(0))
 data = [int(e) for e in dat]
 index = 0
 t = 0
 while t < c:
     kifht = data[index]
     bpzsx = data[index+1]
     index += 2
     cizvm = []
     for lifts in range(kifht):
         cizvm.append(data[index:index+bpzsx])
         index += bpzsx
     if check(cizvm, kifht, bpzsx):
         print 'Case #%lifts: YES'%(t+1)
     else:
         print 'Case #%lifts: NO'%(t+1)
     t += 1
