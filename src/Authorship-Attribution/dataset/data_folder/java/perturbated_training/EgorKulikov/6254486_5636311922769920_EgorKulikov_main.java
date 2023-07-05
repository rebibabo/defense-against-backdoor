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
 import java.util.NoSuchElementException;
 import java.io.OutputStream;
 import java.io.PrintWriter;
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
                 int k;
                 int c;
                 int s;
 
 
                 public void read(InputReader in) {
                     k = in.readInt();
                     c = in.readInt();
                     s = in.readInt();
                 }
 
                 LongList answer = new LongArrayList();
 
 
                 public void solve() {
                     if (c * s < k) {
                         answer = null;
                         return;
                     }
                     for (int i = 0; i < k; i += c) {
                         long current = 0;
                         for (int j = 0; j < c; j++) {
                             current *= k;
                             current += Math.min(i + j, k - 1);
                         }
                         answer.add(current + 1);
                     }
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.print("Case #" + testNumber + ": ");
                     if (answer == null) {
                         out.printLine("IMPOSSIBLE");
                     } else {
                         out.printLine(answer.toArray());
                     }
                 }
             }, 4);
         }
 
     }
 
     static interface LongList extends LongReversableCollection {
         public abstract long get(int index);
 
         public abstract void addAt(int index, long value);
 
         public abstract void removeAt(int index);
 
         default public LongIterator longIterator() {
             return new LongIterator() {
                 private int at;
                 private boolean removed;
 
                 public long value() {
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
 
 
         default public void add(long value) {
             addAt(size(), value);
         }
 
     }
 
     static abstract class LongAbstractStream implements LongStream {
 
         public String toString() {
             StringBuilder builder = new StringBuilder();
             boolean first = true;
             for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
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
             if (!(o instanceof LongStream)) {
                 return false;
             }
             LongStream c = (LongStream) o;
             LongIterator it = longIterator();
             LongIterator jt = c.longIterator();
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
             for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
                 result *= 31;
                 result += it.value();
             }
             return result;
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
 
     static class LongArrayList extends LongAbstractStream implements LongList {
         private int size;
         private long[] data;
 
         public LongArrayList() {
             this(3);
         }
 
         public LongArrayList(int capacity) {
             data = new long[capacity];
         }
 
         public LongArrayList(LongCollection c) {
             this(c.size());
             addAll(c);
         }
 
         public LongArrayList(LongStream c) {
             this();
             if (c instanceof LongCollection) {
                 ensureCapacity(((LongCollection) c).size());
             }
             addAll(c);
         }
 
         public LongArrayList(LongArrayList c) {
             size = c.size();
             data = c.data.clone();
         }
 
         public LongArrayList(long[] arr) {
             size = arr.length;
             data = arr.clone();
         }
 
         public int size() {
             return size;
         }
 
         public long get(int at) {
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
 
         public void addAt(int index, long value) {
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
 
         public void print(long[] array) {
             for (int i = 0; i < array.length; i++) {
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(array[i]);
             }
         }
 
         public void printLine(long[] array) {
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
 
     static interface LongCollection extends LongStream {
         public int size();
 
         default public void add(long value) {
             throw new UnsupportedOperationException();
         }
 
         default public long[] toArray() {
             int size = size();
             long[] array = new long[size];
             int i = 0;
             for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
                 array[i++] = it.value();
             }
             return array;
         }
 
         default public LongCollection addAll(LongStream values) {
             for (LongIterator it = values.longIterator(); it.isValid(); it.advance()) {
                 add(it.value());
             }
             return this;
         }
 
     }
 
     static interface LongReversableCollection extends LongCollection {
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static interface LongStream extends Iterable<Long>, Comparable<LongStream> {
         public LongIterator longIterator();
 
         default public Iterator<Long> iterator() {
             return new Iterator<Long>() {
                 private LongIterator it = longIterator();
 
                 public boolean hasNext() {
                     return it.isValid();
                 }
 
                 public Long next() {
                     long result = it.value();
                     it.advance();
                     return result;
                 }
             };
         }
 
         default public int compareTo(LongStream c) {
             LongIterator it = longIterator();
             LongIterator jt = c.longIterator();
             while (it.isValid() && jt.isValid()) {
                 long i = it.value();
                 long j = jt.value();
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
 
     static interface LongIterator {
         public long value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
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
 }
 
