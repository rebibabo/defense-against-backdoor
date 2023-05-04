import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.util.InputMismatchException;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.OutputStreamWriter;
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
                 int c;
                 int[] e;
                 int[] l;
                 int[] d;
 
 
                 public void read(InputReader in) {
                     c = in.readInt();
                     e = new int[2 * c];
                     l = new int[2 * c];
                     d = new int[2 * c];
                     IOUtils.readIntArrays(in, e, l, d);
                 }
 
                 long answer;
 
 
                 public void solve() {
                     MiscUtils.decreaseByOne(e);
                     answer = ArrayUtils.sumArray(d);
                     int[] next = new int[2 * c];
                     int[] dif = new int[c];
                     for (int i = 0; i < c; i++) {
                         int e1 = -1;
                         int e2 = -1;
                         for (int j = 0; j < 2 * c; j++) {
                             if (e[j] == i) {
                                 if (e1 == -1) {
                                     e1 = j;
                                 } else {
                                     e2 = j;
                                 }
                             }
                         }
                         int t1 = 0;
                         int t2 = 0;
                         if (i == 0) {
                             t1 = Math.min(l[0] + wait(e1, 1), l[1] + wait(e2, 0));
                             t2 = Math.min(l[1] + wait(e1, 0), l[0] + wait(e2, 1));
                         } else {
                             t2 = wait(e1, 2 * i) + wait(e2, 2 * i + 1);
                             t1 = wait(e1, 2 * i + 1) + wait(e2, 2 * i);
                         }
                         if (t1 < t2) {
                             answer += t1;
                             next[e1] = 2 * i + 1;
                             next[e2] = 2 * i;
                             dif[i] = t2 - t1;
                         } else {
                             answer += t2;
                             next[e2] = 2 * i + 1;
                             next[e1] = 2 * i;
                             dif[i] = t1 - t2;
                         }
                     }
                     IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(2 * c);
                     for (int i = 0; i < 2 * c; i++) {
                         setSystem.join(i, next[i]);
                     }
                     while (setSystem.getSetCount() > 1) {
                         int best = -1;
                         int delta = Integer.MAX_VALUE;
                         for (int i = 0; i < c; i++) {
                             if (setSystem.get(2 * i) != setSystem.get(2 * i + 1) && dif[i] < delta) {
                                 best = i;
                                 delta = dif[i];
                             }
                         }
                         answer += delta;
                         setSystem.join(2 * best, 2 * best + 1);
                     }
                 }
 
                 int wait(int from, int to) {
                     int end = (l[from] + d[from]) % 24;
                     int start = l[to];
                     if (end <= start) {
                         return start - end;
                     }
                     return start - end + 24;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":", answer);
                 }
             }, 4);
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static class IntArray extends IntAbstractStream implements IntList {
         private int[] data;
 
         public IntArray(int[] arr) {
             data = arr;
         }
 
         public int size() {
             return data.length;
         }
 
         public int get(int at) {
             return data[at];
         }
 
         public void removeAt(int index) {
             throw new UnsupportedOperationException();
         }
 
     }
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
     }
 
     static interface IntList extends IntReversableCollection {
         public abstract int get(int index);
 
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
 
     static class ArrayUtils {
         public static long sumArray(int[] array) {
             return new IntArray(array).sum();
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
 
         default public long sum() {
             long result = 0;
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 result += it.value();
             }
             return result;
         }
 
     }
 
     static interface IntCollection extends IntStream {
         public int size();
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
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
 
     static class RecursiveIndependentSetSystem implements IndependentSetSystem {
         private final int[] color;
         private final int[] rank;
         private int setCount;
         private IndependentSetSystem.Listener listener;
 
         public RecursiveIndependentSetSystem(int size) {
             color = new int[size];
             rank = new int[size];
             for (int i = 0; i < size; i++) {
                 color[i] = i;
             }
             setCount = size;
         }
 
         public RecursiveIndependentSetSystem(RecursiveIndependentSetSystem other) {
             color = other.color.clone();
             rank = other.rank.clone();
             setCount = other.setCount;
         }
 
         public boolean join(int first, int second) {
             first = get(first);
             second = get(second);
             if (first == second) {
                 return false;
             }
             if (rank[first] < rank[second]) {
                 int temp = first;
                 first = second;
                 second = temp;
             } else if (rank[first] == rank[second]) {
                 rank[first]++;
             }
             setCount--;
             color[second] = first;
             if (listener != null) {
                 listener.joined(second, first);
             }
             return true;
         }
 
         public int get(int index) {
             int start = index;
             while (color[index] != index) {
                 index = color[index];
             }
             while (start != index) {
                 int next = color[start];
                 color[start] = index;
                 start = next;
             }
             return index;
         }
 
         public int getSetCount() {
             return setCount;
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
 
     static interface IndependentSetSystem {
         public boolean join(int first, int second);
 
         public int get(int index);
 
         public int getSetCount();
 
         public static interface Listener {
             public void joined(int joinedRoot, int root);
 
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
 
     static interface IntReversableCollection extends IntCollection {
     }
 
     static class IOUtils {
         public static void readIntArrays(InputReader in, int[]... arrays) {
             for (int i = 0; i < arrays[0].length; i++) {
                 for (int j = 0; j < arrays.length; j++) {
                     arrays[j][i] = in.readInt();
                 }
             }
         }
 
     }
 
     static class MiscUtils {
         public static void decreaseByOne(int[]... arrays) {
             for (int[] array : arrays) {
                 for (int i = 0; i < array.length; i++) {
                     array[i]--;
                 }
             }
         }
 
     }
 }
 
