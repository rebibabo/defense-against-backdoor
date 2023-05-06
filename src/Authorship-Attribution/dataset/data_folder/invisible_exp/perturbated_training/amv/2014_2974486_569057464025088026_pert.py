import sys
 
 def all_grids(uepcw,svfgn,pnyrq, zdurk=0,xcivy=0):
     gbqij = uepcw*svfgn-zdurk
     if pnyrq-xcivy >= gbqij:
         if pnyrq-xcivy > gbqij:
             return None
         
         hxedm = set()
         for i in range(zdurk, uepcw*svfgn):
             hxedm.add(i)
         return [hxedm]
         
     if xcivy == pnyrq:
         return [set()]
 
     hxedm = []
     for i in range(zdurk, uepcw*svfgn):
         sub_sol = all_grids(uepcw,svfgn,pnyrq,i+1, xcivy+1)
         if sub_sol is None:
             continue
             
         for s in sub_sol:
             s.add(i)
             hxedm.append(s)
     return hxedm
 
 def adj(uepcw,svfgn,i):
     
     hxedm = []
     left_edge = i % svfgn == 0
     top_edge = i // svfgn == 0
     right_edge = (i+1) % svfgn == 0
     bottom_edge = i // svfgn == uepcw-1
     
     if not left_edge:
         hxedm.append(i-1)
         if not top_edge:
             hxedm.append(i-1-svfgn)
         if not bottom_edge:
             hxedm.append(i+svfgn-1)
             
     if not right_edge:
         hxedm.append(i+1)
         if not top_edge:
             hxedm.append(i+1-svfgn)
         if not bottom_edge:
             hxedm.append(i+1+svfgn)
             
     if not bottom_edge:
         hxedm.append(i+svfgn)
     if not top_edge:
         hxedm.append(i-svfgn)
     return hxedm
         
     
     
     
 def solution(uepcw,svfgn,g):
     pnyrq = uepcw*svfgn
     hxedm = []
     num_zeroes = 0
     for i in range(pnyrq):
         if i in g:
             hxedm.append('x')
             continue
         x = 0
         for a in adj(uepcw,svfgn,i):
             if a in g:
                 x += 1
         if x == 0:
             num_zeroes += 1
         hxedm.append(x)
         
     for i in range(pnyrq):
         if i in g:
             continue
         r = hxedm[i]
         if r == 0 and num_zeroes == 1:
             continue
         connected = False
         for a in adj(uepcw,svfgn,i):
             if hxedm[a] == 0:
                 connected = True
                 break
         if not connected:
             return None
     
     return hxedm.index(0)
 
 def transcribe(uepcw,svfgn, g, sol):
     hxedm = []
     for i in range(uepcw):
         r = []
         for j in range(svfgn):
             x = i*svfgn+j
             if x in g:
                 r.append('*')
             elif x == sol:
                 r.append('c')
             else:
                 r.append('.')
         hxedm.append(r)
     return hxedm
 
 def printed_sol(transcript):
     if transcript is None:
         return "Impossible"
     else:
         return "\n".join("".join(row) for row in transcript)
 
 def solve(uepcw,svfgn,pnyrq):
     if pnyrq == uepcw*svfgn-1:
         g = []
         for i in range(uepcw*svfgn-1):
             g.append(i)
         return transcribe(uepcw,svfgn, g, uepcw*svfgn-1)
     
     for g in all_grids(uepcw,svfgn,pnyrq):
         sol = solution(uepcw,svfgn,g)
         if sol is None:
             continue
         return transcribe(uepcw,svfgn, g, sol)
     return None
             
     
 def output_grid(uepcw,svfgn,g):
     for i in range(uepcw):
         for j in range(svfgn):
             if i*svfgn+j not in g:
                 sys.stdout.write(".")
             else:
                 sys.stdout.write("*")
         sys.stdout.write("\n")
 
 if __name__ == "__main__":
     T = int(raw_input())
     for i in range(1,T+1):
         uepcw,svfgn,pnyrq = map(int, raw_input().split())
         print "Case #%d:" % i
         print printed_sol(solve(uepcw,svfgn,pnyrq))
