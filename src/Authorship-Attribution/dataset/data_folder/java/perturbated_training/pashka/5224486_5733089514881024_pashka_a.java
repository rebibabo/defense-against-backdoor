
 import java.io.*;
 import java.util.HashMap;
 import java.util.StringTokenizer;
 
 public class A {
 
     private int n;
     private int[] a;
     private int[] ps;
 
     private int solveTest() throws IOException {
         String s = next();
         n = s.length();
         a = new int[n];
         for (int i = 0; i < n; i++) {
             a[i] = s.charAt(i) - '0';
         }
         ps = new int[n + 1];
         for (int i = 0; i < n; i++) {
             ps[i + 1] = ps[i] + a[i];
         }
         mem = new HashMap<>();
         return calc(0, n);
     }
 
     private int calc(int l, int r) {
         if (r == l + 1) {
             return a[l];
         }
         if (r == l) {
             return 0;
         }
         Pair pair = new Pair(l, r);
         {
             Integer res = mem.get(pair);
             if (res != null) return res;
         }
         int ll = l;
         int rr = r;
         while (rr > ll + 1) {
             int mm = (ll + rr) >> 1;
             int s1 = ps[mm] - ps[l];
             int s2 = ps[r] - ps[mm + 1];
             if (s1 < s2) {
                 ll = mm;
             } else {
                 rr = mm;
             }
         }
         int res = Math.min(a[ll] + Math.max(calc(l, ll), calc(ll + 1, r)), a[rr] + Math.max(calc(l, rr), calc(rr + 1, r)));
         mem.put(pair, res);
         return res;
     }
 
     HashMap<Pair, Integer> mem;
 
     class Pair {
         int l, r;
         public Pair(int l, int r) {
             this.l = l;
             this.r = r;
         }
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             Pair pair = (Pair) o;
 
             if (l != pair.l) return false;
             return r == pair.r;
 
         }
 
         @Override
         public int hashCode() {
             int result = l;
             result = 31 * result + r;
             return result;
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