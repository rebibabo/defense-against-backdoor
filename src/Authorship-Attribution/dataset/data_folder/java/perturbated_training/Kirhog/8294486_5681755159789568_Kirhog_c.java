package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Locale;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
     private Horse[] horses;
     private long[] speeds;
     private int n;
     
 
     Vertex[] vv;
 
     void solve() {
         n = sc.nextInt();
         int q = sc.nextInt();
 
         horses = new Horse[n];
         vv = new Vertex[n];
         speeds = new long[n];
         for (int i = 0; i < n; ++i) {
             long dist = sc.nextLong();
             long speed = sc.nextLong();
             horses[i] = new Horse(dist, speed);
             speeds[i] = speed;
             vv[i] = new Vertex();
             vv[i].total[i] = dist;
         }
 
         Graph g = new Graph();
 
         double times[][] = new double[n][n];
 
         for (int i = 0; i < n; ++i) {
             for (int k = 0; k < n; ++k) {
                 long dist = sc.nextLong();
                 if (dist != -1) {
                     g.addEdge(i, k, dist);
                 }
                 times[i][k] = Double.POSITIVE_INFINITY;
             }
         }
 
         for (int h = 0; h < n; ++h) {
             Horse horse = horses[h];
             long dist[] = new long[n];
             Arrays.fill(dist, -1);
             dist[h] = 0;
 
             PriorityQueue<DV> queue = new PriorityQueue<>();
             queue.add(new DV(h, 0));
 
             while (!queue.isEmpty()) {
                 DV cur = queue.poll();
                 if (dist[cur.num] != -1 && dist[cur.num] < cur.dist) {
                     continue;
                 }
 
                 for (Edge edge : g.edges[cur.num]) {
                     long newDist = cur.dist + edge.dist;
                     if (newDist > horse.total) {
                         continue;
                     }
 
                     if (dist[edge.to] == -1 || dist[edge.to] > newDist) {
                         dist[edge.to] = newDist;
                         queue.add(new DV(edge.to, newDist));
                     }
                 }
             }
 
             for (int k = 0; k < n; ++k) {
                 if (dist[k] != -1) {
                     double time = ((double) dist[k]) / horse.speed;
                     times[h][k] = time;
                 }
             }
         }
 
         for (int k = 0; k < n; ++k) {
             for (int i = 0; i < n; ++i) {
                 for (int j = 0; j < n; ++j) {
                     double newTime = times[i][k] + times[k][j];
                     if (newTime < times[i][j]) {
                         times[i][j] = newTime;
                     }
                 }
             }
         }
 
         List<String> result = new ArrayList<>(q);
         for (int delivery = 0; delivery < q; ++delivery) {
             int start = sc.nextInt() - 1;
             int dest = sc.nextInt() - 1;
             double time = times[start][dest];
             String timeStr = String.format("%.9f", time);
             result.add(timeStr);
         }
 
         out.printf("%s\n", String.join(" ", result));
     }
 
     static class DV implements Comparable<DV> {
         int num;
         long dist;
 
         public DV(int num, long dist) {
             this.num = num;
             this.dist = dist;
         }
 
         @Override
         public int compareTo(DV o) {
             return Long.compare(dist, o.dist);
         }
     }
 
     @SuppressWarnings("unchecked")
     class Graph {
         long[][] dist;
         double[][] time;
         Vertex[] vv;
         List<Edge>[] edges;
 
 
         public Graph() {
             dist = new long[n][n];
             time = new double[n][n];
             vv = new Vertex[n];
             for (int i = 0; i < n; ++i) {
                 vv[i] = new Vertex();
             }
             edges = new List[n];
             for (int i = 0; i < n; ++i) {
                 edges[i] = new ArrayList<>();
             }
         }
 
         Graph copy() {
             Graph copy = new Graph();
             return copy;
         }
 
         void addEdge(int from, int to, long dist) {
             edges[from].add(new Edge(from, to, dist));
         }
 
         double time(int start, int dest) {
             return 0;
         }
     }
 
 
     class Vertex {
         long[] total = new long[n];
 
         public Vertex() {
         }
     }
 
     static class Edge {
         int from;
         int to;
         long dist;
 
         public Edge(int from, int to, long dist) {
             this.from = from;
             this.to = to;
             this.dist = dist;
         }
 
         Edge changeDist(long newDist) {
             return new Edge(from, to, newDist);
         }
     }
 
     static class Horse {
         long total;
         long speed;
 
         public Horse(long total, long speed) {
             this.total = total;
             this.speed = speed;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "C-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             C template = new C();
             template.caseNumber = caseNumber;
             template.solve();
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
