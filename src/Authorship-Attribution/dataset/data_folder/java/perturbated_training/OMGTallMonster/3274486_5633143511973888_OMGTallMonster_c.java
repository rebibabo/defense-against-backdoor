import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class C {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static Object solve() {
     int n = nextInt(), k = nextInt();
     double u = nextDouble();
     double[] probs = new double[n+1];
     for (int i = 0; i < probs.length; i++) { probs[i] = i==n ? 1 : nextDouble(); }
     Arrays.sort(probs);
     for (int i = 0; i < n+1; i++) {
       double diff = probs[i] - probs[0];
       if (diff * i > u+0.00001) {
         for (int j = 0; j < i; j++) {
           probs[j] += u/i;
         }
         break;
       }
       u -= diff * i;
       for (int j = 0; j < i; j++) {
         probs[j] = probs[i];
       }
     }
     double out = 1;
     for (double d : probs) { out *= d; }
     return out;
   }
   public static void main(String[] args) {
     int T = nextInt();
     for (int i = 0; i < T; i++) {
       outWriter.print("Case #"+(i+1)+": ");
       Object tmp = solve();
       if (tmp != null) { outWriter.println(tmp); }
     }
     outWriter.flush();
   }
   public static StringTokenizer tokenizer = null;
   public static String nextLine() {
     try { return buffer.readLine(); } catch (IOException e) { throw new UncheckedIOException(e); }
   }
   public static String next() {
     while (tokenizer == null || !tokenizer.hasMoreElements()) { tokenizer = new StringTokenizer(nextLine()); }
     return tokenizer.nextToken();
   }
   public static int nextInt() { return Integer.parseInt(next()); }
   public static long nextLong() { return Long.parseLong(next()); }
   public static double nextDouble() { return Double.parseDouble(next()); }
 }
