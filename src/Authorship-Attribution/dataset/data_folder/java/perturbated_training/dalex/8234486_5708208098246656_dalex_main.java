import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.FilenameFilter;
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
         int m = in.readInt();
         char[][] a = IOUtils.readTable(in, n, m);
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 if (a[i][j] != '.') {
                     int cntX = 0;
                     int cntY = 0;
                     for (int x = 0; x < n; x++) {
                         if (a[x][j] != '.') cntX++;
                     }
                     for (int y = 0; y < m; y++) {
                         if (a[i][y] != '.') cntY++;
                     }
                     if (cntX == 1 && cntY == 1) {
                         out.printLine("IMPOSSIBLE");
                         return;
                     }
                 }
             }
         }
         int ans = 0;
         for (int j = 0; j < m; j++) {
             char firstInCol = 0, lastInCol = 0;
             for (int x = 0; x < n; x++) {
                 if (a[x][j] != '.') {
                     if (firstInCol == 0) firstInCol = a[x][j];
                     lastInCol = a[x][j];
                 }
             }
             if (firstInCol == '^') ans++;
             if (lastInCol == 'v') ans++;
         }
         for (int i = 0; i < n; i++) {
             char firstInRow = 0, lastInRow = 0;
             for (int y = 0; y < m; y++) {
                 if (a[i][y] != '.') {
                     if (firstInRow == 0) firstInRow = a[i][y];
                     lastInRow = a[i][y];
                 }
             }
             if (firstInRow == '<') ans++;
             if (lastInRow == '>') ans++;
         }
         out.printLine(ans);
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
 
    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
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
 
    public void printLine(Object...objects) {
        print(objects);
        writer.println();
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
 
    public static char[] readCharArray(InputReader in, int size) {
        char[] array = new char[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readCharacter();
        return array;
    }
 
    public static char[][] readTable(InputReader in, int rowCount, int columnCount) {
        char[][] table = new char[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readCharArray(in, columnCount);
        return table;
    }
 
 }
 
