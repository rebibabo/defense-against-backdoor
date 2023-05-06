def solve_case(ftodu):
     jiypg, buwgs, mnahb = [float(vkpbn) for vkpbn in raw_input().strip().split()]
     dugmt = mnahb / 2.0
 
     jfdek = 1
     build_farm_time = jiypg / (2.0 + float((jfdek - 1) * buwgs))
     attemp_time_usage = (mnahb / (2.0 + float(jfdek * buwgs))) + build_farm_time
 
     while attemp_time_usage < dugmt:
         dugmt = attemp_time_usage
 
         jfdek += 1
         build_farm_time += jiypg / (2.0 + float((jfdek - 1) * buwgs))
         attemp_time_usage = (mnahb / (2.0 + float(jfdek * buwgs))) + build_farm_time
 
     print 'Case #%d: %.7f' % (ftodu, dugmt,)
 
 def main():
     ftodu = int(raw_input().strip())
     for i in range(1, ftodu + 1):
         solve_case(i)
 
 if __name__ == '__main__':
     main()
