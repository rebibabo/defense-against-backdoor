import java.io.File;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class HikeDumb implements Runnable {
   private static final String NAME = "hike";
 
   int nextInt(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong(StreamTokenizer in) throws Exception {
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
         List<Integer> d = new ArrayList<Integer>();
         List<Integer> m = new ArrayList<Integer>();
         for (int i = 0; i < n; i++) {
           int di = in.nextInt();
           int h = in.nextInt();
           int mi = in.nextInt();
           for (int j = 0; j < h; j++) {
             d.add(di);
             m.add(mi + j);
           }
         }
         if (d.size() > 2) {
           throw new IllegalStateException();
         }
         if (d.size() < 2) {
           out.println("Case #" + test + ": " + 0);
           continue;
         }
         double d0 = 1.0 * (360 - d.get(0)) * m.get(0) / 360;
         double d1 = 1.0 * (360 - d.get(1)) * m.get(1) / 360;
         if (d0 + m.get(0) <= d1 || d1 + m.get(1) <= d0) {
           out.println("Case #" + test + ": " + 1);
         } else {
           out.println("Case #" + test + ": " + 0);
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new HikeDumb()).start();
   }
 }
