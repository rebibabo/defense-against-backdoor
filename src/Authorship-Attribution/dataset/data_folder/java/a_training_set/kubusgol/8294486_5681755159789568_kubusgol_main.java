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
 import java.util.List;
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
 import java.util.LinkedList;
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
         int N;
         int Q;
         List<Edge>[] next;
         int[] CAPACITY;
         int[] SPEED;
         double[][] DP;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             N = in.nextInt();
             Q = in.nextInt();
 
             next = (List<Edge>[]) new LinkedList[N];
             for (int i = 0; i < N; i++) next[i] = new LinkedList<>();
 
             CAPACITY = new int[N];
             SPEED = new int[N];
 
             for (int i = 0; i < N; i++) {
                 CAPACITY[i] = in.nextInt();
                 SPEED[i] = in.nextInt();
             }
 
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < N; j++) {
                     int d = in.nextInt();
                     if (d != -1) {
                         next[i].add(new Edge(i, i + 1, d));
                     }
                 }
             }
 
             int U = in.nextInt() - 1;
             int V = in.nextInt() - 1;
             DP = new double[N][N];
             for (double[] X : DP) Arrays.fill(X, Double.POSITIVE_INFINITY);
             for (int i = 0; i < N; i++) DP[V][i] = 0D;
 
             double ANS = find(0, 0, CAPACITY[0], V);
 
             out.println("Case #" + testNumber + ": " + ANS);
         }
 
         private double find(int cur, int horseId, int capacityLeft, int target) {
             if (DP[cur][horseId] != Double.POSITIVE_INFINITY) return DP[cur][horseId];
 
             double ans = Double.POSITIVE_INFINITY;
 
             Edge edge = next[cur].get(0);
             if (edge.distance <= capacityLeft) {
                 ans = Math.min(ans, ((double) edge.distance / SPEED[horseId]) + find(cur + 1, horseId, capacityLeft - edge.distance, target));
             }
 
             if (edge.distance <= CAPACITY[cur]) {
                 ans = Math.min(ans, ((double) edge.distance / SPEED[cur]) + find(cur + 1, cur, CAPACITY[cur] - edge.distance, target));
             }
 
             return DP[cur][horseId] = ans;
         }
 
         private class Edge {
             private int from;
             private int to;
             private int distance;
 
             public Edge(int from, int to, int distance) {
                 this.from = from;
                 this.to = to;
                 this.distance = distance;
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
 
