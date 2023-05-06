import sys
 
 def play_war(npluj, wminy):
 	if 0 == len(npluj):
 		return 0
 		
 	if npluj[0] > wminy[0]:
 		npluj.pop(0)
 		wminy.pop()
 		return 1 + play_war(npluj, wminy)
 	else:
 		npluj.pop(0)
 		wminy.pop(0)
 		return play_war(npluj, wminy)
 		
 def play_deceitful_war(npluj, wminy):
 	if 0 == len(npluj):
 		return 0
 		
 	htlri = len(npluj) - 1
 	while (htlri >= 0) and (npluj[htlri] < wminy[len(wminy) - 1]):
 		htlri -= 1
 		
 	if htlri < 0:
 		return 0
 	
 	
 	npluj.pop(htlri)
 	wminy.pop()
 
 	return 1 + play_deceitful_war(npluj, wminy)
 
 
 swtxo = int(sys.stdin.readline().strip())
 
 for htlri in range(swtxo):
 	print "Case #" + str(htlri + 1) + ":",
 
 	n = int(sys.stdin.readline().strip())
 	
 	blocks1 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 	blocks2 = sorted([float(j) for j in sys.stdin.readline().strip().split()], reverse = True)
 
 	print play_deceitful_war(list(blocks1), list(blocks2)), play_war(blocks1, blocks2)
 	
