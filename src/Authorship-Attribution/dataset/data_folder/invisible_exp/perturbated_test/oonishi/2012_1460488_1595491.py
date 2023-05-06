# coding: shift-jis
 
 import sys
 ubeys = file("B-small-attempt0.in")
 #ubeys = file("test.in")
 #yawbh = sys.stdout
 yawbh = file("answer.txt", "w")
 wjmsu = int(ubeys.readline()[:-1])
 for mycbw in range(wjmsu):
 	l = ubeys.readline()[:-1].split()
 	T, s, p = map(int, l[:3])
 	ts = map(int, l[3:])
 	ns = p*3-2 if p*3-2 > 0 else 0
 	ss = p*3-4 if p*3-4 > 0 else 31
 	l = filter(lambda x: x<ns, ts)
 	c = min([len(filter(lambda x: x>=ss, l)), s])
 	
 	print>>yawbh, "Case #%d:"%(mycbw+1), T-len(l)+c
 
 
