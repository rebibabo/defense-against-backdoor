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
 import java.util.Random;
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
             final String regex = "B-(small|large).*[.]in";
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
                 int f;
                 int p;
                 int[] a;
                 int[] b;
 
 
                 public void read(InputReader in) {
                     f = in.readInt();
                     p = in.readInt();
                     a = new int[p];
                     b = new int[p];
                     IOUtils.readIntArrays(in, a, b);
                 }
 
                 int[] answer;
 
                 Graph graph;
                 boolean[] was;
 
 
                 public void solve() {
                     MiscUtils.decreaseByOne(a, b);
                     answer = new int[p];
                     graph = BidirectionalGraph.createGraph(f, a, b);
                     was = new boolean[f];
                     for (int i = 0; i < p; i++) {
                         if (answer[i] != 0) {
                             continue;
                         }
                         Arrays.fill(was, false);
                         IntList path = getPath(b[i], a[i], i);
                         if (path == null) {
                             answer = null;
                             return;
                         }
                         IntSet forbidden = new IntHashSet();
                         int current = a[i];
                         for (int j : path) {
                             if (current == a[j]) {
                                 forbidden.add(-answer[j]);
                                 current = b[j];
                             } else {
                                 forbidden.add(answer[j]);
                                 current = a[j];
                             }
                         }
                         int change = 0;
                         for (int j = 1; ; j++) {
                             if (!forbidden.contains(j)) {
                                 change = j;
                                 break;
                             }
                             if (!forbidden.contains(-j)) {
                                 change = -j;
                                 break;
                             }
                         }
                         answer[i] = -change;
                         current = a[i];
                         for (int j : path) {
                             if (current == a[j]) {
                                 answer[j] += change;
                                 current = b[j];
                             } else {
                                 answer[j] -= change;
                                 current = a[j];
                             }
                         }
                     }
                     for (int i = 0; i < p; i++) {
                         if (Math.abs(answer[i]) > f * f) {
                             throw new RuntimeException();
                         }
                     }
                 }
 
                 private IntList getPath(int from, int to, int forbiddenEdge) {
                     if (was[from]) {
                         return null;
                     }
                     was[from] = true;
                     if (from == to) {
                         return new IntArrayList();
                     }
                     for (int i = graph.firstOutbound(from); i != -1; i = graph.nextOutbound(i)) {
                         if ((i >> 1) == forbiddenEdge) {
                             continue;
                         }
                         IntList call = getPath(graph.destination(i), to, forbiddenEdge);
                         if (call != null) {
                             call.add(i >> 1);
                             return call;
                         }
                     }
                     return null;
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.print("Case #" + testNumber + ": ");
                     if (answer == null) {
                         out.printLine("IMPOSSIBLE");
                     } else {
                         out.printLine(answer);
                     }
                 }
             }, 4);
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
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
 
     static interface IntSet extends IntCollection {
     }
 
     static class IntHashSet extends IntAbstractStream implements IntSet {
         private static final Random RND = new Random();
         private static final int[] SHIFTS = new int[4];
         private static final byte PRESENT_MASK = 1;
         private static final byte REMOVED_MASK = 2;
         private int size;
         private int realSize;
         private int[] values;
         private byte[] present;
         private int step;
         private int ratio;
 
         static {
             for (int i = 0; i < 4; i++) {
                 SHIFTS[i] = RND.nextInt(31) + 1;
             }
         }
 
         public IntHashSet() {
             this(3);
         }
 
         public IntHashSet(int capacity) {
             capacity = Math.max(capacity, 3);
             values = new int[capacity];
             present = new byte[capacity];
             ratio = 2;
             initStep(capacity);
         }
 
         public IntHashSet(IntCollection c) {
             this(c.size());
             addAll(c);
         }
 
         public IntHashSet(int[] arr) {
             this(new IntArray(arr));
         }
 
         private void initStep(int capacity) {
             step = RND.nextInt(capacity - 2) + 1;
             while (IntegerUtils.gcd(step, capacity) != 1) {
                 step++;
             }
         }
 
 
         public IntIterator intIterator() {
             return new IntIterator() {
                 private int position = size == 0 ? values.length : -1;
 
                 public int value() throws NoSuchElementException {
                     if (position == -1) {
                         advance();
                     }
                     if (position >= values.length) {
                         throw new NoSuchElementException();
                     }
                     if ((present[position] & PRESENT_MASK) == 0) {
                         throw new IllegalStateException();
                     }
                     return values[position];
                 }
 
                 public boolean advance() throws NoSuchElementException {
                     if (position >= values.length) {
                         throw new NoSuchElementException();
                     }
                     position++;
                     while (position < values.length && (present[position] & PRESENT_MASK) == 0) {
                         position++;
                     }
                     return isValid();
                 }
 
                 public boolean isValid() {
                     return position < values.length;
                 }
 
                 public void remove() {
                     if ((present[position] & PRESENT_MASK) == 0) {
                         throw new IllegalStateException();
                     }
                     present[position] = REMOVED_MASK;
                 }
             };
         }
 
 
         public int size() {
             return size;
         }
 
 
         public void add(int value) {
             ensureCapacity((realSize + 1) * ratio + 2);
             int current = getHash(value);
             while (present[current] != 0) {
                 if ((present[current] & PRESENT_MASK) != 0 && values[current] == value) {
                     return;
                 }
                 current += step;
                 if (current >= values.length) {
                     current -= values.length;
                 }
             }
             while ((present[current] & PRESENT_MASK) != 0) {
                 current += step;
                 if (current >= values.length) {
                     current -= values.length;
                 }
             }
             if (present[current] == 0) {
                 realSize++;
             }
             present[current] = PRESENT_MASK;
             values[current] = value;
             size++;
         }
 
         private int getHash(int value) {
             int hash = IntHash.hash(value);
             int result = hash;
             for (int i : SHIFTS) {
                 result ^= hash >> i;
             }
             result %= values.length;
             if (result < 0) {
                 result += values.length;
             }
             return result;
         }
 
         private void ensureCapacity(int capacity) {
             if (values.length < capacity) {
                 capacity = Math.max(capacity * 2, values.length);
                 rebuild(capacity);
             }
         }
 
         private void rebuild(int capacity) {
             initStep(capacity);
             int[] oldValues = values;
             byte[] oldPresent = present;
             values = new int[capacity];
             present = new byte[capacity];
             size = 0;
             realSize = 0;
             for (int i = 0; i < oldValues.length; i++) {
                 if ((oldPresent[i] & PRESENT_MASK) == PRESENT_MASK) {
                     add(oldValues[i]);
                 }
             }
         }
 
 
         public boolean contains(int value) {
             int current = getHash(value);
             while (present[current] != 0) {
                 if (values[current] == value && (present[current] & PRESENT_MASK) != 0) {
                     return true;
                 }
                 current += step;
                 if (current >= values.length) {
                     current -= values.length;
                 }
             }
             return false;
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
 
         default public boolean contains(int value) {
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 if (it.value() == value) {
                     return true;
                 }
             }
             return false;
         }
 
     }
 
     static class IntegerUtils {
         public static int gcd(int a, int b) {
             a = Math.abs(a);
             b = Math.abs(b);
             while (b != 0) {
                 int temp = a % b;
                 a = b;
                 b = temp;
             }
             return a;
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
 
     static class MiscUtils {
         public static void decreaseByOne(int[]... arrays) {
             for (int[] array : arrays) {
                 for (int i = 0; i < array.length; i++) {
                     array[i]--;
                 }
             }
         }
 
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
 
         public void addAt(int index, int value) {
             throw new UnsupportedOperationException();
         }
 
         public void removeAt(int index) {
             throw new UnsupportedOperationException();
         }
 
     }
 
     static interface Edge {
     }
 
     static interface IntCollection extends IntStream {
         public int size();
 
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
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
     }
 
     static class BidirectionalGraph extends Graph {
         public int[] transposedEdge;
 
         public BidirectionalGraph(int vertexCount) {
             this(vertexCount, vertexCount);
         }
 
         public BidirectionalGraph(int vertexCount, int edgeCapacity) {
             super(vertexCount, 2 * edgeCapacity);
             transposedEdge = new int[2 * edgeCapacity];
         }
 
         public static BidirectionalGraph createGraph(int vertexCount, int[] from, int[] to) {
             BidirectionalGraph graph = new BidirectionalGraph(vertexCount, from.length);
             for (int i = 0; i < from.length; i++) {
                 graph.addSimpleEdge(from[i], to[i]);
             }
             return graph;
         }
 
 
         public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
             int lastEdgeCount = edgeCount;
             super.addEdge(fromID, toID, weight, capacity, reverseEdge);
             super.addEdge(toID, fromID, weight, capacity, reverseEdge == -1 ? -1 : reverseEdge + 1);
             this.transposedEdge[lastEdgeCount] = lastEdgeCount + 1;
             this.transposedEdge[lastEdgeCount + 1] = lastEdgeCount;
             return lastEdgeCount;
         }
 
 
         protected int entriesPerEdge() {
             return 2;
         }
 
 
         protected void ensureEdgeCapacity(int size) {
             if (size > edgeCapacity()) {
                 super.ensureEdgeCapacity(size);
                 transposedEdge = resize(transposedEdge, edgeCapacity());
             }
         }
 
     }
 
     static class Graph {
         public static final int REMOVED_BIT = 0;
         protected int vertexCount;
         protected int edgeCount;
         private int[] firstOutbound;
         private int[] firstInbound;
         private Edge[] edges;
         private int[] nextInbound;
         private int[] nextOutbound;
         private int[] from;
         private int[] to;
         private long[] weight;
         public long[] capacity;
         private int[] reverseEdge;
         private int[] flags;
 
         public Graph(int vertexCount) {
             this(vertexCount, vertexCount);
         }
 
         public Graph(int vertexCount, int edgeCapacity) {
             this.vertexCount = vertexCount;
             firstOutbound = new int[vertexCount];
             Arrays.fill(firstOutbound, -1);
 
             from = new int[edgeCapacity];
             to = new int[edgeCapacity];
             nextOutbound = new int[edgeCapacity];
             flags = new int[edgeCapacity];
         }
 
         public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
             ensureEdgeCapacity(edgeCount + 1);
             if (firstOutbound[fromID] != -1) {
                 nextOutbound[edgeCount] = firstOutbound[fromID];
             } else {
                 nextOutbound[edgeCount] = -1;
             }
             firstOutbound[fromID] = edgeCount;
             if (firstInbound != null) {
                 if (firstInbound[toID] != -1) {
                     nextInbound[edgeCount] = firstInbound[toID];
                 } else {
                     nextInbound[edgeCount] = -1;
                 }
                 firstInbound[toID] = edgeCount;
             }
             this.from[edgeCount] = fromID;
             this.to[edgeCount] = toID;
             if (capacity != 0) {
                 if (this.capacity == null) {
                     this.capacity = new long[from.length];
                 }
                 this.capacity[edgeCount] = capacity;
             }
             if (weight != 0) {
                 if (this.weight == null) {
                     this.weight = new long[from.length];
                 }
                 this.weight[edgeCount] = weight;
             }
             if (reverseEdge != -1) {
                 if (this.reverseEdge == null) {
                     this.reverseEdge = new int[from.length];
                     Arrays.fill(this.reverseEdge, 0, edgeCount, -1);
                 }
                 this.reverseEdge[edgeCount] = reverseEdge;
             }
             if (edges != null) {
                 edges[edgeCount] = createEdge(edgeCount);
             }
             return edgeCount++;
         }
 
         protected final GraphEdge createEdge(int id) {
             return new GraphEdge(id);
         }
 
         public final int addFlowWeightedEdge(int from, int to, long weight, long capacity) {
             if (capacity == 0) {
                 return addEdge(from, to, weight, 0, -1);
             } else {
                 int lastEdgeCount = edgeCount;
                 addEdge(to, from, -weight, 0, lastEdgeCount + entriesPerEdge());
                 return addEdge(from, to, weight, capacity, lastEdgeCount);
             }
         }
 
         protected int entriesPerEdge() {
             return 1;
         }
 
         public final int addWeightedEdge(int from, int to, long weight) {
             return addFlowWeightedEdge(from, to, weight, 0);
         }
 
         public final int addSimpleEdge(int from, int to) {
             return addWeightedEdge(from, to, 0);
         }
 
         protected final int edgeCapacity() {
             return from.length;
         }
 
         public final int firstOutbound(int vertex) {
             int id = firstOutbound[vertex];
             while (id != -1 && isRemoved(id)) {
                 id = nextOutbound[id];
             }
             return id;
         }
 
         public final int nextOutbound(int id) {
             id = nextOutbound[id];
             while (id != -1 && isRemoved(id)) {
                 id = nextOutbound[id];
             }
             return id;
         }
 
         public final int destination(int id) {
             return to[id];
         }
 
         public final boolean flag(int id, int bit) {
             return (flags[id] >> bit & 1) != 0;
         }
 
         public final boolean isRemoved(int id) {
             return flag(id, REMOVED_BIT);
         }
 
         protected void ensureEdgeCapacity(int size) {
             if (from.length < size) {
                 int newSize = Math.max(size, 2 * from.length);
                 if (edges != null) {
                     edges = resize(edges, newSize);
                 }
                 from = resize(from, newSize);
                 to = resize(to, newSize);
                 nextOutbound = resize(nextOutbound, newSize);
                 if (nextInbound != null) {
                     nextInbound = resize(nextInbound, newSize);
                 }
                 if (weight != null) {
                     weight = resize(weight, newSize);
                 }
                 if (capacity != null) {
                     capacity = resize(capacity, newSize);
                 }
                 if (reverseEdge != null) {
                     reverseEdge = resize(reverseEdge, newSize);
                 }
                 flags = resize(flags, newSize);
             }
         }
 
         protected final int[] resize(int[] array, int size) {
             int[] newArray = new int[size];
             System.arraycopy(array, 0, newArray, 0, array.length);
             return newArray;
         }
 
         private long[] resize(long[] array, int size) {
             long[] newArray = new long[size];
             System.arraycopy(array, 0, newArray, 0, array.length);
             return newArray;
         }
 
         private Edge[] resize(Edge[] array, int size) {
             Edge[] newArray = new Edge[size];
             System.arraycopy(array, 0, newArray, 0, array.length);
             return newArray;
         }
 
         protected class GraphEdge implements Edge {
             protected int id;
 
             protected GraphEdge(int id) {
                 this.id = id;
             }
 
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
 
     static class IntHash {
         private IntHash() {
         }
 
         public static int hash(int c) {
             return c;
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
 }
 
