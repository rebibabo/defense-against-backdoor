import java.io.*;
 import java.util.*;
 import java.util.List;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     private static double EPS = 0.0000001;
 
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
     public static double nextDouble() throws IOException {
         return Double.parseDouble(nextToken());
     }
     public static List<Integer> nextInts(int N) throws IOException {
         List<Integer> ret = new ArrayList<Integer>();
         for (int i = 0; i < N; i++) {
             ret.add(nextInt());
         }
         return ret;
     }
 
     public static class Source {
         double rate;
         double temp;
         public Source(double r, double t) {
             rate = r;
             temp = t;
         }
     }
 
     public static int N;
     public static double V;
     public static double X;
     public static double[] rate;
     public static double[] temp;
 
     public static double average(int start, int end) {
         double rateSum = 0;
         double avgTemp = 0;
         for (int i = start; i < end; i++) {
             rateSum += rate[i];
             avgTemp += rate[i] * temp[i];
         }
         avgTemp /= rateSum;
         return avgTemp;
     }
 
     public static double total(int i, int j) {
         double rateSum = 0;
         for (int k = i; k < j; k++) rateSum += rate[k];
         return rateSum;
     }
 
     public static double combine(double r1, double c1, double c2) {
         double r2x = (X - c1) * r1 / (c2 - X);
         return r2x + r1;
     }
 
     public static double solve() {
         if (temp[0] > X || temp[N-1] < X)
             return -1;
 
         double maxRate = 0;
         if (average(0, N) >= X) {
             int i = N;
             while (average(0, i) > X + EPS)
                 i--;
             
             if (Math.abs(average(0, i) - X) < EPS) maxRate = total(0, i);
             else maxRate = combine(total(0, i), average(0, i), temp[i]);
         } else {
             int i = 0;
             while (average(i, N) < X - EPS) i++;
             if (Math.abs(average(i, N) - X) < EPS) maxRate = total(i, N);
             else maxRate = combine(total(i, N), average(i, N), temp[i-1]);
         }
         return (V / maxRate);
     }
 
     public static void main(String[] args) throws IOException {
         br = new BufferedReader(new InputStreamReader(System.in));
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             N = nextInt();
             V = nextDouble();
             X = nextDouble();
             rate = new double[N];
             temp = new double[N];
             List<Source> sources = new ArrayList<Source>();
             for (int i = 0; i < N; i++) sources.add(new Source(nextDouble(), nextDouble()));
             Collections.sort(sources, new Comparator<Source>() {
                 @Override
                 public int compare(Source s1, Source s2) {
                     return (int) Math.signum(s1.temp - s2.temp);
                 }
             });
 
             for (int i = 0; i < N; i++) {
                 rate[i] = sources.get(i).rate;
                 temp[i] = sources.get(i).temp;
             }
             double ans = solve();
             if (ans == -1) {
                 System.out.printf("Case #%d: IMPOSSIBLE%n", t);
             } else {
                 System.out.printf("Case #%d: %.9f%n", t, ans);
             }
         }
     }
 }