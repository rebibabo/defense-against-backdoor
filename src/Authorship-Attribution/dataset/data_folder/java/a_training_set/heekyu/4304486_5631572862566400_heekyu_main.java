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
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "C-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("c.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         private int dfs(int root, int cur, int d, int[] BFF, int[][] dist, boolean[] visit) {
             if (visit[cur]) {
                 return 0;
             }
             visit[cur] = true;
             dist[root][cur] = d;
             if (BFF[cur] == root) {
                 return d + 1;
             } else {
                 return dfs(root, BFF[cur], d + 1, BFF, dist, visit);
             }
         }
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int N = in.nextInt();
             int[] BFF = new int[N];
             for (int i = 0; i < N; i++) {
                 BFF[i] = in.nextInt() - 1;
             }
             int ans = 2;
             int[][] dist = new int[N][N];
             for (int i = 0; i < N; i++) {
                 boolean[] visit = new boolean[N];
                 dfs(i, i, 0, BFF, dist, visit);
                 for (int j = i + 1; j < N; j++) {
                     if (BFF[j] == i && dist[i][j] > 0) {
                         ans = Math.max(ans, dist[i][j] + 1);
                     }
                 }
             }
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < N; j++) {
                     if (BFF[i] == j && BFF[j] == i) {
                         int a = 1;
                         for (int k = 0; k < N; k++) {
                             if (k != i && dist[k][i] > dist[k][j] && dist[k][j] > 0) {
                                 a = Math.max(a, dist[k][j] + 1);
                             }
                         }
                         int b = 1;
                         for (int k = 0; k < N; k++) {
                             if (k != j && dist[k][i] < dist[k][j] && dist[k][i] > 0) {
                                 b = Math.max(b, dist[k][i] + 1);
                             }
                         }
                         ans = Math.max(ans, a + b);
                     }
                 }
             }
             out.println(ans);
         }
 
     }
 
     static class InputReader {
         public BufferedReader reader;
         public StringTokenizer tokenizer;
 
         public InputReader(InputStream stream) {
             reader = new BufferedReader(new InputStreamReader(stream));
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
 
