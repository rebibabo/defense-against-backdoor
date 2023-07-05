package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.regex.Pattern;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     private static final Pattern LINE_PATTERN = Pattern.compile("[-+]+");
 
     void solve() {
         char[] line = sc.next(LINE_PATTERN).toCharArray();
         int n = line.length;
 
         char prev = line[n - 1];
         int ans = prev == '+' ? 0 : 1;
         for (int i = n - 2; i >= 0; --i) {
             if (line[i] != prev) {
                 ++ans;
                 prev = line[i];
             }
         }
 
         out.printf("%d\n", ans);
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
