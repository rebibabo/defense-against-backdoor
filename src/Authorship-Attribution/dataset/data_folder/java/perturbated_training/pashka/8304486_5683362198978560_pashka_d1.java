
 import java.io.*;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class D1 {
 
     private String solveTest() throws IOException {
         int r = nextInt();
         int c = nextInt();
         int n = nextInt();
         int d = nextInt();
 
         long[][] st = new long[r][c];
         for (long[] ints : st) {
             Arrays.fill(ints, Long.MAX_VALUE / 2);
         }
         for (int i = 0; i< n; i++) {
             int x = nextInt() - 1;
             int y = nextInt() - 1;
             st[x][y] = nextInt();
         }
         long[][] cc = new long[r][c];
         for (int i = 0; i < r; i++) {
             for (int j = 0; j < c; j++) {
                 cc[i][j] = st[i][j];
             }
         }
         for (int tt = 0; tt < r + c; tt++) {
             for (int i = 0; i < r; i++) {
                 for (int j = 0; j < c; j++){
                     if (i < r - 1) {
                         cc[i][j] = Math.min(cc[i][j], cc[i + 1][j] + d);
                         cc[i + 1][j] = Math.min(cc[i][j] + d, cc[i + 1][j]);
                     }
                     if (j < c - 1) {
                         cc[i][j] = Math.min(cc[i][j], cc[i][j + 1] + d);
                         cc[i][j + 1] = Math.min(cc[i][j] + d, cc[i][j + 1]);
                     }
                 }
             }
         }
 
 
 
 
 
 
 
 
         long s = 0;
         for (int i = 0; i < r; i++) {
             for (int j = 0; j < c; j++) {
                 s += cc[i][j];
                 s %= (long)(1e9 + 7);
                 if (st[i][j] != Long.MAX_VALUE / 2 && cc[i][j] != st[i][j]) return "IMPOSSIBLE";
             }
         }
         return "" + s;
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
         new D1().run();
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