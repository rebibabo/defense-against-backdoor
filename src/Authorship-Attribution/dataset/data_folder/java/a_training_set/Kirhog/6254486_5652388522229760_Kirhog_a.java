package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.math.BigInteger;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class A {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     void solve() {
         int n0 = sc.nextInt();
 
         int target = (1 << 10) - 1;
 
         int mask = 0;
         int n = n0;
         for (int i = 1; i <= 100; ++i) {
             mask |= getMask(n);
             if (mask == target) {
                 out.printf("%d\n", n);
                 return;
             }
             n += n0;
         }
 
         out.printf("INSOMNIA\n");
     }
 
     int getMask(int n) {
         int mask = 0;
         while (n > 0) {
             int digit = n % 10;
             n /= 10;
 
             mask |= 1 << digit;
         }
 
         return mask;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "A-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             A template = new A();
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
