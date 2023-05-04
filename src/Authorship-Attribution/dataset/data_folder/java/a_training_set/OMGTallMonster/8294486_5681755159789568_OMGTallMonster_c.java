import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class C {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static class Horse {
     public long maxDist;
     public double speed;
     public long maxDist() { return maxDist; }
     public double speed() { return speed; }
     Horse(long maxDist_, double speed_) { maxDist=maxDist_; speed=speed_; }
     public Horse clone() { return new Horse(maxDist,speed); }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Horse o = (Horse)oo;
       return maxDist==o.maxDist&&speed==o.speed;
     }
     public int hashCode() { return Objects.hash(maxDist,speed); }
     public String toString() { return ""+maxDist+","+speed; }
   }
   public static Object solve() {
     int n = nextInt(), q = nextInt();
     Horse[] horses = new Horse[n];
     for (int i = 0; i < horses.length; i++) { horses[i] = new Horse(nextLong(), nextDouble()); }
     long[] dists = new long[n-1];
     for (int i = 0; i < dists.length; i++) { dists[i] = Long.parseLong(nextLine().split(" ")[i+1]); }
     nextLine(); nextLine();
     double[] minTimes = new double[n];
     for (int i = 0; i < minTimes.length; i++) { minTimes[i] = Double.MAX_VALUE; }
     minTimes[0] = 0;
     for (int i = 0; i < n-1; i++) {
       long maxDist = horses[i].maxDist, dist = 0;
       double speed = horses[i].speed;
       for (int j = i+1; j < n; j++) {
         dist += dists[j-1];
         if (dist > maxDist) break;
         minTimes[j] = Math.min(minTimes[j], minTimes[i]+dist/speed);
       }
     }
     return minTimes[n-1];
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
