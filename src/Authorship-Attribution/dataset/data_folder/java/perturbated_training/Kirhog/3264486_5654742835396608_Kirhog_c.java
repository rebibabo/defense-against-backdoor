package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     void solve() {
         long n = sc.nextLong();
         long k = sc.nextLong();
 
         long[] partLength = new long[2]; 
         long[] partCount = new long[2];
         long[] nextLength = new long[2]; 
         long[] nextCount = new long[2];
         partLength[0] = n;
         partCount[0] = 1;
         int part = 0;
 
         long last = n;
         while (k > 0) {
             if (part == 2) {
                 System.arraycopy(nextLength, 0, partLength, 0, 2);
                 System.arraycopy(nextCount, 0, partCount, 0, 2);
 
                 Arrays.fill(nextLength, 0);
                 Arrays.fill(nextCount, 0);
 
                 part = 0;
             }
 
             long count = partCount[part];
             if (count != 0) {
                 long length = partLength[part];
                 last = length;
 
                 long left = (length - 1) / 2;
                 long right = length / 2;
                 
 
                 if (part == 0) {
                     nextLength[0] = right;
                     if (left == right) {
                         nextCount[0] = count * 2;
                     } else {
                         nextCount[0] = count;
 
                         nextLength[1] = left;
                         nextCount[1] = count;
                     }
                 } else {
                     if (left == right) {
                         assert nextLength[1] == left;
                         nextCount[1] += count * 2;
                     } else {
                         assert nextLength[1] == 0;
                         nextCount[0] += count;
 
                         nextLength[1] = left;
                         nextCount[1] = count;
                     }
                 }
 
                 k -= count;
             }
 
             ++part;
         }
 
         out.printf("%d %d\n", last / 2, (last - 1) / 2);
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "C-small-1-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             C template = new C();
             template.caseNumber = caseNumber;
             template.solve();
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
