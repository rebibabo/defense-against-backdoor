import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class BathroomStalls {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C-small-attempt0.in"));
         PrintStream cout = new PrintStream("C-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
             int n = cin.nextInt();
             int k = cin.nextInt();
             Queue<Integer> queue = new PriorityQueue<>((u, v) -> -Integer.compare(u, v));
             queue.add(n);
             for (int i = 0; i < k - 1; i++) {
                 int num = queue.poll();
                 int cb = (num - 1) / 2;
                 int wb = num - 1 - cb;
                 if (cb > 0) queue.add(cb);
                 if (wb > 0) queue.add(wb);
                 System.out.println(queue);
             }
             System.out.println();
 
             int num = queue.poll();
             int cb = (num - 1) / 2;
             int wb = num - 1 - cb;
 
             cout.printf("Case #%d: %d %d%n", _case, wb, cb);
         }
 
         cin.close();
         cout.close();
     }
 }
