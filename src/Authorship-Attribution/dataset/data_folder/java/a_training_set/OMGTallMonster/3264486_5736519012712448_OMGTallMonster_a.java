import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class A {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static Object solve() {
     char[] arr = next().toCharArray();
     int width = nextInt();
     int count = 0;
     for (int i = 0; i < arr.length-width+1; i++) {
       if (arr[i] == '-') {
         flip(i, width, arr);
         count++;
       }
     }
     for (int i = arr.length-width; i < arr.length; i++) {
       if (arr[i] == '-') {
         return "IMPOSSIBLE";
       }
     }
     return count;
   }
   public static void flip(int start, int width, char[] arr) {
     for (int i = 0; i < width; i++) {
       arr[start+i] = arr[start+i] == '-' ? '+' : '-';
     }
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
