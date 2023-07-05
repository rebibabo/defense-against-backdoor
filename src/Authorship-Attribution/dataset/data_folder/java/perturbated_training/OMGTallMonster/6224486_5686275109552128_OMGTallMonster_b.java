import java.util.*;
 
 class B {
    static class Frac implements Comparable<Frac> {
       int num, denom = 1, c;
       public Frac(int num_) { num = c = num_; }
       public String toString() { return String.format("%d/%d(%d)",num,denom,c); }
 
       public int compareTo(Frac o) {
          return o.c != c ? o.c - c : o.num - num;
       }
       public void incrDenom() {
          denom++;
          c = (num+denom-1)/denom;
       }
    }
 
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       int D = scan.nextInt();
 
       
       
       
       
       
 
       PriorityQueue<Frac> heap = new PriorityQueue<Frac>(D);
       for (int i = 0; i < D; i++) {
          heap.offer(new Frac(scan.nextInt()));
       }
 
       int upperBound = heap.peek().c;
       for (int i = 0; i < upperBound; i++) {
          Frac tmp = heap.poll();
          upperBound = Math.min(tmp.c+i, upperBound);
          tmp.incrDenom();
          heap.offer(tmp);
       }
       return upperBound;
    }
 }
