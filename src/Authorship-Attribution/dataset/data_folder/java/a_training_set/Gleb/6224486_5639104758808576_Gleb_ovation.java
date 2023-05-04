import java.io.*;
 import java.util.Scanner;
 
 public class Ovation implements Runnable {
   private static final String NAME = "ovation";
 
   @Override
   public void run() {
     try {
       
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
       
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         int res = 0;
         String[] ss = in.readLine().split(" ");
         int max = Integer.parseInt(ss[0]);
         String s = ss[1];
         int cur = 0;
         for (int i = 0; i <= max; i++) {
           int num = s.charAt(i) - '0';
           if (cur < i) {
             res += i - cur;
             cur = i;
           }
           cur += num;
         }
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Ovation()).start();
   }
 }
