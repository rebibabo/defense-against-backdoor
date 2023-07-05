import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 import static java.lang.Math.max;
 import static java.lang.Math.min;
 import static java.util.Arrays.fill;
 
 public class ProblemC3 {
     BufferedReader rd;
     int[][][][] e = new int[101][101][101][101];
 
     ProblemC3() throws IOException {
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
         int res = new Solver().solve();
         if(res == -2) {
             return "IMPOSSIBLE";
         } else {
             return Integer.toString(res);
         }
     }
 
     private class Solver {
         int ihd, iad, ihk, iak, b, d;
 
         public int solve() throws IOException {
             int[] a = intarr();
             ihd = a[0];
             iad = a[1];
             ihk = a[2];
             iak = a[3];
             b = a[4];
             d = a[5];
             deepfill(e, -1);
             return compute(ihd,iad,ihk,iak);
         }
 
         private int compute(int hd, int ad, int hk, int ak) {
             int res = e[hd][ad][hk][ak];
             if(res == -1) {
                 if(ad >= hk) {
                     res = 1;
                 } else {
                     int best = Integer.MAX_VALUE;
                     if(hd - ak > 0 && (ak > 0 || ad > 0)) {
                         int b0 = compute(hd - ak, ad, hk - ad, ak);
                         if(b0 >= 0) {
                             best = min(best, 1+b0);
                         }
                     }
                     if(ad < 100 && hd - ak > 0 && b > 0) {
                         int b1 = compute(hd - ak, min(100, ad + b), hk, ak);
                         if(b1 >= 0) {
                             best = min(best, 1+b1);
                         }
                     }
                     if(hd != ihd && ihd - ak > 0 && ihd - ak != hd) {
                         int b2 = compute(ihd - ak, ad, hk, ak);
                         if(b2 >= 0) {
                             best = min(best, 1+b2);
                         }
                     }
                     int ak2 = max(0, ak - d);
                     if(ak > 0 && hd - ak2 > 0 && d > 0) {
                         int b3 = compute(hd - ak2, ad, hk, ak2);
                         if(b3 >= 0) {
                             best = min(best, 1+b3);
                         }
                     }
                     if(best < Integer.MAX_VALUE) {
                         res = best;
                     } else {
                         res = -2;
                     }
                 }
                 e[hd][ad][hk][ak] = res;
             }
             return res;
         }
 
     }
 
     private void deepfill(int[][][][] e, int x) {
         for(int[][][] a: e) {
             deepfill(a, x);
         }
     }
 
     private void deepfill(int[][][] e, int x) {
         for(int[][] a: e) {
             deepfill(a, x);
         }
     }
 
     private void deepfill(int[][] e, int x) {
         for(int[] a: e) {
             fill(a, x);
         }
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
 
     public String[] split(String s) {
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
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemC3();
     }
 }
