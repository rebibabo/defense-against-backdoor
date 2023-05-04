
 import java.io.*;
 import java.math.BigInteger;
 import java.util.StringTokenizer;
 
 public class B {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int t = nextInt();
 
         init(n);
 
         long q = 0;
         for (int k = 0; k <= n / 2; k++) {
             q += calc(n, k);
             q %= MOD;
         }
 
         long p = 0;
         for (int i = 0; i < 2; i++) {
             int n1 = t - 1 - i;
             int n2 = n - t  - (1 - i);
             if (n1 < 0 || n2 < 0) continue;
             for (int k1 = 0; k1 <= n1 / 2; k1++) {
                 for (int k2 = 0; k2 <= n2 / 2; k2++) {
                     long u = calc(n1, k1) * calc(n2, k2);
 
                     u %= MOD;
                     u *= c(k1 + k2, k1);
                     u %= MOD;
                     u *= (k1 + k2 + 1);
                     u %= MOD;
                     p += u;
                     p %= MOD;
                 }
             }
         }
 
         System.out.println(p + " " + q);
 
         long res = p * inverse(q);
         res %= MOD;
 
         return "" + res;
     }
 
     private long calc(int n, int k) {
         int p = n - 2 * k;
         if (k < p - 1) return 0;
         long res = c(k + 1, p);
         res *= fact[k];
         res %= MOD;
         return res;
     }
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             String res = solveTest();
             System.out.println("Case #" + (i + 1) + ": " + res);
             out.println("Case #" + (i + 1) + ": " + res);
         }
     }
 
     private static final long MOD = (long) (1e9 + 7);
     private static final BigInteger MOD2 = BigInteger.valueOf(MOD);
 
     long[] fact;
     long[] invf;
 
     void init(int n) {
         fact = new long[n + 1];
         invf = new long[n + 1];
         fact[0] = 1;
         invf[0] = 1;
         for (int i = 1; i <= n; i++) {
             fact[i] = fact[i - 1] * i;
             fact[i] %= MOD;
             invf[i] = inverse(fact[i]);
         }
     }
 
     private long inverse(long q) {
         return BigInteger.valueOf(q).modInverse(MOD2).longValue();
     }
 
     private long c(int n, int k) {
         long res = fact[n] * invf[k];
         res %= MOD;
         res *= invf[n - k];
         res %= MOD;
         return res;
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