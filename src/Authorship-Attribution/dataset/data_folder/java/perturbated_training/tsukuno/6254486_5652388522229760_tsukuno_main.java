import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       long K = cin.nextLong();
 
       String ans = solve(K);
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
   public String solve(long K) {
 
     boolean see[] = new boolean[10];
     int rest = 10;
     String ans = "INSOMNIA";
 
   SUB:
     for(int i=1; i<1000; ++i) {
       String s = "" + (K * i);
       for(int j=0; j<s.length(); ++j) {
         for(int k=0; k<10; ++k) {
           if( s.charAt(j) == (k + '0') && see[k] == false ) {
             see[k] = true;
             --rest;
             if( rest == 0 ) {
               ans = s;
               break SUB;
             }
           }
         }
       }
     }
 
     return ans;
 
   }
 
 }
