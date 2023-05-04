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
         String[] h = split(rd.readLine());
         String s = h[1];
         int res = 0;
         int standing = 0;
         for(int i=0;i<s.length();i++) {
             if(s.charAt(i) > '0') {
                 if(standing < i) {
                     int diff = i-standing;
                     standing += diff;
                     res += diff;
                 }
             }
             standing += s.charAt(i)-'0';
         }
         return Integer.toString(res);
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
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
