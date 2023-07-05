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
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 public int hd, ad, hk, ak, b, d;
 
                 public void read(InputReader in) {
                     hd = in.nextInt();
                     ad = in.nextInt();
                     hk = in.nextInt();
                     ak = in.nextInt();
                     b = in.nextInt();
                     d = in.nextInt();
                 }
 
                 public String ret;
 
                 public void solve() {
                     long a1 = solve1(hk, ad, b);
                     long ans = 1L << 60;
                     for (int debuff = 0; debuff <= 100; debuff++) {
                         if (d > 0 || debuff == 0)
                             ans = Math.min(ans, solve2(debuff, hd, ak, d, a1));
                     }
 
                     if (ans >= 1L << 60) ret = "IMPOSSIBLE";
                     else ret = String.valueOf(ans);
                 }
 
                 public long solve2(int numdebuff, int hd, int ak, int d, long a1) {
                     int turns = 0;
                     boolean dec = false;
                     int ch = hd;
                     while (numdebuff > 0) {
                         
                         turns++;
                         if (ak - d >= ch) {
                             
                             if (!dec) return 1L << 60;
                             dec = false;
                             ch = hd;
                         } else {
                             dec = d > 0;
                             ak -= d;
                             numdebuff--;
                         }
                         
                         ch -= ak;
                     }
 
                     dec = true;
                     while (a1 > 0) {
                         
                         turns++;
                         if (a1 == 1) break;
                         if (ak >= ch) {
                             if (!dec) return 1L << 60;
                             dec = false;
                             ch = hd;
                         } else {
                             dec = true;
                             a1--;
                         }
 
                         
                         ch -= ak;
                     }
                     return turns;
                 }
 
                 public long solve1(long hk, long ad, long b) {
                     long ans = (hk + ad - 1) / ad;
                     for (int times = 0; times < 30000; times++) {
                         long ca = ad + b * times;
                         ans = Math.min(ans, times + (hk + ca - 1) / ca);
                     }
 
                     if (b != 0) {
                         for (int hits = 1; hits <= 30000; hits++) {
                             long need = (hk + hits - 1) / hits;
                             long times = Math.max(0, (need - ad + b - 1) / b);
                             long ca = ad + b * times;
                             ans = Math.min(ans, times + (hk + ca - 1) / ca);
                         }
                     }
 
                     return ans;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.println("Case #" + testNumber + ": " + ret);
                 }
             }, 16);
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
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
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
 }
 
