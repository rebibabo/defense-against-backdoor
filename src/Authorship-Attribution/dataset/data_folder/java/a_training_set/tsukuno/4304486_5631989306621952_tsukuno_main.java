import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       String S = cin.next();
       String ans = "";
 
       for(int i=0; i<S.length(); ++i) {
         char c = S.charAt(i);
         if( i > 0 ) {
           char d = ans.charAt(0);
           if( c < d ) {
             ans += c;
           }
           else {
             ans = c + ans;
           }
         }
         else {
           ans += c;
         }
       }
 
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
 }
