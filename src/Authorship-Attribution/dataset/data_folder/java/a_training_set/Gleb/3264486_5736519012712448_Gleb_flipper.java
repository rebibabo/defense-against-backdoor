import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 
 public class Flipper implements Runnable {
   private static final String NAME = "flipper";
 
   private BufferedReader in;
 
   @Override
   public void run() {
     try {
       in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String[] ss = in.readLine().split(" ");
         int n = ss[0].length();
         boolean[] c = new boolean[n];
         for (int i = 0; i < n; i++) {
           c[i] = ss[0].charAt(i) == '+';
         }
         int k = Integer.parseInt(ss[1]);
 
         int res = 0;
         for (int i = 0; i + k <= n; i++) {
           if (!c[i]) {
             res++;
             for (int j = i; j < i + k; j++) {
               c[j] = !c[j];
             }
           }
         }
         boolean ok = true;
         for (int i = 0; i < n; i++) {
           ok &= c[i];
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
     new Thread(new Flipper()).start();
   }
 }
