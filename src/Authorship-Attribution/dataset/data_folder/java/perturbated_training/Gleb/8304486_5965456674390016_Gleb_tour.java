import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 
 public class Tour implements Runnable {
   private static final String NAME = "tour";
 
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
         int c = nextInt();
         int[] e = new int[2 * c];
         int[] l = new int[2 * c];
         int[] d = new int[2 * c];
         for (int i = 0; i < 2 * c; i++) {
           e[i] = nextInt() - 1;
           l[i] = nextInt();
           d[i] = nextInt();
         }
         int r = Integer.MAX_VALUE;
         for (int mask = 0; mask < (1 << c); mask++) {
           
           int[] v = new int[c];
           int res = 0;
           int cc = 0;
           boolean good = true;
           while (cc != 0 || v[cc] != 2) {
             if (v[cc] == 2) {
               good = false;
               break;
             }
             boolean f = (mask & (1 << cc)) == 0;
             int t;
             if (v[cc] == 0) {
               t = f ? 2 * cc : 2 * cc + 1;
             } else {
               t = f ? 2 * cc + 1 : 2 * cc;
             }
             
             int h = res % 24;
             if (h <= l[t]) {
               res += l[t] - h;
             } else {
               res += 24 + l[t] - h;
             }
             res += d[t];
             
             v[cc]++;
             cc = e[t];
           }
           for (int i = 0; i < c; i++) {
             if (v[i] != 2) {
               good = false;
             }
           }
           if (good) {
             
             r = Math.min(r, res);
           }
         }
         out.println("Case #" + test + ": " + r);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Tour()).start();
   }
 }