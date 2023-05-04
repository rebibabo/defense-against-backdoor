import java.io.File;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class CultureDumb implements Runnable {
   private static final String NAME = "culture";
 
   int nextInt(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   int inv(int a) {
     int res = 0;
     while (a > 0) {
       res = res * 10 + a % 10;
       a = a / 10;
     }
     return res;
   }
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
       
       
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       int max = 1000000;
       int[] res = new int[max + 1];
       Arrays.fill(res, -1);
       res[1] = 1;
       Queue<Integer> q = new LinkedList<Integer>();
       q.add(1);
       while (!q.isEmpty()) {
         int a = q.poll();
         int n = a + 1;
         if (n <= max && res[n] == -1) {
           res[n] = res[a] + 1;
           q.add(n);
         }
         n = inv(a);
         if (n <= max && res[n] == -1) {
           res[n] = res[a] + 1;
           q.add(n);
         }
       }
       for (int test = 1; test <= tests; test++) {
         
         out.println("Case #" + test + ": " + res[in.nextInt()]);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new CultureDumb()).start();
   }
 }
