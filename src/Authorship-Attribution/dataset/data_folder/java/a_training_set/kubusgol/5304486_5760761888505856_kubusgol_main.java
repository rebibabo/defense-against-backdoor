import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 import java.util.Set;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.TreeSet;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "A-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("a.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         char[][] grid;
         char[][] grid2;
         int x1;
         int y1;
         int x2;
         int y2;
         int N;
         int M;
         Set<Character> used;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             N = in.nextInt();
             M = in.nextInt();
             used = new TreeSet<>();
             grid = new char[N][M];
             for (int i = 0; i < N; i++) {
                 grid[i] = in.next().toCharArray();
             }
             grid2 = new char[M][N];
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < M; j++) {
                     grid2[j][i] = grid[i][j];
                 }
             }
 
 
 
 
             rozw(grid, N, M);
 
             if (!valid(grid)) {
                 rozw(grid2, M, N);
                 out.println(String.format("Case #%d:", testNumber));
                 for (int i = 0; i < N; i++) {
                     for (int j = 0; j < M; j++) {
 
                         out.print(grid2[j][i]);
                     }
                     out.println();
                 }
             } else {
                 out.println(String.format("Case #%d:", testNumber));
                 for (int i = 0; i < grid.length; i++) {
                     out.println(grid[i]);
                 }
             }
 
         }
 
         private boolean valid(char[][] grid) {
             for (int i = 0; i < grid.length; i++) {
                 for (int j = 0; j < grid[0].length; j++) {
                     if (grid[i][j] == '?') return false;
                 }
             }
             return true;
         }
 
         private void rozw(char[][] grid, int n, int m) {
             used = new TreeSet<>();
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < m; j++) {
                     if (grid[i][j] != '?' && !used.contains(grid[i][j])) {
                         x1 = i;
                         y1 = j;
                         x2 = i;
                         y2 = j;
                         used.add(grid[i][j]);
                         fillUp(grid[i][j], grid);
                         fillDown(grid[i][j], n, grid);
                         fillLeft(grid[i][j], grid);
                         fillRight(grid[i][j], m, grid);
                     }
                 }
             }
         }
 
         private void fillRight(char c, int m, char[][] grid) {
             for (int j = y2 + 1; j < m && colFree(j, grid); j++) {
                 fillCol(j, c, grid);
                 y2 = j;
             }
         }
 
         private boolean colFree(int j, char[][] grid) {
             for (int i = x1; i <= x2; i++) {
                 if (grid[i][j] != '?') return false;
             }
             return true;
         }
 
         private void fillLeft(char c, char[][] grid) {
             for (int j = y1 - 1; j >= 0 && colFree(j, grid); j--) {
                 fillCol(j, c, grid);
                 y1 = j;
             }
         }
 
         private void fillCol(int j, char c, char[][] grid) {
             for (int i = x1; i <= x2; i++) {
                 grid[i][j] = c;
             }
         }
 
         private void fillUp(char c, char[][] grid) {
             for (int i = x1 - 1; i >= 0 && grid[i][y1] == '?'; i--) {
                 grid[i][y1] = c;
                 x1 = i;
             }
         }
 
         private void fillDown(char c, int n, char[][] grid) {
             for (int i = x2 + 1; i < n && grid[i][y2] == '?'; i++) {
                 grid[i][y2] = c;
                 x2 = i;
             }
         }
 
     }
 
     static class InputReader {
         public BufferedReader reader;
         public StringTokenizer tokenizer;
 
         public InputReader(InputStream stream) {
             reader = new BufferedReader(new InputStreamReader(stream), 32768);
             tokenizer = null;
         }
 
         public String next() {
             while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                 try {
                     tokenizer = new StringTokenizer(reader.readLine());
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
             return tokenizer.nextToken();
         }
 
         public int nextInt() {
             return Integer.parseInt(next());
         }
 
     }
 }
 
