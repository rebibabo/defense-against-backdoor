import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        String whole = cin.next();
        int size = cin.nextInt();
        
       System.out.println("Case #" + C + ": " + solve(whole, size));
 
     }
 
   }
   
   String solve(String whole, int size) {
      int length = whole.length();
      boolean happy[] = new boolean[length];
      for(int i = 0; i < length; ++i ) {
          happy[i] = whole.charAt(i) == '+';
      }
      int result = 0;
      for(int i=0; i<=length-size; ++i) {
          if( happy[i] ) { continue; }
          ++result;
          for(int j=0; j<size; ++j) {
              happy[i + j] ^= true;
          }
      }
      for(int i=0; i<length; ++i) {
          if( happy[i] == false ) {
              return "IMPOSSIBLE";
          }
      }
      return "" + result;
   }
 
 }
