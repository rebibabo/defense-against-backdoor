import java.io.OutputStreamWriter;
 import java.io.BufferedWriter;
 import java.util.Locale;
 import java.util.Comparator;
 import java.io.OutputStream;
 import java.util.RandomAccess;
 import java.io.PrintWriter;
 import java.io.File;
 import java.util.AbstractList;
 import java.io.Writer;
 import java.io.FilenameFilter;
 import java.util.List;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.io.FileOutputStream;
 import java.io.FileInputStream;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.util.Collections;
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
     private int answer = Integer.MAX_VALUE;
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.print("Case #" + testNumber + ": ");
         answer = Integer.MAX_VALUE;
         int count = in.readInt();
         int[] A = IOUtils.readIntArray(in, count);
         dfs(A, 0);
 
         out.printLine(answer);
     }
 
     private void dfs(int[] A, int deep) {
         if (deep > answer) {
             return;
         }
         int maxIdx = ArrayUtils.maxPosition(A);
         int maxVal = A[maxIdx];
         if (maxVal <= 0) {
             answer = Math.min(answer, deep);
         }
         int[] next = new int[A.length];
         for (int i = 0; i < A.length; i++) {
             next[i] = Math.max(A[i] - 1, 0);
         }
         dfs(next, deep + 1);
         next = new int[A.length + 1];
         for (int i = 0; i < A.length; i++) {
             next[i] = A[i];
         }
 
         for (int i = 1; i <= maxVal / 2; i++) {
             next[maxIdx] = A[maxIdx] - i;
             next[A.length] = i;
             dfs(next, deep + 1);
         }
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
 
     public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
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
 
 class ArrayUtils {
 
     public static int maxPosition(int[] array) {
        return maxPosition(array, 0, array.length);
    }
 
     public static int maxPosition(int[] array, int from, int to) {
        if (from >= to)
            return -1;
        int max = array[from];
        int result = from;
        for (int i = from + 1; i < to; i++) {
            if (array[i] > max) {
                max = array[i];
                result = i;
            }
        }
        return result;
    }
 
 }
 
