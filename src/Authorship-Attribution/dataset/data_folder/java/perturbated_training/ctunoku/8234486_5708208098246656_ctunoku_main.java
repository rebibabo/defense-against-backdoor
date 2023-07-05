import java.io.*;
 import java.util.*;
 import java.util.List;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     private static double EPS = 0.000001;
 
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
     public static void printArr(char[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + "");
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
 
     public static int check(int i, int j) {
         char cur = grid[i][j];
         if (cur == '.') return 0;
         boolean impossible = true;
         boolean free = false;
         for (int i2 = 0; i2 < i; i2++)
             if (grid[i2][j] != '.') {
                 impossible = false;
                 if (cur == '^') free = true;
             }
         for (int i2 = i+1; i2 < R; i2++)
             if (grid[i2][j] != '.') {
                 impossible = false;
                 if (cur == 'v') free = true;
             }
         for (int j2 = 0; j2 < j; j2++)
             if (grid[i][j2] != '.') {
                 impossible = false;
                 if (cur == '<') free = true;
             }
         for (int j2 = j+1; j2 < C; j2++)
             if (grid[i][j2] != '.') {
                 impossible = false;
                 if (cur == '>') free = true;
             }
         if (impossible) return -1;
         if (free) return 0;
         return 1;
     }
 
     public static String solve() {
         int total = 0;
         for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                 int val = check(i, j);
                 if (val == -1) return "IMPOSSIBLE";
                 total += val;
             }
         }
         return "" + total;
     }
 
     public static int R;
     public static int C;
     public static char[][] grid;
 
     public static void main(String[] args) throws IOException {
         br = new BufferedReader(new InputStreamReader(System.in));
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             R = nextInt();
             C = nextInt();
             grid = new char[R][C];
             for (int i = 0; i < R; i++) grid[i] = nextToken().toCharArray();
             System.out.printf("Case #%d: %s%n", t, solve());
         }
     }
 }