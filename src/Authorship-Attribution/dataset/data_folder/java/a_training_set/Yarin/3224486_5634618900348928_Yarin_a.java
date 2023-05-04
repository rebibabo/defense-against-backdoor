package round3;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class A {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("round3/A-small-attempt2.in"), new FileOutputStream("round3/A-small-2.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new A().solve(io);
         }
         io.close();
     }
 
     int n;
     int[] mood; 
     int[][] memo;
 
     private int go(int start, int stop) {
         if (start == stop) return 0;
         if (memo[start][stop] > 0) return memo[start][stop]-1;
         int best = -1000; 
 
         for (int i = start+1; i < stop; i++) {
             int score = (mood[start] == mood[i]) ? 10 : 5;
             best = Math.max(best, score + go(start+1,i) + go(i+1,stop));
         }
 
         memo[start][stop] = best + 1;
         return best;
     }
 
     private void solve(Kattio io) {
         String word = io.getWord();
         
         int score = 0;
         while (true) {
             int c = word.indexOf("CC");
             if (c >= 0) {
                 score += 10;
                 word = word.substring(0, c) + word.substring(c+2);
                 continue;
             }
             int j = word.indexOf("JJ");
             if (j >= 0) {
                 score += 10;
                 word = word.substring(0, j) + word.substring(j+2);
                 continue;
             }
             break;
         }
         score += 5 * (word.length() / 2);
         io.println(score);
     }
 }
