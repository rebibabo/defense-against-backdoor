package ra;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p1 {
 
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
         String s = br.readLine();
         StringBuilder res = new StringBuilder(s.substring(0,1));
         int first = s.charAt(0)-'A';
         for (int i=1;i<s.length();i++) {
             int v = s.charAt(i)-'A';
             if (v >= first) {
                 first = v;
                 res.insert(0, s.charAt(i));
             }
             else {
                 res.append(s.charAt(i));
             }
         }
         return res.toString();
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
