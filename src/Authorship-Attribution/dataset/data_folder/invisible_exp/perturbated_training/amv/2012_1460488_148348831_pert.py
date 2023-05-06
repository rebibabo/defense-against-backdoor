       
 """
 GCJ framework (gcj.fw.framework)
  - Command Line and Package interface
  - output redirection
  - parsing case input
  - executing problem code against cases
  - testing framework
 """
 import sys
 import unittest
 import StringIO
 
 class Framework(object):
     class Case(object):
         def __init__(ydwso, tbcfa, vejns=None):
             ydwso.rlznd = tbcfa
             ydwso.yveoi = vejns
             ydwso.result = None
     
         @classmethod
         def parser(cls, f_in):
             pass
     
         def run(ydwso):
             pass
     
         def execute(ydwso, f_in=None):
             if ydwso.yveoi is None:
                 ydwso.yveoi = ydwso.parser(f_in)
             ydwso.result = ydwso.run(**ydwso.yveoi)
     
         def __str__(ydwso):
             return "Case #%d: %s" % (ydwso.rlznd, ydwso.result)
     
     
     class Result(object):
         def __init__(ydwso, resultData):
             ydwso.yveoi = resultData
     
         def __str__(ydwso):
             return str(ydwso.ata)
 
     def __init__(ydwso, f_in, f_out):
         sys.stdout = f_out
         ydwso.f_in = f_in if f_in is not None else sys.stdin
 
     def run(ydwso):
         nCases = int(ydwso.f_in.readline().strip())
         for num in xrange(nCases):
             case = type(ydwso).Case(num+1)
             case.execute( f_in=ydwso.f_in)
             print case
 
 
     @classmethod
     def __main__(cls):
         f_in = sys.stdin
         if len(sys.argv) > 1:
             if sys.argv[1] == "-t":
                 unittest.main()
                 sys.exit()
             f_in = open(sys.argv[1])
         framework = cls(f_in, sys.stdout)
         framework.run()
     
 class Test(unittest.TestCase):
     cases = []
     case = None
     c=[]
     
     def setUp(ydwso):
         ydwso.c = []
         ydwso.defineCases()
         counter = 1
         ydwso.cases = []
         for c in ydwso.c:
             case = ydwso.case(counter)
             case.yveoi = case.parser(StringIO.StringIO(c[0]))
             ydwso.cases.append( [case, c[1]])
     
     def defineCases(ydwso):
         pass
     
     def tearDown(ydwso):
         pass 
 
     def test_Name(ydwso):
         ydwso.setUp()
         for case in ydwso.cases:
             print case[0].yveoi, case[1]
             case[0].execute()
             ydwso.assertEqual(case[0].result, case[1])
 '''
 Created on Apr 8, 2012
 
 @author: Joe
 '''
 
 from math import log
 class C(Framework):
     class Case(Framework.Case):
         def parser(ydwso, fh):
             args = map(int, fh.readline().strip().split(" "))
             A,B = args[:2]
             return {"A":A,"B":B}
         
         def run(ydwso,A=None,B=None):
             ret = 0
             found = set()
             for i in range(A,B+1):
                 if i in found: continue
                 n = int(log(i,10))
                 nums = set([i])
                 tmp = i
                 for j in range(n):
                     tmp = tmp / 10 + (tmp%10)*10**(n)
                     if tmp <= B and tmp>= A:
                         nums.add(tmp)
                         found.add(tmp)
                 ret += len(nums)*(len(nums)-1)/2
             return str(ret)
             
 class Test(Test):
     def defineCases(ydwso):
         ydwso.case = C.Case
         ydwso.c = [
                   ["1 9", "0"],
                   ["10 40", "3",                   ],
                   ["10 99", "36"],
                   ["100 500", "156"],
                   ["1111 2222", "287"],
                   ["1000000 2000000", "2000000"]
                   ]
 
 if __name__ == "__main__":
     C.__main__()
     
