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
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             int N = nextInt();
             int[] eaten = new int[N];
             for (int i = 0; i < N; i++) eaten[i] = nextInt();
 
             int total1 = 0;
             int rate = 0;
             for (int i = 0; i < N - 1; i++) {
                 int diff = Math.max(0, eaten[i] - eaten[i+1]);
                 total1 += diff;
                 rate = Math.max(rate, diff);
             }
 
             int total2 = 0;
             for (int i = 0; i < N - 1; i++) {
                 total2 += Math.min(rate, eaten[i]);
             }
 
             System.out.printf("Case #%d: %d %d%n", t, total1, total2);
         }
     }
 }
