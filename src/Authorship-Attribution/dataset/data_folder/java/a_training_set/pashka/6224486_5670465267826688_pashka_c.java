
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class C {
 
     private String solveTest() throws IOException {
         int l = nextInt();
         int x = nextInt();
         String s = next();
         String q = "1ijk";
         String[] a = {
                 "1 i j k",
                 "i -1 k -j",
                 "j -k -1 i",
                 "k j -i -1"
         };
         int[][] m = new int[8][8];
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 boolean k = false;
                 if (i >= 4) k = !k;
                 if (j >= 4) k = !k;
                 String ss = a[i % 4].split(" ")[j % 4];
                 if (ss.length() > 1) k = !k;
                 int r = q.indexOf(ss.charAt(ss.length() - 1));
                 if (k) r += 4;
                 m[i][j] = r;
             }
         }
         if (x >= 16) {
             x = (x - 16) % 4 + 16;
         }
         int n = x * l;
         int p = 0;
         int c = 0;
         for (int i = 0; i < n; i++) {
             p = m[p][q.indexOf(s.charAt((i % l)))];
             if (c == 0 && p == 1) {
                 c++;
             }
             if (c == 1 && p == m[1][2]) {
                 c++;
             }
         }
         if (c < 2 || p != m[m[1][2]][3]) {
             return "NO";
         }
         return "YES";
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
         new C().run();
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