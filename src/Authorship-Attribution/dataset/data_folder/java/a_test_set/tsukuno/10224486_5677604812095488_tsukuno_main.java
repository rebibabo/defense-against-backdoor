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
 
       double prob[] = new double[N];
       for(int i=0; i<N; ++i) {
         prob[i] = cin.nextDouble();
       }
 
       double ans = 0.0;
 
       int max = 1 << N;
       for(int i=0; i<max; ++i) {
         if( Integer.bitCount(i) != K ) { continue; }
         ans = Math.max(ans, solve(prob, i, N, K));
       }
 
       System.out.println("Case #" + C + ": " + ans);
 
     }
 
   }
 
   double solve(double prob[], int state, int N, int K) {
     double ret[] = new double[] { 1.0 };
     for(int i=0; i<N; ++i) {
       if( (state & (1 << i)) != 0 ) {
         ret = update(ret, prob[i]);
       }
     }
     return ret[K / 2];
   }
 
   double[] update(double a[], double prob) {
     double ret[] = new double[ a.length + 1 ];
     for(int i=0; i<a.length; ++i) {
       ret[i] += a[i] * prob;
       ret[i + 1] += a[i] * (1 - prob);
     }
     return ret;
   }
 
 }
