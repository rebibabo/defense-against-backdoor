import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.stream.Stream;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
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
         OutputWriter out = new OutputWriter(outputStream);
         TaskB solver = new TaskB();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 public int n, m;
                 public List<Pair<Integer, Integer>>[] ograph;
                 public int[] indeg, outdeg;
 
                 public void read(InputReader in) {
                     n = in.nextInt();
                     m = in.nextInt();
                     ograph = LUtils.genArrayList(n);
                     for (int i = 0; i < m; i++) {
                         int a = in.nextInt() - 1;
                         int b = in.nextInt() - 1;
                         ograph[a].add(new Pair<>(b, 2 * i + 0));
                         ograph[b].add(new Pair<>(a, 2 * i + 1));
                     }
                 }
 
                 public void dfs(int node) {
                     vis[node] = true;
                     for (Pair<Integer, Integer> next : ograph[node]) {
                         int idx = next.v / 2, d = next.v & 1;
                         if (!used[idx]) {
                             used[idx] = true;
                             dir[idx] = d == 1;
                             graph[node].add(new Pair<>(next.u, idx));
                             indeg[next.u]++;
                             outdeg[node]++;
                             xx[node].add(next.u);
                         }
                         if (!vis[next.u]) {
                             dfs(next.u);
                         }
                     }
                 }
 
                 public int[] ans;
                 public boolean[] vis;
                 public boolean[] dir;
                 public boolean[] used;
                 public List<Pair<Integer, Integer>>[] graph;
                 public List<Integer>[] xx;
 
                 public void solve() {
                     indeg = new int[n];
                     outdeg = new int[n];
                     vis = new boolean[n];
                     dir = new boolean[m];
                     used = new boolean[m];
                     graph = LUtils.genArrayList(n);
                     xx = LUtils.genArrayList(n);
                     for (int i = 0; i < n; i++) {
                         if (!vis[i]) {
                             dfs(i);
                         }
                     }
                     int nnodes = n + 2;
                     List<MaxFlowDinic.Edge>[] gg = LUtils.genArrayList(nnodes);
                     int total = 0;
                     for (int i = 0; i < n; i++) {
                         if (indeg[i] < outdeg[i]) {
                             total += outdeg[i] - indeg[i];
                             MaxFlowDinic.addEdge(gg, i, nnodes - 2, outdeg[i] - indeg[i], -1);
                         } else if (indeg[i] > outdeg[i]) {
                             MaxFlowDinic.addEdge(gg, nnodes - 1, i, indeg[i] - outdeg[i], -1);
                         }
                         for (Pair<Integer, Integer> p : graph[i]) {
                             MaxFlowDinic.addEdge(gg, i, p.u, n * n - 1, p.v);
                         }
                     }
                     int x = MaxFlowDinic.maxFlow(gg, nnodes - 1, nnodes - 2);
                     if (x != total) {
                         ans = null;
                         return;
                     }
                     ans = new int[m];
                     Arrays.fill(ans, 1);
                     for (int j = 0; j < n; j++) {
                         for (MaxFlowDinic.Edge p : gg[j]) {
                             if (p.idx != -1 && p.f > 0) {
                                 ans[p.idx] += p.f;
                             }
                         }
                     }
                     for (int i = 0; i < m; i++) {
                         if (dir[i]) ans[i] = -ans[i];
                     }
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.print("Case #" + testNumber + ": ");
                     if (ans == null) {
                         out.println("IMPOSSIBLE");
                     } else {
                         out.println(ans);
                     }
                 }
             }, 1);
         }
 
     }
 
     static class MaxFlowDinic {
         public static void addEdge(List<MaxFlowDinic.Edge>[] graph, int s, int t, int cap, int idx) {
             graph[s].add(new MaxFlowDinic.Edge(t, graph[t].size(), cap, idx));
             graph[t].add(new MaxFlowDinic.Edge(s, graph[s].size() - 1, 0, idx));
         }
 
         static boolean dinicBfs(List<MaxFlowDinic.Edge>[] graph, int src, int dest, int[] dist) {
             Arrays.fill(dist, -1);
             dist[src] = 0;
             int[] Q = new int[graph.length];
             int sizeQ = 0;
             Q[sizeQ++] = src;
             for (int i = 0; i < sizeQ; i++) {
                 int u = Q[i];
                 for (MaxFlowDinic.Edge e : graph[u]) {
                     if (dist[e.t] < 0 && e.f < e.cap) {
                         dist[e.t] = dist[u] + 1;
                         Q[sizeQ++] = e.t;
                     }
                 }
             }
             return dist[dest] >= 0;
         }
 
         static int dinicDfs(List<MaxFlowDinic.Edge>[] graph, int[] ptr, int[] dist, int dest, int u, int f) {
             if (u == dest)
                 return f;
             for (; ptr[u] < graph[u].size(); ++ptr[u]) {
                 MaxFlowDinic.Edge e = graph[u].get(ptr[u]);
                 if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
                     int df = dinicDfs(graph, ptr, dist, dest, e.t, Math.min(f, e.cap - e.f));
                     if (df > 0) {
                         e.f += df;
                         graph[e.t].get(e.rev).f -= df;
                         return df;
                     }
                 }
             }
             return 0;
         }
 
         public static int maxFlow(List<MaxFlowDinic.Edge>[] graph, int src, int dest) {
             int flow = 0;
             int[] dist = new int[graph.length];
             while (dinicBfs(graph, src, dest, dist)) {
                 int[] ptr = new int[graph.length];
                 while (true) {
                     int df = dinicDfs(graph, ptr, dist, dest, src, Integer.MAX_VALUE);
                     if (df == 0)
                         break;
                     flow += df;
                 }
             }
             return flow;
         }
 
         public static class Edge {
             public int t;
             public int rev;
             public int cap;
             public int f;
             public int idx;
 
             public Edge(int t, int rev, int cap) {
                 this.t = t;
                 this.rev = rev;
                 this.cap = cap;
             }
 
             public Edge(int t, int rev, int cap, int idx) {
                 this.t = t;
                 this.rev = rev;
                 this.cap = cap;
                 this.idx = idx;
             }
 
         }
 
     }
 
     static class Scheduler {
         private final AtomicInteger testsRemaining;
         private final AtomicInteger threadsRemaining;
 
         public Scheduler(InputReader in, OutputWriter out, TaskFactory factory, int numParallel) {
             try {
                 testsRemaining = new AtomicInteger(in.nextInt());
                 threadsRemaining = new AtomicInteger(numParallel);
                 Task[] tasks = new Task[testsRemaining.get()];
                 for (int i = 0; i < tasks.length; i++) {
                     tasks[i] = factory.newTask();
                 }
                 for (Task task : tasks) {
                     task.read(in);
                     new Thread(() -> {
                         boolean freeThread = false;
                         synchronized (this) {
                             do {
                                 try {
                                     wait(10);
                                 } catch (InterruptedException ignored) {
                                 }
                                 if (threadsRemaining.get() != 0) {
                                     synchronized (threadsRemaining) {
                                         if (threadsRemaining.get() != 0) {
                                             threadsRemaining.decrementAndGet();
                                             freeThread = true;
                                         }
                                     }
                                 }
                             } while (!freeThread);
                         }
                         task.solve();
                         System.err.println(testsRemaining.decrementAndGet());
                         threadsRemaining.incrementAndGet();
                     }).start();
                 }
                 synchronized (this) {
                     while (testsRemaining.get() > 0) {
                         wait(10);
                     }
                 }
                 for (int i = 0; i < tasks.length; i++) {
                     tasks[i].write(out, i + 1);
                 }
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
     }
 
     static class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
         public final U u;
         public final V v;
 
         public Pair(U u, V v) {
             this.u = u;
             this.v = v;
         }
 
         public int hashCode() {
             return (u == null ? 0 : u.hashCode() * 31) + (v == null ? 0 : v.hashCode());
         }
 
         public boolean equals(Object o) {
             if (this == o)
                 return true;
             if (o == null || getClass() != o.getClass())
                 return false;
             Pair<U, V> p = (Pair<U, V>) o;
             return (u == null ? p.u == null : u.equals(p.u)) && (v == null ? p.v == null : v.equals(p.v));
         }
 
         public int compareTo(Pair<U, V> b) {
             int cmpU = u.compareTo(b.u);
             return cmpU != 0 ? cmpU : v.compareTo(b.v);
         }
 
         public String toString() {
             return String.format("[Pair = (%s, %s)", u.toString(), v.toString());
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static class OutputWriter {
         private final PrintWriter writer;
 
         public OutputWriter(OutputStream outputStream) {
             writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
         }
 
         public OutputWriter(Writer writer) {
             this.writer = new PrintWriter(writer);
         }
 
         public void print(Object... objects) {
             for (int i = 0; i < objects.length; i++) {
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(objects[i]);
             }
         }
 
         public void print(int[] array) {
             for (int i = 0; i < array.length; i++) {
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(array[i]);
             }
         }
 
         public void println(int[] array) {
             print(array);
             writer.println();
         }
 
         public void println(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (this.numChars == -1) {
                 throw new InputMismatchException();
             } else {
                 if (this.curChar >= this.numChars) {
                     this.curChar = 0;
 
                     try {
                         this.numChars = this.stream.read(this.buf);
                     } catch (IOException var2) {
                         throw new InputMismatchException();
                     }
 
                     if (this.numChars <= 0) {
                         return -1;
                     }
                 }
 
                 return this.buf[this.curChar++];
             }
         }
 
         public int nextInt() {
             int c;
             for (c = this.read(); isSpaceChar(c); c = this.read()) {
                 ;
             }
 
             byte sgn = 1;
             if (c == 45) {
                 sgn = -1;
                 c = this.read();
             }
 
             int res = 0;
 
             while (c >= 48 && c <= 57) {
                 res *= 10;
                 res += c - 48;
                 c = this.read();
                 if (isSpaceChar(c)) {
                     return res * sgn;
                 }
             }
 
             throw new InputMismatchException();
         }
 
         public static boolean isSpaceChar(int c) {
             return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
         }
 
     }
 
     static class LUtils {
         public static <E> List<E>[] genArrayList(int size) {
             return Stream.generate(ArrayList::new).limit(size).toArray(List[]::new);
         }
 
     }
 }
 
