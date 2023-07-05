import java.io.*;
 import java.util.Scanner;
 
 public class Drum implements Runnable {
   private static final String NAME = "drum";
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       long mod = 1000000007;
       for (int test = 1; test <= tests; test++) {
         int n = in.nextInt();
         int m = in.nextInt();
         
         long[][][] r = new long[n + 1][2][2];
         r[0][0][0] = 1;
         r[0][1][0] = 1;
 
         r[1][0][0] = 1;
         r[1][1][0] = 0;
 
         
         boolean o = m % 6 == 0;
         if (o) {
           r[2][0][1] = 1;
         }
         r[2][1][0] = 1;
 
         for (int i = 3; i <= n; i++) {
           r[i][1][0] = r[i - 2][0][0];
           r[i][1][1] = r[i - 2][0][1];
           r[i][0][0] = r[i - 1][1][0];
           r[i][0][1] = r[i - 1][1][1];
           if (o) {
             r[i][0][1] += 6 * r[i - 2][1][1] + r[i - 2][1][0];
           }
           r[i][0][1] = r[i][0][1] % mod;
         }
         long l = r[n][0][0] + r[n][1][0] + r[n][0][1] + r[n][1][1];
         out.println("Case #" + test + ": " + l);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Drum()).start();
   }
 }
