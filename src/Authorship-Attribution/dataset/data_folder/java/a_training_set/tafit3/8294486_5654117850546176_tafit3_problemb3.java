import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 import static java.util.Arrays.*;
 
 public class ProblemB3 {
     BufferedReader rd;
 
     ProblemB3() throws IOException {
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
         int[] c = new int[]{a[1], a[3], a[5]};
         int[] res = solve(c);
         if(res != null) {
             return conv(res);
         }
         return "IMPOSSIBLE";
     }
 
     private int[] solve(int[] w) {
         int n = w[0] + w[1] + w[2];
         int[] c = copyOf(w, w.length);
         int[] res = new int[n];
         fill(res,-1);
         int k = 0;
         for(int i=0;i<3;i++) {
             int ma = 0;
             for (int j = 1; j <= 2; j++) {
                 if (c[j] > c[ma]) {
                     ma = j;
                 }
             }
             for(int e=0;e<c[ma];e++) {
                 res[k] = ma;
                 k = k+2;
                 if(k >= n) {
                     k = 1;
                 }
             }
             c[ma] = 0;
         }
         for(int i=0;i<n;i++) {
             int next = (i+1)%n;
             if(res[i]==res[next]) {
                 return null;
             }
         }
         return res;
     }
 
     char[] colors = "RYB".toCharArray();
 
     private String conv(int[] res) {
         StringBuilder buf = new StringBuilder();
         for(int x: res) {
             buf.append(colors[x]);
         }
         return buf.toString();
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
         new ProblemB3();
     }
 }
