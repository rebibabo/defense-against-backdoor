
 import java.io.*;
 import java.math.BigInteger;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B {
 
     private long MOD = (long) (1e9+7);
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int x = nextInt();
         long[] f = new long[n + 1];
         long[] finv = new long[n + 1];
         f[0] = 1;
         finv[0] = 1;
         for (int i = 1; i <= n; i++) {
             f[i] = (f[i - 1] * i) % MOD;
             finv[i] = BigInteger.valueOf(f[i]).modInverse(BigInteger.valueOf(MOD)).longValue();
         }
         long[] p = new long[n + 1];
         p[0] = 1;
         p[1] = 0;
         for (int i = 2; i <= n; i++) {
             p[i] = (p[i - 1] + p[i - 2]) * (i - 1) % MOD;
         }
 
         long res = 0;
         for (int i = x; i <= n; i++) {
             long q = p[n - i] * finv[i];
             q %= MOD;
             q *= finv[n - i];
             q %= MOD;
             q *= f[n];
             q %= MOD;
             q *= f[n];
             q %= MOD;
 
             res += q;
             res %= MOD;
         }
 
 
 
 
         return (int) res;
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