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
       String a[] = new String[N];
       String b[] = new String[N];
 
       for(int i=0; i<N; ++i) {
         a[i] = cin.next();
         b[i] = cin.next();
       }
 
       int max = 1 << N;
       int ans = 0;
 
     MAIN:
       for(int i=0; i<max; ++i) {
         for(int j=0; j<N; ++j) {
           
           if( (i & (1 << j)) == 0 ) { continue; }
           boolean f1 = false;
           boolean f2 = false;
           for(int k=0; k<N; ++k) {
             
             if( (i & (1 << k)) != 0 ) { continue; }
             f1 |= a[j].equals(a[k]);
             f2 |= b[j].equals(b[k]);
           }
           
           if( f1 == false || f2 == false ) {
             continue MAIN;
           }
         }
         ans = Math.max(ans, Integer.bitCount(i));
       }
 
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
 }
