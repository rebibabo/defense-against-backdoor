"""Usage:
     X.py < X.in > X.out
 """
 
 import sys
 
 
 class Solver(object):
     enkpb = {}
 
     def __init__(lcmfv, gqxlf, pmkbf):
         lcmfv.pmkbf = pmkbf
         lcmfv.nojgh = nojgh = map(int, gqxlf.next().split())
 
     def init_cache(lcmfv):
         if 'main' in lcmfv.enkpb:
             return
         #lcmfv.enkpb['main'] = res
 
     def solve(lcmfv):
 
         dqrov, vrpge, p = lcmfv.nojgh[:3]
         G = sorted(lcmfv.nojgh[3:], reverse=True)
 
         r = 0
         s = 0
         for g in G:
             if g >= 3 * p - 2 and g >= p:
                 r += 1
             elif g >= 3 * p - 4 and g >= p:
                 if s == vrpge:
                     break
                 r += 1
                 s += 1
 
         return r
 
 
 def main():
     T = int(sys.stdin.next())
     for t in xrange(T):
         sys.stdout.write('Case #%s: %s\n' % (t + 1, Solver(sys.stdin, t).solve()))
 
 
 if __name__ == '__main__':
     main()
