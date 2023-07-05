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
       int friend[] = new int[N];
       for(int i=0; i<N; ++i) {
         friend[i] = cin.nextInt() - 1;
       }
 
       int score[] = new int[N];
       int target[] = new int[N];
 
       for(int i=0; i<N; ++i) {
         int res[] = findL(N, i, friend);
         score[ res[1] ] = Math.max( score[ res[1] ], res[0] );
         target[ res[1] ] = res[2];
       }
 
       int ans = 0;
       int ans2 = 0;
       for(int i=0; i<N; ++i) {
         if( target[i] == -1 ) {
           ans = Math.max(ans, score[i]);
         }
         else {
           ans2 = Math.max(ans2, score[i] + score[ target[i] ] );
         }
       }
 
       System.out.println("Case #" + C + ": " + Math.max(ans, ans2));
 
     }
 
   }
 
   
   int[] findL(int N, int self, int friend[]) {
 
     int res[] = new int[N];
     int C = 0;
     res[C++] = self;
 
     while( true ) {
       int target = friend[ res[C - 1] ];
       for(int i=0; i<C; ++i) {
         
         if( res[i] == target ) {
           int min = N;
           for(int j=i; j<C; ++j) {
             min = Math.min(min, res[j]);
           }
           
           if( C - i == 2 ) {
             if( res[i] < res[i + 1] ) { return new int[] { C, res[i], res[i + 1] }; }
             else { return new int[] { i, res[i], res[i + 1] }; }
           }
           
           return new int[] { C - i, min, -1 };
         }
       }
       res[C++] = target;
     }
 
   }
 
 }
