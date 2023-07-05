
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class Dsmall {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int l = nextInt();
         String[] g = new String[n];
         for (int i = 0; i < n; i++) {
             g[i] = next();
         }
         String b = next();
         for (int i = 0; i < n; i++) {
             if (g[i].equals(b)) {
                 return "IMPOSSIBLE";
             }
         }
         String x = "";
         for (int i = 0; i < l; i++) {
             x += "0?";
         }
         String y = "";
         for (int i = 0; i < l - 1; i++) {
             y += "1";
         }
         y += "0";
         return x + " " + y;
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
         new Dsmall().run();
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