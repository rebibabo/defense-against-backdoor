import java.io.*;
 import java.util.Scanner;
 
 public class PoolDumb implements Runnable {
   private static final String NAME = "pool";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
       
       
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         
         int n = in.nextInt();
         long v = (long) (10000 * in.nextDouble());
         double x = (long) (10000 * in.nextDouble());
         double[] r = new double[n];
         double[] c = new double[n];
         for (int i = 0; i < n; i++) {
           r[i] = (long) (10000 * in.nextDouble());
           c[i] = (long) (10000 * in.nextDouble());
         }
         if (n == 1) {
           if (c[0] == x) {
             out.println("Case #" + test + ": " + (1.0 * v / r[0]));
           } else {
             out.println("Case #" + test + ": IMPOSSIBLE");
           }
         } else if (n == 2) {
           double res0 = Double.MAX_VALUE;
           if (c[0] == x) {
             res0 = 1.0 * v / r[0];
           }
           double res1 =  Double.MAX_VALUE;
           if (c[1] == x) {
             res1 = 1.0 * v / r[1];
           }
           if (res0 != Double.MAX_VALUE || res1 != Double.MAX_VALUE) {
             out.println("Case #" + test + ": " + Math.min(res0, res1));
             continue;
           }
           double y = (x - c[1]) / (c[0] - x);
           if ((x - c[1]) * (c[0] - x) < 0) {
             out.println("Case #" + test + ": IMPOSSIBLE");
             continue;
           }
           double b = v / (1 + y);
           double a = v - b;
           out.println("Case #" + test + ": " + Math.max(1.0 * v * (c[1] - x) / ((c[1] - c[0]) * r[0]), 1.0 * v * (c[0] - x) / ((c[0] - c[1]) * r[1])));
         } else {
           throw new IllegalStateException();
         }
       }
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new PoolDumb()).start();
   }
 }
