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
             out.print("Case #" + testNumber + ": ");
 
             String s = in.readString();
             String otherS = "";
             for (char c : s.toCharArray()) {
                 otherS += (c == '+' ? '-' : '+');
             }
             int answer = Math.min(calc(s), calc(otherS) + 1);
 
             out.printLine(answer);
         }
 
         private int calc(String s) {
             int answer = 0;
             char[] a = s.toCharArray();
 
             int minusIndex = -1;
             for (int i = 0; i < a.length; i++) {
                 if (a[i] == '-') {
                     minusIndex = i;
                 }
             }
 
             if (minusIndex < 0) {
                 return answer;
             }
 
             if (a[0] == '+') {
                 int maxPlus = 0;
                 while (a[maxPlus] == '+') {
                     maxPlus++;
                 }
 
                 for (int i = 0; i <= maxPlus; i++) {
                     a[i] = '-';
                 }
 
                 answer++;
             }
 
             for (int i = 0; i <= minusIndex; i++) {
                 a[i] = (a[i] == '-' ? '+' : '-');
             }
 
             ArrayUtils.reverse(a, 0, minusIndex + 1);
 
             String updatedS = String.valueOf(a);
 
             return answer + 1 + calc(updatedS.substring(0, minusIndex));
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
 
     static class ArrayUtils {
         public static void reverse(char[] array, int from, int to) {
             for (int i = from, j = to - 1; i < j; i++, j--) {
                 char temp = array[i];
                 array[i] = array[j];
                 array[j] = temp;
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
 }
 
