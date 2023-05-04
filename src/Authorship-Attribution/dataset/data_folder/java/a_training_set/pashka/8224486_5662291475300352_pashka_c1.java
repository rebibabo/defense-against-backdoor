
 import java.io.*;
 import java.math.BigInteger;
 import java.util.StringTokenizer;
 
 public class C1 {
 
     private static final int SIZE = 500000;
     long[] a = new long[SIZE];
     long[] b = new long[SIZE];
     int n;
 
     private int solveTest2() {
         int res = n;
         for (int x = 1; x < (1 << n); x++) {
 
             long max = 0;
             for (int i = 0; i < n; i++) {
                 if ((x & (1 << i)) > 0) {
                     long t = b[i] * (360 - a[i]);
 
                     if (t > max) {
                         max = t;
                     }
                 }
             }
 
             boolean ok = true;
             for (int i = 0; i < n; i++) {
 
                     long t = b[i] * (720 - a[i]);
 
                     if (t <= max) {
                         ok = false;
                     }
 
             }
             if (ok) {
                 res = Math.min(res, n - Integer.bitCount(x));
             }
         }
         return res;
     }
 
     class Fraction implements Comparable<Fraction> {
         BigInteger p, q;
 
         public Fraction(BigInteger p, BigInteger q) {
             BigInteger d = p.gcd(q);
             this.p = p.divide(d);
             this.q = q.divide(d);
         }
 
         public int compareTo(Fraction o) {
             return p.multiply(o.q).compareTo(o.p.multiply(q));
         }
 
         @Override
         public String toString() {
             return p + "/" + q;
         }
     }
 
 
     private int solveTest() throws IOException {
         int m = nextInt();
         n = 0;
         for (int i = 0; i < m; i++) {
             int d = nextInt();
             int h = nextInt();
             int mm = nextInt();
             for (int j = 0; j < h; j++) {
                 a[n] = d;
                 b[n] = mm++;
                 n++;
             }
         }
         return solveTest2();
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
         new C1().run();
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