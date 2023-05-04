import java.io.OutputStream;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
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
             inputStream = new FileInputStream("input.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("output.txt");
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
             out.print("Case #" + testNumber + ": ");
             int count = in.readInt();
             int k = in.readInt();
             double U = in.readDouble();
             double[] cores = IOUtils.readDoubleArray(in, count);
             double left = 0.0;
             double right = 1.0;
             int loopCount = 1000;
             while (loopCount-- > 0) {
                 double mid = (right + left) / 2;
                 if (valid(mid, U, cores)) {
                     left = mid;
                 } else {
                     right = mid;
                 }
             }
 
             double answer = 1.0;
             if (valid(right, U, cores)) {
                 for (int i = 0; i < count; i++) {
                     if (Double.compare(cores[i], right) > 0) {
                         answer *= cores[i];
                     } else {
                         answer *= right;
                     }
                 }
             } else {
                 for (int i = 0; i < count; i++) {
                     if (Double.compare(cores[i], left) > 0) {
                         answer *= cores[i];
                     } else {
                         answer *= right;
                     }
                 }
             }
 
             out.printFormat("%.6f\n", answer);
         }
 
         private boolean valid(double cur, double u, double[] cores) {
             double sum = 0.0;
             for (int i = 0; i < cores.length; i++) {
                 if (Double.compare(cores[i], cur) < 0) {
                     sum += cur - cores[i];
                 }
             }
             return Double.compare(sum, u) <= 0;
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
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(objects[i]);
             }
         }
 
         public void printFormat(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static class IOUtils {
         public static double[] readDoubleArray(InputReader in, int size) {
             double[] array = new double[size];
             for (int i = 0; i < size; i++) {
                 array[i] = in.readDouble();
             }
             return array;
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private InputReader.SpaceCharFilter filter;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (numChars == -1) {
                 throw new InputMismatchException();
             }
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0) {
                     return -1;
                 }
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c)) {
                     res.appendCodePoint(c);
                 }
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public double readDouble() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             double res = 0;
             while (!isSpaceChar(c) && c != '.') {
                 if (c == 'e' || c == 'E') {
                     return res * Math.pow(10, readInt());
                 }
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             }
             if (c == '.') {
                 c = read();
                 double m = 1;
                 while (!isSpaceChar(c)) {
                     if (c == 'e' || c == 'E') {
                         return res * Math.pow(10, readInt());
                     }
                     if (c < '0' || c > '9') {
                         throw new InputMismatchException();
                     }
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
 }
 
