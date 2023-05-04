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
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.math.BigInteger;
 import java.util.Collections;
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
                 class Operation {
                     public char type;
                     public int value;
 
                     public Operation(char type, int value) {
                         this.type = type;
                         this.value = value;
                     }
                 }
 
                 class Fraction {
                     public BigInteger num, den;
 
                     public Fraction(BigInteger x, BigInteger y) {
                         if (y.compareTo(BigInteger.ZERO) < 0) {
                             x = x.negate();
                             y = y.negate();
                         }
 
                         BigInteger gg = x.gcd(y);
                         if (gg.compareTo(BigInteger.ZERO) != 0) {
                             x = x.divide(gg);
                             y = y.divide(gg);
                         } else {
                             if (x.compareTo(BigInteger.ZERO) != 0) x = BigInteger.ONE;
                             if (y.compareTo(BigInteger.ZERO) != 0) y = BigInteger.ONE;
                         }
                         this.num = x;
                         this.den = y;
                     }
 
                     public Fraction multiply(int vv) {
                         return new Fraction(num.multiply(BigInteger.valueOf(vv)), den);
                     }
 
                     public Fraction divide(int vv) {
                         return new Fraction(num, den.multiply(BigInteger.valueOf(vv)));
                     }
 
                     public Fraction add(int vv) {
                         return new Fraction(num.add(den.multiply(BigInteger.valueOf(vv))), den);
                     }
 
                     public Fraction sub(int vv) {
                         return new Fraction(num.subtract(den.multiply(BigInteger.valueOf(vv))), den);
                     }
 
                     public int compareTo(Fraction other) {
                         return num.multiply(other.den).compareTo(other.num.multiply(den));
                     }
 
                     public String toString() {
                         return num + " " + den;
                     }
 
 
                     public boolean equals(Object o) {
                         if (this == o) return true;
                         if (o == null || getClass() != o.getClass()) return false;
 
                         Fraction fraction = (Fraction) o;
 
                         if (!num.equals(fraction.num)) return false;
                         return den.equals(fraction.den);
                     }
 
 
                     public int hashCode() {
                         int result = num.hashCode();
                         result = 31 * result + den.hashCode();
                         return result;
                     }
                 }
 
                 public Operation[] oo;
                 public int s, c;
 
                 public void read(InputReader in) {
                     s = in.nextInt();
                     c = in.nextInt();
                     oo = new Operation[c];
                     for (int i = 0; i < c; i++) {
                         oo[i] = new Operation(in.next().charAt(0), in.nextInt());
                     }
                 }
 
                 public Fraction comp(Fraction a, Fraction b, int way) {
                     if (a == null) return b;
                     if (a.compareTo(b) * way > 0) return a;
                     return b;
                 }
 
                 public Fraction res;
 
                 public void solve() {
 
                     HashSet<Fraction>[] ss = new HashSet[1 << c];
                     ss[0] = new HashSet<>();
                     ss[0].add(new Fraction(BigInteger.valueOf(s), BigInteger.ONE));
 
                     for (int mask = 1; mask < 1 << c; mask++) {
                         ss[mask] = new HashSet<>();
                         for (int op = 0; op < c; op++) {
                             if (((mask >> op) & 1) == 1) {
                                 int pmask = mask ^ (1 << op);
                                 int vv = oo[op].value;
                                 switch (oo[op].type) {
                                     case '+':
                                         for (Fraction ff : ss[pmask]) {
                                             ss[mask].add(ff.add(vv));
                                         }
                                         break;
                                     case '-':
                                         for (Fraction ff : ss[pmask]) {
                                             ss[mask].add(ff.sub(vv));
                                         }
                                         break;
                                     case '*':
                                         for (Fraction ff : ss[pmask]) {
                                             ss[mask].add(ff.multiply(vv));
                                         }
                                         break;
                                     case '/':
                                         for (Fraction ff : ss[pmask]) {
                                             ss[mask].add(ff.divide(vv));
                                         }
                                         break;
                                 }
                             }
                         }
                         ArrayList<Fraction> fs = new ArrayList<>(ss[mask]);
                         Collections.sort(fs, (a, b) -> a.compareTo(b));
                         ss[mask] = new HashSet<>();
                         for (int i = 0, j = fs.size() - 1; i <= j && i < 5; i++, j--) {
                             ss[mask].add(fs.get(i));
                             ss[mask].add(fs.get(j));
                         }
                     }
 
                     res = null;
                     for (Fraction ff : ss[(1 << c) - 1]) res = comp(res, ff, +1);
 
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.println("Case #" + testNumber + ": " + res);
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
 
         public String next() {
             int c;
             while (isSpaceChar(c = this.read())) {
                 ;
             }
 
             StringBuilder result = new StringBuilder();
             result.appendCodePoint(c);
 
             while (!isSpaceChar(c = this.read())) {
                 result.appendCodePoint(c);
             }
 
             return result.toString();
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
 }
 
