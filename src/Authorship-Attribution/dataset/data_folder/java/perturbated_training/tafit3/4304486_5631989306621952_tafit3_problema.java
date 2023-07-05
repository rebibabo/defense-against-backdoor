import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemA {
     BufferedReader rd;
 
     ProblemA() throws IOException {
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
         char[] s = rd.readLine().toCharArray();
         StringBuilder buf = new StringBuilder();
         for(char c: s) {
             if(buf.length() == 0) {
                 buf.append(c);
             } else {
                 if(c>=buf.charAt(0)) {
                     buf.insert(0, c);
                 } else {
                     buf.append(c);
                 }
             }
         }
         return buf.toString();
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
         new ProblemA();
     }
 }
