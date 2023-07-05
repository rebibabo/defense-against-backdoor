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
 import java.util.PriorityQueue;
 
 public class B {
 
   static final String islarge = "-large";
   private static final String fileLoc = "src/con2017/con2017R1A/files/";
   private static final String fileName = fileLoc + B.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   static class Pair implements Comparable<Pair> {
     final int min, max;
 
     public Pair(int min, int max) {
       this.min = min;
       this.max = max;
     }
 
     @Override
     public int compareTo(Pair o) {
       if (this.min == o.min) {
         return Integer.signum(this.min - o.min);
       }
       return Integer.signum(this.max - o.max);
     }
   }
 
   @SuppressWarnings("unchecked")
   private void solve() {
     int N = in.readInt(), P = in.readInt();
     int[] rec = new int[N];
     for (int i = 0; i < N; i++) {
       rec[i] = in.readInt();
     }
     PriorityQueue<Pair>[] ps = new PriorityQueue[N];
     for (int r = 0; r < N; r++) {
       ps[r] = new PriorityQueue<>();
       for (int c = 0; c < P; c++) {
         double d = in.readInt();
         int min = (int) Math.floor((d / 1.1D) / rec[r]);
         while (min * rec[r] < (d / 1.1D)) {
           min++;
         }
         
         int max = (int) Math.ceil((d / 0.9D) / rec[r]);
         while (max * rec[r] > (d / 0.9D)) {
           max--;
         }
         
         
         if (min <= max) {
           ps[r].add(new Pair(min, max));
         }
       }
     }
     int sol = 0;
     W1: while (true) {
       int min = -1, max = Integer.MAX_VALUE;
       for (PriorityQueue<Pair> pq : ps) {
         if (pq.isEmpty()) {
           break W1;
         }
         min = Math.max(min, pq.peek().min); 
         max = Math.min(max, pq.peek().max); 
       }
       if (min <= max) {
         sol++;
         for (PriorityQueue<Pair> pq : ps) {
           pq.poll();
         }
       } else {
         for (PriorityQueue<Pair> pq : ps) {
           while (!pq.isEmpty() && pq.peek().max < min) {
             pq.poll();
           }
         }
       }
     }
     out.printLine(sol);
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
