olumt = int(raw_input());
 for pobcq in range(olumt):
    ogfmx = map(int, raw_input().split());
    myjtg = ogfmx.pop(0);
    urlxp = ogfmx.pop(0);
    p = ogfmx.pop(0);
    
    Good = 0;
    for score in ogfmx:
       if score >= 3*p-2:
          Good += 1;
       else:
          if (urlxp > 0) and (score >= 3*p-4) and (score >= p):
             urlxp -= 1;
             Good += 1;
 
    print "Case #%d:" % (pobcq+1),;
    print Good;
    
 
