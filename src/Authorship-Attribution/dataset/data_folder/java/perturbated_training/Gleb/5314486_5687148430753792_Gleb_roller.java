import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 
 public class Roller implements Runnable {
   private static final String NAME = "roller";
 
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
         int c = nextInt();
         int m = nextInt();
         int[] nn = new int[n];
         int[] cc = new int[c];
         for (int i = 0; i < m; i++) {
           int p = nextInt() - 1;
           int b = nextInt() - 1;
           nn[p]++;
           cc[b]++;
         }
         int maxC = -1;
         for (int i = 0; i < c; i++) {
           maxC = Math.max(maxC, cc[i]);
         }
 
         int res = -1;
         int minP = 0;
         for (int k = maxC; k <= m; k++) {
           boolean ok = true;
           int d = 0;
           int pp = 0;
           for (int i = 0; i < n; i++) {
             d = d + k - nn[i];
             pp += Math.max(0, nn[i] - k);
             if (d < 0) {
               ok = false;
               break;
             }
           }
           if (ok) {
             res = k;
             minP = pp;
             break;
           }
         }
         out.println("Case #" + test + ": " + res + " " + minP);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Roller()).start();
   }
 }