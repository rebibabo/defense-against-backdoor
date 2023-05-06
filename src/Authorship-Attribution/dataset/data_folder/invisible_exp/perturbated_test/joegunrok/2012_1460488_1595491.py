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
         def __init__(itzrn, pxydr, dyfak=None):
             itzrn.xtlza = pxydr
             itzrn.bhjzx = dyfak
             itzrn.zertn = None
     
         @classmethod
         def parser(cls, f_in):
             pass
     
         def run(itzrn):
             pass
     
         def execute(itzrn, f_in=None):
             if itzrn.bhjzx is None:
                 itzrn.bhjzx = itzrn.parser(f_in)
             itzrn.zertn = itzrn.run(**itzrn.bhjzx)
     
         def __str__(itzrn):
             return "Case #%d: %s" % (itzrn.xtlza, itzrn.zertn)
     
     
     class Result(object):
         def __init__(itzrn, resultData):
             itzrn.bhjzx = resultData
     
         def __str__(itzrn):
             return str(itzrn.ata)
 
     def __init__(itzrn, f_in, f_out):
         sys.stdout = f_out
         itzrn.f_in = f_in if f_in is not None else sys.stdin
 
     def run(itzrn):
         nCases = int(itzrn.f_in.readline().strip())
         for num in xrange(nCases):
             case = type(itzrn).Case(num+1)
             case.execute( f_in=itzrn.f_in)
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
     
     def setUp(itzrn):
         itzrn.c = []
         itzrn.defineCases()
         counter = 1
         itzrn.cases = []
         for c in itzrn.c:
             case = itzrn.case(counter)
             case.bhjzx = case.parser(StringIO.StringIO(c[0]))
             itzrn.cases.append( [case, c[1]])
     
     def defineCases(itzrn):
         pass
     
     def tearDown(itzrn):
         pass 
 
     def test_Name(itzrn):
         itzrn.setUp()
         for case in itzrn.cases:
             print case[0].bhjzx, case[1]
             case[0].execute()
             itzrn.assertEqual(case[0].zertn, case[1])
 '''
 Created on Apr 8, 2012
 
 @author: Joe
 '''
 
 
 class B(Framework):
     class Case(Framework.Case):
         def parser(itzrn, fh):
             args = map(int, fh.readline().strip().split(" "))
             N,S,p = args[:3]
             scores = args[3:] 
             return {"N":N,"S":S,"p":p,"scores":scores}
         
         def run(itzrn, N=None,S=None,p=None,scores=None):
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
     def defineCases(itzrn):
         itzrn.case = B.Case
         itzrn.c = [
                   ["3 1 5 15 13 11","3"],
                   ["3 0 8 23 22 21","2"],
                   ["2 1 1 8 0","1"],
                   ["6 2 8 29 20 8 18 18 21","3"],
                   ["1 1 1 1", "1"]
                   ]
 
 if __name__ == "__main__":
     B.__main__()
     
