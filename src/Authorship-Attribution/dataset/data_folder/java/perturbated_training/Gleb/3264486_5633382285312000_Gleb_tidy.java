import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 public class Tidy implements Runnable {
   private static final String NAME = "tidy";
 
   private  BufferedReader in;
 
   @Override
   public void run() {
     try {
       in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String n = in.readLine();
         List<Integer> d = new ArrayList<Integer>();
         for (int i = 0; i < n.length(); i++) {
           d.add(n.charAt(i) - '0');
         }
 
         int p = 0;
         while (p < d.size() - 1 && d.get(p) <= d.get(p + 1)) {
           p++;
         }
 
         if (p == d.size() - 1) {
           out.println("Case #" + test + ": " + n);
         } else {
           int pp = p;
           while (pp > 0 && d.get(pp).equals(d.get(pp - 1))) {
             pp--;
           }
 
           d.set(pp, d.get(pp) - 1);
           for (int i = pp + 1; i < d.size(); i++) {
             d.set(i, 9);
           }
 
           String res = "";
           for (Integer dd : d) {
             if (dd > 0) {
               res += (char) ('0' + dd);
             }
           }
           out.println("Case #" + test + ": " + res);
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Tidy()).start();
   }
 }
