
 import java.io.*;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B {
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int p = nextInt();
         int[] r = new int[n];
         for (int i = 0; i < n; i++) {
             r[i] = nextInt();
         }
         int[][] q = new int[n][p];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < p; j++) {
                 q[i][j] = nextInt();
             }
             Arrays.sort(q[i]);
         }
         int res = 0;
         int[] c = new int[n];
         while (true) {
             int lower = Integer.MIN_VALUE;
             for (int i = 0; i < n; i++) {
                 
                 lower = Math.max(lower, (q[i][c[i]] * 10 + r[i] * 11 - 1) / (r[i] * 11));
             }
             boolean ok = true;
             for (int i = 0; i < n; i++) {
                 
                 if (q[i][c[i]] * 10 / (r[i] * 9) < lower) {
                     c[i]++;
                     ok = false;
                 }
             }
             if (ok) {
                 res++;
                 for (int i = 0; i < n; i++) {
                     c[i]++;
                 }
             }
             ok = true;
             for (int i = 0; i < n; i++) {
                 if (c[i] == p) ok = false;
             }
             if (!ok) break;
         }
         return res;
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
         new B().run();
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