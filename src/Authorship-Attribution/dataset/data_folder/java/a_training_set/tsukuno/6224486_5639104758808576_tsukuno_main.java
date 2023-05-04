import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       int N = cin.nextInt();
       String s = cin.next();
 
       int cur = 0;
       int ret = 0;
 
       for(int i=0; i<=N; ++i) {
         if( cur < i ) {
           ++cur;
           ++ret;
         }
         cur += s.charAt(i) - '0';
       }
 
       System.out.println("Case #" + C + ": " + ret);
 
     }
 
   }
 
 }
