package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
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
         String line = sc.nextLine();
         if (line.trim().isEmpty()) {
             line = sc.nextLine();
         }
 
         char[] chars = line.toCharArray();
 
         StringBuilder result = new StringBuilder();
         result.append(chars[0]);
         for (int i = 1; i < chars.length; ++i) {
             char first = result.charAt(0);
             char next = chars[i];
             if (next >= first) {
                 result.insert(0, next);
             } else {
                 result.append(next);
             }
         }
 
         out.printf("%s\n", result);
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
