import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ProblemB1 {
     BufferedReader rd;
 
     ProblemB1() throws IOException {
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
         int n = pint();
         int[][] e = new int[2*n-1][];
         for(int i=0;i<2*n-1;i++) {
             e[i] = intarr();
         }
         int[] pe = new int[2*n-1];
         boolean[] used = new boolean[2*n-1];
         int cc = 0;
         for(int i=0;i<n;i++) {
             int min = Integer.MAX_VALUE;
             List<Integer> minX = new ArrayList<>();
             for(int j=0;j<2*n-1;j++) {
                 if(!used[j]) {
                     if(e[j][i] < min) {
                         min = e[j][i];
                         minX = new ArrayList<>();
                         minX.add(j);
                     } else if(e[j][i] == min) {
                         minX.add(j);
                     }
                 }
             }
             for(Integer f: minX) {
                 pe[cc] = f;
                 used[f] = true;
                 cc++;
             }
         }
         int[][] d = new int[2*n-1][n];
         for(int i=0;i<2*n-1;i++) {
             System.arraycopy(e[pe[i]],0,d[i],0,n);
         }
         int[][] p = new int[n][n];
         int i = 0;
         int j = 0;
         int[] b = new int[n];
         boolean[] c = new boolean[n];
         int ci = -1;
         int cj = -1;
         while(i >= 0 && i < n) {
             if(j < d.length-1 && d[j][i] == d[j+1][i]) {
                 b[i] = 2;
                 boolean ok = true;
                 int x = c[i]?j+1:j;
                 for(int k=0;k<n;k++) {
                     if(k<i && (ci == -1 || !c[ci] || k != ci)) {
                         if(p[i][k] != d[x][k]) {
                             ok = false;
                             break;
                         }
                     } else {
                         p[i][k] = d[x][k];
                     }
                 }
                 if(!ok) {
                     if(!c[i]) {
                         c[i] = true;
                     } else {
                         while(true) {
                             if (i > 0) {
                                 j -= b[i - 1];
                             }
                             i--;
                             if (i >= 0) {
                                 if (!c[i]) {
                                     c[i] = true;
                                     break;
                                 }
                             } else {
                                 break;
                             }
                         }
                     }
                 } else {
                     x = c[i]?j:j+1;
                     for(int k=0;k<n;k++) {
                         if(k<i && (ci == -1 || c[ci] || k != ci)) {
                             if(p[k][i] != d[x][k]) {
                                 ok = false;
                                 break;
                             }
                         } else {
                             p[k][i] = d[x][k];
                         }
                     }
                     if(!ok) {
                         if(!c[i]) {
                             c[i] = true;
                         } else {
                             while(true) {
                                 if (i > 0) {
                                     j -= b[i - 1];
                                 }
                                 i--;
                                 if (i >= 0) {
                                     if (!c[i]) {
                                         c[i] = true;
                                         break;
                                     }
                                 } else {
                                     break;
                                 }
                             }
                         }
                     } else {
                         j += b[i];
                         i++;
                         if(i < n) {
                             c[i] = false;
                         }
                     }
                 }
             } else {
                 ci = i;
                 cj = j;
                 p[i][i] = d[j][i];
                 b[i] = 1;
                 boolean ok = true;
                 for(int k=0;k<n;k++) {
                     if(k<i) {
                         int z = c[i]?p[i][k]:p[k][i];
                         if(z != d[j][k]) {
                             ok = false;
                             break;
                         }
                     } else {
                         if(c[i]) {
                             p[i][k] = d[j][k];
                         } else {
                             p[k][i] = d[j][k];
                         }
                     }
                 }
                 if(ok) {
                     j++;
                     i++;
                     if(i < n) {
                         c[i] = false;
                     }
                 } else {
                     if(!c[i]) {
                         c[i] = true;
                     } else {
                         while(true) {
                             if (i > 0) {
                                 j -= b[i - 1];
                             }
                             i--;
                             if (i >= 0) {
                                 if (!c[i]) {
                                     c[i] = true;
                                     break;
                                 }
                             } else {
                                 break;
                             }
                         }
                     }
                 }
             }
         }
         boolean ok = false;
         for(int k=0;k<n;k++) {
             if(ci != k && p[ci][k] != d[cj][k]) {
                 ok = true;
                 break;
             }
         }
         StringBuilder buf = new StringBuilder();
         for (int k = 0; k < n; k++) {
             if (buf.length() != 0) {
                 buf.append(' ');
             }
             int z = ok ? p[ci][k] : p[k][ci];
             buf.append(z);
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
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemB1();
     }
 }
