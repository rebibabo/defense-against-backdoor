import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 
 public class Modern implements Runnable {
   private static final String NAME = "modern";
   private static final long MOD = 1000000007L;
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
         
         int r = nextInt();
         int c = nextInt();
         int n = nextInt();
         long d = nextInt();
         int[] rr = new int[n];
         int[] cc = new int[n];
         long[] dd = new long[n];
         for (int i = 0; i < n; i++) {
           rr[i] = nextInt() - 1;
           cc[i] = nextInt() - 1;
           dd[i] = nextInt();
         }
         long res = 0;
         boolean ok = true;
         for (int i = 0; i < r; i++) {
           for (int j = 0; j < c; j++) {
             Long min = 1L;
             Long max = Long.MAX_VALUE;
             for (int k = 0; k < n; k++) {
               int len = Math.abs(i - rr[k]) + Math.abs(j - cc[k]);
               min = Math.max(min, Math.max(1, dd[k] - len * d));
               max = Math.min(max, dd[k] + len * d);
             }
             if (min > max) {
               ok = false;
             }
             res = (res + max) % MOD;
           }
         }
         if (ok) {
           out.println("Case #" + test + ": " + res);
         } else {
           out.println("Case #" + test + ": IMPOSSIBLE");
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Modern()).start();
   }
 }