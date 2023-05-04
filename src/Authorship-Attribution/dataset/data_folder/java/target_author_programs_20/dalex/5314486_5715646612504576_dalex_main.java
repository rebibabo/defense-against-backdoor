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
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.io.InputStream;
 
 
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
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.printFormat("Case #%d: ", testNumber);
             int n = in.readInt();
             int m = in.readInt();
             char[][] a = IOUtils.readTable(in, n, m);
             boolean[][] dontTouch = new boolean[n][m];
 
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < m; j++) {
                     if (a[i][j] == '-' || a[i][j] == '|') {
                         boolean leftWall = (j == 0) || a[i][j - 1] == '#';
                         boolean rightWall = (j == m - 1) || a[i][j + 1] == '#';
                         if (leftWall && rightWall) {
                             a[i][j] = '|';
                         }
 
                         boolean upWall = (i == 0) || (a[i - 1][j] == '#');
                         boolean downWall = (i == n - 1) || (a[i + 1][j] == '#');
                         if (upWall && downWall) {
                             a[i][j] = '-';
                         }
                     }
                 }
             }
 
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < m; j++) {
                     if (a[i][j] == '-') {
                         {
                             int c = j - 1;
                             while (c >= 0 && a[i][c] == '.') {
                                 c--;
                             }
                             if (c >= 0 && a[i][c] != '#') {
                                 if (a[i][c] == '-') {
                                     if (dontTouch[i][c]) {
                                         out.printLine("IMPOSSIBLE");
                                         return;
                                     } else {
                                         a[i][c] = a[i][j] = '|';
                                         dontTouch[i][c] = dontTouch[i][j] = true;
                                     }
                                 }
                             }
                         }
                         {
                             int c = j + 1;
                             while (c < m && a[i][c] == '.') {
                                 c++;
                             }
                             if (c < m && a[i][c] != '#') {
                                 if (a[i][c] == '-') {
                                     if (dontTouch[i][c]) {
                                         out.printLine("IMPOSSIBLE");
                                         return;
                                     } else {
                                         a[i][c] = a[i][j] = '|';
                                         dontTouch[i][c] = dontTouch[i][j] = true;
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
 
             for (int j = 0; j < m; j++) {
                 for (int i = 0; i < n; i++) {
                     if (a[i][j] == '|') {
                         {
                             int r = i - 1;
                             while (r >= 0 && a[r][j] == '.') {
                                 r--;
                             }
                             if (r >= 0 && a[r][j] != '#') {
                                 if (a[r][j] == '|') {
                                     if (dontTouch[r][j]) {
                                         out.printLine("IMPOSSIBLE");
                                         return;
                                     } else {
                                         a[r][j] = a[i][j] = '-';
                                         dontTouch[r][j] = dontTouch[i][j] = true;
                                     }
                                 }
                             }
                         }
                         {
                             int r = i + 1;
                             while (r < n && a[r][j] == '.') {
                                 r++;
                             }
                             if (r < n && a[r][j] != '#') {
                                 if (a[r][j] == '-') {
                                     if (dontTouch[r][j]) {
                                         out.printLine("IMPOSSIBLE");
                                         return;
                                     } else {
                                         a[r][j] = a[i][j] = '-';
                                         dontTouch[r][j] = dontTouch[i][j] = true;
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
 
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < m; j++) {
                     boolean ok = false;
                     if (a[i][j] == '.') {
                         {
                             int c = j - 1;
                             while (c >= 0 && a[i][c] == '.') {
                                 c--;
                             }
                             if (c >= 0 && a[i][c] == '-') {
                                 ok = true;
                             }
                         }
                         {
                             int c = j + 1;
                             while (c < m && a[i][c] == '.') {
                                 c++;
                             }
                             if (c < m && a[i][c] == '-') {
                                 ok = true;
                             }
                         }
 
                         {
                             int r = i - 1;
                             while (r >= 0 && a[r][j] == '.') {
                                 r--;
                             }
                             if (r >= 0 && a[r][j] == '|') {
                                 ok = true;
                             }
                         }
                         {
                             int r = i + 1;
                             while (r < n && a[r][j] == '.') {
                                 r++;
                             }
                             if (r < n && a[r][j] == '|') {
                                 ok = true;
                             }
                         }
 
                         if (!ok) {
                             out.printLine("IMPOSSIBLE");
                             return;
                         }
                     }
                 }
             }
 
             out.printLine("POSSIBLE");
             IOUtils.printTable(out, a);
         }
 
     }
 
     static class IOUtils {
         public static char[] readCharArray(InputReader in, int size) {
             char[] array = new char[size];
             for (int i = 0; i < size; i++) {
                 array[i] = in.readCharacter();
             }
             return array;
         }
 
         public static char[][] readTable(InputReader in, int rowCount, int columnCount) {
             char[][] table = new char[rowCount][];
             for (int i = 0; i < rowCount; i++) {
                 table[i] = readCharArray(in, columnCount);
             }
             return table;
         }
 
         public static void printTable(OutputWriter out, char[][] table) {
             for (char[] row : table) {
                 out.printLine(new String(row));
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
 
         public char readCharacter() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             return (char) c;
         }
 
         public String next() {
             return readString();
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
         }
 
     }
 }
 
