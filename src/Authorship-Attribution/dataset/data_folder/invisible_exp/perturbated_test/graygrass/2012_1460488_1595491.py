#! /usr/bin/env python
 #coding=utf-8
 
 def solve(vcdle, eoszj):
     bxivo = 'Case #%i: '%vcdle
     
     fzvgx = 0
     raw = [int(x) for x in eoszj[0].split()]
     ts = raw[3:]
     ct = raw[1]
     n = raw[2]
     cc = 0
     for i in ts:
         if i > 3*n-3:
             fzvgx += 1
         elif i > max(3*n-5, 0):
             cc += 1
     fzvgx += min(cc, ct)
 
     bxivo += '%d'%fzvgx
     
     return bxivo
 
 
 def main():
     global use_test_data
     global test_data
     global input_file
     global output_file
     
     if use_test_data:
         data = [x.strip() for x in test_data.split('\n')]
     else:
         data = [x.strip() for x in input_file.readlines()]
     
     T = int(data[0])
     iLine = 1
     caseLineNum = 1
     for vcdle in range(1, T + 1):
         input = []
         for i in range(caseLineNum):
             input.append(data[iLine])
             iLine += 1
         rslt = solve(vcdle, input)
         print rslt
         if not use_test_data:
             print >> output_file, rslt
     
     if not use_test_data:
         input_file.close()
         output_file.close()
     
     
 if __name__ == '__main__':
     test_data = """4
 3 1 5 15 13 11
 3 0 8 23 22 21
 2 1 1 8 0
 6 2 8 29 20 8 18 18 21
 """
     use_test_data = False
     
     test_file = 'B-small-attempt0.in'
     if not use_test_data and '' != test_file:
         input_file = open(test_file)
         output_file = open(test_file + '.out', 'w')
     
     main()