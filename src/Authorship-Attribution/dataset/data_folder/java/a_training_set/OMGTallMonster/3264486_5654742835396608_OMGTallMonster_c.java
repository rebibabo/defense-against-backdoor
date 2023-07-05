import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class C {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static class LongWrapper {
     public long i;
     public long i() { return i; }
     LongWrapper(long i_) { i=i_; }
     public LongWrapper clone() { return new LongWrapper(i); }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       LongWrapper o = (LongWrapper)oo;
       return i==o.i;
     }
     public int hashCode() { return Objects.hash(i); }
     public String toString() { return ""+i; }
   }
   public static class MyMap extends TreeMap<Long, LongWrapper> {
     public void add(Long key, long newVal) {
       LongWrapper val = get(key);
       if (val == null) {
         put(key, new LongWrapper(newVal));
       } else {
         val.i += newVal;
       }
     }
   }
   public static Object solve() {
     long n = nextLong(), k = nextLong();
     MyMap map = new MyMap();
     map.add(n, 1);
     long count = 0;
     while (count < k && map.size() > 0) {
       long tmp = map.lastKey(), num = map.remove(tmp).i;
       count += num;
       if (count >= k) {
         return max2(tmp)+" "+min2(tmp);
       }
       map.add(max2(tmp), num);
       map.add(min2(tmp), num);
     }
     System.err.println("This shouldn't happen...");
     return "0 0";
   }
   public static long max2(long tmp) { return tmp/2; }
   public static long min2(long tmp) { return (tmp-1)/2; }
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
