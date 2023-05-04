import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       
       int L = cin.nextInt();
 
       int rep = cin.nextInt();
       String s = cin.next();
 
       System.out.println("Case #" + C + ": " + (solve(s, rep) ? "YES" : "NO"));
 
     }
 
   }
 
   
   int trans[][] = {
     { 1, 2, 3 },
     { 4, 3, 6 },
     { 7, 4, 1 },
     { 2, 5, 4 },
     { 5, 6, 7 },
     { 0, 7, 2 },
     { 3, 0, 5 },
     { 6, 1, 0 }
   };
 
   boolean solve(String s, int rep) {
 
     if( rep % 4 == 0 ) { return false; }
 
     int cur = 0;
 
     for(int i=0; i<s.length(); ++i) {
       cur = trans[cur][s.charAt(i) - 'i'];
     }
 
     if( isMinusOne(cur, rep) == false ) { return false; }
 
     int sub = Math.min(8, rep);
 
     int tmp = 0;
     int want = 1;
 
     for(int v=0; v<sub; ++v) {
       for(int i=0; i<s.length(); ++i) {
         tmp = trans[tmp][s.charAt(i) - 'i'];
         if( tmp == want ) {
           tmp = 0;
           ++want;
         }
         if( want == 3 ) { return true; }
       }
     }
 
     return false;
 
   }
 
   boolean isMinusOne(int cur, int rep) {
 
     switch( rep % 4 ) {
     case 0:
       {
         
         return false;
       }
     case 1:
       {
         
         return cur == 4;
       }
     case 2:
       {
         
         return cur != 0 && cur != 4;
       }
     case 3:
       {
         
         return cur == 4;
       }
     default:
       {
         return false;
       }
     }
 
   }
 
 }
