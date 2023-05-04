import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Scanner;
 
 public class Match implements Runnable {
   private static final String NAME = "match";
 
   String[] solve(String c, String j) {
     int k = 1;
     for (int i = 0; i < c.length(); i++) {
       if (c.charAt(i) == '?') k*=10;
     }
     for (int i = 0; i < j.length(); i++) {
       if (j.charAt(i) == '?') k*=10;
     }
     int res = Integer.MAX_VALUE / 2;
     int rc = -1;
     int rj = -1;
     for (int m = 0; m < k; m++) {
       int cc = 0;
       int jj = 0;
       int curM = m;
       for (int i = 0; i < c.length(); i++) {
         if (c.charAt(i) == '?') {
           cc = cc * 10 + (curM % 10);
           curM /= 10;
         } else {
           cc = cc * 10 + (c.charAt(i) - '0');
         }
       }
       for (int i = 0; i < j.length(); i++) {
         if (j.charAt(i) == '?') {
           jj = jj * 10 + (curM % 10);
           curM /= 10;
         } else {
           jj = jj * 10 + (j.charAt(i) - '0');
         }
       }
       int curRes = Math.abs(cc - jj);
       if (curRes < res) {
         res = curRes;
         rc = cc;
         rj = jj;
       } else {
         if (curRes == res) {
           if (cc < rc || (cc == rc && jj < rj)) {
             res = curRes;
             rc = cc;
             rj = jj;
           }
         }
       }
     }
     if (rc == -1) {
       throw new IllegalStateException();
     }
     String resC = Integer.toString(rc);
     String resJ = Integer.toString(rj);
     while (resC.length() != c.length()) {
       resC = "0" + resC;
     }
     while (resJ.length() != j.length()) {
       resJ = "0" + resJ;
     }
     return new String[] { resC, resJ };
   }
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         
         String c = in.next();
         String j = in.next();
         String[] res = solve(c, j);
         out.println("Case #" + test + ": " + res[0] + " " + res[1]);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Match()).start();
   }
 }
