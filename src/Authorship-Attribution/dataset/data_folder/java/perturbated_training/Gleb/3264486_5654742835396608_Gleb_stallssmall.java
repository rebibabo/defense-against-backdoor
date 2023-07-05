import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 
 public class StallsSmall implements Runnable {
   private static final String NAME = "stalls";
 
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
         int k = nextInt();
 
         boolean[] o = new boolean[n + 2];
         o[0] = o[n + 1] = true;
 
         int resMin = -1;
         int resMax = -1;
         for (int i = 0; i < k; i++) {
           int[] l = new int[n + 2];
           int curL = 0;
           for (int j = 0; j < n + 2; j++) {
             if (o[j]) {
               curL = 0;
             } else {
               l[j] = curL;
               curL++;
             }
           }
 
           int[] r = new int[n + 2];
           int curR = 0;
           for (int j = n + 1; j >= 0; j--) {
             if (o[j]) {
               curR = 0;
             } else {
               r[j] = curR;
               curR++;
             }
           }
 
           int p = -1;
           int min = -1;
           int max = -1;
           for (int j = 0; j < n + 2; j++) {
             if (!o[j]) {
               int jMin = Math.min(l[j], r[j]);
               int jMax = Math.max(l[j], r[j]);
               if (jMin > min || (jMin == min && jMax > max)) {
                 p = j;
                 min = jMin;
                 max = jMax;
               }
             }
           }
           o[p] = true;
           resMin = min;
           resMax = max;
         }
         out.println("Case #" + test + ": " + resMax + " " + resMin);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new StallsSmall()).start();
   }
 }
