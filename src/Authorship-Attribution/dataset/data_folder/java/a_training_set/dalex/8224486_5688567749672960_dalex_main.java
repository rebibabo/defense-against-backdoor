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
            final String regex = "A-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("a.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskA solver = new TaskA();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskA {
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printFormat("Case #%d: ", testNumber);
         int n = in.readInt();
         int[] d = new int[n + 1];
         EzIntArrayDeque q = new EzIntArrayDeque();
         Arrays.fill(d, (int) 1e9);
         q.add(1);
         d[1] = 0;
         while (!q.isEmpty()) {
             int x = q.removeFirst();
             if (x + 1 <= n && d[x + 1] > d[x] + 1) {
                 d[x + 1] = d[x] + 1;
                 q.add(x + 1);
             }
             int y = rev(x);
             if (y <= n && d[y] > d[x] + 1) {
                 d[y] = d[x] + 1;
                 q.add(y);
             }
         }
         out.printLine(d[n] + 1);
     }
 
     private int rev(int x) {
         String s = "" + x;
         String r = new StringBuilder(s).reverse().toString();
         return Integer.parseInt(r, 10);
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
 
 class EzIntArrayDeque implements EzIntDeque, EzIntList {
     private static final int DEFAULT_CAPACITY = 8;
     private static final int HASHCODE_INITIAL_VALUE = 0x811c9dc5;
     private static final int HASHCODE_MULTIPLIER = 0x01000193;
 
     private int[] deque;
     private int size;
     private int head;
     private int tail;
     private int mask;
 
     public EzIntArrayDeque() {
         this(DEFAULT_CAPACITY);
     }
 
     public EzIntArrayDeque(int capacity) {
         if (capacity < 0) {
             throw new IllegalArgumentException("Capacity must be non-negative");
         }
         if (capacity < 1) {
             capacity = 1;
         }
         if ((capacity & (capacity - 1)) != 0) {
             capacity = Integer.highestOneBit(capacity) << 1;
         }
         deque = new int[capacity];
         size = 0;
         head = 0;
         tail = 0;
         mask = deque.length - 1;
     }
 
     public boolean isEmpty() {
         return size == 0;
     }
 
     public boolean add(int element) {
         deque[tail] = element;
         tail = (tail + 1) & mask;
         size++;
         if (size == deque.length) {
             enlarge();
         }
         return true;
     }
 
     public int removeFirst() {
         if (size == 0) {
             throw new NoSuchElementException("Trying to call removeFirst() on empty ArrayDeque");
         }
         final int removedElement = deque[head];
         size--;
         head = (head + 1) & mask;
         return removedElement;
     }
 
     private void enlarge() {
         int newSize = (size << 1);
         int[] newArray = new int[newSize];
         System.arraycopy(deque, head, newArray, 0, deque.length - head);
         System.arraycopy(deque, 0, newArray, deque.length - tail, tail);
         deque = newArray;
         head = 0;
         tail = size;
         mask = deque.length - 1;
     }
 
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         EzIntArrayDeque that = (EzIntArrayDeque) o;
 
         if (size != that.size) {
             return false;
         }
         for (int i = head, j = that.head; i != tail; i = (i + 1) & mask, j = (j + 1) & that.mask) {
             if (deque[i] != that.deque[j]) {
                 return false;
             }
         }
         return true;
     }
 
     public int hashCode() {
         int hash = HASHCODE_INITIAL_VALUE;
         for (int i = head; i != tail; i = (i + 1) & mask) {
             hash = (hash ^ PrimitiveHashCalculator.getHash(deque[i])) * HASHCODE_MULTIPLIER;
         }
         return hash;
     }
 
     public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append('[');
         for (int i = head; i != tail; i = (i + 1) & mask) {
             if (i != head) {
                 sb.append(", ");
             }
             sb.append(deque[i]);
         }
         sb.append(']');
         return sb.toString();
     }
 
 }
 
 interface EzIntDeque extends EzIntQueue, EzIntStack {
 
     boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 
 }
 
 interface EzIntList extends EzIntCollection {
 
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
 
 interface EzIntQueue extends EzIntCollection {
 
     boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 
 }
 
 interface EzIntStack extends EzIntCollection {
 
     boolean equals(Object object);
 
     int hashCode();
 
     String toString();
 
 }
 
