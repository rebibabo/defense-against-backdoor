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
 import java.util.Arrays;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
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
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         static final char[] v = "RPS".toCharArray();
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.printFormat("Case #%d: ", testNumber);
             int n = in.readInt();
             int R = in.readInt();
             int P = in.readInt();
             int S = in.readInt();
             char[] a = new char[1 << n];
             List<Integer> have = new ArrayList<>();
             List<String> winStrings = new ArrayList<>();
             int[] cnt = new int[256];
             for (char winnerChar : v) {
                 have.clear();
                 have.add(0);
                 Arrays.fill(a, (char) 0);
                 a[0] = winnerChar;
                 cnt['R'] = R;
                 cnt['S'] = S;
                 cnt['P'] = P;
                 cnt[winnerChar]--;
                 for (int d = (1 << n) / 2; d >= 1; d /= 2) {
                     int sz = have.size();
                     for (int i = 0; i < sz; i++) {
                         int pos = have.get(i);
                         int loserPos = pos ^ d;
                         char loserChar = loserFor(a[pos]);
                         a[loserPos] = loserChar;
                         have.add(loserPos);
                         cnt[loserChar]--;
                     }
                 }
                 if (cnt['R'] == 0 && cnt['S'] == 0 && cnt['P'] == 0) {
                     winStrings.add(new String(a));
                 }
             }
             if (winStrings.isEmpty()) {
                 out.printLine("IMPOSSIBLE");
             } else {
                 String min = null;
                 for (String s : winStrings) {
                     String t = process(s);
                     if (min == null || t.compareTo(min) < 0) {
                         min = t;
                     }
                 }
                 out.printLine(min);
             }
         }
 
         private String process(String s) {
             int n = s.length();
             if ((n & (n - 1)) != 0) {
                 throw new IllegalArgumentException();
             }
             StringBuilder sb = new StringBuilder();
             for (int d = 1; d < n; d *= 2) {
                 sb.setLength(0);
                 for (int i = 0; i < n; i += 2 * d) {
                     String a = s.substring(i, i + d);
                     String b = s.substring(i + d, i + 2 * d);
                     if (a.compareTo(b) > 0) {
                         String t = a;
                         a = b;
                         b = t;
                     }
                     sb.append(a);
                     sb.append(b);
                 }
                 s = sb.toString();
             }
             return s;
         }
 
         private char loserFor(char c) {
             if (c == 'R') return 'S';
             if (c == 'S') return 'P';
             if (c == 'P') return 'R';
             throw new IllegalArgumentException();
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
 
         public void print(Object... objects) {
             for (int i = 0; i < objects.length; i++) {
                 if (i != 0)
                     writer.print(' ');
                 writer.print(objects[i]);
             }
         }
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void printFormat(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 }
 
