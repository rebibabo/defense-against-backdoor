
 import java.io.*;
 import java.util.HashSet;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 public class A {
 
     private int l;
     private HashSet<String> cnt;
     private HashSet<String> nc;
 
     private int solveTest() throws IOException {
         String s = next();
         l = s.length();
         cnt = new HashSet<>();
         nc = new HashSet<>();
         go(s);
         return cnt.size();
     }
 
     int[] a = new int[10];
 
     private void go(String s) {
         if (cnt.contains(s)) return;
         cnt.add(s);
 
         int ss = 0;
         for (int i = 0; i < l; i++) {
             a[i + 1] = s.charAt(i) - '0';
             ss += a[i + 1];
         }
         a[0] = l - ss;
         if (a[0] >= 0) {
             nc = new HashSet<>();
             bt(0);
 
             Set<String> q = nc;
             for (String s1 : q) {
                 go(s1);
             }
         }
     }
 
     char[] b = new char[10];
 
     private void bt(int i) {
         if (i == l) {
             String s = new String(b, 0, l);
             nc.add(s);
             return;
         }
         for (int x = 0; x <= l; x++) {
             if (a[x] > 0) {
                 a[x]--;
                 b[i] = (char) ('0' + x);
                 bt(i + 1);
                 a[x]++;
             }
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