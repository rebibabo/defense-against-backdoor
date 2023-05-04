import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.util.Arrays;
 import java.io.InputStream;
 import java.io.FilenameFilter;
 import java.util.Collection;
 import java.util.Locale;
 import java.io.OutputStreamWriter;
 import java.io.FileOutputStream;
 import java.io.File;
 
 
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
 }
 
 class TaskC {
     int min;
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printFormat("Case #%d: ", testNumber);
         final int c = in.readInt();
         final int n = in.readInt();
         final int V = in.readInt();
         final int[] a = IOUtils.readIntArray(in, n);
         Arrays.sort(a);
         min = V;
         brute(a, V, new EzIntArrayList(), 1);
         out.printLine(min);
     }
 
     private void brute(final int[] a, final int V, final EzIntArrayList b, final int startX) {
         if (b.size() >= min) {
             return;
         }
         if (startX > 2*V) {
             return;
         }
         if (check(a, b, V)) {
             min = b.size();
             return;
         }
         for (int x = startX; x <= V; x++)  {
             if (Arrays.binarySearch(a, x) < 0) {
                 b.add(x);
                 brute(a, V, b, x + 1);
                 b.removeLast();
             }
         }
     }
 
     private boolean check(final int[] a, final EzIntArrayList b, final int V) {
         int[] t = new int[a.length + b.size()];
         int n = 0;
         for (int x : a) t[n++] = x;
         for (int i = 0; i < b.size(); i++) t[n++] = b.get(i);
         Arrays.sort(t);
         for (int i = 1; i < n; i++) {
             if (t[i - 1] == t[i]) {
                 throw new RuntimeException();
             }
         }
         boolean[] can1 = new boolean[V + 1];
         boolean[] can2 = new boolean[V + 1];
         can1[0] = true;
         for (int i = 0; i < n; i++) {
             for (int v = 0; v <= V; v++) {
                 if (can1[v]) {
                     can2[v] = true;
                     if (v + t[i] <= V) {
                         can2[v + t[i]] = true;
                     }
                 }
             }
             boolean[] tmp = can1;
             can1 = can2;
             can2 = tmp;
         }
         for (int v = 1; v <= V; v++) {
             if (!can1[v]) {
                 return false;
             }
         }
         return true;
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
 
 class OutputWriter {
    private final PrintWriter writer;
 
    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }
 
     public void printFormat(String format, Object...objects) {
        writer.printf(format, objects);
    }
 
    public void close() {
        writer.close();
    }
 
    public void printLine(int i) {
        writer.println(i);
    }
 }
 
 class IOUtils {
 
    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }
 
 }
 
 class EzIntArrayList implements EzIntList, EzIntStack {
     private static final int DEFAULT_CAPACITY = 10;
     private static final double ENLARGE_SCALE = 2.0;
     private static final int HASHCODE_INITIAL_VALUE = 0x811c9dc5;
     private static final int HASHCODE_MULTIPLIER = 0x01000193;
 
     private int[] array;
     private int size;
 
     public EzIntArrayList() {
         this(DEFAULT_CAPACITY);
     }
 
     public EzIntArrayList(int capacity) {
         if (capacity < 0) {
             throw new IllegalArgumentException("Capacity must be non-negative");
         }
         array = new int[capacity];
         size = 0;
     }
 
    public int size() {
         return size;
     }
 
     public boolean add(int element) {
         if (size == array.length) {
             enlarge();
         }
         array[size++] = element;
         return true;
     }
 
     public int get(int index) {
         if (index < 0 || index >= size) {
             throw new IndexOutOfBoundsException("Index " + index + " is out of range, size = " + size);
         }
         return array[index];
     }
 
    public int removeLast() {
         if (size == 0) {
             throw new NoSuchElementException("Trying to call removeLast() on empty ArrayList");
         }
         return array[--size];
     }
 
    private void enlarge() {
         int newSize = Math.max(size + 1, (int) (size * ENLARGE_SCALE));
         int[] newArray = new int[newSize];
         System.arraycopy(array, 0, newArray, 0, size);
         array = newArray;
     }
 
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         EzIntArrayList that = (EzIntArrayList) o;
 
         if (size != that.size) {
             return false;
         }
         for (int i = 0; i < size; i++) {
             if (array[i] != that.array[i]) {
                 return false;
             }
         }
         return true;
     }
 
     public int hashCode() {
         int hash = HASHCODE_INITIAL_VALUE;
         for (int i = 0; i < size; i++) {
             hash = (hash ^ PrimitiveHashCalculator.getHash(array[i])) * HASHCODE_MULTIPLIER;
         }
         return hash;
     }
 
     public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append('[');
         for (int i = 0; i < size; i++) {
             sb.append(array[i]);
             if (i < size - 1) {
                 sb.append(", ");
             }
         }
         sb.append(']');
         return sb.toString();
     }
 
 }
 
 interface EzIntList extends EzIntCollection {
 
     boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 
 }
 
 interface EzIntStack extends EzIntCollection {
 
     boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 
 }
 
 interface EzIntCollection {
 
    boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 }
 
 class PrimitiveHashCalculator {
 
    public static int getHash(int x) {
         return x;
     }
 
 }
 
