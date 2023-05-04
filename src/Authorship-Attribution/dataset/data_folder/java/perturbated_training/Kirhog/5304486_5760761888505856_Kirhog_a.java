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
         int rows = sc.nextInt();
         int cols = sc.nextInt();
         sc.nextLine();
 
         char[][] grid = new char[rows][cols];
         for (int i = 0; i < rows; ++i) {
             String line = sc.nextLine();
             grid[i] = line.toCharArray();
         }
 
         for (int i = 0; i < rows; ++i) {
             char lastLetter = 0;
             for (int k = 0; k < cols; ++k) {
                 if (grid[i][k] == '?') {
                     if (lastLetter != 0) {
                         grid[i][k] = lastLetter;
                         continue;
                     }
 
                     int j = k + 1;
                     while (j < cols && grid[i][j] == '?') {
                         ++j;
                     }
 
                     if (j < cols) {
                         char c = grid[i][j];
                         for (int t = k; t < j; ++t) {
                             grid[i][t] = c;
                         }
                     }
 
                     k = j - 1;
                 } else {
                     lastLetter = grid[i][k];
                 }
             }
         }
 
         
         for (int k = 0; k < cols; ++k) {
             char lastLetter = 0;
             for (int i = 0; i < rows; ++i) {
                 if (grid[i][k] == '?') {
                     if (lastLetter != 0) {
                         grid[i][k] = lastLetter;
                         continue;
                     }
 
                     int j = i + 1;
                     while (j < rows && grid[j][k] == '?') {
                         ++j;
                     }
 
                     if (j < rows) {
                         char c = grid[j][k];
                         for (int t = i; t < j; ++t) {
                             grid[t][k] = c;
                         }
                     }
 
                     i = j - 1;
                 } else {
                     lastLetter = grid[i][k];
                 }
             }
         }
 
         out.println();
         for (int i = 0; i < rows; ++i) {
             out.printf("%s\n", new String(grid[i]));
         }
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
