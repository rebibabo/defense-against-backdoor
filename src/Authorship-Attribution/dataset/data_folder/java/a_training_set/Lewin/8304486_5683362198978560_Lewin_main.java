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
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "D-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("d.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskD solver = new TaskD();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskD {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
 
                 public int r, c, n, d;
                 public TaskD.Cell[] cs;
 
                 public void read(InputReader in) {
                     r = in.nextInt();
                     c = in.nextInt();
                     n = in.nextInt();
                     d = in.nextInt();
                     cs = new TaskD.Cell[n];
                     for (int i = 0; i < n; i++) {
                         cs[i] = new TaskD.Cell(in.nextInt(), in.nextInt(), in.nextInt());
                     }
                 }
 
                 public int mod = 1000000007;
                 public long ans;
 
                 public void solve() {
                     for (TaskD.Cell b1 : cs) {
                         for (TaskD.Cell b2 : cs) {
                             if (1L * (Math.abs(b1.r - b2.r) + Math.abs(b1.c - b2.c)) * d < Math.abs(b1.b - b2.b)) {
                                 ans = -1;
                                 return;
                             }
                         }
                     }
                     long ret = 0;
                     for (int i = 1; i <= r; i++) {
                         for (int j = 1; j <= c; j++) {
                             long best = 1L << 60;
                             for (TaskD.Cell b : cs) {
                                 best = Math.min(best, 1L * (Math.abs(b.r - i) + Math.abs(b.c - j)) * d + b.b);
                             }
                             ret += best;
                             ret %= mod;
                         }
                     }
                     ans = ret;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.println("Case #" + testNumber + ": " + (ans == -1 ? "IMPOSSIBLE" : ans));
                 }
             }, 16);
         }
 
         static class Cell {
             public int r;
             public int c;
             public int b;
 
             public Cell(int r, int c, int b) {
                 this.r = r;
                 this.c = c;
                 this.b = b;
             }
 
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
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
     }
 }
 
