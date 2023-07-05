import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Arrays;
 
 public class RatSmall implements Runnable {
   private static final String NAME = "rat";
 
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
       
       
       in = new StreamTokenizer(new BufferedReader(new FileReader(new File(NAME + ".in"))));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int n = nextInt();
         int p = nextInt();
         int[] r = new int[n];
         for (int i = 0; i < n; i++) {
           r[i] = nextInt();
         }
         int[][] pp = new int[n][p];
         int[][] min = new int[n][p];
         int[][] max = new int[n][p];
         for (int i = 0; i < n; i++) {
           for (int j = 0; j < p; j++) {
             pp[i][j] = nextInt();
             max[i][j] = (int) Math.floor(1.0 * pp[i][j] / r[i] / 0.9);
             min[i][j] = (int) Math.ceil(1.0 * pp[i][j] / r[i] / 1.1);
           }
         }
         if (n > 2) {
           throw new IllegalStateException();
         }
         int res = 0;
         if (n == 1) {
           for (int i = 0; i < p; i++) {
             if (min[0][i] <= max[0][i]) {
               res++;
             }
           }
         } else {
           Solve solve = new Solve(p, pp, min, max);
           solve.gen(0);
           res = solve.val;
         }
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   private static class Solve {
     int p;
     int[][] pp;
     int[][] min;
     int[][] max;
 
     int[] perm;
     boolean[] used;
 
     private Solve(int p, int[][] pp, int[][] min, int[][] max) {
       this.p = p;
       this.pp = pp;
       this.min = min;
       this.max = max;
 
       perm = new int[p];
       used = new boolean[p];
     }
 
     void gen(int k) {
       if (k == p) {
         check();
         return;
       }
       for (int i = 0; i < p; i++) {
         if (!used[i]) {
           perm[k] = i;
           used[i] = true;
           gen(k + 1);
           used[i] = false;
         }
       }
     }
 
     int val = 0;
 
     private void check() {
       System.out.println(Arrays.toString(perm));
       int res = 0;
       for (int i = 0; i < p; i++) {
         if (Math.max(min[0][i], min[1][perm[i]]) <= Math.min(max[0][i], max[1][perm[i]])) {
           res++;
         }
       }
       val = Math.max(val, res);
     }
     
   }
   public static void main(String[] args) {
     new Thread(new RatSmall()).start();
   }
 }