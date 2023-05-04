package con2017.con2017R1B;
 
 
 import java.io.*;
 import java.util.*;
 
 public class B {
 
   static final String islarge = "-large";
   private static final String fileLoc = "src/con2017/con2017R1B/files/";
   private static final String fileName = fileLoc + B.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   
   static final int[] col = new int[] {0, 1, 3, 2, 6, 4, 5};
   static final char[] dec = new char[] {' ', 'R', 'Y', 'O', 'B', 'V', 'G'};
 
   private void solve() {
     System.err.println("--adazdazdazdaz--");
     int N = in.readInt();
     int[] tmp = new int[col.length];
     for (int i = 1; i < col.length; i++) {
       tmp[i] = in.readInt();
     }
     int[] tots = new int[col.length];
     for (int i = 1; i < col.length; i++) {
       tots[col[i]] = tmp[i];
     }
     
     for (int i = 1; i <= 4; i <<= 1) {
       if (tots[7 - i] == 0) {
         continue;
       }
       if (tots[i] < tots[7 - i]) {
         System.err.println("imp1");
         out.printLine("IMPOSSIBLE");
         return;
       } else if (tots[i] == tots[7 - i]) {
         if (tots[i] + tots[7 - i] < N) {
           System.err.println("imp2: " + tots[i] + " + " + tots[7 - i] + " < " + N);
           out.printLine("IMPOSSIBLE");
           return;
         } else {
           for (int j = 0; j < N; j += 2) {
             out.print(dec[i] + "" + dec[7 - i]);
           }
           return;
         }
       }
     }
     int left = 0;
     int prev = 1;
     for (int i = 1; i <= 4; i <<= 1) {
       tots[i] -= tots[7 - i];
       left += tots[i];
       if (tots[i] <= tots[prev]) {
         prev = i;
       }
     }
     for (int i = 1; i <= 4; i <<= 1) {
       if (tots[i] * 2 > left) {
         System.err.println("imp3");
         out.printLine("IMPOSSIBLE");
         return;
       }
     }
     int first = -1;
     while (true) {
       int mx = prev == 1 ? 2 : 1;
       if (prev != 2 && (tots[mx] < tots[2] || (first == 2 && tots[mx] == tots[2]))) {
         mx = 2;
       }
       if (prev != 4 && (tots[mx] < tots[4] || (first == 4 && tots[mx] == tots[4]))) {
         mx = 4;
       }
       if (first == -1) {
         first = mx;
       }
       if (tots[mx] == 0) {
         break;
       }
       if (tots[7 - mx] > 0) {
         while (tots[7 - mx] > 0) {
           out.print(dec[mx] + "" + dec[7 - mx]);
           tots[7 - mx]--;
         }
       }
       out.print(dec[mx]);
       prev = mx;
       tots[mx]--;
     }
     out.printLine();
   }
 
   static void oldmeth() {
     int N = 0;
     int[] tots = new int[N];
     
     int max = 0;
     for (int c = 1; c <= 3; c++) {
       int sub = 0;
       for (int i = 1; i <= col.length; i++) {
         if ((i & c) == 0) {
           continue;
         }
         sub += tots[i];
       }
       max = Math.max(sub, max);
     }
     if (max * 2 > N) {
       out.printLine("IMPOSSIBLE");
       return;
     }
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     in = new InputReader(new FileInputStream(inputFileName));
     out = new OutputWriter(new FileOutputStream(outputFileName));
     int tests = in.readInt();
     for (int t = 1; t <= tests; t++) {
       out.print("Case #" + t + ": ");
       new B().solve();
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
       int sgn = 1;
       if (c == '-') {
         sgn = -1;
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
       return res * sgn;
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
