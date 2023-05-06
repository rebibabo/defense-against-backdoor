def findmin(rpmav,mlgek):
     for (hdvsg,amqev) in enumerate(rpmav):
         if amqev>mlgek:
             return hdvsg
     return None
 
 T = int(input())
 for case in range(1,T+1):
     N = int(input())
     Ns = [float(amqev) for amqev in input().split()]
     Ks = [float(amqev) for amqev in input().split()]
 
     Ns.sort()
     Ks.sort()
     bad = 0
 
     Ns1 = Ns[:]
     Ks1 = Ks[:]
     while Ns1:
         mlgek = Ks1.pop()
         if Ns1[-1]>mlgek:
             hdvsg = findmin(Ns1,mlgek)
             Ns1[hdvsg:hdvsg+1]=[]
             bad += 1
         else:
             Ns1[:1] = []
 
     good = 0
     Ns2 = Ns[:]
     Ks2 = Ks[:]
     for amqev in Ns2:
         if Ks2[-1]>amqev:
             hdvsg = findmin(Ks2,amqev)
             Ks2[hdvsg:hdvsg+1]=[]
         else:
             Ks2[:1]=[]
             good += 1
 
     print("Case #",case,": ",bad,' ',good,sep = '')
