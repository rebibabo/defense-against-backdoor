import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        long Hd = cin.nextLong();
        long Ad = cin.nextLong();
        
        long Hk = cin.nextLong();
        long Ak = cin.nextLong();
        
        long B = cin.nextLong();
        long D = cin.nextLong();
        
        
        
        
        long answer = solve(Hd, Ad, Hk, Ak, B, D);
       System.out.println("Case #" + C + ": " + (answer == inf ? "IMPOSSIBLE" : ("" + answer)));
 
     }
 
   }
   
   
   private static final long inf = 1000L * 1000L * 1000L * 1000L;
   
   long solve(long Hd, long Ad, long Hk, long Ak, long B, long D) {
 
      long result = inf;
      for(int debuff=0; debuff<=100; ++debuff) {
          for(int buff=0; buff<=100; ++buff) {
              result = Math.min(result, solve(Hd, Ad, Hk, Ak, B, D, debuff, buff));
          }
      }
      return result;
   
   }
   
   long solve(long Hd, long Ad, long Hk, long Ak, long B, long D, int debuff, int buff) {
      long cHd = Hd;
      long result = 0;
      while( debuff > 0 ) {
          
          if( cHd <= Ak - D ) {
              cHd = Hd - Ak;
              if( cHd <= Ak - D ) {
                  
                  return inf;
              }
          }
          
          else {
              Ak = Math.max(0, Ak - D);
              cHd -= Ak;
              --debuff;
          }
          ++result;
      }
      while( buff > 0 ) {
          
          if( cHd <= Ak ) {
              cHd = Hd - Ak;
              if( cHd <= Ak ) {
                  
                  return inf;
              }
          }
          else {
              Ad += B;
              cHd -= Ak;
              --buff;
          }
          ++result;
      }
      while( Hk > 0 ) {
          
          if( Hk <= Ad ) {
              Hk -= Ad;
          }
          
          else if( cHd <= Ak ) {
              cHd = Hd - Ak;
              if( cHd <= Ak ) {
                  
                  return inf;
              }
          }
          else {
              Hk -= Ad;
              cHd -= Ak;
          }
          ++result;
      }
      return result;
   }
 
 }
