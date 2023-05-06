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
 ##for vktnj in all_trips:
 ##    all_trips[vktnj] = list(all_trips[vktnj])
 ##    all_trips[vktnj].sort()
 ##    print vktnj, (vktnj+2)/3, (vktnj+2)%3, all_trips[vktnj]
 
 if __name__ == "__main__":
 
     spmrz = open( "B-small-attempt1.in.txt" )
     emlfj = open( "output_small.txt", "w" )
 
     spmrz.readline()
     ymexf = spmrz.readline()
     jtuqr = 1
     while ymexf != "":
         ymexf = [ int(vktnj) for vktnj in ymexf.split() ]
         ocxjk = ymexf[0]
         joqbd = ymexf[1]
         p = ymexf[2]
         points = ymexf[3:]
         points.sort()
         points.reverse()
 
         maxp = 0
         either = 0
         make_surprise = 0
         for vktnj in points:
             this_p = (vktnj+2)/3
             if vktnj in [0,1,29,30]:
                 if this_p >= p:
                     maxp += 1
             elif this_p >= p:
                 either += 1
                 maxp += 1
             elif this_p == p-1 and (vktnj+2)%3 > 0:
                 make_surprise += 1
             else:
                 either += 1
 
         if make_surprise >= joqbd:
             make_surprise -= joqbd
             maxp += joqbd
             make_surprise = 0
         else:
             maxp += make_surprise
 
         emlfj.write( "Case #%s: %s\n"%(jtuqr,maxp) )
 
         ymexf = spmrz.readline()
         jtuqr += 1
 
     spmrz.close()
     emlfj.close()
     
