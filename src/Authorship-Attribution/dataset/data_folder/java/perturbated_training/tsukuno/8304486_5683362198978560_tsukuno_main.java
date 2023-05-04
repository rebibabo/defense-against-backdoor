import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        int Row = cin.nextInt();
        int Col = cin.nextInt();
        int N = cin.nextInt();
        int D = cin.nextInt();
        
        int r[] = new int[N];
        int c[] = new int[N];
        int v[] = new int[N];
        
        for( int i = 0 ; i < N; ++ i ) {
            r[i] = cin.nextInt() - 1;
            c[i] = cin.nextInt() - 1;
            v[i] = cin.nextInt();
        }
 
       System.out.println("Case #" + C + ": " + solve(Row, Col, N, r, c, v, D));
 
     }
 
     cin.close();
 
   }
   
   private static final int MOD = 1000 * 1000 * 1000 + 7;
   
   String solve(int Row, int Col, int N, int r[], int c[], int v[], long D) {
      
      for(int i = 0; i < N; ++i ) {
          for( int j = i + 1; j < N; ++j ) {
              if( Math.abs(v[i] - v[j]) > (Math.abs(r[i] - r[j]) + Math.abs(c[i] - c[j])) * D ) {
                  return "IMPOSSIBLE";
              }
          }
      }
      int result = 0;
      for(int y = 0; y < Row; ++y ) {
          for(int x = 0; x < Col; ++x) {
              long cur = Long.MAX_VALUE;
              for( int i = 0; i < N; ++i ) {
                  cur = Math.min(cur, v[i] + (Math.abs(r[i] - y) + Math.abs(c[i] - x)) * D);
              }
              result = (int)((cur + result) % MOD);
          }
      }
      return ""+ result;
   }
 
 }
