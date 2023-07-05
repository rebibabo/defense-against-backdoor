import java.io.*;
 import java.util.*;
 
 public class B {
     FastScanner in;
     PrintWriter out;
 
     class Edge {
         int id;
         int to;
         int flow;
         Edge rev;
         boolean used2;
         boolean used;
 
         public Edge(int to, int id) {
             this.to = to;
             this.id = id;
         }
     }
 
     boolean dfs(int v, int need, ArrayList<Edge> edges, boolean[] was, ArrayList<Edge>[] g) {
         if (v == need) {
             return true;
         }
         if (was[v]) {
             return false;
         }
         was[v] = true;
         for (Edge e : g[v]) {
             if (!e.used2) {
                 continue;
             }
             edges.add(e);
             if (dfs(e.to, need, edges, was, g)) {
                 return true;
             }
             edges.remove(edges.size() - 1);
         }
         return false;
     }
 
     void dfs2(int v, ArrayList<Edge>[] g, boolean[] was) {
         if (was[v]) {
             return;
         }
         was[v] = true;
         for (Edge e : g[v]) {
             if (e.used2 || e.rev.used2) {
                 continue;
             }
             e.used2 = true;
             dfs2(e.to, g, was);
         }
     }
 
 
     void solve() {
         int tc = in.nextInt();
         for (int t = 0; t < tc; t++) {
             out.print("Case #" + (t + 1) + ": ");
             int n = in.nextInt();
             ArrayList<Edge>[] g = new ArrayList[n];
             for (int i = 0; i < n; i++) {
                 g[i] = new ArrayList<>();
             }
             int m = in.nextInt();
             for (int i = 0; i < m; i++) {
                 int fr = in.nextInt() - 1;
                 int to = in.nextInt() - 1;
                 Edge e = new Edge(to, i);
                 Edge rev = new Edge(fr, ~i);
                 e.rev = rev;
                 rev.rev = e;
                 g[fr].add(e);
                 g[to].add(rev);
             }
             boolean[] was = new boolean[n];
             for (int i = 0; i < n; i++) {
                 dfs2(i, g, was);
             }
             boolean ok = true;
             ArrayList<ArrayList<Edge>> cycles = new ArrayList<>();
             for (int i = 0; i < n; i++) {
                 if (!ok) {
                     break;
                 }
                 for (Edge e : g[i]) {
                     if (!ok) {
                         break;
                     }
                     if (!e.used && e.used2) {
                         ArrayList<Edge> tmp = new ArrayList<>();
                         tmp.add(e);
                         Arrays.fill(was, false);
                         ok &= dfs(e.to, i, tmp, was, g);
                         if (ok) {
                             cycles.add(tmp);
                         }
                     }
                 }
             }
             if (!ok) {
                 out.println("IMPOSSIBLE");
             } else {
                 for (ArrayList<Edge> edges : cycles) {
                     boolean need = false;
                     for (Edge e : edges) {
                         if (e.flow == 0) {
                             need = true;
                         }
                     }
                     if (need) {
                         for (Edge e : edges) {
                             e.flow++;
                         }
                     }
                 }
 
                 int[] res = new int[m];
                 for (int i = 0; i < n; i++) {
                     for (Edge e : g[i]) {
                         if (e.used2) {
                             if (e.id >= 0) {
                                 res[e.id] = e.flow;
                             } else {
                                 res[~e.id] = -e.flow;
                             }
                         }
                     }
                 }
                 for (int x : res) {
                     out.print(x + " ");
                 }
                 out.println();
             }
             System.err.println((t + 1) + "/" + tc + " done");
         }
     }
 
     void run() {
         try {
             in = new FastScanner(new File("B.in"));
             out = new PrintWriter(new File("B.out"));
 
             solve();
 
             out.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }
 
     void runIO() {
         in = new FastScanner(System.in);
         out = new PrintWriter(System.out);
 
         solve();
 
         out.close();
     }
 
     class FastScanner {
         BufferedReader br;
         StringTokenizer st;
 
         public FastScanner(File f) {
             try {
                 br = new BufferedReader(new FileReader(f));
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
 
         public FastScanner(InputStream f) {
             br = new BufferedReader(new InputStreamReader(f));
         }
 
         String next() {
             while (st == null || !st.hasMoreTokens()) {
                 String s = null;
                 try {
                     s = br.readLine();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 if (s == null)
                     return null;
                 st = new StringTokenizer(s);
             }
             return st.nextToken();
         }
 
         boolean hasMoreTokens() {
             while (st == null || !st.hasMoreTokens()) {
                 String s = null;
                 try {
                     s = br.readLine();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 if (s == null)
                     return false;
                 st = new StringTokenizer(s);
             }
             return true;
         }
 
         int nextInt() {
             return Integer.parseInt(next());
         }
 
         long nextLong() {
             return Long.parseLong(next());
         }
 
         double nextDouble() {
             return Double.parseDouble(next());
         }
     }
 
     public static void main(String[] args) {
         new B().run();
     }
 }