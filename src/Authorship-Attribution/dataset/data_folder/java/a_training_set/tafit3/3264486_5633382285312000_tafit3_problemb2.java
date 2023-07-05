import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 import static java.util.Arrays.fill;
 
 public class ProblemB2 {
     BufferedReader rd;
 
     ProblemB2() throws IOException {
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
         return solve(rd.readLine().trim());
     }
 
     private String solve(String s) {
         char[] c = s.toCharArray();
         int n = c.length;
         int i = 1;
         while(i < n && c[i] >= c[i-1]) i++;
         if(i < n) {
             if(c[i-1] != '1') {
                 i--;
                 while(i > 0 && c[i-1] == c[i]) i--;
                 c[i]--;
                 fill(c,i+1,n,'9');
             } else {
                 char[] z = new char[n-1];
                 fill(z, '9');
                 return new String(z);
             }
         }
         return new String(c);
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
         new ProblemB2();
     }
 }
