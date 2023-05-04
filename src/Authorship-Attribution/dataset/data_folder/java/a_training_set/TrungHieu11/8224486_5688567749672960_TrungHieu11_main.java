import java.io.OutputStreamWriter;
 import java.io.BufferedWriter;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.File;
 import java.io.Writer;
 import java.io.FilenameFilter;
 import java.util.Collection;
 import java.io.IOException;
 import java.util.InputMismatchException;
 import java.io.FileOutputStream;
 import java.util.ArrayDeque;
 import java.io.FileInputStream;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.util.Queue;
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
        TaskAVet solver = new TaskAVet();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskAVet {
     private class Number {
         public int val;
         public int times;
 
         private Number(int val, int times) {
             this.val = val;
             this.times = times;
         }
     }
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.print("Case #" + testNumber + ": ");
         int count = in.readInt();
         boolean[] visited = new boolean[count + 1];
         Queue<Number> queue = new ArrayDeque<Number>();
         queue.add(new Number(1, 1));
         visited[1] = visited[0] = true;
         int answer = 0;
         while (!queue.isEmpty()) {
             Number top = queue.poll();
             if (top.val == count) {
                 answer = top.times;
                 break;
             }
             int reverse = (int)reverseNumber(top.val);
             if (reverse <= count && !visited[reverse]) {
                 queue.add(new Number(reverse, top.times + 1));
                 visited[reverse] = true;
             }
             if (!visited[top.val + 1]) {
                 queue.add(new Number(top.val + 1, top.times + 1));
                 visited[top.val + 1] = true;
             }
         }
         out.printLine(answer);
     }
 
     private long reverseNumber(long x) {
         char[] s = String.valueOf(x).toCharArray();
         StringBuilder builder = new StringBuilder();
         for (int i = s.length - 1; i >= 0; i--) {
             builder.append(s[i]);
         }
         return Long.valueOf(builder.toString());
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
 
