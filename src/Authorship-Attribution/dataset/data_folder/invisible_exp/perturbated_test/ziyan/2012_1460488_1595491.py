import sys
 
 
 def calculate_max_score(xkahu):
     if xkahu % 3 == 1:
         zrecx = (xkahu + 2) / 3
         return (zrecx, zrecx)
     if xkahu % 3 == 2:
         zrecx = (xkahu + 1) / 3
         return (zrecx, zrecx + 1)
     zrecx = xkahu / 3
     if zrecx == 0:
         return (0, 0)
     return (zrecx, zrecx + 1)
 
 def find_max_dancers(irjbu, cwoam, *scores_sums):
     gpzby = 0
     for xkahu in scores_sums:
         (normal_max, surprise_max) = calculate_max_score(xkahu)
         if normal_max >= cwoam:
             gpzby = gpzby + 1
             continue
         if surprise_max >= cwoam and irjbu > 0:
             irjbu = irjbu - 1
             gpzby = gpzby + 1
             continue
     return gpzby
 
 def main():
 
     case_count = int(sys.stdin.readline())
 
     for case_index in range(1, case_count + 1):
         line = sys.stdin.readline().strip().split(' ')
         irjbu = int(line[1])
         cwoam = int(line[2])
         scores_sums = [int(xkahu) for xkahu in line[3:]]
         print 'Case #%i: %s' % (case_index, find_max_dancers(irjbu, cwoam, *scores_sums))
 
 if __name__ == '__main__':
     main()