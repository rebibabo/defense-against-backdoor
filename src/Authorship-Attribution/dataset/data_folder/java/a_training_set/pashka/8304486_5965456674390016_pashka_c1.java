
 import java.io.*;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class C1 {
 
     private int solveTest() throws IOException {
         int n = nextInt();
         int[] q = new int[n];
         int[] e = new int[n * 2];
         int[] d = new int[n * 2];
         int[] l = new int[n * 2];
         for (int i = 0; i < 2 * n; i++) {
             e[i] = nextInt() - 1;
             l[i] = nextInt();
             d[i] = nextInt();
         }
 
         int[] t = new int[n];
         int best = Integer.MAX_VALUE;
         for (int m = 0; m < (1 << n); m++) {
             for (int i = 0; i < n; i++) {
                 t[i] = (m >> i) & 1;
             }
             boolean[] z = new boolean[2 * n];
             int time = 0;
             int cur = 0;
             boolean ok = true;
             for (int i = 0; i < 2 * n; i++) {
                 int k = 2 * cur + t[cur];
                 t[cur] ^= 1;
                 if (z[k]) ok = false;
                 z[k] = true;
                 time += ((l[k] - time) % 24 + 24) % 24;
                 time += d[k];
                 cur = e[k];
             }
             if (ok) {
                 best = Math.min(best, time);
             }
         }
         return best;
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