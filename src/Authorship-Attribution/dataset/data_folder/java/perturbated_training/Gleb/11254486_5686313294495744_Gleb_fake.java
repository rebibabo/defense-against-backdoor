import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class Fake implements Runnable {
   private static final String NAME = "fake";
 
   @Override
   public void run() {
     try {
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         int n = Integer.parseInt(in.readLine());
         List<int[]> s = new ArrayList<int[]>();
         for (int i = 0; i < n; i++) {
           s.add(new int[2]);
         }
         Map<String, Integer> num = new HashMap<String, Integer>();
         int cur = 0;
         for (int i = 0; i < n; i++) {
           String[] r = in.readLine().split(" ");
           if (!num.containsKey(r[0])) {
             num.put(r[0], cur++);
           }
           if (!num.containsKey(r[1])) {
             num.put(r[1], cur++);
           }
           s.get(i)[0] = num.get(r[0]);
           s.get(i)[1] = num.get(r[1]);
         }
         boolean[] f = new boolean[n];
         for (int i = 0; i < n; i++) {
           boolean o1 = false, o2 = false;
           for (int j = 0; j < i; j++) {
             o1 |= s.get(i)[0] == s.get(j)[0];
             o2 |= s.get(i)[1] == s.get(j)[1];
           }
           if (o1 && o2) {
             f[i] = true;
           }
         }
         while (true) {
           boolean found = false;
           for (int i = 0; i < n; i++) {
             if (f[i]) continue;
             if (found) {
               break;
             }
             ArrayList<Integer> e1 = new ArrayList<Integer>();
             for (int j = 0; j < n; j++) {
               if (i != j && s.get(i)[0] == s.get(j)[0]) e1.add(j);
             }
             ArrayList<Integer> e2 = new ArrayList<Integer>();
             for (int j = 0; j < n; j++) {
               if (i != j && s.get(i)[1] == s.get(j)[1]) e2.add(j);
             }
             for (int p1 : e1) {
               if (found) {
                 break;
               }
               for (int p2 : e2) {
                 if (p1 < p2) {
                   int t = p1;
                   p1 = p2;
                   p2 = t;
                 }
                 if (i < p1 && i < p2 && !f[p1] && !f[p2]) {
                   int[] t1 = s.get(p1);
                   int[] t2 = s.get(p2);
                   s.remove(p1);
                   s.remove(p2);
                   s.add(i, t1);
                   s.add(i, t2);
                   found = true;
                   break;
                 }
                 if (p1 < i && i < p2) {
                   int[] ti = s.get(i);
                   int[] tp = s.get(p2);
                   s.remove(i);
                   s.add(i, tp);
                   s.remove(p2);
                   s.add(p2, ti);
                   found = true;
                   break;
                 }
               }
             }
           }
           if (!found) {
             break;
           }
           Arrays.fill(f, false);
           for (int i = 0; i < n; i++) {
             boolean o1 = false, o2 = false;
             for (int j = 0; j < i; j++) {
               o1 |= s.get(i)[0] == s.get(j)[0];
               o2 |= s.get(i)[1] == s.get(j)[1];
             }
             if (o1 && o2) {
               f[i] = true;
             }
           }
         }
         
         int res = 0;
         for (int i = 0; i < n; i++) {
           if (f[i]) res++;
         }
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Fake()).start();
   }
 }
