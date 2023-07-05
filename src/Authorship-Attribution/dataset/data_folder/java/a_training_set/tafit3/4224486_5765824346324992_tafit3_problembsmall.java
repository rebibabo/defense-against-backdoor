import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemBSmall {
     BufferedReader rd;
 
     private ProblemBSmall() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ": " + solve());
         }
     }
 
     private String solve() throws IOException {
         int[] a = intarr();
         int[] m = intarr();
         int b = a[0];
         long n = a[1]-1;
         if(b == 1) {
             return "1";
         } else {
             long g = m[0];
             long x = m[0];
             for(int k=1;k<m.length;k++) {
                 g = gcd(g,m[k]);
                 x *= m[k];
             }
             long nww = x / g;
             long cnt = 0;
             for(int k=0;k<m.length;k++) {
                 cnt += nww / m[k];
             }
             long rest = n % cnt;
             long[] end = new long[b];
             long y = 0;
             long t = 0;
             while(true) {
                 long next = Long.MAX_VALUE;
                 for(int j=0;j<b;j++) {
                     if(t == end[j]) {
                         end[j] += m[j];
                         if(y == rest) {
                             return Integer.toString(j+1);
                         }
                         y++;
                     }
                     next = Math.min(end[j], next);
                 }
                 t = next;
             }
         }
     }
 
     public static long gcd(long a, long b) {
         while (b > 0) {
             long c = a % b;
             a = b;
             b = c;
         }
         return a;
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private int[] intarr() throws IOException {
         return intarr(rd.readLine());
     }
 
     private int[] intarr(String s) {
         String[] q = split(s);
         int n = q.length;
         int[] a = new int[n];
         for(int i=0;i<n;i++) {
             a[i] = Integer.parseInt(q[i]);
         }
         return a;
     }
 
     private String[] split(String s) {
         int n = s.length();
         int sp = 0;
         for(int i=0;i<n;i++) {
             if(s.charAt(i)==' ') {
                 sp++;
             }
         }
         String[] res = new String[sp+1];
         int last = 0;
         int x = 0;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(c == ' ') {
                 res[x++] = s.substring(last,i);
                 last = i+1;
             }
         }
         res[x] = s.substring(last,n);
         return res;
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemBSmall();
     }
 }
