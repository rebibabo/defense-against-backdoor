import java.io.BufferedInputStream;
 import java.text.NumberFormat;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class TaskC {
 
     public static void main(String[] args) {
         NumberFormat nf = NumberFormat.getInstance(Locale.US);
         nf.setMinimumFractionDigits(6);
         nf.setMaximumFractionDigits(6);
         nf.setGroupingUsed(false);
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int q = sc.nextInt();
             int[] e = new int[n];
             int[] s = new int[n];
             for (int j = 0; j < n; j++) {
                 e[j] = sc.nextInt();
                 s[j] = sc.nextInt();
             }
             int[][] d = readInts(sc, n, n);
             int u = sc.nextInt();
             int v = sc.nextInt();
             int[] dist = new int[n-1];
             for (int j = 0; j < n-1; j++) {
                 dist[j] = d[j][j+1];
             }
             double res = calc(0, e[0], s[0], 0., dist, e, s);
             print(i, nf.format(res));
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static double calc(int curr, int ce, int cs, double time, int[] dist, int[] e, int[] s) {
         if (curr == e.length - 1) {
             return time;
         }
         double res = Double.MAX_VALUE;
         if (ce > dist[curr]) {
             res = calc(curr+1, ce - dist[curr], cs, time + dist[curr] / ((double) cs), dist, e, s);
         }
         if (e[curr] > dist[curr]) {
             res = Math.min(res, calc(curr+1, e[curr] - dist[curr], s[curr], time + dist[curr]/ ((double) s[curr]), dist, e, s));
         }
         return res;
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[][] readInts(Scanner sc, int n, int m) {
         int[][] result = new int[n][m];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 result[i][j] = sc.nextInt();
             }
         }
         return result;
     }
 
 }
