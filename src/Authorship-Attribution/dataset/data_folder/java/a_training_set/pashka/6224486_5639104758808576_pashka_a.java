
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class A {
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             int res = solveTest();
             System.out.println("Case #" + (i + 1) + ": " + res);
             out.println("Case #" + (i + 1) + ": " + res);
         }
     }
 
     private int solveTest() throws IOException {
         int n = nextInt();
         String s = next();
         int l = -1;
         int r = n;
         while (r > l + 1) {
             int m = (l + r) / 2;
             int k = m;
             boolean ok = true;
             for (int i = 0; i <= n; i++) {
                 if (k >= i) {
                     k += s.charAt(i) - '0';
                 } else {
                     ok = false;
                 }
             }
             if (ok) {
                 r = m;
             } else {
                 l = m;
             }
         }
         return r;
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