import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "D-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("d.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskD solver = new TaskD();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskD {
         List<Character> ans_c;
         List<Integer> ans_x;
         List<Integer> ans_y;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int N = in.nextInt();
             int M = in.nextInt();
             char[][] grid = new char[N][N];
             for (char[] x : grid) Arrays.fill(x, '.');
 
             ans_c = new ArrayList<>();
             ans_x = new ArrayList<>();
             ans_y = new ArrayList<>();
 
             for (int i = 0; i < M; i++) {
                 char s = in.next().charAt(0);
                 int x = in.nextInt() - 1;
                 int y = in.nextInt() - 1;
                 grid[x][y] = s;
             }
 
             int[] os = findSymbol(grid, 0, 'o');
             int[] xs = findSymbol(grid, 0, 'x');
 
             if (os == null) {
                 
                 if (xs == null) {
                     
                     set(grid, 0, 0, 'o');
                     coverRow(grid, 0, '+');
                     coverDiag(grid, 0, 0, 'x');
                 } else {
                     set(grid, xs[0], xs[1], 'o');
                     coverRow(grid, 0, '+');
                     coverDiag(grid, xs[0], xs[1], 'x');
                 }
             } else {
                 coverRow(grid, 0, '+');
                 coverDiag(grid, os[0], os[1], 'x');
             }
 
             int sc = score(grid);
 
             out.println(String.format("Case #%d: %d %d", testNumber, sc, ans_c.size()));
             for (int i = 0; i < ans_c.size(); i++) {
                 out.println(String.format("%s %d %d", ans_c.get(i), ans_x.get(i), ans_y.get(i)));
             }
 
 
         }
 
         private int[] findSymbol(char[][] grid, int row, char x) {
             for (int i = 0; i < grid.length; i++) {
                 if (grid[row][i] == x) return new int[]{row, i};
             }
             return null;
         }
 
         private void coverDiag(char[][] grid, int x, int y, char c) {
             for (int i = x, j = y; i < grid.length; i++, j = (j + 1) % grid.length) {
                 if (grid[i][j] == '.') set(grid, i, j, c);
             }
         }
 
         private void coverRow(char[][] grid, int row, char c) {
             for (int j = 0; j < grid.length; j++) {
                 if (grid[row][j] == '.') set(grid, row, j, '+');
             }
         }
 
         private void set(char[][] grid, int x, int y, char c) {
             if (grid[x][y] != c) {
                 grid[x][y] = c;
                 ans_c.add(c);
                 ans_x.add(x + 1);
                 ans_y.add(y + 1);
             }
         }
 
         private int score(char[][] cur) {
             int ans = 0;
             for (int i = 0; i < cur.length; i++) {
                 for (int j = 0; j < cur.length; j++) {
                     if (cur[i][j] == 'o') ans += 2;
                     if (cur[i][j] == 'x') ans++;
                     if (cur[i][j] == '+') ans++;
                 }
             }
             return ans;
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
 
