import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 import static java.lang.System.arraycopy;
 
 public class ProblemA1 {
     BufferedReader rd;
 
     ProblemA1() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ":");
             solve();
         }
     }
 
     private void solve() throws IOException {
         int[] a = intarr();
         int r = a[0];
         int c = a[1];
         char[][] d = new char[r][];
         for(int i=0;i<r;i++) {
             d[i] = rd.readLine().trim().toCharArray();
         }
         for(int i=0;i<r;i++) {
             char last = 0;
             for(int j=0;j<c;j++) {
                 if(d[i][j] != '?') {
                     last = d[i][j];
                     int k = j-1;
                     while(k >= 0) {
                         if(d[i][k] == '?') {
                             d[i][k] = last;
                         } else {
                             break;
                         }
                         k--;
                     }
                 }
             }
             if(last != 0) {
                 int k = c-1;
                 while(k >= 0) {
                     if(d[i][k] == '?') {
                         d[i][k] = last;
                     } else {
                         break;
                     }
                     k--;
                 }
             }
         }
         boolean changed = true;
         while(changed) {
             changed = false;
             for(int i=0;i<r;i++) {
                 if(d[i][0] == '?') {
                     int ref = -1;
                     if(i > 0) {
                         if(d[i-1][0] != '?') {
                             ref = i-1;
                         }
                     }
                     if(ref == -1) {
                         if (i < r - 1) {
                             if (d[i + 1][0] != '?') {
                                 ref = i + 1;
                             }
                         }
                     }
                     if(ref != -1) {
                         arraycopy(d[ref],0,d[i],0,c);
                         changed = true;
                     }
                 }
             }
         }
         for(int i=0;i<r;i++) {
             out(new String(d[i]));
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
         new ProblemA1();
     }
 }
