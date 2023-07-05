import java.io.*;
 import java.util.Scanner;
 
 public class CountingSheep implements Runnable {
   private static final String NAME = "sheep";
 
   long find(long n) {
     boolean[] u = new boolean[10];
     int total = 10;
     long cur = n;
     int num = 1;
     while (true) {
       long t = cur;
       while (t != 0) {
         int d = (int) (t % 10);
         if (!u[d]) {
           total--;
           u[d] = true;
         }
         t = t / 10;
       }
       if (total == 0) {
         return cur;
       }
       num++;
       cur = n * num;
     }
   }
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int n = in.nextInt();
         if (n == 0) {
           out.println("Case #" + test + ": INSOMNIA");
         } else {
           out.println("Case #" + test + ": " + find(n));
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new CountingSheep()).start();
   }
 }
