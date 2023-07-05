import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.TreeSet;
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
         int ans = 0;
         boolean[] marked;
         boolean[] onStack;
         List<Integer>[] adj;
         TreeSet<Integer>[] maxy;
         int[] ttl;
         int[] inDeg;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int N = in.nextInt();
             int F[] = new int[N];
             for (int i = 0; i < N; i++) {
                 F[i] = in.nextInt() - 1;
             }
             ans = 0;
             marked = new boolean[N];
             onStack = new boolean[N];
             adj = new LinkedList[N];
             ttl = new int[N];
             inDeg = new int[N];
             for (int i = 0; i < N; i++) {
                 adj[i] = new LinkedList<>();
                 adj[i].add(F[i]);
                 inDeg[F[i]]++;
             }
 
             maxy = new TreeSet[N];
             for (int i = 0; i < N; i++) {
                 maxy[i] = new TreeSet<>();
             }
 
             for (int v = 0; v < N; v++) {
                 if (!marked[v]) dfs(v, 1);
             }
             int ttt = 0;
             for (int v = 0; v < N; v++) {
                 int w = F[v];
                 if (F[w] == v) {
                     
                     boolean mark[] = new boolean[N];
                     TreeSet<Distance> setc1 = new TreeSet<>();
                     TreeSet<Distance> setc2 = new TreeSet<>();
                     for (int x = 0; x < N; x++) {
                         if (!mark[x] && x != v && x != w && inDeg[x] == 0) {
                             dfs2(x, mark, setc1, setc2, 1, ttt++, v, w);
                         }
                     }
                     int ff = setc1.isEmpty() ? 0 : setc1.pollLast().dist;
                     int ss = setc2.isEmpty() ? 0 : setc2.pollLast().dist;
                     ans = Math.max(ans, 2 + ff + ss);
                 }
             }
 
 
             out.println("Case #" + testNumber + ": " + ans);
         }
 
         private void dfs2(int x, boolean[] mark, TreeSet<Distance> setc1, TreeSet<Distance> setc2, int dep, int idx, int c1, int c2) {
             mark[x] = true;
 
             for (Integer w : adj[x]) {
                 if (!mark[w]) {
                     if (w == c1) {
                         setc1.add(new Distance(dep, idx));
                     } else if (w == c2) {
                         setc2.add(new Distance(dep, idx));
                     } else {
                         dfs2(w, mark, setc1, setc2, dep + 1, idx, c1, c2);
                     }
                 }
             }
         }
 
         private void dfs(int v, int dep) {
             marked[v] = true;
             onStack[v] = true;
             ttl[v] = dep;
             for (Integer w : adj[v]) {
                 if (!marked[w]) {
                     dfs(w, dep + 1);
                 } else if (onStack[w]) {
                     
                     ans = Math.max(ans, ttl[v] - ttl[w] + 1);
                 }
             }
             onStack[v] = false;
         }
 
         private static class Distance implements Comparable<Distance> {
             private int idx;
             private int dist;
 
             public Distance(int dist, int idx) {
                 this.dist = dist;
                 this.idx = idx;
             }
 
             public int compareTo(Distance that) {
                 if (this.dist == that.dist) return this.idx - that.idx;
                 return this.dist - that.dist;
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
 
