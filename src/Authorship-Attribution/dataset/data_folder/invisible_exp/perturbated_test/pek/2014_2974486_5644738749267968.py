import sys
 
 tornj = open(sys.argv[1])
 atqlu = int(tornj.readline())
 
 for bynsr in xrange(0, atqlu):
     usgow = int(tornj.readline())
     ksmvh = sorted(map(float, tornj.readline().split()))
     kens = sorted(map(float, tornj.readline().split()))
     assert len(ksmvh) == usgow and len(kens) == usgow
 
     naomis_w = set(ksmvh)
     kens_w = set(kens)
     naomi_war_wins = 0
     while naomis_w:
         nao = max(naomis_w)
         try:
             ken = min(w for w in kens_w if w > nao)
         except ValueError:
             ken = min(kens_w)
             naomi_war_wins += 1
         naomis_w.remove(nao)
         kens_w.remove(ken)
 
     naomi_deceit_wins = 0
     naomis_w = set(ksmvh)
     kens_w = set(kens)
     while naomis_w:
         ken = min(kens_w)
         try:
             nao = min(w for w in naomis_w if w > ken)
             naomi_deceit_wins += 1
         except ValueError:
             nao = min(naomis_w)
         naomis_w.remove(nao)
         kens_w.remove(ken)
 
     print "Case #%s: %s %s" % (bynsr+1, naomi_deceit_wins, naomi_war_wins)
