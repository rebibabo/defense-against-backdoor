import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.*;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     public static double EPS = 0.000001;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static String join(Collection<?> x, String space) {
         if (x.size() == 0) return "";
         StringBuilder sb = new StringBuilder();
         boolean first = true;
         for (Object elt : x) {
             if (first) first = false;
             else sb.append(space);
             sb.append(elt);
         }
         return sb.toString();
     }
 
     public static String nextToken() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             String line = br.readLine();
             st = new StringTokenizer(line.trim());
         }
         return st.nextToken();
     }
     public static int nextInt() throws IOException {
         return Integer.parseInt(nextToken());
     }
     public static long nextLong() throws IOException {
         return Long.parseLong(nextToken());
     }
     public static double nextDouble() throws IOException {
         return Double.parseDouble(nextToken());
     }
 
     public static void increment(SortedMap<Long, Long> counts, long size, long count) {
         if (!counts.containsKey(size)) {
             counts.put(size, 0L);
         }
         counts.put(size, counts.get(size) + count);
     }
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             long N = nextLong();
             long K = nextLong();
 
             SortedMap<Long, Long> counts = new TreeMap<>(); 
             counts.put(N, 1L);
             long max = -1;
             long min = -1;
             while (K > 0) {
                 long size = counts.lastKey();
                 long amount = counts.get(size);
                 counts.remove(size);
                 K -= amount;
 
                 if (size % 2 == 1) {
                     increment(counts, size/2, amount * 2);
                     min = max = size/2;
                 } else {
                     increment(counts, size/2, amount);
                     increment(counts, size/2 - 1, amount);
                     min = size/2 - 1;
                     max = size/2;
                 }
 
             }
 
             System.out.printf("Case #%d: %d %d%n", t, max, min);
         }
     }
 }
