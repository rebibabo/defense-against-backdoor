import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
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
 import java.util.Comparator;
 import java.util.Collections;
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
         OutputWriter out = new OutputWriter(outputStream);
         TaskC solver = new TaskC();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskC {
         public int HOURS = 24;
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 public int n;
                 public List<TaskC.Edge>[] oute;
                 public List<TaskC.Edge>[] ine;
                 public TaskC.Edge[] es;
 
                 public void read(InputReader in) {
                     n = in.nextInt();
                     oute = LUtils.genArrayList(n);
                     ine = LUtils.genArrayList(n);
                     base = 0;
                     es = new TaskC.Edge[2 * n];
                     for (int i = 0; i < 2 * n; i++) {
                         int from = i / 2;
                         int to = in.nextInt() - 1;
                         int start = in.nextInt();
                         int duration = in.nextInt();
                         base += duration;
                         int end = (start + duration) % HOURS;
                         int idx = i;
                         TaskC.Edge e = new TaskC.Edge(from, to, start, end, idx);
                         es[i] = e;
                         ine[to].add(e);
                         oute[from].add(e);
                     }
                 }
 
                 public int[] next;
                 public int[] cycle;
                 public int total, base;
                 public boolean[] vis;
 
                 public void solve() {
                     total = 1 << 29;
                     for (int x1 = 0; x1 < 2; x1++) {
                         for (int x2 = 0; x2 < 2; x2++) {
                             int add = oute[0].get(x2).start + wait(ine[0].get(1 - x1).end, oute[0].get(1 - x2).start);
                             int[] ss = new int[n];
                             next = new int[2 * n];
                             for (int i = 1; i < n; i++) {
                                 int b1 = wait(ine[i].get(0).end, oute[i].get(0).start) + wait(ine[i].get(1).end, oute[i].get(1).start);
                                 int b2 = wait(ine[i].get(1).end, oute[i].get(0).start) + wait(ine[i].get(0).end, oute[i].get(1).start);
                                 if (b1 < b2) {
                                     add += b1;
                                     ss[i] = b2 - b1;
                                     next[ine[i].get(0).idx] = oute[i].get(0).idx;
                                     next[ine[i].get(1).idx] = oute[i].get(1).idx;
                                 } else {
                                     add += b2;
                                     ss[i] = b1 - b2;
                                     next[ine[i].get(0).idx] = oute[i].get(1).idx;
                                     next[ine[i].get(1).idx] = oute[i].get(0).idx;
                                 }
                             }
                             next[ine[0].get(x1).idx] = oute[0].get(x2).idx;
                             next[ine[0].get(1 - x1).idx] = oute[0].get(1 - x2).idx;
                             cycle = new int[n];
                             int[] count = new int[n];
                             Arrays.fill(cycle, -1);
                             vis = new boolean[2 * n];
                             ArrayList<TaskC.E2> ess = new ArrayList<>();
                             int cidx = -1;
                             for (int i = 0; i < 2 * n; i++) {
                                 if (!vis[i]) {
                                     cidx++;
                                     int cur = i;
                                     do {
                                         vis[cur] = true;
                                         if (es[cur].from != 0 && cycle[es[cur].from] != -1) {
                                             ess.add(new TaskC.E2(cycle[es[cur].from], cidx, ss[es[cur].from]));
                                         }
                                         count[es[cur].from]++;
                                         if (count[es[cur].from] > 2) {
                                             System.out.println("BAD!" + " " + es[cur].from);
                                         }
                                         cycle[es[cur].from] = cidx;
                                         cur = next[cur];
                                     } while (cur != i);
                                 }
                             }
                             Collections.sort(ess, Comparator.comparingInt(bb -> bb.w));
                             int cur = add;
                             int[] p = DisjointSets.createSets(cidx + 1);
                             for (TaskC.E2 ww : ess) {
                                 if (DisjointSets.unite(p, ww.a, ww.b)) {
                                     cur += ww.w;
                                 }
                             }
                             total = Math.min(total, cur);
                         }
                     }
                 }
 
                 public int wait(int start, int end) {
                     return (end - start + HOURS) % HOURS;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.println("Case #" + testNumber + ": " + (total + base));
                 }
             }, 16);
         }
 
         static class Edge {
             public int from;
             public int to;
             public int start;
             public int end;
             public int idx;
 
             public Edge(int from, int to, int start, int end, int idx) {
                 this.from = from;
                 this.to = to;
                 this.start = start;
                 this.end = end;
                 this.idx = idx;
             }
 
         }
 
         static class E2 {
             public int a;
             public int b;
             public int w;
 
             public E2(int a, int b, int w) {
                 this.a = a;
                 this.b = b;
                 this.w = w;
             }
 
         }
 
     }
 
     static class DisjointSets {
         public static int[] createSets(int size) {
             int[] p = new int[size];
             for (int i = 0; i < size; i++)
                 p[i] = i;
             return p;
         }
 
         public static int root(int[] p, int x) {
             return x == p[x] ? x : (p[x] = root(p, p[x]));
         }
 
         public static boolean unite(int[] p, int a, int b) {
             a = root(p, a);
             b = root(p, b);
             if (a != b) {
 
                 p[a] = b;
 
 
                 return true;
             }
             return false;
         }
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
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
 
         public void println(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void close() {
             writer.close();
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
 }
 
