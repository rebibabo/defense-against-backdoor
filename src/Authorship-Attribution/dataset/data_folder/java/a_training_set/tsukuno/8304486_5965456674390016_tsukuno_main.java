import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        int K = cin.nextInt();
        int target[] = new int[K * 2];
        int start[] = new int[K * 2];
        int duration[] = new int[K * 2];
        
        for(int i = 0; i < K * 2; ++i ) {
            target[i] = cin.nextInt();
            start[i] = cin.nextInt();
            duration[i] = cin.nextInt();
        }
 
       System.out.println("Case #" + C + ": " + solve(K, target, start, duration));
 
     }
 
     cin.close();
 
   }
   
   int solve(int K, int target[], int start[], int duration[]) {
      
      int max = 1 << K;
      int ans = Integer.MAX_VALUE;
      
      
      for( int i = 0; i < max; ++i ) {
          int cur = 0;
          int count[] = new int[K];
          int total = 0;
          int score = 0;
          int now = 0;
          while( true ) {
              
              if( count[cur] == 2 ) { break; }
              int index = cur * 2 + (((i >> cur) & 1) ^ count[cur]);
              score += wait(now, start[index]);
              score += duration[index];
              now = (start[index] + duration[index]) % 24;
              ++count[cur];
              ++total;
              cur = target[index] - 1;
          }
          if( total == K * 2 ) {
              ans = Math.min(ans, score);
          }
      }
      return ans;
   }
 
   int wait(int at, int st) {
      if( at <= st ) { return st - at; }
      return (st + 24 - at);
   }
 
 }
