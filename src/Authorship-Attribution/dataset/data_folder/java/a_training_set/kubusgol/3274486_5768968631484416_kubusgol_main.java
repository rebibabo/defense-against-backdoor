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
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
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
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int N = in.nextInt();
             int K = in.nextInt();
             double INF = -1e19;
             double[][] DP = new double[N + 1][K + 1];
             for (double[] X : DP) Arrays.fill(X, INF);
 
             Pancake[] P = new Pancake[N];
             for (int i = 0; i < N; i++) {
                 P[i] = new Pancake(i, in.nextInt(), in.nextInt());
             }
 
             Arrays.sort(P);
 
             DP[0][0] = 0D;
 
             for (int i = 1; i <= N; i++) {
                 
                 DP[i][1] = Math.PI * P[i - 1].r * P[i - 1].r + 2D * Math.PI * P[i - 1].r * P[i - 1].h;
             }
 
             for (int k = 2; k <= K; k++) {
                 for (int n = 1; n <= N; n++) {
                     for (int prev = n - 1; prev > 0; prev--) {
                         if (P[prev - 1].r >= P[n - 1].r) {
                             DP[n][k] = Math.max(DP[n][k], DP[prev][k - 1] + 2D * Math.PI * P[n - 1].r * P[n - 1].h);
                         }
                     }
                 }
             }
 
             double ANS = 0D;
 
             for (int i = 1; i <= N; i++) {
                 ANS = Math.max(ANS, DP[i][K]);
             }
 
             out.println(String.format("Case #%d: %f", testNumber, ANS));
         }
 
         private class Pancake implements Comparable<Pancake> {
             private int id;
             int r;
             int h;
 
             public Pancake(int id, int r, int h) {
                 this.id = id;
                 this.r = r;
                 this.h = h;
             }
 
 
             public int compareTo(Pancake that) {
                 if (this.r > that.r) return -1;
                 if (this.r < that.r) return +1;
 
                 if (this.h < that.h) return -1;
                 if (this.h > that.h) return +1;
                 return this.id - that.id;
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
 
