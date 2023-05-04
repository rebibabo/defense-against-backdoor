
 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 
 public class D {
 
     private String solveTest() throws IOException {
         long k = nextInt();
         long c = nextInt();
         long s = nextInt();
         if (s * c < k) {
             return "IMPOSSIBLE";
         }
         List<String> res = new ArrayList<>();
         for (int i = 0; i < k; i += c) {
             long q = 0;
             for (int j = i; j < Math.min(k, i + c); j++) {
                 q = q * k + j;
             }
             res.add("" + (q + 1));
         }
         return String.join(" ", res);
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