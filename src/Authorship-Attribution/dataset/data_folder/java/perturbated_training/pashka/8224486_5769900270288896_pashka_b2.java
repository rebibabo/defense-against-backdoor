
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class B2 {
 
     private int solveTest() throws IOException {
         int r = nextInt();
         int c = nextInt();
         int n = nextInt();
         int m = r * c;
         int res = Integer.MAX_VALUE;
         for (int x = 0; x < (1 << m); x++) {
             if (Integer.bitCount(x) == n) {
                 int s = 0;
                 for (int i = 0; i < r; i++) {
                     for (int j = 0; j < c; j++) {
                         int t = i * c + j;
                         if ((x & (1 << t)) > 0) {
                             if (i < r - 1 && ((x & (1 << (t + c))) > 0)) {
                                 s++;
                             }
                             if (j < c - 1 && ((x & (1 << (t + 1))) > 0)) {
                                 s++;
                             }
                         }
                     }
                 }
                 res = Math.min(res, s);
             }
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
         new B2().run();
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