package Round1C;
 
 import java.util.Scanner;
 
 public class ABrattleship {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int height = sc.nextInt();
             int width = sc.nextInt(); 
             int shipSize = sc.nextInt();
             int narrowHits = (int)Math.ceil(width / (double)shipSize) - 1;
             int extraHits = shipSize;
             int totalHits = narrowHits + extraHits;
             System.out.printf("Case #%d: %d%n", t, totalHits);
         }
         sc.close();
     }
 }
