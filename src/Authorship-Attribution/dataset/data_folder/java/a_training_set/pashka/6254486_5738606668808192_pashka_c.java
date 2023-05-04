
 import java.io.*;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Random;
 import java.util.StringTokenizer;
 
 public class C {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int J = nextInt();
         Random random = new Random();
         List<String> res = new ArrayList<>();
         while (res.size() < J) {
             String s = "1";
             for (int i = 0; i < n - 2; i++) s += random.nextInt(2);
             s += '1';
             boolean ok = true;
             int[] div = new int[9];
             for (int i = 2; i <= 10; i++) {
                 BigInteger q = new BigInteger(s, i);
                 for (int j = 2; j < 100; j++) {
                     if (q.mod(BigInteger.valueOf(j)).signum() == 0) {
                         div[i - 2] = j;
                         break;
                     }
                 }
                 if (div[i - 2] == 0) {
                     ok = false;
                     break;
                 }
             }
             if (ok) {
                 String ss = s;
                 for (int i : div) {
                     ss += " " + i;
                 }
                 res.add(ss);
             }
         }
         return "\n" + String.join("\n", res);
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