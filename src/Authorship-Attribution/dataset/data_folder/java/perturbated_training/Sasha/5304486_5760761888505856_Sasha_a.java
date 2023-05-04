import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int R = in.nextInt();
             int C = in.nextInt();
             char[][] grid = new char[R][C];
             for (int i = 0; i < R; i++) {
                 String s = in.next();
                 for (int j = 0; j < C; j++) {
                     grid[i][j] = s.charAt(j);
                 }
             }
 
             for (int i = 0; i < R; i++) {
                 for (int j = 0; j < C; j++) {
                     if (grid[i][j] != '?') {
                         int j1 = j - 1;
                         while (j1 >= 0 && grid[i][j1] == '?') {
                             grid[i][j1] = grid[i][j];
                             j1--;
                         }
                         j1 = j + 1;
                         while (j1 < C && grid[i][j1] == '?') {
                             grid[i][j1] = grid[i][j];
                             j1++;
                         }
                     }
                 }
             }
 
             for (int i = 0; i < R; i++) {
                 for (int j = 0; j < C; j++) {
                     if (grid[i][j] != '?') {
                         int i1 = i - 1;
                         while (i1 >= 0 && grid[i1][j] == '?') {
                             grid[i1][j] = grid[i][j];
                             i1--;
                         }
                         i1 = i + 1;
                         while (i1 < R && grid[i1][j] == '?') {
                             grid[i1][j] = grid[i][j];
                             i1++;
                         }
                     }
                 }
             }
 
             out.printf("Case #%d:\n", test);
             for (int i = 0; i < R; i++) {
                 for (int j = 0; j < C; j++) {
                     out.print(grid[i][j]);
                 }
                 out.println();
             }
         }
     }
 }
