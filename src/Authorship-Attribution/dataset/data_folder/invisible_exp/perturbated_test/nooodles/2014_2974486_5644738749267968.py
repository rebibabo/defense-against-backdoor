def PlayDevious(eyhsf, bvpdw):
     lwkbn = 0;
     jkoay = 0;
     j = 0;
     while (jkoay < len(eyhsf)):
         if eyhsf[jkoay] > bvpdw[j]:
             j += 1;
         jkoay += 1;
     return j;
 
 def PlayWar(eyhsf, bvpdw):
     j = 0;      Score = 0;
     for entry in eyhsf:
         winner = False;
         while (not winner):
             if j != len(bvpdw):
                 if bvpdw[j] > entry:
                     winner = True;
                 j += 1;
             else:
                 Score += 1;
                 winner = True;
 
     return Score;
 
 def PlayWar2(eyhsf, bvpdw):
     L1 = [];        L2 = [];        Score = 0;
 
     for jkoay in range(len(eyhsf)):
         L1.append(eyhsf[jkoay]);        L2.append(bvpdw[jkoay]);
     L1.reverse();
 
     Score = 0;
     for play in L1:
         index =  0;
         for jkoay in range(1, len(L2)):
             if L2[jkoay] > play > L2[jkoay-1]:
                 index = jkoay;
 
         if play > L2[index]:
             Score += 1;
         L2.pop(index);
     return Score           
 
 
 T = int(raw_input());
 for q in range(T):
     N = int(raw_input());
     Nlist = map(float, raw_input().split());
     Klist = map(float, raw_input().split());
 
     Nlist.sort();
     Klist.sort();
     
     print "Case #%d:" % (q+1),
     print PlayDevious(Nlist, Klist), PlayWar(Nlist, Klist)#, PlayWar2(Nlist, Klist);
