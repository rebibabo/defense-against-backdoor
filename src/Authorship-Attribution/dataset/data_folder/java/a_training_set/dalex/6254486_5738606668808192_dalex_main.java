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
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             int[] primes = IntegerUtils.generatePrimes(70000);
             out.printFormat("Case #%d:\n", testNumber);
             int len = in.readInt();
             int cnt = in.readInt();
 
 
 
 
             int[] counter = new int[11];
             for (long mask = 1; mask < (1L << (len - 1)); mask += 2) {
                 long num = mask | (1L << (len - 1));
                 String str = Long.toString(num, 2);
                 Arrays.fill(counter, -1);
                 for (int base = 2; base <= 10; base++) {
                     long x = Long.parseLong(str, base);
                     for (int p : primes) {
                         if (p >= x) {
                             break;
                         }
                         if (x % p == 0) {
                             counter[base] = p;
                         }
                     }
                 }
                 boolean good = true;
                 for (int base = 2; base <= 10; base++) {
                     if (counter[base] == -1) {
                         good = false;
                         break;
                     }
                 }
                 if (good) {
                     out.print(str);
                     for (int base = 2; base <= 10; base++) {
                         out.print(" " + counter[base]);
                     }
                     out.printLine();
                     cnt--;
                     if (cnt == 0) {
                         break;
                     }
                 }
             }
         }
 
     }
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public boolean advance();
 
         public boolean isValid();
 
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
 
     static class IntegerUtils {
         public static int[] generatePrimes(int upTo) {
             int[] isPrime = generateBitPrimalityTable(upTo);
             IntList primes = new IntArrayList();
             for (int i = 0; i < upTo; i++) {
                 if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1)
                     primes.add(i);
             }
             return primes.toArray();
         }
 
         public static int[] generateBitPrimalityTable(int upTo) {
             int[] isPrime = new int[(upTo + 31) >> 5];
             if (upTo < 2)
                 return isPrime;
             Arrays.fill(isPrime, -1);
             isPrime[0] &= -4;
             for (int i = 2; i * i < upTo; i++) {
                 if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1) {
                     for (int j = i * i; j < upTo; j += i)
                         isPrime[j >> 5] &= -1 - (1 << (j & 31));
                 }
             }
             return isPrime;
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
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c))
                 c = read();
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c))
                     res.appendCodePoint(c);
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null)
                 return filter.isSpaceChar(c);
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public String next() {
             return readString();
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
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
 
     static interface IntCollection extends IntStream {
         public int size();
 
         default public void add(int value) {
             throw new UnsupportedOperationException();
         }
 
         default public int[] toArray() {
             int size = size();
             int[] array = new int[size];
             int i = 0;
             for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
                 array[i++] = it.value();
             }
             return array;
         }
 
         default public IntCollection addAll(IntStream values) {
             for (IntIterator it = values.intIterator(); it.isValid(); it.advance()) {
                 add(it.value());
             }
             return this;
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
                 if (i != 0)
                     writer.print(' ');
                 writer.print(objects[i]);
             }
         }
 
         public void printLine() {
             writer.println();
         }
 
         public void printFormat(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static interface IntReversableCollection extends IntCollection {
     }
 }
 
