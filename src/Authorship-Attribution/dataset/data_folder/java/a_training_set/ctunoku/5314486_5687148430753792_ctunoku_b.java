import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.*;
 
 public class B {
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
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             int N = nextInt(); 
             int C = nextInt(); 
             int M = nextInt();
 
             int[][] tickets = new int[C][N]; 
             int[] totalPosition = new int[N];
             for (int i = 0; i < M; i++) {
                 int P = nextInt() - 1;
                 int B = nextInt() - 1;
                 tickets[B][P] += 1;
             }
 
             int maxTickets = 0;
             for (int c = 0; c < C; c++) {
                 int cur = 0;
                 for (int p = 0; p < N; p++) {
                     cur += tickets[c][p];
                     totalPosition[p] += tickets[c][p];
                 }
                 maxTickets = Math.max(maxTickets, cur);
             }
             int rides = Math.max(maxTickets, totalPosition[0]);
             int promotions = 0;
             for (int p = N-1; p >= 0; p--) {
                 if (totalPosition[p] > rides) {
 
                     promotions += totalPosition[p] - rides;
                 }
             }
 
             System.out.printf("Case #%d: %d %d%n", t, rides, promotions);
         }
     }
 }
