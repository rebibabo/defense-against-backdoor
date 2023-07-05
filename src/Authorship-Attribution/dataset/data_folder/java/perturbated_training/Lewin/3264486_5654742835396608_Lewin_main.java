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
 import java.util.HashMap;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
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
         public long lo;
         public HashMap<Pair<Long, Long>, Long> mp;
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             long n = in.nextInt(), k = in.nextLong();
             mp = new HashMap<>();
             long lo = 0, hi = n - 1;
             while (lo < hi) {
                 long mid = (lo + hi + 1) / 2;
                 if (count1(n, mid) < k) hi = mid - 1;
                 else lo = mid;
             }
             this.lo = lo;
 
             mp = new HashMap<>();
             long lo2 = 0, hi2 = n - 1;
             while (lo2 < hi2) {
                 long mid2 = (lo2 + hi2 + 1) / 2;
                 if (count2(n, mid2) < k) hi2 = mid2 - 1;
                 else lo2 = mid2;
             }
 
             out.printf("Case #%d: %d %d\n", testNumber, lo2, lo);
         }
 
         public long count1(long n, long b) {
             if (b == 0) return n;
             Pair<Long, Long> p = new Pair<>(n, b);
             Long x = mp.get(p);
             if (x != null) return x;
             if (n < b) return 0;
             long pos = ((n - 1) / 2);
             if (pos < b) return 0;
 
             return 1 + count1(pos, b) + count1(n - pos - 1, b);
         }
 
         public long count2(long n, long b) {
             if (b == 0) return n;
             Pair<Long, Long> p = new Pair<>(n, b);
             Long x = mp.get(p);
             if (x != null) return x;
             if (n < b) return 0;
             long pos = ((n - 1) / 2);
             if (pos < lo || (pos < b && (n - pos - 1) < b)) return 0;
             return 1 + count2(pos, b) + count2(n - pos - 1, b);
         }
 
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
             if (this.numChars == -1) {
                 throw new InputMismatchException();
             } else {
                 if (this.curChar >= this.numChars) {
                     this.curChar = 0;
 
                     try {
                         this.numChars = this.stream.read(this.buf);
                     } catch (IOException var2) {
                         throw new InputMismatchException();
                     }
 
                     if (this.numChars <= 0) {
                         return -1;
                     }
                 }
 
                 return this.buf[this.curChar++];
             }
         }
 
         public int nextInt() {
             int c;
             for (c = this.read(); isSpaceChar(c); c = this.read()) {
                 ;
             }
 
             byte sgn = 1;
             if (c == 45) {
                 sgn = -1;
                 c = this.read();
             }
 
             int res = 0;
 
             while (c >= 48 && c <= 57) {
                 res *= 10;
                 res += c - 48;
                 c = this.read();
                 if (isSpaceChar(c)) {
                     return res * sgn;
                 }
             }
 
             throw new InputMismatchException();
         }
 
         public long nextLong() {
             int c;
             for (c = this.read(); isSpaceChar(c); c = this.read()) {
                 ;
             }
 
             byte sgn = 1;
             if (c == 45) {
                 sgn = -1;
                 c = this.read();
             }
 
             long res = 0L;
 
             while (c >= 48 && c <= 57) {
                 res *= 10L;
                 res += (long) (c - 48);
                 c = this.read();
                 if (isSpaceChar(c)) {
                     return res * (long) sgn;
                 }
             }
 
             throw new InputMismatchException();
         }
 
         public String next() {
             int c;
             while (isSpaceChar(c = this.read())) {
                 ;
             }
 
             StringBuilder result = new StringBuilder();
             result.appendCodePoint(c);
 
             while (!isSpaceChar(c = this.read())) {
                 result.appendCodePoint(c);
             }
 
             return result.toString();
         }
 
         public static boolean isSpaceChar(int c) {
             return c == 32 || c == 10 || c == 13 || c == 9 || c == -1;
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
 
         public void printf(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
         public final U u;
         public final V v;
 
         public Pair(U u, V v) {
             this.u = u;
             this.v = v;
         }
 
         public int hashCode() {
             return (u == null ? 0 : u.hashCode() * 31) + (v == null ? 0 : v.hashCode());
         }
 
         public boolean equals(Object o) {
             if (this == o)
                 return true;
             if (o == null || getClass() != o.getClass())
                 return false;
             Pair<U, V> p = (Pair<U, V>) o;
             return (u == null ? p.u == null : u.equals(p.u)) && (v == null ? p.v == null : v.equals(p.v));
         }
 
         public int compareTo(Pair<U, V> b) {
             int cmpU = u.compareTo(b.u);
             return cmpU != 0 ? cmpU : v.compareTo(b.v);
         }
 
     }
 }
 
