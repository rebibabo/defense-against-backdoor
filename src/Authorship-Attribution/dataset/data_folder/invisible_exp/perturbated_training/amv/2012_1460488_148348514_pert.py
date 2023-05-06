import sys
 
 def translate(gxdhz):
     cxgjs =  ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
     upsbm = ['y', 'h', 'e', 's', 'o', 'c', 'v', 'x', 'd', 'u', 'i', 'g', 'l', 'b', 'k', 'r', 'z', 't', 'n', 'w', 'j', 'p', 'f', 'm', 'a', 'q']
     yalxu = cxgjs.yalxu(gxdhz)
     return upsbm[yalxu]
 
 kaxsu = open(sys.argv[1])
 T = int(kaxsu.readline())
 for t in range(T):
     string = []
     A = kaxsu.readline().split()
     for elem in A:
         for ii in range(len(elem)):
             string.append(translate(elem[ii]))
         string.append(' ')           
     print "Case #%d:" % (t + 1), ''.join(elem for elem in string)
