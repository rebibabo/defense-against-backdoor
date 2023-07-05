import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Set;
 
 public class Elem implements Runnable {
   private static final String NAME = "elem";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   List<Integer> prev(int cur, int base) {
     int[] num = new int[base + 1];
     int all = 0;
     int cc = base;
     while (cur != 0) {
       num[cc] = cur % 10;
       all += num[cc];
       cur = cur / 10;
       cc--;
     }
 
     if (all > base) {
       return Collections.emptyList();
     }
 
     num[0] = base - all;
     List<Integer> res = new ArrayList<>();
     gen(num, res, 0);
     return res;
   }
 
   private void gen(int[] num, List<Integer> res, int cur) {
     boolean last = true;
     for (int i = 0; i < num.length; i++) {
       if (num[i] > 0) {
         last = false;
         num[i]--;
         gen(num, res, cur * 10 + i);
         num[i]++;
       }
     }
     if (last) {
       res.add(cur);
     }
   }
 
   @Override
   public void run() {
     try {
       
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
       
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String s = in.readLine();
         int base = s.length();
         int n = Integer.parseInt(s);
         LinkedList<Integer> q = new LinkedList<>();
         Set<Integer> found = new HashSet<>();
         q.add(n);
         while (!q.isEmpty()) {
           int cur = q.removeFirst();
           found.add(cur);
           for (int prev : prev(cur, base)) {
             if (!found.contains(prev)) {
               q.add(prev);
             }
           }
         }
         out.println("Case #" + test + ": " + found.size());
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Elem()).start();
   }
 }