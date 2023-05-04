import java.io.*;
 
 public class PancakesRevenge implements Runnable {
   private static final String NAME = "pancakes";
 
   int res(String s) {
     int num = 0;
     for (int i = 1; i < s.length(); i++) {
       if (s.charAt(i) != s.charAt(i - 1)) {
         num++;
       }
     }
     if (s.charAt(s.length() - 1) == '-') {
       num++;
     }
     return num;
   }
 
   @Override
   public void run() {
     try {
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         out.println("Case #" + test + ": " + res(in.readLine()));
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new PancakesRevenge()).start();
   }
 }
