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
 import java.util.ArrayList;
 import java.util.List;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.util.Comparator;
 import java.util.Collections;
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
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.print("Case #" + testNumber + ": ");
             int count = in.readInt();
             int k = in.readInt();
             Pankage[] pankages = new Pankage[count];
             for (int i = 0; i < count; i++) {
                 pankages[i] = new Pankage(in.readDouble(), in.readDouble());
             }
 
             double answer = 0.0;
             for (long mask = 0; mask < (1 << count); mask++) {
                 if (BitUtils.countBit(mask) == k) {
                     List<Pankage> choosePankage = new ArrayList<>();
                     double curAnswer = 0.0;
                     for (int bit = 0; bit < count; bit++) {
                         if (BitUtils.isContain(mask, bit)) {
                             choosePankage.add(pankages[bit]);
                         }
                     }
 
                     Collections.sort(choosePankage, new Comparator<Pankage>() {
 
                         public int compare(Pankage o1, Pankage o2) {
                             return Double.compare(o2.radian, o1.radian);
                         }
                     });
 
                     for (int i = 0; i < choosePankage.size(); i++) {
                         if (i == 0) {
                             curAnswer += choosePankage.get(i).area;
                         } else {
                             curAnswer += choosePankage.get(i).height * choosePankage.get(i).radian * Math.PI * 2;
                         }
                     }
 
                     answer = Math.max(answer, curAnswer);
                 }
             }
 
             out.printFormat("%.9f\n", answer);
         }
 
         private class Pankage {
             double radian;
             double height;
             double area;
 
             public Pankage(double radian, double height) {
                 this.radian = radian;
                 this.height = height;
                 this.area = this.radian * this.radian * Math.PI + Math.PI * 2 * this.radian * this.height;
             }
 
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
 
     static class BitUtils {
         public static int countBit(long value) {
             return (value == 0) ? 0 : (1 + countBit(value & (value - 1)));
         }
 
         public static boolean isContain(long mask, int bit) {
             return (mask & ((long) 1 << bit)) != 0;
         }
 
     }
 }
 
