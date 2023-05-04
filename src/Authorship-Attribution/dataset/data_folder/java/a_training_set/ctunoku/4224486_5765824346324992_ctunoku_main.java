import java.io.*;
 import java.util.*;
 import java.util.List;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static void printArr(long[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(int[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
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
     public static List<Integer> nextInts(int N) throws IOException {
         List<Integer> ret = new ArrayList<Integer>();
         for (int i = 0; i < N; i++) {
             ret.add(nextInt());
         }
         return ret;
     }
 
     public static long[] times;
     public static long N;
 
     public static long inProgress(long time) {
         long total = 0;
         for (int i = 0; i < times.length; i++)
             total += 1 + time / times[i];
         return total;
     }
     public static long search(long start, long end) {
         if (start == end - 1) return end;
         long mid = (start + end) / 2;
 
         if (inProgress(mid) >= N) return search(start, mid);
         else return search(mid, end);
     }
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             int B = nextInt();
             N = nextInt();
             times = new long[B];
             for (int i = 0; i < B; i++) times[i] = nextInt();
 
             long barber = -1;
             if (N > B) {
                 long endTime = search(0, 1000000000000000L);
                 long next = N - inProgress(endTime-1);
                 for (int i = 0; i < B; i++)
                     if (endTime % times[i] == 0) {
                         if (next == 1) {
                             barber = i+1;
                             break;
                         } else next -= 1;
                     }
             } else {
                 barber = N;
             }
 
 
             System.out.printf("Case #%d: %d%n", t, barber);
         }
     }
 }
