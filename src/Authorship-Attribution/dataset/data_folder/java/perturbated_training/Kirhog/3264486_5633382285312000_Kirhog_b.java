package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.math.BigInteger;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     void solve() {
         char[] number = Long.toString(sc.nextLong()).toCharArray();
 
         for (int i = 1; i < number.length; ++i) {
             if (number[i] < number[i - 1]) {
 
                 int k = i - 1;
                 while (k >= 0 && number[k] > number[k + 1]) {
                     --number[k];
                     --k;
                 }
 
                 for (k += 2; k < number.length; ++k) {
                     number[k] = '9';
                 }
 
                 break;
             }
         }
 
         out.printf("%d\n", Long.parseLong(new String(number), 10));
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "B-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             B template = new B();
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
