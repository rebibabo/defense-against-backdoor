import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       int H = cin.nextInt();
       int W = cin.nextInt();
 
       System.out.println("Case #" + C + ": " + solve(H, W));
 
     }
 
   }
 
   private static final int MOD = 1000 * 1000 * 1000 + 7;
 
   int solve(int H, int W) {
 
     int dp[][] = new int[H + 1][3];
 
     int N_NON = 0;
     int N_TWO = 1;
     int N_THR = 2;
 
     dp[0][0] = 1;
 
     boolean triple = W % 3 == 0;
     boolean six = W % 6 == 0;
 
     for(int i=0; i<H; ++i) {
       for(int j=0; j<3; ++j) {
         if( dp[i][j] == 0 ) { continue; }
         
         if( j != N_TWO ) {
           dp[i + 1][N_TWO] = dp[i + 1][N_TWO] + dp[i][j];
           if( dp[i + 1][N_TWO] >= MOD ) { dp[i + 1][N_TWO] -= MOD; }
           
           
           if( triple && i + 2 <= H ) {
             dp[i + 2][N_TWO] = (int)((dp[i + 2][N_TWO] + dp[i][j] * 3L) % MOD);
           }
           
           
           if( six && i + 2 <= H ) {
             dp[i + 2][N_TWO] = (int)((dp[i + 2][N_TWO] + dp[i][j] * 6L) % MOD);
           }
         }
         
         if( j != N_THR && i + 2 <= H ) {
           dp[i + 2][N_THR] = dp[i + 2][N_THR] + dp[i][j];
           if( dp[i + 2][N_THR] >= MOD ) { dp[i + 2][N_THR] -= MOD; }
         }
       }
     }
 
     return ((dp[H][0] + dp[H][1]) % MOD + dp[H][2]) % MOD;
 
   }
 
 }
