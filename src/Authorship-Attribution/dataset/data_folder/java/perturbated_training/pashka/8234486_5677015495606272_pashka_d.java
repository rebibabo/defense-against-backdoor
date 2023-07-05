
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class D {
 
 
     private int n;
     private int m;
     private int[][] a;
     double res;
 
     private int solveTest() throws IOException {
         n = nextInt();
         m = nextInt();
         a = new int[n][m];
         res = 0;
         bt(0, 0);
         System.out.println(res);
         return (int) Math.round(res);
     }
 
     private void bt(int i, int j) {
         if (j == m) {
             i++;
             j = 0;
         }
         if (i == n) {
             for (i = 0; i < n; i++) {
                 for (j = 0; j < m; j++) {
                     int r = 0;
                     if (i > 0 && a[i - 1][j] == a[i][j]) r++;
                     if (i < n - 1 && a[i + 1][j] == a[i][j]) r++;
                     if (a[i][(j + 1) % m] == a[i][j]) r++;
                     if (a[i][(j + m - 1) % m] == a[i][j]) r++;
                     if (a[i][j] != r) return;
                 }
             }
             int c = 0;
             for (int w = 0; w < m; w++) {
                 boolean ok = true;
                 for (i = 0; i < n; i++) {
                     for (j = 0; j < m; j++) {
                         if (a[i][j] != a[i][(j+w)%m]) ok = false;
                     }
                 }
                 if (ok) c++;
             }
 
 
 
 
 
 
 
             res += 1.0 * c / m;
             return;
         }
         for (a[i][j] = 1; a[i][j] <= 3; a[i][j]++) {
             if (i > 0) {
                 if (!check(a, i - 1, j)) continue;
             }
             bt(i, j + 1);
         }
     }
 
     private boolean check(int[][] a, int i, int j) {
         int r = 0;
         if (i > 0 && a[i - 1][j] == a[i][j]) r++;
         if (i < n - 1 && a[i + 1][j] == a[i][j]) r++;
         if (a[i][(j + 1) % m] == a[i][j]) r++;
         if (a[i][(j + m - 1) % m] == a[i][j]) r++;
         return  (a[i][j] == r);
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
         new D().run();
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