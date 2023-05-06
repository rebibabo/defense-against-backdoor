kmict = raw_input("Name of file: ")
 pejmo = open(kmict, "r")
 tbqas = open(kmict + ".out", "w")
 
 
 cnxwd = int(pejmo.readline()[:-1])
 ## Code starts here
 
 def playDecWars(otykc, hfyng):
     n = len(otykc)
     count = 0
     while n > 0:
         if otykc[0] < hfyng[0]:
             del(otykc[0])
             del(hfyng[-1])
         else:
             del(otykc[0])
             del(hfyng[0])
             count += 1
         n -= 1
     return count
 
 def playWars(otykc, hfyng):
     n = len(otykc)
     count = 0
     while n > 0:
         if otykc[0] > hfyng[-1]:
             count += 1
             del(otykc[0])
             del(hfyng[0])
         else:
             his_index = 0
             for num in hfyng:
                 if num > otykc[0]:
                     break
                 his_index += 1
             del(otykc[0])
             del(hfyng[his_index])
         n -= 1
     return count
 
 for t in range(1, cnxwd + 1):
     n = int(pejmo.readline()[:-1])
     otykc = pejmo.readline()[:-1].split(" ")
     hfyng = pejmo.readline()[:-1].split(" ")
     otykc = [float(x) for x in otykc]
     hfyng = [float(x) for x in hfyng]
 
     otykc.sort()
     hfyng.sort()
 
     d_wars = playDecWars(otykc[:], hfyng[:])
     wars = playWars(otykc[:], hfyng[:])
     
     tbqas.write("Case #%d: %d %d\n" % (t, d_wars, wars))
     
         
     
 ## code ends here
 
 tbqas.close()
 pejmo.close()
