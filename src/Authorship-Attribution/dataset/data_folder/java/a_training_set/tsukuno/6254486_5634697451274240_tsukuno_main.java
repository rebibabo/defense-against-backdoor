import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       String s = cin.next();
 
       int ans = 0;
       while( true ) {
         if( s.indexOf('-') == -1 ) { break; }
         ++ans;
         if( s.indexOf('+') == -1 ) { break; }
 
         if( s.indexOf('+') < s.indexOf('-') ) {
           s = s.substring(s.indexOf('-'));
         }
         else {
           s = s.substring(s.indexOf('+'));
         }
       }
 
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
 }
