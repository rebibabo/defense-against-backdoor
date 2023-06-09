
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class B {
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int k = nextInt();
         int[] s = new int[n - k + 1];
         for (int i = 0; i < n - k + 1; i++) {
             s[i] = nextInt();
         }
         int[] h = new int[k];
         int ss = 0;
         for (int i = 0; i < k; i++) {
             int c = 0;
             int min = 0;
             int max = 0;
             for (int j = i + k; j < n; j += k) {
                 c = c - s[j - k] + s[j - k + 1];
                 min = Math.min(min, c);
                 max = Math.max(max, c);
             }
             ss = ss - min;
             h[i] = max - min;
         }
         ss = (s[0] - ss) % k;
         if (ss < 0) ss += k;
         int maxh = 0;
         for (int i = 0; i < k; i++) maxh = Math.max(maxh, h[i]);
         int tt = 0;
         for (int i = 0; i < k; i++) {
             tt += maxh - h[i];
         }
         if (ss <= tt) {
             return maxh;
         } else {
             return maxh + 1;
         }
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