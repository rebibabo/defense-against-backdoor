import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 import static java.lang.Math.*;
 import static java.util.Arrays.*;
 
 public class ProblemC1 {
     BufferedReader rd;
 
     ProblemC1() throws IOException {
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
         int n = a[0];
         int q = a[1];
         int[][] h = new int[n][];
         for(int i=0;i<n;i++) {
             h[i] = intarr();
         }
         int[][] d = new int[n][];
         for(int i=0;i<n;i++) {
             d[i] = intarr();
         }
         int[][] qs = new int[q][];
         for(int i=0;i<q;i++) {
             qs[i] = intarr();
             qs[i][0]--;
             qs[i][1]--;
         }
         long[] dist = new long[n];
         for(int i=n-2;i>=0;i--) {
             dist[i] = dist[i+1] + d[i][i+1];
         }
         double[] r = new double[n];
         fill(r,1e20);
         r[n-1] = 0;
         for(int i=n-2;i>=0;i--) {
             for(int j=i+1;j<n;j++) {
                 long singleDist = dist[i] - dist[j];
                 if(h[i][0] >= singleDist) {
                     double time = ((double)singleDist) / h[i][1];
                     time += r[j];
                     r[i] = min(r[i], time);
                 } else {
                     break;
                 }
             }
         }
         return Double.toString(r[0]);
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
         if(s == null) {
             return new String[0];
         }
         int n = s.length();
         int start = -1;
         int end = 0;
         int sp = 0;
         boolean lastWhitespace = true;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(isWhitespace(c)) {
                 lastWhitespace = true;
             } else {
                 if(lastWhitespace) {
                     sp++;
                 }
                 if(start == -1) {
                     start = i;
                 }
                 end = i;
                 lastWhitespace = false;
             }
         }
         if(start == -1) {
             return new String[0];
         }
         String[] res = new String[sp];
         int last = start;
         int x = 0;
         lastWhitespace = true;
         for(int i=start;i<=end;i++) {
             char c = s.charAt(i);
             boolean w = isWhitespace(c);
             if(w && !lastWhitespace) {
                 res[x++] = s.substring(last,i);
             } else if(!w && lastWhitespace) {
                 last = i;
             }
             lastWhitespace = w;
         }
         res[x] = s.substring(last,end+1);
         return res;
     }
 
     private boolean isWhitespace(char c) {
         return c==' ' || c=='\t';
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemC1();
     }
 }
