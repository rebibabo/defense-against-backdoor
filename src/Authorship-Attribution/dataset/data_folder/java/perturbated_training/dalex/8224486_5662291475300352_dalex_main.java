import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.util.ArrayList;
 import java.util.List;
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
 import java.io.PrintStream;
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
     @SuppressWarnings({"UnusedDeclaration", "NullableProblems", "EqualsWhichDoesntCheckParameterClass"})
     static class Rational implements Comparable<Rational> {
         final BigInteger num;
         final BigInteger den;
 
        public Rational(long num) {
             this.num = BigInteger.valueOf(num);
             this.den = BigInteger.ONE;
         }
 
         public Rational(long num, long den) {
             this(BigInteger.valueOf(num), BigInteger.valueOf(den));
         }
 
         public Rational(BigInteger num, BigInteger den) {
             if (!den.abs().equals(BigInteger.ONE)) {
                 BigInteger gcd = num.gcd(den);
                 if (gcd.signum() != 0 && !gcd.equals(BigInteger.ONE)) {
                     num = num.divide(gcd);
                     den = den.divide(gcd);
                 }
             }
             if (den.signum() < 0) {
                 num = num.negate();
                 den = den.negate();
             }
             this.num = num;
             this.den = den;
         }
 
         public Rational add(Rational r) {
             return new Rational(num.multiply(r.den).add(r.num.multiply(den)), den.multiply(r.den));
         }
 
        public Rational mul(Rational r) {
             return new Rational(num.multiply(r.num), den.multiply(r.den));
         }
 
         public Rational div(Rational r) {
             return new Rational(num.multiply(r.den), den.multiply(r.num));
         }
 
        public int compareTo(Rational other) {
             return (num.multiply(other.den).compareTo(other.num.multiply(den)));
         }
 
         public boolean equals(Object obj) {
             return num.equals(((Rational) obj).num) && den.equals(((Rational) obj).den);
         }
 
         public int hashCode() {
             return num.hashCode() * 31 + den.hashCode();
         }
 
         public String toString() {
             return num + "/" + den;
         }
     }
 
     static class Hiker {
         int startPos;
         Rational speed;
 
         public Hiker(int startPos, Rational speed) {
             this.startPos = startPos;
             this.speed = speed;
         }
     }
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printFormat("Case #%d: ", testNumber);
         int groups = in.readInt();
         List<Hiker> hikers = new ArrayList<Hiker>();
         for (int g = 0; g < groups; g++) {
             int startPos = in.readInt();
             int num = in.readInt();
             int time = in.readInt();
             for (int i = 0; i < num; i++) {
                 hikers.add(new Hiker(startPos, new Rational(360, time + i)));
             }
         }
         Hiker A = hikers.get(0);
         Hiker B = hikers.get(1);
         if (A.speed.equals(B.speed)) {
             out.printLine(0);
             return;
         }
         if (A.speed.compareTo(B.speed) < 0) {
             Hiker t = A; A = B; B = t;
         }
         Rational timeB = new Rational(360 - B.startPos).div(B.speed);
         Rational coordA = new Rational(A.startPos).add(timeB.mul(A.speed));
         System.err.println(coordA);
         boolean meet = false;
         if (A.startPos < B.startPos) {
             if (coordA.compareTo(new Rational(360)) >= 0) {
                 meet = true;
             }
         } else {
             if (coordA.compareTo(new Rational(720)) >= 0) {
                 meet = true;
             }
         }
         if (meet) {
             out.printLine(1);
         } else {
             out.printLine(0);
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
 
