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
             final String regex = "E-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("e.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskE solver = new TaskE();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskE {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             int p = in.readInt();
             int[][] v = new int[p][];
             int[][] s = new int[p][];
             for (int i = 0; i < p; i++) {
                 int c = in.readInt();
                 v[i] = new int[c];
                 s[i] = new int[c];
                 IOUtils.readIntArrays(in, v[i], s[i]);
                 ArrayUtils.reverse(v[i]);
                 ArrayUtils.reverse(s[i]);
                 MiscUtils.decreaseByOne(v[i], s[i]);
             }
 
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 int n;
                 int c;
                 int[] ids;
 
 
                 public void read(InputReader in) {
                     n = in.readInt();
                     c = in.readInt();
                     ids = IOUtils.readIntArray(in, n);
                 }
 
                 int[][] vv;
                 int[][] ss;
                 boolean answer;
 
 
                 public void solve() {
                     vv = new int[n][];
                     ss = new int[n][];
                     for (int i = 0; i < n; i++) {
                         vv[i] = v[ids[i]];
                         ss[i] = s[ids[i]];
                     }
                     TaskE.State start =
                             new TaskE.State(ArrayUtils.createArray(n, c - 1), ArrayUtils.createArray(4, -1), 0);
                     answer = go(start);
                 }
 
                 private boolean go(TaskE.State start) {
                     while (true) {
                         boolean updated = false;
                         for (int i = 0; i < n; i++) {
                             int id = start.at[i];
                             if (id != -1) {
                                 if (start.inFree[ss[i][id]] != -1 && start.inFree[ss[i][id]] < vv[i][id]) {
                                     start.freeRemaining++;
                                     start.inFree[ss[i][id]] = -1;
                                     updated = true;
                                 } else if (start.inFree[ss[i][id]] > vv[i][id]) {
                                     start.at[i]--;
                                     if (start.at[i] == -1) {
                                         start.freeRemaining++;
                                     }
                                     updated = true;
                                 } else {
                                     for (int j = 0; j < n; j++) {
                                         int jd = start.at[j];
                                         if (jd != -1 && ss[i][id] == ss[j][jd] && vv[i][id] > vv[j][jd]) {
                                             start.at[j]--;
                                             if (start.at[j] == -1) {
                                                 start.freeRemaining++;
                                             }
                                             updated = true;
                                         }
                                     }
                                 }
                             }
                         }
                         if (!updated) {
                             break;
                         }
                     }
                     if (ArrayUtils.maxElement(start.at) <= 0) {
                         return true;
                     }
                     if (start.freeRemaining == 0) {
                         return false;
                     }
                     for (int i = 0; i < n; i++) {
                         int id = start.at[i];
                         if (id > 0) {
                             TaskE.State next = start.copy();
                             next.at[i]--;
                             next.inFree[ss[i][id]] = vv[i][id];
                             next.freeRemaining--;
                             if (go(next)) {
                                 return true;
                             }
                         }
                     }
                     return false;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":", answer ? "POSSIBLE" : "IMPOSSIBLE");
                 }
             }, 23);
         }
 
         static class State {
             int[] at;
             int[] inFree;
             int freeRemaining;
 
             public State(int[] at, int[] inFree, int freeRemaining) {
                 this.at = at;
                 this.inFree = inFree;
                 this.freeRemaining = freeRemaining;
             }
 
             public TaskE.State copy() {
                 return new TaskE.State(at.clone(), inFree.clone(), freeRemaining);
             }
 
         }
 
     }
 
     static class ArrayUtils {
         public static int maxElement(int[] array) {
             return new IntArray(array).max();
         }
 
         public static void reverse(int[] array) {
             new IntArray(array).inPlaceReverse();
         }
 
         public static int[] createArray(int count, int value) {
             int[] array = new int[count];
             Arrays.fill(array, value);
             return array;
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
 
         default public int max() {
             int result = Integer.MIN_VALUE;
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 int current = it.value();
                 if (current > result) {
                     result = current;
                 }
             }
             return result;
         }
 
     }
 
     static class IOUtils {
         public static int[] readIntArray(InputReader in, int size) {
             int[] array = new int[size];
             for (int i = 0; i < size; i++) {
                 array[i] = in.readInt();
             }
             return array;
         }
 
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
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
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
 
         public void set(int index, int value) {
             data[index] = value;
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
 
     static interface IntList extends IntReversableCollection {
         public abstract int get(int index);
 
         public abstract void set(int index, int value);
 
         public abstract void removeAt(int index);
 
         default public void swap(int first, int second) {
             if (first == second) {
                 return;
             }
             int temp = get(first);
             set(first, get(second));
             set(second, temp);
         }
 
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
 
         default public void inPlaceReverse() {
             for (int i = 0, j = size() - 1; i < j; i++, j--) {
                 swap(i, j);
             }
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
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
     }
 
     static interface IntCollection extends IntStream {
         public int size();
 
     }
 
     static interface IntReversableCollection extends IntCollection {
     }
 }
 
