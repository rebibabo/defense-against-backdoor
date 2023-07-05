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
 
 
     private static final Pattern WORD_PATTERN = Pattern.compile("[?0-9]+");
     private int n;
 
     void solve() {
         String pattern1 = sc.next(WORD_PATTERN);
         String pattern2 = sc.next(WORD_PATTERN);
 
         n = pattern1.length();
         String sFormat = "%0" + n + "d";
         int to = (int) Math.pow(10, n);
 
         int best1 = 1000;
         int best2 = 1000;
         int min = 1000;
 
         String[] strings = new String[to];
         for (int num = 0; num < to; ++num) {
             String str = String.format(sFormat, num);
             strings[num] = str;
         }
 
         for (int num1 = 0; num1 < to; ++num1) {
             String num1s = strings[num1];
             if (!check(num1s, pattern1)) {
                 continue;
             }
 
             for (int num2 = 0; num2 < to; ++num2) {
                 String num2s = strings[num2];
                 if (!check(num2s, pattern2)) {
                     continue;
                 }
 
                 int diff = Math.abs(num1 - num2);
                 if (diff < min) {
                     min = diff;
                     best1 = num1;
                     best2 = num2;
                 } else if (diff == min) {
                     if (num1 < best1) {
                         best1 = num1;
                         best2 = num2;
                     } else if (num1 == best1) {
                         if (num2 < best2) {
                             best2 = num2;
                         }
                     }
                 }
             }
         }
 
         out.printf(sFormat + " " + sFormat + "\n", best1, best2);
     }
 
     boolean check(String num, String pattern) {
         for (int i = 0; i < n; ++i) {
             if (pattern.charAt(i) != '?') {
                 if (pattern.charAt(i) != num.charAt(i)) {
                     return false;
                 }
             }
         }
 
         return true;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
         String file = "B-small-attempt1";
 
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
