package con2017.con2017R1B;
 
 
 import java.io.*;
 import java.util.*;
 
 public class C {
 
   static final String islarge = "-large";
   private static final String fileLoc = "src/con2017/con2017R1B/files/";
   private static final String fileName = fileLoc + C.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   private void solve() {
     int N = in.readInt(), Q = in.readInt();
     double[] stam = new double[N], sp = new double[N];
     for (int i = 0; i < N; i++) {
       stam[i] = in.readInt();
       sp[i] = in.readInt();
     }
     double[][] dist = new double[N][N];
     for (int r = 0; r < N; r++) {
       for (int c = 0; c < N; c++) {
         dist[r][c] = in.readInt();
       }
     }
     double[] time = new double[N];
     boolean[] vis = new boolean[N];
     int[] lastvis = new int[N];
     int TIME = 1;
     while (Q-- > 0) {
       for (int r = 0; r < N; r++) {
         time[r] = -1;
         vis[r] = false;
       }
       int u = in.readInt() - 1, v = in.readInt() - 1;
       time[u] = 0;
       while (true) {
         int cur = -1;
         for (int i = 0; i < N; i++) {
           if (vis[i] || time[i] == -1) {
             continue;
           }
           if (cur == -1 || time[i] < time[cur]) {
             cur = i;
           }
         }
         if (cur == -1 || cur == v) {
           break;
         }
         dfs(cur, time[cur], dist, stam[cur], sp[cur], lastvis, ++TIME, time);
         vis[cur] = true;
       }
       out.print(" " + time[v]);
     }
     out.printLine();
   }
 
   static void dfs(int u, double curtime, double[][] dist, double stamina, double sp, int[] lastvis,
       int it, double[] time) {
     if (stamina <= 0)
       return;
     for (int v = 0; v < dist[u].length; v++) {
       if (dist[u][v] == -1 || stamina < dist[u][v]) {
         continue;
       }
       double newTime = curtime + (dist[u][v] / sp);
       if (time[v] == -1 || newTime < time[v]) {
         time[v] = newTime;
         
         
       }
       dfs(v, newTime, dist, stamina - dist[u][v], sp, lastvis, it, time);
     }
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     in = new InputReader(new FileInputStream(inputFileName));
     out = new OutputWriter(new FileOutputStream(outputFileName));
     int tests = in.readInt();
     for (int t = 1; t <= tests; t++) {
       out.print("Case #" + t + ":");
       new C().solve();
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
