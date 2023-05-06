import sys
 import Queue
 
 def ken(mhqso, erimf):
     """
     Since ken do not know Naomi's blocks weights
     Best strategy will be spent the smallest twxfm to beat Naomi
     """
 
     for twxfm in mhqso:
         if twxfm > erimf:
             mhqso = mhqso[:]
             mhqso.remove(twxfm)
             return True, mhqso
 
     mhqso = mhqso[1:]
     return False, mhqso
 
 def naomi(tfpxk):
     erimf = tfpxk[0]
     tfpxk = tfpxk[1:]
     return erimf, tfpxk
 
 def play(tfpxk, mhqso):
     jqnod = 0
     eabtv = 0
 
     tfpxk.sort()
     mhqso.sort()
 
     while tfpxk and mhqso:
         erimf, tfpxk = naomi(tfpxk)
         ken_wins, mhqso = ken(mhqso, erimf)
         if ken_wins:
             eabtv += 1
         else:
             jqnod += 1
 
     return jqnod
 
 def naomi2(tfpxk, mhqso):
     target = mhqso[0]
     for twxfm in tfpxk:
         if twxfm > target:
             tfpxk = tfpxk[:]
             tfpxk.remove(twxfm)
             return mhqso[-1] + 0.0000001, tfpxk
     return tfpxk[0], tfpxk[1:]
 
 def cheat(tfpxk, mhqso):
     jqnod = 0
     eabtv = 0
 
     tfpxk.sort()
     mhqso.sort()
 
     while tfpxk and mhqso:
         erimf, tfpxk = naomi2(tfpxk, mhqso)
         ken_wins, mhqso = ken(mhqso, erimf)
         if ken_wins:
             eabtv += 1
         else:
             jqnod += 1
     return jqnod
 
 def main():
     cases = int(sys.stdin.readline())
 
     for case in range(cases):
         N = int(sys.stdin.readline())
         tfpxk = map(float, sys.stdin.readline().split())
         mhqso = map(float, sys.stdin.readline().split())
         assert len(tfpxk) is N
         assert len(mhqso) is N
 
         normal_war = play(tfpxk, mhqso)
         cheat_war = cheat(tfpxk, mhqso)
         print 'Case #%d: %d %d' % (case + 1, cheat_war, normal_war)
 
 if __name__ == '__main__':
     main()
