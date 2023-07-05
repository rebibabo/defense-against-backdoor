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
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
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
             final String regex = "C-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified()) {
                     toRun = candidate;
                 }
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
                 int n;
                 int j;
 
 
                 public void read(InputReader in) {
                     n = in.readInt();
                     j = in.readInt();
                 }
 
                 List<String> coins = new ArrayList<>();
                 List<int[]> proofs = new ArrayList<>();
 
 
                 public void solve() {
                     int[] proof = new int[9];
                     for (int i = 0; j > 0; i++) {
                         String current = "1";
                         for (int k = 0; k < n - 2; k++) {
                             if ((i >> k & 1) == 1) {
                                 current += "1";
                             } else {
                                 current += "0";
                             }
                         }
                         current += "1";
                         boolean good = true;
                         for (int b = 2; b <= 10; b++) {
                             BigInteger number = new BigInteger(current, b);
                             boolean found = false;
                             for (int k = 2; k <= 97; k++) {
                                 BigInteger bigK = BigInteger.valueOf(k);
                                 if (number.equals(bigK)) {
                                     good = false;
                                     break;
                                 }
                                 if (number.remainder(bigK).equals(BigInteger.ZERO)) {
                                     found = true;
                                     proof[b - 2] = k;
                                     break;
                                 }
                             }
                             if (!found) {
                                 good = false;
                                 break;
                             }
                         }
                         if (good) {
                             coins.add(current);
                             proofs.add(proof.clone());
                             j--;
                         }
                     }
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":");
                     for (int i = 0; i < coins.size(); i++) {
                         out.print(coins.get(i) + " ");
                         out.printLine(proofs.get(i));
                     }
                 }
             }, 4);
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private SpaceCharFilter filter;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (numChars == -1) {
                 throw new InputMismatchException();
             }
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0) {
                     return -1;
                 }
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
         }
 
     }
 
     static class Scheduler {
         private final AtomicInteger testsRemaining;
         private final AtomicInteger threadsRemaining;
 
         public Scheduler(InputReader in, OutputWriter out, TaskFactory factory, int numParallel) {
             try {
                 testsRemaining = new AtomicInteger(in.readInt());
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
 
         public void printLine(int[] array) {
             print(array);
             writer.println();
         }
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 }
 
