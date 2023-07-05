package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.regex.Pattern;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class A {
     private static final Pattern PATTERN = Pattern.compile("[-+]+");
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
 
 
     void solve() {
         String line = sc.next(PATTERN);
         int k = sc.nextInt();
 
         char[] sides = line.toCharArray();
         int ans = 0;
         for (int i = 0, end = sides.length - k; i <= end; ++i) {
             char c = sides[i];
             if (c == '-') {
                 for (int j = 0; j < k; ++j) {
                     sides[i + j] = sides[i + j] == '+' ? '-' : '+';
                 }
                 ++ans;
             }
         }
 
         for (int i = sides.length - k; i < sides.length; ++i) {
             if (sides[i] == '-') {
                 ans = -1;
             }
         }
 
         out.printf("%s\n", ans != -1 ? ans : "IMPOSSIBLE");
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
