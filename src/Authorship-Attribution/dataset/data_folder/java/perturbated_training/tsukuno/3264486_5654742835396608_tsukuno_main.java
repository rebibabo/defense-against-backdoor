import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
        
        long N = cin.nextLong();
        long K = cin.nextLong();
        PriorityQueue<Long> queue = new PriorityQueue<Long>();
        Map<Long, Long> count = new HashMap<Long, Long>();
        queue.add(-N);
        count.put(N, 1L);
        
        long result[] = solve(queue, count, K);
 
       System.out.println("Case #" + C + ": " + result[0] + " " + result[1]);
 
     }
 
   }
   
   long[] solve(PriorityQueue<Long> queue, Map<Long, Long> count, long K) {
      while( queue.isEmpty() == false ) {
          long cur = -queue.poll();
          long member = count.get(cur);
          long max = cur / 2;
          long min = (cur - 1) / 2;
          
          if( member >= K ) {
              return new long[] { max, min };
          }
          K -= member;
          if( min == max ) {
              add(min, member * 2, queue, count);
          }
          else {
              add(min, member, queue, count);
              add(max, member, queue, count);
          }
      }
      throw new RuntimeException("invalid input or implementation error");
   }
   
   void add(long key, long value, PriorityQueue<Long> queue, Map<Long, Long> count) {
      if( count.containsKey(key) ) {
          
          count.put(key, count.get(key) + value);
          return;
      }
      queue.add(-key);
      count.put(key, value);
   }
 
 }
