import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemA {
     BufferedReader rd;
 
     private ProblemA() throws IOException {
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
         int s1 = 0;
         int max = 0;
         for(int i=1;i<a.length;i++) {
             if(a[i]-a[i-1] < 0) {
                 s1 += a[i-1]-a[i];
                 max = Math.max(max, a[i-1]-a[i]);
             }
         }
         int s2 = 0;
         for(int i=0;i<a.length-1;i++) {
             if(a[i] >= max) {
                 s2 += max;
             } else {
                 s2 += a[i];
             }
         }
         return s1+" "+s2;
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
         new ProblemA();
     }
 }
