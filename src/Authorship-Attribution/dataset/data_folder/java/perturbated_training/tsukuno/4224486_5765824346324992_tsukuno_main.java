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
       int M = cin.nextInt();
 
       int wait[] = new int[N];
       for(int i=0; i<N; ++i) {
         wait[i] = cin.nextInt();
       }
 
       System.out.println("Case #" + C + ": " + solve(wait, N, M));
 
     }
 
   }
 
   int solve(int wait[], int N, int M) {
 
     long LO = 0;
     long HI = (long)(wait[0]) * M;
 
     
     for(int i=0; i<70; ++i) {
       long MID = (LO + HI) / 2;
       long score[] = count(MID, wait, N);
       
       if( score[0] < M && M <= score[1] ) {
         return answer(M - score[0], MID, wait, N);
       }
       
       if( score[1] >= M ) {
        HI = MID;
       }
       
       else {
         LO = MID;
       }
     }
 
     throw new RuntimeException("no answer: too small HIGHEST");
 
   }
 
   long[] count(long time, int wait[], int N) {
     long res[] = new long[2];
     int tmp = 0;
     for(int i=0; i<N; ++i) {
       res[1] += time / wait[i] + 1;
       if( time % wait[i] == 0 ) { ++tmp; }
     }
     res[0] = res[1] - tmp;
     return res;
   }
 
   int answer(long res, long time, int wait[], int N) {
     for(int i=0; i<N; ++i) {
       if( time % wait[i] == 0 ) {
         if( --res == 0 ) { return i + 1; }
       }
     }
     throw new RuntimeException("implementation error: count function returns invalid result");
   }
 
 }
