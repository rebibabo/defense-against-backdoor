import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
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
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.printFormat("Case #%d: ", testNumber);
             int n = in.readInt();
             int k = in.readInt();
             double[] p = new double[n];
             for (int i = 0; i < n; i++) {
                 p[i] = in.readDouble();
             }
             if (k % 2 != 0) {
                 throw new IllegalArgumentException();
             }
             double stupid = stupid(n, k, p);
             out.printFormat("%.15f\n", stupid);
 
 
         }
 
         private double stupid(int n, int k, double[] p) {
             double max = -1.0;
             int[] arr = new int[n];
             int length;
             double[][] dp = new double[k + 1][k + 1];
             for (int mask = 0; mask < (1 << n); mask++) {
                 if (Integer.bitCount(mask) == k) {
                     length = 0;
                     for (int i = 0; i < n; i++)
                         if ((mask & (1 << i)) != 0) {
                             arr[length++] = i;
                         }
                     dp[0][0] = 1.0;
                     for (int i = 0; i < length; i++) {
                         dp[i + 1][0] = dp[i][0] * p[arr[i]];
                         for (int j = 0; j <= i; j++) {
                             dp[i + 1][j + 1] = dp[i][j + 1] * p[arr[i]] + dp[i][j] * (1.0 - p[arr[i]]);
                         }
                     }
                     max = Math.max(max, dp[length][k / 2]);
                 }
             }
             return max;
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
 
         public double readDouble() {
             int c = read();
             while (isSpaceChar(c))
                 c = read();
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             double res = 0;
             while (!isSpaceChar(c) && c != '.') {
                 if (c == 'e' || c == 'E')
                     return res * Math.pow(10, readInt());
                 if (c < '0' || c > '9')
                     throw new InputMismatchException();
                 res *= 10;
                 res += c - '0';
                 c = read();
             }
             if (c == '.') {
                 c = read();
                 double m = 1;
                 while (!isSpaceChar(c)) {
                     if (c == 'e' || c == 'E')
                         return res * Math.pow(10, readInt());
                     if (c < '0' || c > '9')
                         throw new InputMismatchException();
                     m /= 10;
                     res += (c - '0') * m;
                     c = read();
                 }
             }
             return res * sgn;
         }
 
         public String next() {
             return readString();
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
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
 
         public void printFormat(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 }
 
