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
       int R = cin.nextInt();
       int P = cin.nextInt();
       int S = cin.nextInt();
 
       int ans = -1;
 
       for(int k=0; k<3; ++k) {
         int init[] = new int[3];
         init[k] = 1;
         for(int i=0; i<N; ++i) {
           int next[] = new int[3];
           for(int j=0; j<3; ++j) {
             next[j] = init[j] + init[(j + 1) % 3];
           }
           init = next;
         }
         if( init[0] == R && init[1] == P && init[2] == S ) {
           ans = k;
           break;
         }
       }
 
       System.out.println("Case #" + C + ": " + gen(ans, N));
 
     }
 
   }
 
   String gen(int ans, int depth) {
     if( ans == -1 ) {
       return "IMPOSSIBLE";
     }
 
     if( depth == 1 ) {
       if( ans == 0 ) { return "RS"; }
       if( ans == 1 ) { return "PR"; }
       if( ans == 2 ) { return "PS"; }
       throw new RuntimeException("implementation error");
     }
 
     String a = gen(ans, depth - 1);
     String b = gen((ans + 2) % 3, depth - 1);
 
     
     if( a.compareTo(b) < 0 ) { return a + b; }
     return b + a;
 
   }
 
 }
