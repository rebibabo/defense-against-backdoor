import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Core1 implements Runnable {
   private static final String NAME = "core";
 
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
         int k = in.nextInt();
         double U = in.nextDouble();
         double[] p = new double[n];
         for (int i = 0; i < n; i++) {
           p[i] = in.nextDouble();
         }
         Arrays.sort(p);
         int m = 0;
         double[] d = new double[n];
         for (int i = 1; i < n; i++) {
           for (int j = 0; j < i; j++) {
             d[i] += p[i] - p[j];
           }
           if (d[i] <= U) {
             m = i;
           }
         }
         U -= d[m];
         for (int i = 0; i < m; i++) {
           p[i] = p[m];
         }
         double add = U / (m + 1);
         for (int i = 0; i < m + 1; i++) {
           p[i] = Math.min(1, p[i] + add);
         }
         double res = 1;
         for (int i = 0; i < n; i++) {
           res *= p[i];
         }
 
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Core1()).start();
   }
 }