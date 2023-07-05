import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.util.ArrayDeque;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.util.Queue;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.util.Arrays;
 import java.io.InputStream;
 import java.util.HashMap;
 import java.io.FilenameFilter;
 import java.util.Map;
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
            final String regex = "B-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("b.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskB solver = new TaskB();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskB {
     static class ArrayHolder {
         int[] arr;
 
         public ArrayHolder(int[] arr) {
             this.arr = arr;
         }
 
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             ArrayHolder that = (ArrayHolder) o;
             return Arrays.equals(arr, that.arr);
         }
 
         public int hashCode() {
             return Arrays.hashCode(arr);
         }
     }
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printFormat("Case #%d: ", testNumber);
         int n = in.readInt();
         int[] a = IOUtils.readIntArray(in, n);
         Arrays.sort(a);
         Map<ArrayHolder, Integer> d = new HashMap<ArrayHolder, Integer>();
         ArrayHolder aa = new ArrayHolder(a);
         d.put(aa, 0);
         Queue<ArrayHolder> q = new ArrayDeque<ArrayHolder>();
         q.add(aa);
         while (!q.isEmpty()) {
             ArrayHolder xx = q.poll();
             int curDist = d.get(xx);
             int[] x = xx.arr;
             if (x.length == 0) {
                 break;
             }
 
             int ones = 0;
             for (int t : x) if (t == 1) ones++;
             int[] y = new int[x.length - ones];
             for (int i = ones; i < x.length; i++) {
                 y[i - ones] = x[i] - 1;
             }
             Arrays.sort(y);
             ArrayHolder yy = new ArrayHolder(y);
             if (!d.containsKey(yy)) {
                 d.put(yy, curDist + 1);
                 q.add(yy);
             }
 
             for (int pos = 0; pos < x.length; pos++) {
                 if (x[pos] > 1) {
                     for (int t1 = 1; t1 < x[pos]; t1++) {
                         int t2 = x[pos] - t1;
                         int[] z = new int[x.length + 1];
                         for (int i = 0; i < x.length; i++) {
                             if (i == pos) {
                                 z[pos] = t1;
                             } else {
                                 z[i] = x[i];
                             }
                         }
                         z[x.length] = t2;
                         Arrays.sort(z);
 
                         ArrayHolder zz = new ArrayHolder(z);
                         if (!d.containsKey(zz)) {
                             d.put(zz, curDist + 1);
                             q.add(zz);
                         }
                     }
                 }
             }
         }
 
         out.printLine(d.get(new ArrayHolder(new int[0])));
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
 
