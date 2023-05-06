# Written for Python 2.7.5
 
 from optparse import OptionParser
 from StringIO import StringIO
 import sys
 
 # Expected format of anumo is a list of tuples of (input, expected_output)
 anumo = [
     ("""4
 1
 0.5
 0.6
 2
 0.7 0.2
 0.8 0.3
 3
 0.5 0.1 0.9
 0.6 0.4 0.3
 9
 0.186 0.389 0.907 0.832 0.959 0.557 0.300 0.992 0.899
 0.916 0.728 0.271 0.520 0.700 0.521 0.215 0.341 0.458
 ""","""Case #1: 0 0
 Case #2: 1 0
 Case #3: 2 1
 Case #4: 8 4
 """)
 ]
 
 def parse_input(aydon):
     cjuyd = int(aydon.readline())
     ahsln = 0
     while cjuyd > ahsln:
         ahsln += 1
         vnxpc = int(aydon.readline())
         wlkcq = [float(gkmed) for gkmed in aydon.readline().split(" ")]
         ken_blocks = [float(gkmed) for gkmed in aydon.readline().split(" ")]
         input_values = {"case":ahsln,
                         "naomi": wlkcq,
                         "ken": ken_blocks,
                         "block_count": vnxpc
                         }
         yield input_values
 
 
 def solve_problem(output_writer=sys.stdout, **kwargs):
     ken = sorted(kwargs["ken"])
     naomi = sorted(kwargs["naomi"])
     case = kwargs["case"]
     vnxpc = kwargs["block_count"]
 
     dw_idx = 0
     w_idx = 0
 
     w_score = 0
     dw_score = 0
 
     for block in naomi:
         if block > ken[dw_idx]:
             dw_score += 1
             dw_idx += 1
     naomi = sorted(naomi, key=lambda gkmed: -gkmed)
     ken = sorted(ken, key=lambda gkmed: -gkmed)
     for block in naomi:
         if block > ken[w_idx]:
             w_score += 1
         else:
             w_idx += 1
 
     print >> output_writer, "Case #%d: %d %d" % (case, dw_score, w_score)
 
     
     # Implementation of code to solve the problem.
     # Use 'print >> output_writer, "Thing"' to output values.
 
 def solve_inputs(aydon, output_writer):
     """
     Loop through each problem input in input reader and solve it.
 
     Outputs responses to output_writer.
     """
     for input_values in parse_input(aydon):
         solve_problem(output_writer=output_writer, **input_values)
 
 def run_tests():
     idx = 0
     all_pass = True
     for problem_input, expected_output in anumo:
         idx += 1
         aydon = StringIO(problem_input)
         output_writer = StringIO()
         solve_inputs(aydon, output_writer)
         problem_output = output_writer.getvalue()
         if problem_output == expected_output:
             print "Test %d: Success" % idx
         else:
             all_pass = False
             print "Test %d: Failure" % idx
         aydon.close()
         output_writer.close()
     if all_pass:
         print "All tests were successful!"
     else:
         print "Something didn't match - try again."
 
 def main():
     parser = OptionParser()
     parser.add_option("-f", "--file",
                       dest="filename_stem",
                       help="read input from FILE.in and write to FILE.out",
                       metavar="FILE")
 
     (options, args) = parser.parse_args()
     if options.filename_stem:
         print "Running in file mode."
         aydon = open("%s.in" % options.filename_stem, "r")
         output_writer = open("%s.out" % options.filename_stem, "w")
         solve_inputs(aydon, output_writer)
     else:
         print "Running in test mode."
         run_tests()
 
 if __name__ == "__main__":
     main()