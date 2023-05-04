import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       int K = cin.nextInt();
       int R = cin.nextInt();
       int S = cin.nextInt();
 
       String ans = "IMPOSSIBLE";
 
       if( S * R >= K ) {
         ans = "";
         int st = 0;
         String sep = "";
         while( st < K ) {
           long tmp = 0;
           for(int i=0; i<R; ++i) {
             tmp *= K;
             tmp += (st >= K ? K - 1 : st);
             ++st;
           }
           ans += sep;
           ans += (tmp + 1);
           sep = " ";
         }
       }
 
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
 }
