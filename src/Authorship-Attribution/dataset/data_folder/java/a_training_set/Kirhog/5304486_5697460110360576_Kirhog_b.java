package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.Set;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     boolean debug = true;
 
     private int[] need;
     private int n;
     private int p;
     private int[][] packages;
 
     private Range[][] arr;
 
     void solve() {
         n = sc.nextInt();
         if (debug) {
             System.out.println();
         }
 
         p = sc.nextInt();
 
         need = new int[n];
         for (int i = 0; i < n; ++i) {
             need[i] = sc.nextInt();
 
             if (debug) {
                 System.out.printf("%d ", need[i]);
             }
         }
 
         if (debug) {
             System.out.println();
         }
 
         packages = new int[n][p];
         arr = new Range[n][p];
         for (int i = 0; i < n; ++i) {
             for (int k = 0; k < p; ++k) {
                 int amount = sc.nextInt();
                 packages[i][k] = amount;
 
                 
 
                 double d1 = ((double) amount) / need[i];
                 double dmax = d1 / 0.9;
                 double dmin = d1 / 1.1;
 
                 int min = (int) Math.ceil(dmin);
                 int max = (int) Math.floor(dmax);
 
                 arr[i][k] = new Range(min, max);
 
                 
             }
         }
 
         if (debug) {
             for (int i = 0; i < n; ++i) {
                 for (int k = 0; k < p; ++k) {
                     System.out.printf("%d ", packages[i][k]);
                 }
                 System.out.println();
             }
             for (int i = 0; i < n; ++i) {
                 for (int k = 0; k < p; ++k) {
                     System.out.printf("[%d, %d], ", arr[i][k].from, arr[i][k].to);
                 }
                 System.out.println();
             }
         }
 
         int vcount = n * p + 2;
         MaxFlow flow = new MaxFlow(vcount);
 
         for (int k = 0; k < p; ++k) {
             int v1 = k + 1;
             Range r1 = arr[0][k];
             if (!r1.isInvalid()) {
                 flow.addEdge(0, v1, 1);
             }
 
             int v2 = (n - 1) * p + k + 1;
             Range r2 = arr[n - 1][k];
             if (!r2.isInvalid()) {
                 flow.addEdge(v2, vcount - 1, 1);
             }
         }
 
         for (int i = 0; i < n - 1; ++i) {
             for (int k = 0; k < p; ++k) {
                 int v1 = i * p + k + 1;
                 Range r1 = arr[i][k];
 
                 int i2 = i + 1;
                 for (int k2 = 0; k2 < p; ++k2) {
                     int v2 = i2 * p + k2 + 1;
                     Range r2 = arr[i2][k2];
 
                     if (r1.intersects(r2)) {
                         flow.addEdge(v1, v2, 1);
                     }
                 }
             }
         }
 
         out.printf("%d\n", flow.flow());
     }
 
     static class Range implements Comparable<Range> {
         int from;
         int to;
 
         public Range(int from, int to) {
             this.from = from;
             this.to = to;
 
             if (to <= 0 || to < from) {
                 this.to = this.from = -1;
             }
         }
 
         boolean isInvalid() {
             return from == -1 || to == -1;
         }
 
         boolean intersects(Range other) {
             if (isInvalid() || other.isInvalid()) {
                 return false;
             }
 
             return this.from <= other.to && other.from <= this.to;
         }
 
         @Override
         public int compareTo(Range o) {
             int res = from - o.from;
             if (res != 0) {
                 return res;
             }
 
             return to - o.to;
         }
     }
 
     static class MaxFlow {
         int source;
         int sink;
 
         Set<Edge>[] forward;
         Set<Edge>[] backward;
 
         Vertex[] path;
 
         @SuppressWarnings("unchecked")
         public MaxFlow(int n) {
             source = 0;
             sink = n - 1;
 
             forward = new Set[n];
             backward = new Set[n];
             for (int i = 0; i < n; ++i) {
                 forward[i] = new HashSet<>();
                 backward[i] = new HashSet<>();
             }
 
             path = new Vertex[n];
         }
 
         
         public void addEdge(int v, int u, int c) {
             Edge edge = new Edge(v, u, c);
             forward[v].add(edge);
             backward[u].add(edge);
         }
 
         public int flow() {
             compute();
 
             int flow = 0;
             for (Edge edge : forward[source]) {
                 flow += edge.flow;
             }
 
             return flow;
         }
 
         private void compute() {
             while (findAugmentingPath()) {
                 processPath();
             }
         }
 
         private boolean findAugmentingPath() {
             Arrays.fill(path, null);
 
             path[source] = new Vertex(-1, false, null);
 
             return findAugmentingPath(source);
         }
 
         private boolean findAugmentingPath(int v) {
             if (v == sink) {
                 return true;
             }
 
             for (Edge edge : forward[v]) {
                 if (path[edge.to] == null && edge.capacity > edge.flow) {
                     path[edge.to] = new Vertex(v, true, edge);
                     if (findAugmentingPath(edge.to)) {
                         return true;
                     }
                 }
             }
 
             for (Edge edge : backward[v]) {
                 if (path[edge.from] == null && edge.flow > 0) {
                     path[edge.from] = new Vertex(v, false, edge);
                     if (findAugmentingPath(edge.from)) {
                         return true;
                     }
                 }
             }
 
             path[v] = null;
 
             return false;
         }
 
         private void processPath() {
             int delta = findDelta();
             adjustFlow(delta);
         }
 
         private int findDelta() {
             int v = sink;
             int delta = Integer.MAX_VALUE;
             while (v != source) {
                 Vertex vertex = path[v];
                 Edge edge = vertex.edge;
                 int t;
                 if (vertex.isForward) {
                     t = edge.capacity - edge.flow;
                 } else {
                     t = edge.flow;
                 }
                 if (t < delta) {
                     delta = t;
                 }
 
                 v = vertex.start;
             }
 
             return delta;
         }
 
         private void adjustFlow(int delta) {
             int v = sink;
             while (v != source) {
                 Vertex vertex = path[v];
                 Edge edge = vertex.edge;
                 if (vertex.isForward) {
                     edge.flow += delta;
                 } else {
                     edge.flow -= delta;
                 }
 
                 v = vertex.start;
             }
         }
 
         static class Vertex {
             int start;
             boolean isForward;
             Edge edge;
 
             Vertex(int start, boolean isForward, Edge edge) {
                 this.start = start;
                 this.isForward = isForward;
                 this.edge = edge;
             }
         }
 
         static class Edge {
             int from;
             int to;
             int capacity;
             int flow;
 
             Edge(int from, int to, int capacity) {
                 this.from = from;
                 this.to = to;
                 this.capacity = capacity;
                 flow = 0;
             }
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
 
         String file = "B-small-attempt2";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             B template = new B();
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
 
     private int[] start;
 }
