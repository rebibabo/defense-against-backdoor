
 import java.io.*;
 import java.util.HashSet;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 public class A {
 
     private String solveTest() throws IOException {
         long n = nextInt();
         if (n == 0) {
             return "INSOMNIA";
         }
         Set<Character> d = new HashSet<>();
         long cur = n;
         while (true) {
             String s = "" + cur;
             for (int i = 0; i < s.length(); i++) {
                 d.add(s.charAt(i));
             }
             if (d.size() == 10) break;
             cur += n;
         }
         return "" + cur;
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
         new A().run();
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