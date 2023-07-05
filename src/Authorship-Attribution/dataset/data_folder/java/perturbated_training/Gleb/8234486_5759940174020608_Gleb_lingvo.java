import java.io.*;
 import java.util.*;
 
 public class Lingvo implements Runnable {
   private static final String NAME = "lingvo";
 
   @Override
   public void run() {
     try {
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         int n = Integer.parseInt(in.readLine());
         List<List<String>> s = new ArrayList<List<String>>(n);
         for (int i = 0; i < n; i++) {
           s.add(Arrays.asList(in.readLine().split(" ")));
         }
         HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
         for (int i = 0; i < n; i++) {
           for (String ss : s.get(i)) {
             if (!map.containsKey(ss)) {
               map.put(ss, new ArrayList<Integer>());
             }
             map.get(ss).add(i);
           }
         }
         int min = Integer.MAX_VALUE - 1;
         for (int mask = 0; mask < (1 << (n - 2)); mask++) {
           int res = 0;
           for (String ss : map.keySet()) {
             boolean e = false;
             boolean f = false;
             for (Integer i : map.get(ss)) {
               if (i == 0) {
                 e = true;
               } else if (i == 1) {
                 f = true;
               } else {
                 if (((1 << (i - 2)) & mask) == 0) {
                   e = true;
                 } else {
                   f = true;
                 }
               }
             }
             if (e && f) {
               res++;
             }
           }
           min = Math.min(res, min);
         }
         System.out.println(test + " " + min);
         out.println("Case #" + test + ": " + min);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Lingvo()).start();
   }
 }
