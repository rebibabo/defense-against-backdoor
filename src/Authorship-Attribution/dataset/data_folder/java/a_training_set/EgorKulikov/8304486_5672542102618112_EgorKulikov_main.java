import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.util.Arrays;
 import java.util.AbstractSet;
 import java.util.Random;
 import java.util.InputMismatchException;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.util.Map;
 import java.io.OutputStreamWriter;
 import java.util.NoSuchElementException;
 import java.io.PrintStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.Iterator;
 import java.io.BufferedWriter;
 import java.util.Set;
 import java.io.IOException;
 import java.util.AbstractMap;
 import java.io.Writer;
 import java.util.Map.Entry;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "A-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("a.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskA solver = new TaskA();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskA {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             final Counter<Integer>[] result = new Counter[10];
             long[][] c = IntegerUtils.generateBinomialCoefficients(10);
             for (int dig = 1; dig <= 5; dig++) {
                 System.err.println("Generating " + dig);
                 result[dig] = new Counter<>();
                 int[] qty = new int[dig + 1];
                 int upTo = (int) IntegerUtils.power(10, dig);
                 for (int i = 1; i < upTo; i++) {
                     if (i % (upTo / 10) == 0) {
                         System.err.println("Done " + i);
                     }
                     boolean first = true;
                     int j = i;
                     long numWays = 1;
                     while (true) {
                         Arrays.fill(qty, 0);
                         int start = j;
                         boolean good = true;
                         int last = 0;
                         while (j != 0) {
                             if (first && j % 10 > dig) {
                                 good = false;
                                 break;
                             }
                             int cur = j % 10;
 
 
 
 
                             last = cur;
                             qty[cur]++;
                             j /= 10;
                         }
                         if (!good) {
                             break;
                         }
 
 
 
 
 
 
 
 
                         int current = 0;
                         for (int k = 1; k <= dig; k++) {
                             current *= 10;
                             current += qty[k];
                         }
                         if (current == start) {
                             break;
                         }
                         result[dig].add(current, numWays);
                         j = current;
                         first = false;
                     }
                 }
             }
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 String g;
 
 
                 public void read(InputReader in) {
                     g = in.readString();
                 }
 
                 long answer = 0;
 
 
                 public void solve() {
                     answer = 1 + result[g.length()].get(Integer.parseInt(g));
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
 
     static class IntegerUtils {
         public static long[][] generateBinomialCoefficients(int n) {
             long[][] result = new long[n + 1][n + 1];
             for (int i = 0; i <= n; i++) {
                 result[i][0] = 1;
                 for (int j = 1; j <= i; j++) {
                     result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
                 }
             }
             return result;
         }
 
         public static long power(long base, long exponent) {
             if (exponent == 0) {
                 return 1;
             }
             long result = power(base, exponent >> 1);
             result = result * result;
             if ((exponent & 1) != 0) {
                 result = result * base;
             }
             return result;
         }
 
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
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c)) {
                     res.appendCodePoint(c);
                 }
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
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
 
     static class Counter<K> extends EHashMap<K, Long> {
         public Counter() {
             super();
         }
 
         public Counter(int capacity) {
             super(capacity);
         }
 
         public void add(K key, long delta) {
             put(key, get(key) + delta);
         }
 
 
         public Long get(Object key) {
             if (containsKey(key)) {
                 return super.get(key);
             }
             return 0L;
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
 
     static class EHashMap<E, V> extends AbstractMap<E, V> {
         private static final int[] shifts = new int[10];
         private int size;
         private EHashMap.HashEntry<E, V>[] data;
         private int capacity;
         private Set<Entry<E, V>> entrySet;
 
         static {
             Random random = new Random(System.currentTimeMillis());
             for (int i = 0; i < 10; i++) {
                 shifts[i] = 1 + 3 * i + random.nextInt(3);
             }
         }
 
         public EHashMap() {
             this(4);
         }
 
         private void setCapacity(int size) {
             capacity = Integer.highestOneBit(4 * size);
             
             data = new EHashMap.HashEntry[capacity];
         }
 
         public EHashMap(int maxSize) {
             setCapacity(maxSize);
             entrySet = new AbstractSet<Entry<E, V>>() {
 
                 public Iterator<Entry<E, V>> iterator() {
                     return new Iterator<Entry<E, V>>() {
                         private EHashMap.HashEntry<E, V> last = null;
                         private EHashMap.HashEntry<E, V> current = null;
                         private EHashMap.HashEntry<E, V> base = null;
                         private int lastIndex = -1;
                         private int index = -1;
 
                         public boolean hasNext() {
                             if (current == null) {
                                 for (index++; index < capacity; index++) {
                                     if (data[index] != null) {
                                         base = current = data[index];
                                         break;
                                     }
                                 }
                             }
                             return current != null;
                         }
 
                         public Entry<E, V> next() {
                             if (!hasNext()) {
                                 throw new NoSuchElementException();
                             }
                             last = current;
                             lastIndex = index;
                             current = current.next;
                             if (base.next != last) {
                                 base = base.next;
                             }
                             return last;
                         }
 
                         public void remove() {
                             if (last == null) {
                                 throw new IllegalStateException();
                             }
                             size--;
                             if (base == last) {
                                 data[lastIndex] = last.next;
                             } else {
                                 base.next = last.next;
                             }
                         }
                     };
                 }
 
 
                 public int size() {
                     return size;
                 }
             };
         }
 
         public EHashMap(Map<E, V> map) {
             this(map.size());
             putAll(map);
         }
 
 
         public Set<Entry<E, V>> entrySet() {
             return entrySet;
         }
 
 
         public void clear() {
             Arrays.fill(data, null);
             size = 0;
         }
 
         private int index(Object o) {
             return getHash(o.hashCode()) & (capacity - 1);
         }
 
         private int getHash(int h) {
             int result = h;
             for (int i : shifts) {
                 result ^= h >>> i;
             }
             return result;
         }
 
 
         public V remove(Object o) {
             if (o == null) {
                 return null;
             }
             int index = index(o);
             EHashMap.HashEntry<E, V> current = data[index];
             EHashMap.HashEntry<E, V> last = null;
             while (current != null) {
                 if (current.key.equals(o)) {
                     if (last == null) {
                         data[index] = current.next;
                     } else {
                         last.next = current.next;
                     }
                     size--;
                     return current.value;
                 }
                 last = current;
                 current = current.next;
             }
             return null;
         }
 
 
         public V put(E e, V value) {
             if (e == null) {
                 return null;
             }
             int index = index(e);
             EHashMap.HashEntry<E, V> current = data[index];
             if (current != null) {
                 while (true) {
                     if (current.key.equals(e)) {
                         V oldValue = current.value;
                         current.value = value;
                         return oldValue;
                     }
                     if (current.next == null) {
                         break;
                     }
                     current = current.next;
                 }
             }
             if (current == null) {
                 data[index] = new EHashMap.HashEntry<E, V>(e, value);
             } else {
                 current.next = new EHashMap.HashEntry<E, V>(e, value);
             }
             size++;
             if (2 * size > capacity) {
                 EHashMap.HashEntry<E, V>[] oldData = data;
                 setCapacity(size);
                 for (EHashMap.HashEntry<E, V> entry : oldData) {
                     while (entry != null) {
                         EHashMap.HashEntry<E, V> next = entry.next;
                         index = index(entry.key);
                         entry.next = data[index];
                         data[index] = entry;
                         entry = next;
                     }
                 }
             }
             return null;
         }
 
 
         public V get(Object o) {
             if (o == null) {
                 return null;
             }
             int index = index(o);
             EHashMap.HashEntry<E, V> current = data[index];
             while (current != null) {
                 if (current.key.equals(o)) {
                     return current.value;
                 }
                 current = current.next;
             }
             return null;
         }
 
 
         public boolean containsKey(Object o) {
             if (o == null) {
                 return false;
             }
             int index = index(o);
             EHashMap.HashEntry<E, V> current = data[index];
             while (current != null) {
                 if (current.key.equals(o)) {
                     return true;
                 }
                 current = current.next;
             }
             return false;
         }
 
 
         public int size() {
             return size;
         }
 
         private static class HashEntry<E, V> implements Entry<E, V> {
             private final E key;
             private V value;
             private EHashMap.HashEntry<E, V> next;
 
             private HashEntry(E key, V value) {
                 this.key = key;
                 this.value = value;
             }
 
             public E getKey() {
                 return key;
             }
 
             public V getValue() {
                 return value;
             }
 
             public V setValue(V value) {
                 V oldValue = this.value;
                 this.value = value;
                 return oldValue;
             }
 
         }
 
     }
 }
 
