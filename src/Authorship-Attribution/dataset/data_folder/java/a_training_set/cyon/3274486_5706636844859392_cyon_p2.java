package round1c;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.NavigableMap;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p2 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static final int MAX = 1440;
     private static String solve(BufferedReader br) throws IOException {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int[] oac = new int[MAX];
         int AC = Integer.parseInt(st.nextToken());
         int AJ = Integer.parseInt(st.nextToken());
         for (int i = 0; i < AC; i++) {
             st = new StringTokenizer(br.readLine());
             int C = Integer.parseInt(st.nextToken());
             int D = Integer.parseInt(st.nextToken());
             Arrays.fill(oac, C, D, 2);
         }
         for (int i = 0; i < AJ; i++) {
             st = new StringTokenizer(br.readLine());
             int C = Integer.parseInt(st.nextToken());
             int D = Integer.parseInt(st.nextToken());
             Arrays.fill(oac, C, D, 1);
         }
         int[][][] dp = new int[MAX][MAX / 2 + 1][2];
 
         int res = Integer.MAX_VALUE;
         int[] ac = new int[MAX];
 
         
 
         System.arraycopy(oac, 0, ac, 0, MAX);
         if (ac[0] != 2 && ac[MAX - 1] != 2) {
             init(dp);
             ac[0] = 1;
             ac[MAX - 1] = 1;
             dp[0][1][0] = 0;
             int x = check(ac, dp, 0);
             debug("a", x);
             res = Math.min(res, x);
         }
 
         System.arraycopy(oac, 0, ac, 0, MAX);
         if (ac[0] != 2 && ac[MAX - 1] != 1) {
             init(dp);
             ac[0] = 1;
             ac[MAX - 1] = 2;
             dp[0][1][0] = 1;
             int x = check(ac, dp, 1);
             debug("b", x);
             res = Math.min(res, x);
         }
 
         System.arraycopy(oac, 0, ac, 0, MAX);
         if (ac[0] != 1 && ac[MAX - 1] != 2) {
             init(dp);
             ac[0] = 2;
             ac[MAX - 1] = 1;
             dp[0][0][1] = 1;
             int x = check(ac, dp, 0);
             debug("c", x);
             res = Math.min(res, x);
         }
 
         System.arraycopy(oac, 0, ac, 0, MAX);
         if (ac[0] != 1 && ac[MAX - 1] != 1) {
             init(dp);
             ac[0] = 2;
             ac[MAX - 1] = 2;
             dp[0][0][1] = 0;
             int x = check(ac, dp, 1);
             debug("d", x);
 
 
 
             res = Math.min(res, x);
         }
 
         
         
         return "" + res;
     }
 
     private static void init(int[][][] dp) {
         for (int i = 0; i < MAX; i++) {
             for (int j = 0; j <= MAX / 2; j++) {
                 dp[i][j][0] = Integer.MAX_VALUE;
                 dp[i][j][1] = Integer.MAX_VALUE;
             }
         }
     }
 
     private static int check(int[] ac, int[][][] dp, int who) {
         for (int i = 1; i < MAX; i++) {
             for (int j = 0; j <= MAX / 2; j++) {
                 if (ac[i] == 0) {
                     if (j > 0)
                         dp[i][j][0] = chmin(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]);
                     dp[i][j][1] = chmin(dp[i-1][j][1], dp[i-1][j][0]);
                 }
                 if (ac[i] == 1) {
                     if (j > 0)
                         dp[i][j][0] = chmin(dp[i-1][j-1][0], dp[i-1][j-1][1]);
                 }
                 if (ac[i] == 2) {
                     dp[i][j][1] = chmin(dp[i-1][j][1], dp[i-1][j][0]);
                 }
             }
         }
         return dp[MAX - 1][MAX/2][who];
     }
 
     private static int chmin(int a, int b) {
         if (b == Integer.MAX_VALUE) {
             return a;
         }
         return Math.min(a, b + 1);
     }
 
     public static void debug(Object...args) {
 
         
     }
 }
