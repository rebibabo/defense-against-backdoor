package con2017.con2017R2;
 
 
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
 import java.util.Locale;
 
 public class A {
 
   static final String islarge = "-large";
   private static final String[] packages = A.class.getPackage().getName().split("\\.");
   private static final String fileLoc = "src/" + packages[0] + "/" + packages[1] + "/files/";
   private static final String fileName = fileLoc + A.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   private void solve() {
     int N = in.readInt(), P = in.readInt();
     int[] cs = new int[P];
     for (int i = 0; i < N; i++) {
       cs[in.readInt() % P]++;
     }
     int tot = 0;
     
     {
       int comb = Math.min(cs[1], cs[P - 1]);
       if (1 == P - 1) {
         comb /= 2;
       }
       cs[1] -= comb;
       cs[P - 1] -= comb;
       tot += comb;
     }
     if (P == 2) {
       
     } else if (P == 3) {
       int todo = cs[1] + cs[2];
       int rest = todo % P;
       if (rest == 2) {
         tot++;
       }
       tot += (todo / 3) * 2; 
     } else if (P == 4) {
       int todo = cs[1] + cs[3];
       int ex = cs[2] & 1;
       if (cs[2] > 1) {
         tot += cs[2] / 2;
       }
       if (todo >= 2 && ex == 1) {
         todo -= 2;
         ex--;
         tot += 2;
       }
       ex += todo % 4; 
       tot += (todo / 4) * 3;
       tot += Math.max(0, ex - 1);
     }
     out.printLine(N - tot);
   }
 
   
   private static void printCase(int t) {
     out.print("Case #" + t + ": ");
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     System.out.println("Contest: " + packages[1] + " problem: " + A.class.getSimpleName());
     System.out.println("Solving " + (fileName.endsWith(islarge) ? "LARGE" : "SMALL") + " dataset");
     System.out.println("------------------------------");
     Locale.setDefault(Locale.US);
     try (InputReader closeIn = new InputReader(new FileInputStream(inputFileName));
         OutputWriter closeOut = new OutputWriter(new FileOutputStream(outputFileName))) {
       in = closeIn;
       out = closeOut;
       int tests = in.readInt();
       for (int t = 1; t <= tests; t++) {
         printCase(t);
         new A().solve();
         System.out.println("Case #" + t + ": solved");
       }
     } finally {
       System.out.println("------------------------------");
       long stop = System.currentTimeMillis();
       System.out.println("Time: " + (stop - start) + " ms");
     }
   }
 
 
   static class InputReader implements AutoCloseable {
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
 
     public void close() throws IOException {
       stream.close();
     }
   }
 
   static class OutputWriter implements AutoCloseable {
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
