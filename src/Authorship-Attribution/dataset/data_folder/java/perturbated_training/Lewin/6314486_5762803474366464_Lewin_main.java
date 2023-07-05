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
 import java.math.BigInteger;
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
                 public int n;
                 public int[] x, y, z;
 
                 public void read(InputReader in) {
                     n = in.nextInt();
                     x = new int[n];
                     y = new int[n];
                     z = new int[n];
                     for (int i = 0; i < n; i++) {
                         x[i] = in.nextInt();
                         y[i] = in.nextInt();
                         z[i] = in.nextInt();
                     }
                 }
 
                 public String ans;
 
                 public void solve() {
                     Geometry3D.Point3D[] pts = new Geometry3D.Point3D[n];
                     for (int i = 0; i < n; i++)
                         pts[i] = new Geometry3D.Point3D(x[i], y[i], z[i]);
                     Geometry3D.Point3D origin = new Geometry3D.Point3D(0, 0, 0);
                     for (int i1 = 0; i1 < n; i1++) {
                         for (int i2 = 0; i2 < n; i2++) {
                             if (i1 == i2) continue;
                             Geometry3D.Point3D ee = Geometry3D.cross(origin, pts[i1], pts[i2]);
                             if (ee.x == 0 && ee.y == 0 && ee.z == 0) {
                                 if (Geometry3D.dot(pts[i1], pts[i2]) < 0) {
                                     ans = "YES";
                                     return;
                                 }
                                 continue;
                             }
                             boolean hasopp = false, hascop = false, haspos = false, hasneg = false;
                             for (int i3 = 0; i3 < n; i3++) {
                                 long dir = Geometry3D.side(origin, pts[i1], pts[i2], pts[i3]);
                                 if (dir == 0) {
                                     Geometry3D.Point3D ff = Geometry3D.cross(origin, pts[i1], pts[i3]);
                                     BigInteger xx = BigInteger.valueOf(ff.x).multiply(BigInteger.valueOf(ee.x));
                                     BigInteger yy = BigInteger.valueOf(ff.y).multiply(BigInteger.valueOf(ee.y));
                                     BigInteger zz = BigInteger.valueOf(ff.z).multiply(BigInteger.valueOf(ee.z));
                                     if (ff.x == 0 && ff.y == 0 && ff.z == 0) {
                                         if (Geometry3D.dot(pts[i1], pts[i3]) < 0) {
                                             ans = "YES";
                                             return;
                                         }
                                     }
                                     hascop = true;
                                     if (xx.add(yy).add(zz).compareTo(BigInteger.ZERO) < 0) hasopp = true;
                                 }
                                 if (dir > 0) haspos = true;
                                 if (dir < 0) hasneg = true;
                             }
                             if ((!hasopp || !hascop) && (!haspos || !hasneg)) {
                                 ans = "NO";
                                 return;
                             }
                         }
                     }
                     ans = "YES";
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.println("Case #" + testNumber + ": " + ans);
                 }
             }, 16);
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
 
     static interface TaskFactory {
         public Task newTask();
 
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
 
     static class Geometry3D {
         public static long dot(Geometry3D.Point3D a, Geometry3D.Point3D b) {
             return a.x * b.x + a.y * b.y + a.z * b.z;
         }
 
         public static Geometry3D.Point3D sub(Geometry3D.Point3D a, Geometry3D.Point3D b) {
             return new Geometry3D.Point3D(a.x - b.x, a.y - b.y, a.z - b.z);
         }
 
         public static Geometry3D.Point3D cross(Geometry3D.Point3D a, Geometry3D.Point3D b, Geometry3D.Point3D c) {
             Geometry3D.Point3D p = sub(b, a), q = sub(c, a);
             return new Geometry3D.Point3D(p.y * q.z - p.z * q.y, p.z * q.x - p.x * q.z, p.x * q.y - p.y * q.x);
         }
 
         public static long side(Geometry3D.Point3D a, Geometry3D.Point3D b, Geometry3D.Point3D c, Geometry3D.Point3D d) {
             return dot(cross(a, b, c), sub(d, a));
         }
 
         public static class Point3D {
             public long x;
             public long y;
             public long z;
 
             public Point3D(long x, long y, long z) {
                 this.x = x;
                 this.y = y;
                 this.z = z;
             }
 
         }
 
     }
 }
 
