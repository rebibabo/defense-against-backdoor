
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class B {
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int[] a = new int[n];
         int max = 0;
         for (int i = 0; i < n; i++) {
             a[i] = nextInt();
             max = Math.max(max, a[i]);
         }
         int l = 0;
         int r = max;
         while (r > l + 1) {
             int m = (l + r) / 2;
             boolean ok = false;
             for (int e = 1; e <= m; e++) {
                 int q = 0;
                 for (int j = 0; j < n; j++) {
                     q += ((a[j] + e - 1) / e) - 1;
                 }
                 if ((e + q) <= m)
                     ok = true;
             }
             if (ok) {
                 r = m;
             } else {
                 l = m;
             }
         }
         return r;
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
         br = new BufferedReader(new FileReader(this.getClass().getSimpleName() + ".in"));
         out = new PrintWriter(this.getClass().getSimpleName() + ".out");
         try {
             solve();
         } catch (IOException e) {
             e.printStackTrace();
         }
         out.close();
     }
 
 }