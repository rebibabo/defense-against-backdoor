import java.io.*;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class Pancakes implements Runnable {
   private static final String NAME = "pancakes";
 
   int nextInt(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   @Override
   public void run() {
     try {
       StreamTokenizer in = new StreamTokenizer(new BufferedReader(new FileReader(new File(NAME + ".in"))));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = nextInt(in);
 
       for (int test = 1; test <= tests; test++) {
         int d = nextInt(in);
         int[] p = new int[d];
         for (int i = 0; i < d; i++) {
           p[i] = nextInt(in);
         }
         Arrays.sort(p);
         int res = p[d - 1];
         for (int eat = 1; eat <= p[d - 1]; eat++) {
           int special = 0;
           for (int i = 0; i < d; i++) {
             if (p[i] > eat) {
               special += (p[i] + eat - 1 ) / eat - 1;
             }
           }
           res = Math.min(res, eat + special);
         }
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Pancakes()).start();
   }
 }
