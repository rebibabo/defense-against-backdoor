import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.OutputStreamWriter;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.PrintStream;
 import java.io.BufferedWriter;
 import java.util.Collection;
 import java.io.IOException;
 import java.io.Writer;
 import java.util.Queue;
 import java.util.ArrayDeque;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "F-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("f.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskF solver = new TaskF();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskF {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 int n;
                 long[] x;
                 long[] y;
                 long[] z;
 
 
                 public void read(InputReader in) {
                     n = in.readInt();
                     x = new long[n + 2];
                     y = new long[n + 2];
                     z = new long[n + 2];
                     IOUtils.readLongArrays(in, x, y, z);
                 }
 
                 int answer;
 
 
                 public void solve() {
                     answer = -1;
                     int[][] can = new int[n][7000];
                     int[][] dist = new int[n + 2][n + 2];
                     for (int i = 0; i < n + 2; i++) {
                         for (int j = 0; j < n + 2; j++) {
                             dist[i][j] = (int) (Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]) + Math.abs(z[i] - z[j]));
                         }
                     }
                     ArrayUtils.fill(can, -1);
                     Queue<IntIntPair> queue = new ArrayDeque<>();
                     for (int i = 0; i < n; i++) {
                         can[i][dist[i + 2][0]] = 1;
                         queue.add(new IntIntPair(i, dist[i + 2][0]));
                     }
                     while (!queue.isEmpty()) {
                         int id = queue.peek().first;
                         int dst = queue.poll().second;
                         if (dist[id + 2][1] == dst) {
                             answer = can[id][dst];
                             return;
                         }
                         for (int j = 0; j < n; j++) {
                             if (id == j) {
                                 continue;
                             }
                             int min = Math.abs(dist[id + 2][j + 2] - dst);
                             int max = dist[id + 2][j + 2] + dst;
                             min = Math.min(min, 6999);
                             max = Math.min(max, 6999);
                             for (int k = min; k <= max; k++) {
                                 if (can[j][k] == -1) {
                                     can[j][k] = can[id][dst] + 1;
                                     queue.add(new IntIntPair(j, k));
                                 }
                             }
                         }
                     }
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":", answer == -1 ? "IMPOSSIBLE" : answer);
                 }
             }, 23);
         }
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
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
 
     static class ArrayUtils {
         public static void fill(int[][] array, int value) {
             for (int[] row : array) {
                 Arrays.fill(row, value);
             }
         }
 
     }
 
     static class IntIntPair implements Comparable<IntIntPair> {
         public final int first;
         public final int second;
 
         public IntIntPair(int first, int second) {
             this.first = first;
             this.second = second;
         }
 
 
         public boolean equals(Object o) {
             if (this == o) {
                 return true;
             }
             if (o == null || getClass() != o.getClass()) {
                 return false;
             }
 
             IntIntPair pair = (IntIntPair) o;
 
             return first == pair.first && second == pair.second;
         }
 
 
         public int hashCode() {
             int result = first;
             result = 31 * result + second;
             return result;
         }
 
 
         public String toString() {
             return "(" + first + "," + second + ")";
         }
 
         @SuppressWarnings({"unchecked"})
         public int compareTo(IntIntPair o) {
             int value = Integer.compare(first, o.first);
             if (value != 0) {
                 return value;
             }
             return Integer.compare(second, o.second);
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private InputReader.SpaceCharFilter filter;
 
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
 
         public long readLong() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             long res = 0;
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
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static class IOUtils {
         public static void readLongArrays(InputReader in, long[]... arrays) {
             for (int i = 0; i < arrays[0].length; i++) {
                 for (int j = 0; j < arrays.length; j++) {
                     arrays[j][i] = in.readLong();
                 }
             }
         }
 
     }
 }
 
