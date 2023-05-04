import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 
 public class ProblemB2 {
     BufferedReader rd;
     int best;
 
     private ProblemB2() throws IOException {
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
         rd.readLine();
         int[] a = intarr();
         int[] c = new int[1002];
         for(int i: a) {
             c[i]++;
         }
         best = 1000;
         return Integer.toString(solve(c, 0));
     }
 
     private int solve(int[] c, int m) {
         int first = 0;
         for(int i=c.length-1;i>0;i--) {
             if(c[i] > 0) {
                 first = i;
                 break;
             }
         }
         best = Math.min(best, m + first);
         int res;
         if(m >= best) {
             res = best;
         } else if(first < 1) {
             res = m;
         } else if(c[first] >= first) {
             res = m + first;
         } else {
             res = m+first;
             if(first > 3) {
                 for(int k=1;k<=first/2;k++) {
                     int[] d = Arrays.copyOf(c, c.length);
                     int p = d[first];
                     d[first] = 0;
                     d[k] += p;
                     d[first-k] += p;
                     res = Math.min(res, solve(d, m+p));
                 }
             }
             int[] e = Arrays.copyOf(c, c.length);
             System.arraycopy(e,1,e,0,e.length-1);
             res = Math.min(res, solve(e, m+1));
         }
         return res;
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
         new ProblemB2();
     }
 }
