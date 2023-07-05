package qualif;
 
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 
 public class Insomnia {
 
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
         long N = Long.parseLong(br.readLine());
         if (N == 0) return "INSOMNIA";
         boolean[] seen = new boolean[10];
         long val = N;
         addAll(seen, val);
         while(!allSeen(seen)) {
             val += N;
             addAll(seen, val);
         }
         return Long.toString(val);
     }
 
     private static boolean allSeen(boolean[] seen) {
         for (int i=0;i<seen.length;i++) {
             if (!seen[i])return false;
         }
         return true;
     }
 
     private static void addAll(boolean[] seen, long val) {
         while(val > 0) {
             int r = (int)(val % 10);
             seen[r] = true;
             val /= 10;
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
 
 
