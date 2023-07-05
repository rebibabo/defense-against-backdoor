import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.math.BigDecimal;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.math.MathContext;
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
     static final double EPS = 1e-8;
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printFormat("Case #%d: ", testNumber);
         int n = in.readInt();
         long needV = readLong(in);
         long needT = readLong(in);
         long[] r = new long[n];
         long[] c = new long[n];
         for (int i = 0; i < n; i++) {
             r[i] = readLong(in);
             c[i] = readLong(in);
         }
         double best = Double.MAX_VALUE;
         for (int single = 0; single < n; single++) {
             if (needT == c[single]) {
                 double t = (double)needV / r[single];
                 best = Math.min(best, t);
             }
         }
         for (int first = 0; first < n; first++) {
             for (int second = 0; second < n; second++) {
                 if (first != second) {
                     double t = f(needT, needV, r[first], r[second], c[first], c[second]);
                     best = Math.min(best, t);
                 }
             }
         }
         if (best == Double.MAX_VALUE) {
             out.printLine("IMPOSSIBLE");
         } else {
             out.printFormat("%.15f\n", best);
         }
     }
 
     private double f(long needT, long needV, long r1, long r2, long c1, long c2) {
         if (c1 < needT && c2 < needT) {
             return Double.MAX_VALUE;
         }
         if (c1 > needT && c2 > needT) {
             return Double.MAX_VALUE;
         }
         if (c1 == c2 && c1 == needT) {
             return (double)needV / (r1 + r2);
         }
         BigInteger A = BigInteger.valueOf(r1 * c1 - r1 * needT);
         BigInteger B = BigInteger.valueOf(r2 * c2 - r2 * needT);
         BigInteger C = BigInteger.ZERO;
         BigInteger D = BigInteger.valueOf(r1);
         BigInteger E = BigInteger.valueOf(r2);
         BigInteger F = BigInteger.valueOf(needV);
         BigInteger det = A.multiply(E).subtract(B.multiply(D));
         if (det.equals(BigInteger.ZERO)) {
             return Double.MAX_VALUE;
         }
         BigInteger det1 = C.multiply(E).subtract(B.multiply(F));
         BigInteger det2 = A.multiply(F).subtract(C.multiply(D));
         BigDecimal t1 = new BigDecimal(det1, MathContext.DECIMAL128).divide(new BigDecimal(det, MathContext.DECIMAL128), MathContext.DECIMAL128);
         BigDecimal t2 = new BigDecimal(det2, MathContext.DECIMAL128).divide(new BigDecimal(det, MathContext.DECIMAL128), MathContext.DECIMAL128);
         if (Math.min(t1.doubleValue(), t2.doubleValue()) < -EPS) {
             return Double.MAX_VALUE;
         }
         double V1 = r1 * t1.doubleValue();
         double V2 = r2 * t2.doubleValue();
         if (Math.abs(V1 + V2 - needV) > EPS) {
             throw new RuntimeException();
         }
         if (Math.abs((V1 * c1 + V2 * c2) / (V1 + V2) - needT) > EPS) {
             throw new RuntimeException();
         }
         return Math.max(t1.doubleValue(), t2.doubleValue());
     }
 
     private long readLong(InputReader in) {
         String s = in.next();
         int pt = s.indexOf('.');
         if (pt == -1) {
             return Long.parseLong(s) * 10000L;
         } else {
             String a = s.substring(0, pt);
             String b = s.substring(pt + 1, s.length());
             return Long.parseLong(a) * 10000L + Long.parseLong(b);
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
 
 }
 
