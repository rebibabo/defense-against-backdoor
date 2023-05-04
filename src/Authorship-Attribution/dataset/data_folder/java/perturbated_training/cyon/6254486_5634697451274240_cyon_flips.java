package qualif;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class Flips {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         String stack = br.readLine();
         boolean up = true;
         int fc = 0;
         for (int i=stack.length()-1;i>=0;i--) {
             boolean isUp = stack.charAt(i) == '+';
             if (up != isUp) {
                 fc++;
                 up=!up;
             }
         }
         return "" + fc;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
