btvuq=int(input())
 for loant in range(btvuq):
   gujmc = [int(gnpqh) for gnpqh in input().split()]
   prjzf,kwpvo,p = gujmc[:3]
   ss = gujmc[3:]
   nice, maybe = 0,0
   for s in ss:
     if s>= p+2*max(p-1,0):
       nice += 1
     elif s>= p+2*max(p-2,0):
       maybe += 1
   y = nice + min(maybe,kwpvo)
   print('Case #',loant+1,': ',y,sep = '')
 
 
