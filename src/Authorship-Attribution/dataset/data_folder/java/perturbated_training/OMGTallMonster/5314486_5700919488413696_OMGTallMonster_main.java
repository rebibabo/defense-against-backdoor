import java.util.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class Main {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static int val(int[] arr, int a, int b, int n) {
     int out = Math.min(arr[a],arr[b]);
     arr[a] -= out;
     arr[b] -= out;
     out += arr[a]/n + arr[b]/n;
     arr[a] %= n;
     arr[b] %= n;
     return out;
   }
   public static Object solve() {
     int n = nextInt(), p = nextInt();
     int[] hist = new int[p];
     for (int i = 0; i < n; i++) {
       hist[nextInt()%p]++;
     }
     int out = hist[0];
     if (p == 2) {
       return out + (hist[1]+1)/2;
     } else if (p == 3) {
       out += val(hist, 1, 2, 3);
       return out + ((hist[1] > 0 || hist[2] > 0) ? 1 : 0);
     } else if (p == 4) {
       out += hist[2]/2 + val(hist, 1, 3, 4);
       hist[2] %= 2;
       return out + ((hist[1] > 0 || hist[2] > 0 || hist[3] > 0) ? 1 : 0);
     } else {
       throw new RuntimeException();
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
