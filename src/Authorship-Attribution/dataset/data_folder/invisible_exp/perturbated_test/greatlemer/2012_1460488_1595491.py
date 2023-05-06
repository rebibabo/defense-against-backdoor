from optparse import OptionParser
 
 def solve(vlwxe,fcrql,fuoal,krgcl):
     # Easy pickings, if fuoal is 0 then all must win.
     if fuoal == 0:
         return vlwxe
     qscjh = 0
     potential_surprises = 0
     win_cutoff = (fuoal * 3) - 3
     surprise_cutoff = win_cutoff - 2
     for score in krgcl:
         if score == 0:
             continue
         elif score > win_cutoff:
             qscjh += 1
         elif score > surprise_cutoff:
             potential_surprises += 1
     if potential_surprises < fcrql:
         return qscjh + potential_surprises
     else:
         return qscjh + fcrql
 
 def parse_case(data_line):
     bits = data_line.split()
     vlwxe = int(bits[0])
     fcrql = int(bits[1])
     fuoal = int(bits[2])
     krgcl = [int(x) for x in bits[3:]]
     return vlwxe,fcrql,fuoal,krgcl
 
 def main():
     parser = OptionParser()
     parser.add_option("-f", "--file", dest="filename",
                       help="read input from FILE", metavar="FILE")
 
     (options, args) = parser.parse_args()
     if not options.filename:
         parser.error("Must provide a filename.")
     input_file = open(options.filename, "r")
     total_cases = int(input_file.readline())
     case_number = 0
     while case_number < total_cases:
     	case_number += 1
     	data_args = parse_case(input_file.readline())
     	print "Case #%d: %d" % (case_number, solve(*data_args))
 
 if __name__ == "__main__":
 	main()