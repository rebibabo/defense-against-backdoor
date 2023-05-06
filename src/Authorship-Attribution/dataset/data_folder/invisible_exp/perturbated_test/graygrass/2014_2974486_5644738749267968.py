import os
 import bisect
 
 class Solver(object):
     def __init__(gmtrp):
         pass
     
     def solve(gmtrp, pfkrc):
         stopg = [float(ixfrl) for ixfrl in pfkrc[1].split()]
         stopg.sort()
         muztl = [float(ixfrl) for ixfrl in pfkrc[2].split()]
         muztl.sort()
         baxhz = gmtrp.dwar(stopg[:], muztl[:])
         eotfr = gmtrp.war(stopg[:], muztl[:])
         return '%d %d'%(baxhz, eotfr)
         pass
     
     def dwar(gmtrp, stopg, muztl):
         turn = len(stopg)
         cnt = 0
         for i in range(turn):
             i = i
             if stopg[-1] < muztl[-1]:
                 stopg.pop(0)
                 muztl.pop(-1)
             elif stopg[0] < muztl[0]:
                 stopg.pop(0)
                 muztl.pop(-1)
             else:
                 cnt += 1
                 stopg.pop(0)
                 muztl.pop(0)
         return cnt
     
     def war(gmtrp, stopg, muztl):
         turn = len(stopg)
         cnt = 0
         for i in range(turn):
             i = i
             j = bisect.bisect_left(muztl, stopg[0])
             if j == len(muztl):
                 cnt += 1
                 muztl.pop(0)
             else:
                 muztl.pop(j)
             stopg.pop(0)
         return cnt
     
     def feed(gmtrp, pfkrc):
         lines = [ixfrl.strip() for ixfrl in pfkrc]
         outputs = []
         test_case_n = int(lines[0])
         cur = 1
         for i in range(test_case_n):
             i = i
             case_line_cnt = 3
             case_inputs = lines[cur:cur+case_line_cnt]
             cur += case_line_cnt
             outputs.append(gmtrp.solve(case_inputs))
         return outputs
 
 if __name__ == '__main__':
     iname = 'D-small-attempt0.in'
 #     iname = 'foo'
     sample_in = '''
 4
 1
 0.5
 0.6
 2
 0.7 0.2
 0.8 0.3
 3
 0.5 0.1 0.9
 0.6 0.4 0.3
 9
 0.186 0.389 0.907 0.832 0.959 0.557 0.300 0.992 0.899
 0.916 0.728 0.271 0.520 0.700 0.521 0.215 0.341 0.458
     '''
     sample_out = '''
 Case #1: 0 0
 Case #2: 1 0
 Case #3: 2 1
 Case #4: 8 4
     '''
     if os.path.exists(iname):
         with open(iname) as f:
             pfkrc = f.readlines()
     else:
         pfkrc = [ixfrl.strip() for ixfrl in sample_in.split('\n') if ixfrl.strip()]
     solver = Solver()
     outputs = solver.feed(pfkrc)
     fail_flag = False
     if os.path.exists(iname):
         with open(iname+'.out', 'w') as f:
             for i, v in enumerate(outputs):
                 print >> f, 'Case #%d: %s'%(i+1, str(v))
     else:
         ans = set([ixfrl.strip() for ixfrl in sample_out.split('\n') if ixfrl.strip()])
         for i, v in enumerate(outputs):
             t = 'Case #%d: %s'%(i+1, str(v))
             if t not in ans:
                 print '!!! Wrong:', t
                 fail_flag = True
     print '===================================================='
     for i, v in enumerate(outputs):
         print 'Case #%d: %s'%(i+1, str(v))
     print '===================================================='
     print 'done' if not fail_flag else 'fail'
     pass