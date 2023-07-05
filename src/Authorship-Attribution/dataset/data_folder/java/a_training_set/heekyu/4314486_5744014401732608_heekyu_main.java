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
             final String regex = "B-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("b.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         private long ways(int[][] g) {
             int N = g.length;
             long[] ways = new long[N];
             ways[0] = 1;
             for (int i = 0; i < N; i++) {
                 for (int j = i + 1; j < N; j++) {
                     if (g[i][j] == 1) {
                         ways[j] += ways[i];
                     }
                 }
             }
             return ways[N - 1];
         }
 
         private void print(int[][] g, PrintWriter out) {
             int B = g.length;
             for (int i = 0; i < B; i++) {
                 StringBuffer sb = new StringBuffer();
                 for (int j = 0; j < B; j++) {
                     sb.append(g[i][j]);
                 }
                 out.println(sb.toString());
             }
         }
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int B = in.nextInt();
             long M = in.nextLong();
             int[][] g = new int[B][B];
 
             int bit = B * (B - 1) / 2;
             int[] x = new int[bit];
             int[] y = new int[bit];
             int p = 0;
             for (int i = 0; i < B; i++) {
                 for (int j = i + 1; j < B; j++) {
                     x[p] = i;
                     y[p] = j;
                     p++;
                 }
             }
             for (int i = 0; i < (1 << bit); i++) {
                 for (int j = 0; j < B; j++) Arrays.fill(g[j], 0);
                 for (int j = 0; j < bit; j++) {
                     if (((1 << j) & i) > 0) {
                         g[x[j]][y[j]] = 1;
                     }
                 }
                 if (ways(g) == M) {
                     out.println("POSSIBLE");
                     print(g, out);
                     return;
                 }
             }
             out.println("IMPOSSIBLE");
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
 
         public long nextLong() {
             return Long.parseLong(next());
         }
 
     }
 }
 
