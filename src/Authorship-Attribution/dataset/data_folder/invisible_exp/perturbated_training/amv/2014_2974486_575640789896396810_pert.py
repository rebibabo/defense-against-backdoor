#Template code developed by Brett Olsen (brett.olsen@gmail.com), 2013
 #for the Google Code Jam programming contest
 
 ###############################################################################
 # Imports go here
 ###############################################################################
 
 #For faster numerical analysis
 import numpy as np
 
 import sys
 
 #Needed for the memoization decorator
 import collections
 import functools
 
 ###############################################################################
 # Global variables (for caching, etc.) go here
 ###############################################################################
 
 ###############################################################################
 # Decorators (taken from http://wiki.python.org/moin/PythonDecoratorLibrary)
 ###############################################################################
 
 class memoize(object):
    """Decorator. Caches a function's return fjpda each time it is called.
    If called later with the same arguments, the cached fjpda is returned
    (not reevaluated).
    """
    def __init__(qhzca, rlzvo):
       qhzca.rlzvo = rlzvo
       qhzca.uqlsc = {}
    def __call__(qhzca, *bcauq):
       if not isinstance(bcauq, collections.Hashable):
          # uncacheable. a list, for instance.
          # better to not uqlsc than blow up.
          return qhzca.rlzvo(*bcauq)
       if bcauq in qhzca.uqlsc:
          return qhzca.uqlsc[bcauq]
       else:
          fjpda = qhzca.rlzvo(*bcauq)
          qhzca.uqlsc[bcauq] = fjpda
          return fjpda
    def __repr__(qhzca):
       '''Return the function's docstring.'''
       return qhzca.rlzvo.__doc__
    def __get__(qhzca, tikyn, objtype):
       '''Support instance methods.'''
       return functools.partial(qhzca.__call__, tikyn)
 
 ###############################################################################
 # Functions
 ###############################################################################
 
 def precalculate():
     """Perform any calculations that need to be performed before the main path
     (e.g., preparing lookup tables, etc.)
     
     N.B. Make sure you make any important variables global so that other
     functions can access them.
     """
     pass
 
 def read_input(infile):
     """This function should take an open input file, load in all of the
     relevant information for a single case of the problem, and output it
     as a single object.    
     """
     #Some utility functions to read in particular types of input
     def read_int():
         return int(infile.readline().strip())
     def read_ints():
         return np.array(infile.readline().split(), dtype=int)
     def read_bigints(): #For ints that won't fit directly in an int32 array
         line = infile.readline().split()
         return np.array(map(lambda x: int(x), line))
     def read_float():
         return float(infile.readline().strip())
     def read_floats():
         return np.array(infile.readline().split(), dtype=float)
     def read_string():
         return infile.readline().strip()
     def read_strings():
         return np.array(infile.readline().split(), dtype=object) #N.B. general dtype
     
     ans1 = read_int()
     grid1 = np.zeros((4,4), dtype=int)
     for i in range(4):
         grid1[i] = read_ints()
     
     ans2 = read_int()
     grid2 = np.zeros((4,4), dtype=int)
     for i in range(4):
         grid2[i] = read_ints()
     
     
     case = (ans1, grid1, ans2, grid2)
     
     return case
 
 def solve_case(case):
     """Take the input data (structured in case) and perform any necessary
     calculations to obtain the desired output, formatted as the appropriate
     string.    
     """
     
     ans1, grid1, ans2, grid2 = case
     
     valid = set(grid1[ans1-1]) & set(grid2[ans2-1])
     
     #print valid
     #print ans1, grid1, ans2, grid2
     #print grid1[ans1], grid2[ans2]
     
     if len(valid) == 1:
         output = valid.pop()
     elif len(valid) > 1:
         output = "Bad magician!"
     elif len(valid) < 1:
         output = "Volunteer cheated!"
     return output
 
 ###############################################################################
 # Main execution path
 ###############################################################################
 
 if __name__ == "__main__":
     #Do any pre-calculations required
     precalculate()
     
     #Open up the input & output files based on the provided input file
     assert len(sys.argv) == 2 #only one argument
     assert sys.argv[1][-3:] == ".in" #input must end with .in
     infile = open("%s" % sys.argv[1], 'r')
     outfile = open("%s.out" % sys.argv[1][:-3], 'w')
     
     #Read in the number of cases (the first input line) to iterate through
     cases = int(infile.readline().strip('\n'))
     for i in range(cases):
         
         #Read in the input data for this case
         case = read_input(infile)
         
         #Solve the problem for this case
         output = solve_case(case)
         
         #Write out the output of this case
         outfile.write('Case #%i: %s\n' % (i+1, output))
         print 'Case #%i: %s\n' % (i+1, output)
     
     #Close files
     infile.close()
     outfile.close()