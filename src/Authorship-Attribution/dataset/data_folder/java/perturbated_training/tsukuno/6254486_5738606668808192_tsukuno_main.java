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
       int K = cin.nextInt();
 
       System.out.println("Case #" + C + ":");
 
       
       
       for(int rep=2; rep<=3; ++rep) {
         if( N % rep != 0 ) { continue; }
         int sub = N / rep;
         int max = 1 << (sub - 2);
         for(int i=0; i<max; ++i) {
           if( K == 0 ) { break; }
           String ans = "1" + pad(Integer.toString(i, 2), sub - 2) + "1";
           for(int j=0; j<rep; ++j) {
             System.out.print(ans);
           }
           String div = "1";
           for(int j=1; j<rep; ++j) { div += pad("1", sub); }
           for(int j=2; j<=10; ++j) {
             System.out.print(" " + Long.parseLong(div, j));
           }
           System.out.println();
           --K;
         }
       }
 
     }
 
   }
 
   private String pad(String s, int K) {
     if( K == 0 ) { return ""; }
     while( s.length() < K ) {
       s = "0" + s;
     }
     return s;
   }
 
 }
