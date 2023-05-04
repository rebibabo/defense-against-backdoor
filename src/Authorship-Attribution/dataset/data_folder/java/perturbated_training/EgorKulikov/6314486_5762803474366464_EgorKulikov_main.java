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
 import java.math.BigInteger;
 import java.util.NoSuchElementException;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.PrintStream;
 import java.util.Iterator;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.io.Writer;
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
                 int n;
                 long[] x;
                 long[] y;
                 long[] z;
 
 
                 public void read(InputReader in) {
                     n = in.readInt();
                     x = new long[n];
                     y = new long[n];
                     z = new long[n];
                     IOUtils.readLongArrays(in, x, y, z);
                 }
 
                 boolean answer;
 
 
                 public void solve() {
                     answer = true;
                     for (int i = 0; i < n; i++) {
                         for (int j = 0; j < i; j++) {
                             long a = y[j] * z[i] - z[j] * y[i];
                             long b = -z[i] * x[j] + z[j] * x[i];
                             long c = y[i] * x[j] - y[j] * x[i];
                             if (a == 0 && b == 0 && c == 0) {
                                 if (Long.signum(x[i]) != Long.signum(x[j])) {
                                     return;
                                 }
                                 if (Long.signum(y[i]) != Long.signum(y[j])) {
                                     return;
                                 }
                                 if (Long.signum(z[i]) != Long.signum(z[j])) {
                                     return;
                                 }
                             }
 
                         }
                     }
                     for (int i = 0; i < n; i++) {
                         for (int j = 0; j < i; j++) {
                             long a = y[j] * z[i] - z[j] * y[i];
                             long b = -z[i] * x[j] + z[j] * x[i];
                             long c = y[i] * x[j] - y[j] * x[i];
                             if (a == 0 && b == 0 && c == 0) {
                                 continue;
                             }
                             if (a * x[i] + b * y[i] + c * z[i] != 0) {
                                 throw new RuntimeException();
                             }
                             if (a * x[j] + b * y[j] + c * z[j] != 0) {
                                 throw new RuntimeException();
                             }
                             boolean up = false;
                             boolean down = false;
                             IntList touch = new IntArrayList();
                             boolean willDoLater = false;
                             for (int k = 0; k < n; k++) {
                                 if (i == k || j == k || same(i, k) || same(j, k)) {
                                     continue;
                                 }
                                 long value = x[k] * a + y[k] * b + z[k] * c;
                                 if (value > 0) {
                                     up = true;
                                 }
                                 if (value < 0) {
                                     down = true;
                                 }
                                 if (value == 0) {
                                     if (k > j) {
                                         willDoLater = true;
                                     } else {
                                         touch.add(k);
                                     }
                                 }
                             }
                             if ((!up || !down) && touch.isEmpty() && !willDoLater) {
                                 answer = false;
                                 return;
                             }
                             if (willDoLater || touch.isEmpty()) {
                                 continue;
                             }
                             if (up && down) {
                                 continue;
                             }
                             touch.add(i);
                             touch.add(j);
                             for (int k = 0; k < touch.size(); k++) {
                                 int id = touch.get(k);
                                 BigInteger a0 = BigInteger.valueOf(y[id] * c - z[id] * b);
                                 BigInteger b0 = BigInteger.valueOf(-c * x[id] + z[id] * a);
                                 BigInteger c0 = BigInteger.valueOf(b * x[id] - y[id] * a);
                                 up = false;
                                 down = false;
                                 for (int l = 0; l < touch.size(); l++) {
                                     if (k == l) {
                                         continue;
                                     }
                                     id = touch.get(l);
                                     BigInteger value = a0.multiply(BigInteger.valueOf(x[id])).add(b0.multiply(BigInteger
                                             .valueOf(y[id]))).add(c0.multiply(BigInteger.valueOf(z[id])));
                                     if (value.signum() > 0) {
                                         up = true;
                                     }
                                     if (value.signum() < 0) {
                                         down = true;
                                     }
                                 }
                                 if (!up || !down) {
                                     answer = false;
                                     return;
                                 }
                             }
                         }
                     }
                 }
 
                 private boolean same(int i, int j) {
                     long a = y[j] * z[i] - z[j] * y[i];
                     long b = -z[i] * x[j] + z[j] * x[i];
                     long c = y[i] * x[j] - y[j] * x[i];
                     return a == 0 && b == 0 && c == 0;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":", answer ? "YES" : "NO");
                 }
             }, 23);
         }
 
     }
 
     static abstract class IntAbstractStream implements IntStream {
 
         public String toString() {
             StringBuilder builder = new StringBuilder();
             boolean first = true;
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 if (first) {
                     first = false;
                 } else {
                     builder.append(' ');
                 }
                 builder.append(it.value());
             }
             return builder.toString();
         }
 
 
         public boolean equals(Object o) {
             if (!(o instanceof IntStream)) {
                 return false;
             }
             IntStream c = (IntStream) o;
             IntIterator it = intIterator();
             IntIterator jt = c.intIterator();
             while (it.isValid() && jt.isValid()) {
                 if (it.value() != jt.value()) {
                     return false;
                 }
                 it.advance();
                 jt.advance();
             }
             return !it.isValid() && !jt.isValid();
         }
 
 
         public int hashCode() {
             int result = 0;
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 result *= 31;
                 result += it.value();
             }
             return result;
         }
 
     }
 
     static class IntArrayList extends IntAbstractStream implements IntList {
         private int size;
         private int[] data;
 
         public IntArrayList() {
             this(3);
         }
 
         public IntArrayList(int capacity) {
             data = new int[capacity];
         }
 
         public IntArrayList(IntCollection c) {
             this(c.size());
             addAll(c);
         }
 
         public IntArrayList(IntStream c) {
             this();
             if (c instanceof IntCollection) {
                 ensureCapacity(((IntCollection) c).size());
             }
             addAll(c);
         }
 
         public IntArrayList(IntArrayList c) {
             size = c.size();
             data = c.data.clone();
         }
 
         public IntArrayList(int[] arr) {
             size = arr.length;
             data = arr.clone();
         }
 
         public int size() {
             return size;
         }
 
         public int get(int at) {
             if (at >= size) {
                 throw new IndexOutOfBoundsException("at = " + at + ", size = " + size);
             }
             return data[at];
         }
 
         private void ensureCapacity(int capacity) {
             if (data.length >= capacity) {
                 return;
             }
             capacity = Math.max(2 * data.length, capacity);
             data = Arrays.copyOf(data, capacity);
         }
 
         public void addAt(int index, int value) {
             ensureCapacity(size + 1);
             if (index > size || index < 0) {
                 throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
             }
             if (index != size) {
                 System.arraycopy(data, index, data, index + 1, size - index);
             }
             data[index] = value;
             size++;
         }
 
         public void removeAt(int index) {
             if (index >= size || index < 0) {
                 throw new IndexOutOfBoundsException("at = " + index + ", size = " + size);
             }
             if (index != size - 1) {
                 System.arraycopy(data, index + 1, data, index, size - index - 1);
             }
             size--;
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
 
     static interface IntStream extends Iterable<Integer>, Comparable<IntStream> {
         public IntIterator intIterator();
 
         default public Iterator<Integer> iterator() {
             return new Iterator<Integer>() {
                 private IntIterator it = intIterator();
 
                 public boolean hasNext() {
                     return it.isValid();
                 }
 
                 public Integer next() {
                     int result = it.value();
                     it.advance();
                     return result;
                 }
             };
         }
 
         default public int compareTo(IntStream c) {
             IntIterator it = intIterator();
             IntIterator jt = c.intIterator();
             while (it.isValid() && jt.isValid()) {
                 int i = it.value();
                 int j = jt.value();
                 if (i < j) {
                     return -1;
                 } else if (i > j) {
                     return 1;
                 }
                 it.advance();
                 jt.advance();
             }
             if (it.isValid()) {
                 return 1;
             }
             if (jt.isValid()) {
                 return -1;
             }
             return 0;
         }
 
     }
 
     static interface IntCollection extends IntStream {
         public int size();
 
         default public boolean isEmpty() {
             return size() == 0;
         }
 
         default public void add(int value) {
             throw new UnsupportedOperationException();
         }
 
         default public IntCollection addAll(IntStream values) {
             for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
                 add(it.value());
             }
             return this;
         }
 
     }
 
     static interface IntReversableCollection extends IntCollection {
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
 
     static interface IntList extends IntReversableCollection {
         public abstract int get(int index);
 
         public abstract void addAt(int index, int value);
 
         public abstract void removeAt(int index);
 
         default public IntIterator intIterator() {
             return new IntIterator() {
                 private int at;
                 private boolean removed;
 
                 public int value() {
                     if (removed) {
                         throw new IllegalStateException();
                     }
                     return get(at);
                 }
 
                 public boolean advance() {
                     at++;
                     removed = false;
                     return isValid();
                 }
 
                 public boolean isValid() {
                     return !removed && at < size();
                 }
 
                 public void remove() {
                     removeAt(at);
                     at--;
                     removed = true;
                 }
             };
         }
 
 
         default public void add(int value) {
             addAt(size(), value);
         }
 
     }
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
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
 }
 
