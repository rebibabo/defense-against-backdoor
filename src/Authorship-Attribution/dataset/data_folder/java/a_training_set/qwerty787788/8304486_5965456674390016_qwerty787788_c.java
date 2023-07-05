import java.io.*;
 import java.util.*;
 
 public class C {
     FastScanner in;
     PrintWriter out;
 
     int get(int x, int y) {
         return (y - x + 24) % 24;
     }
 
     class Edge {
         int start;
         int to;
         boolean was;
         int startTime, endTime;
         Edge bestNext;
 
         public Edge(int start, int to, int startTime, int endTime) {
             this.start = start;
             this.to = to;
             this.startTime = startTime;
             this.endTime = endTime;
         }
     }
 
     void solve() {
         int tc = in.nextInt();
         for (int t = 0; t < tc; t++) {
             out.print("Case #" + (t + 1) + ": ");
             int n = in.nextInt();
             ArrayList<Integer>[] starts = new ArrayList[n];
             ArrayList<Integer>[] ends = new ArrayList[n];
             ArrayList<Edge>[] edgesIn = new ArrayList[n];
             ArrayList<Edge>[] edgesOut = new ArrayList[n];
             for (int i = 0; i < n; i++) {
                 starts[i] = new ArrayList<>();
                 ends[i] = new ArrayList<>();
                 edgesIn[i] = new ArrayList<>();
                 edgesOut[i] = new ArrayList<>();
             }
             int res = 0;
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < 2; j++) {
                     int to = in.nextInt() - 1;
                     int start = in.nextInt();
                     starts[i].add(start);
                     int len = in.nextInt();
                     ends[to].add((start + len) % 24);
                     res += len;
                     Edge e = new Edge(i, to, start, (start + len) % 24);
                     edgesIn[e.to].add(e);
                     edgesOut[i].add(e);
                 }
             }
 
             for (int i = 1; i < n; i++) {
                 Edge eIn1 = edgesIn[i].get(0), eIn2 = edgesIn[i].get(1);
                 Edge eOut1 = edgesOut[i].get(0), eOut2 = edgesOut[i].get(1);
                 int v1 = get(eIn1.endTime, eOut1.startTime) + get(eIn2.endTime, eOut2.startTime);
                 int v2 = get(eIn1.endTime, eOut2.startTime) + get(eIn2.endTime, eOut1.startTime);
                 if (v1 < v2) {
                     eIn1.bestNext = eOut1;
                     eIn2.bestNext = eOut2;
                 } else {
                     eIn1.bestNext = eOut2;
                     eIn2.bestNext = eOut1;
                 }
             }
             int r = Integer.MAX_VALUE;
             for (int it = 0; it < 2; it++) {
                 int curTime = 0;
                 int re = res;
                 for (int cycle = 0; cycle < 2; cycle++) {
                     Edge e = edgesOut[0].get((it+ cycle) % 2);
                     re += get(curTime, e.startTime);
                     while (e.bestNext != null) {
                         re += get(e.endTime, e.bestNext.startTime);
                         e = e.bestNext;
                     }
                     curTime = e.endTime;
                 }
                 r = Math.min(r, re);
             }
             out.println(r);
             System.err.println((t + 1) + "/" + tc + " done");
         }
     }
 
     void run() {
         try {
             in = new FastScanner(new File("C.in"));
             out = new PrintWriter(new File("C.out"));
 
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
         new C().run();
     }
 }