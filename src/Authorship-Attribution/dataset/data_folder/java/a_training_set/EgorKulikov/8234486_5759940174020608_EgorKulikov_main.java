import java.io.BufferedWriter;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.util.Iterator;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.util.Arrays;
 import java.io.InputStream;
 import java.util.Random;
 import java.io.FilenameFilter;
 import java.util.Map;
 import java.util.Locale;
 import java.io.OutputStreamWriter;
 import java.io.PrintStream;
 import java.util.AbstractMap;
 import java.util.Comparator;
 import java.util.AbstractSet;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.Set;
 
 
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
 }
 
 class TaskC {
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
             int count;
             String[][] sentences;
             int answer = 0;
 
             public void read(InputReader in) {
                 count = in.readInt();
                 sentences = new String[count][];
                 for (int i = 0; i < count; i++) {
                     sentences[i] = in.readLine().split(" ");
                 }
             }
 
             public void solve() {
                 int[][] id = new int[count][];
                 Indexer<String> indexer = new Indexer<>();
                 for (int i = 0; i < count; i++) {
                     id[i] = new int[sentences[i].length];
                     for (int j = 0; j < id[i].length; j++) {
                         id[i][j] = indexer.get(sentences[i][j]);
                     }
                 }
                 answer = Integer.MAX_VALUE;
                 int[] type = new int[indexer.size()];
                 for (int i = 0; i < (1 << (count - 2)); i++) {
                     int mask = 2 + 4 * i;
                     Arrays.fill(type, 0);
                     for (int j = 0; j < count; j++) {
                         for (int k : id[j]) {
                             type[k] |= 1 << ((mask >> j) & 1);
                         }
                     }
                     answer = Math.min(answer, ArrayUtils.count(type, 3));
                 }
             }
 
             public void write(OutputWriter out, int testNumber) {
                 out.printLine("Case #" + testNumber + ":", answer);
             }
         }, 4);
     }
 }
 
 class InputReader {
 
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
 
    public InputReader(InputStream stream) {
        this.stream = stream;
    }
 
    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }
 
    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
 
    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }
 
    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    private String readLine0() {
        StringBuilder buf = new StringBuilder();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r')
                buf.appendCodePoint(c);
            c = read();
        }
        return buf.toString();
    }
 
    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0)
            s = readLine0();
        return s;
    }
 
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
 }
 
 class OutputWriter {
    private final PrintWriter writer;
 
    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }
 
    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }
 
    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }
 
    public void close() {
        writer.close();
    }
 
 }
 
 class Scheduler {
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
 
 interface Task {
    public void read(InputReader in);
    public void solve();
    public void write(OutputWriter out, int testNumber);
 }
 
 class Indexer<K> extends EHashMap<K, Integer> {
    private int index = 0;
 
    public Integer get(Object key) {
        if (!containsKey(key))
            put((K) key, index++);
        return super.get(key);
    }
 }
 
 class EHashMap<E, V> extends AbstractMap<E, V> {
    private static final int[] shifts = new int[10];
 
    private int size;
    private HashEntry<E, V>[] data;
    private int capacity;
    private Set<Entry<E, V>> entrySet;
 
    static {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++)
            shifts[i] = 1 + 3 * i + random.nextInt(3);
    }
 
    public EHashMap() {
        this(4);
    }
 
    private void setCapacity(int size) {
        capacity = Integer.highestOneBit(4 * size);
         data = new HashEntry[capacity];
    }
 
    public EHashMap(int maxSize) {
        setCapacity(maxSize);
        entrySet = new AbstractSet<Entry<E, V>>() {
            public Iterator<Entry<E, V>> iterator() {
                return new Iterator<Entry<E, V>>() {
                    private HashEntry<E, V> last = null;
                     private HashEntry<E, V> current = null;
                     private HashEntry<E, V> base = null;
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
                        if (!hasNext())
                            throw new NoSuchElementException();
                         last = current;
                         lastIndex = index;
                         current = current.next;
                         if (base.next != last)
                             base = base.next;
                        return last;
                    }
 
                    public void remove() {
                        if (last == null)
                            throw new IllegalStateException();
                         size--;
                         if (base == last)
                             data[lastIndex] = last.next;
                         else
                             base.next = last.next;
                    }
                };
            }
 
            public int size() {
                return size;
            }
        };
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
        for (int i : shifts)
            result ^= h >>> i;
        return result;
    }
 
    public V remove(Object o) {
        if (o == null)
            return null;
        int index = index(o);
         HashEntry<E, V> current = data[index];
         HashEntry<E, V> last = null;
         while (current != null) {
             if (current.key.equals(o)) {
                 if (last == null)
                     data[index] = current.next;
                 else
                     last.next = current.next;
                 size--;
                 return current.value;
             }
             last = current;
             current = current.next;
         }
         return null;
    }
 
    public V put(E e, V value) {
        if (e == null)
            return null;
        int index = index(e);
         HashEntry<E, V> current = data[index];
         if (current != null) {
             while (true) {
                 if (current.key.equals(e)) {
                     V oldValue = current.value;
                     current.value = value;
                     return oldValue;
                 }
                 if (current.next == null)
                     break;
                 current = current.next;
             }
         }
         if (current == null)
             data[index] = new HashEntry<E, V>(e, value);
         else
             current.next = new HashEntry<E, V>(e, value);
         size++;
         if (2 * size > capacity) {
             HashEntry<E, V>[] oldData = data;
             setCapacity(size);
             for (HashEntry<E, V> entry : oldData) {
                 while (entry != null) {
                     HashEntry<E, V> next = entry.next;
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
        if (o == null)
            return null;
        int index = index(o);
         HashEntry<E, V> current = data[index];
        while (current != null) {
            if (current.key.equals(o))
                 return current.value;
             current = current.next;
         }
        return null;
    }
 
    public boolean containsKey(Object o) {
         if (o == null)
             return false;
         int index = index(o);
         HashEntry<E, V> current = data[index];
         while (current != null) {
             if (current.key.equals(o))
                 return true;
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
         private HashEntry<E, V> next;
 
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
 
 class ArrayUtils {
 
    public static int count(int[] array, int value) {
        int result = 0;
        for (int i : array) {
            if (i == value)
                result++;
        }
        return result;
    }
 
 }
 
 interface TaskFactory {
    public Task newTask();
 }
 
