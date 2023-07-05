
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class A {
 
     private static final int SIZE = 10000000;
     int[] q = new int[SIZE];
     boolean[] z = new boolean[SIZE];
     int[] d = new int[SIZE];
 
     void init() {
         q[0] = 1;
         d[1] = 1;
         z[1] = true;
         int h = 0;
         int t = 1;
         while (t > h) {
             int i = q[h++];
             if (i + 1 < SIZE && !z[i + 1]) {
                 z[i + 1] = true;
                 d[i + 1] = d[i] + 1;
                 q[t++] = i + 1;
             }
             int j = reverse(i);
             if (j < SIZE && !z[j]) {
                 z[j] = true;
                 d[j] = d[i] + 1;
                 q[t++] = j;
             }
         }
     }
 
     private int solveTest() throws IOException {
         int n = nextInt();
         return d[n];
     }
 
     private int reverse(int i) {
         return Integer.parseInt(new StringBuffer().append(i).reverse().toString());
     }
 
     private void solve() throws IOException {
         init();
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