import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemD {
     BufferedReader rd;
 
     private ProblemD() throws IOException {
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
         int[] q = intarr();
         int x = q[0];
         int r = q[1];
         int c = q[2];
         boolean richardWins;
         if(x == 1) {
             richardWins = false;
         } else if(x > 6) {
             richardWins = true;
         } else if(x == 2) {
             richardWins = (r%2==1) && (c%2==1);
         } else if((r*c)%x != 0) {
             richardWins = true;
         } else if(x == 4) {
             int a = Math.min(r,c);
             int b = Math.max(r,c);
             richardWins = !((a>2) && (b==4));
         } else {
             int a = Math.min(r,c);
             int b = Math.max(r,c);
             richardWins = !((a==2&&b==3) || (a==3&&b==3) || (a==3&&b==4));
         }
         return richardWins?"RICHARD":"GABRIEL";
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
         new ProblemD();
     }
 }
