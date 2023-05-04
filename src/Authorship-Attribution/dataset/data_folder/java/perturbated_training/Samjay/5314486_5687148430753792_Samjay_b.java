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
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.InputMismatchException;
 import java.util.Locale;
 
 public class B {
 
   static final String islarge = "-large";
   private static final String[] packages = B.class.getPackage().getName().split("\\.");
   private static final String fileLoc = "src/" + packages[0] + "/" + packages[1] + "/files/";
   private static final String fileName = fileLoc + B.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   static class P implements Comparable<P> {
     final ArrayList<Integer> ts;
     final int id;
 
     public P(int id) {
       this.id = id;
       this.ts = new ArrayList<>();
     }
 
     @Override
     public int compareTo(P o) {
       if (this.ts.size() == o.ts.size()) {
         return this.id < o.id ? -1 : 1;
       }
       return this.ts.size() > o.ts.size() ? -1 : 1;
     }
   }
 
   private void solve() {
     int N = in.readInt(), C = in.readInt(), M = in.readInt();
     P[] ps = new P[C];
     for (int i = 0; i < C; i++) {
       ps[i] = new P(i);
     }
     for (int i = 0; i < M; i++) {
       int pos = in.readInt() - 1, buy = in.readInt() - 1;
       ps[buy].ts.add(pos);
     }
     Arrays.sort(ps);
     int rides = 0, promo = 0;
     for (int i = 0; i < C; i++) {
       if (ps[i].ts.isEmpty()) {
         break;
       }
       rides = Math.max(rides, ps[i].ts.size());
       Collections.sort(ps[i].ts);
     }
     if (C == 2) {
       
       int[] p1 = new int[N], p2 = new int[N];
       for (int p : ps[0].ts) {
         p1[p]++;
       }
       for (int p : ps[1].ts) {
         p2[p]++;
       }
       for (int i = 0; i < N; i++) {
         if (p1[i] + p2[i] > rides) {
           if (i == 0) {
             rides += (p1[i] + p2[i] - rides);
           } else {
             promo += (p1[i] + p2[i] - rides);
           }
         }
       }
     } else {
       throw new RuntimeException("no clue so far");
     }
     out.printLine(rides + " " + promo);
   }
 
   
   private static void printCase(int t) {
     out.print("Case #" + t + ": ");
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     System.out.println("Contest: " + packages[1] + " problem: " + B.class.getSimpleName());
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
         new B().solve();
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
