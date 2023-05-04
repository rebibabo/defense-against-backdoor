import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private int cols;
     private int rows;
     private int cells;
 
 
     int count(int mask) {
         boolean[][] grid = new boolean[rows][cols];
         for (int i = 0; i < cells; ++i) {
             if (((1 << i) & mask) != 0) {
                 grid[i / cols][i % cols] = true;
             }
         }
 
         int res = 0;
         for (int r = 0; r < rows; ++r) {
             for (int c = 0; c < cols; ++c) {
                 if (grid[r][c]) {
                     if (c < cols - 1) {
                         if (grid[r][c + 1]) {
                             ++res;
                         }
                     }
                     if (r < rows - 1) {
                         if (grid[r + 1][c]) {
                             ++res;
                         }
                     }
                 }
             }
         }
 
         return res;
     }
 
     void solve() {
         rows = sc.nextInt();
         cols = sc.nextInt();
         int n = sc.nextInt();
 
         cells = rows * cols;
         int best = Integer.MAX_VALUE;
         for (int mask = 0, end = 1 << cells; mask < end; ++mask) {
             if (Integer.bitCount(mask) == n) {
                 best = Math.min(best, count(mask));
             }
         }
 
         System.out.printf("%d\n", best);
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "B-small-attempt0";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             B template = new B();
             template.caseNumber = caseNumber;
             template.solve();
             System.out.flush();
         }
 
         sc.close();
     }
 
     @SuppressWarnings("UnusedDeclaration")
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
