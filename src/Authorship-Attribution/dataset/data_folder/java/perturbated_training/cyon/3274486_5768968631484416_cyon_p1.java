package round1c;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
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
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int K = Integer.parseInt(st.nextToken());
         List<Pan> p = new ArrayList<>();
         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             int R = Integer.parseInt(st.nextToken());
             int H = Integer.parseInt(st.nextToken());
             p.add(new Pan(R, H));
         }
         Collections.sort(p);
         long[][] dp = new long[N][K+1];
         for (int i = 0; i < N; i++) {
             Pan pan = p.get(i);
             dp[i][1] = pan.r * pan.r + pan.r * pan.h * 2;
         }
 
 
 
         for (int i = 1; i < N; i++) {
             Pan pan = p.get(i);
             for (int j = 1; j <= i + 1 && j <= K; j++) {
                 dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i - 1][j - 1] + pan.r * pan.h * 2));
             }
         }
 
 
 
 
         return "" + (dp[N-1][K] * Math.PI);
     }
 
     private static final class Pan implements Comparable<Pan>{
         private long r, h;
 
         public Pan(int r, int h) {
             this.r = r;
             this.h = h;
         }
 
         @Override
         public int compareTo(Pan o) {
             return -Long.compare(r, o.r);
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
